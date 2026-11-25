#!/usr/bin/env python3
"""创建素材桶并设置匿名公共读（仅 stdlib，AWS SigV4 直连 S3 兼容存储）。

背景：共享栈的 minio 容器已改用 RustFS（S3 兼容，镜像内无 mc）。
默认值与《部署手册-中间件与数据》一致：endpoint 192.168.20.88:9000、
minioadmin/itxinfei123、bucket xftt；均可用环境变量覆盖。

用法：python3 s3_public_bucket.py
"""
import datetime
import hashlib
import hmac
import http.client
import os
import sys
from urllib.parse import quote

ENDPOINT = os.getenv("MINIO_ENDPOINT", "http://192.168.20.88:9000")
ACCESS_KEY = os.getenv("MINIO_ACCESS_KEY", "minioadmin")
SECRET_KEY = os.getenv("MINIO_SECRET_KEY", "itxinfei123")
BUCKET = os.getenv("MINIO_BUCKET", "xftt")
REGION = os.getenv("AWS_REGION", "us-east-1")
SERVICE = "s3"

_SCHEME = "http"
if ENDPOINT.startswith("https://"):
    _SCHEME, ENDPOINT = "https", ENDPOINT[len("https://"):]
elif ENDPOINT.startswith("http://"):
    ENDPOINT = ENDPOINT[len("http://"):]
HOST, _, PORT_S = ENDPOINT.partition(":")
PORT = int(PORT_S) if PORT_S else (443 if _SCHEME == "https" else 80)

PUBLIC_READ_POLICY = (
    '{"Version":"2012-10-17","Statement":[{"Sid":"PublicReadGetObject","Effect":"Allow",'
    '"Principal":{"AWS":["*"]},"Action":["s3:GetObject"],'
    f'"Resource":["arn:aws:s3:::{BUCKET}/*"]}}]}}'
).encode("utf-8")


def _sign(method, path, query="", body=b""):
    t = datetime.datetime.now(datetime.timezone.utc)
    amzdate, datestamp = t.strftime("%Y%m%dT%H%M%SZ"), t.strftime("%Y%m%d")
    payload_hash = hashlib.sha256(body).hexdigest()
    headers = {
        "host": f"{HOST}:{PORT}",
        "x-amz-date": amzdate,
        "x-amz-content-sha256": payload_hash,
    }
    canonical_headers = "".join(f"{k}:{headers[k]}\n" for k in sorted(headers))
    signed_headers = ";".join(sorted(headers))
    canonical_request = "\n".join([method, path, query, canonical_headers, signed_headers, payload_hash])
    scope = f"{datestamp}/{REGION}/{SERVICE}/aws4_request"
    string_to_sign = "\n".join([
        "AWS4-HMAC-SHA256", amzdate, scope,
        hashlib.sha256(canonical_request.encode("utf-8")).hexdigest(),
    ])
    key = ("AWS4" + SECRET_KEY).encode("utf-8")
    for msg in (datestamp, REGION, SERVICE, "aws4_request"):
        key = hmac.new(key, msg.encode("utf-8"), hashlib.sha256).digest()
    signature = hmac.new(key, string_to_sign.encode("utf-8"), hashlib.sha256).hexdigest()
    headers["Authorization"] = (
        f"AWS4-HMAC-SHA256 Credential={ACCESS_KEY}/{scope}, "
        f"SignedHeaders={signed_headers}, Signature={signature}"
    )
    return headers


def request(method, path, query="", body=b"", extra=None, timeout=15):
    headers = _sign(method, path, query, body)
    if extra:
        headers.update(extra)
    conn_cls = http.client.HTTPSConnection if _SCHEME == "https" else http.client.HTTPConnection
    conn = conn_cls(HOST, PORT, timeout=timeout)
    conn.request(method, path + ("?" + query if query else ""), body=body or None, headers=headers)
    resp = conn.getresponse()
    data = resp.read()
    conn.close()
    return resp.status, data


def main():
    failed = []

    status, _ = request("GET", "/")
    print(f"[list-buckets] {status}")
    if status != 200:
        failed.append(f"鉴权/连通失败（HTTP {status}）")

    status, data = request("PUT", f"/{BUCKET}/")
    print(f"[create-bucket] {status}")
    if status not in (200, 409):
        failed.append(f"建桶失败（HTTP {status} {data[:120]!r}）")

    status, data = request("PUT", f"/{BUCKET}/", "policy=", PUBLIC_READ_POLICY,
                           {"Content-Type": "application/json"})
    print(f"[public-read-policy] {status}")
    if status not in (200, 204):
        failed.append(f"策略设置失败（HTTP {status} {data[:120]!r}）")

    probe = "_probe_bucket.txt"
    status, data = request("PUT", f"/{BUCKET}/{quote(probe)}", body=b"ok",
                           extra={"Content-Type": "text/plain"})
    print(f"[put-probe] {status}")
    anon_ok = False
    if status in (200, 204):
        try:
            import urllib.request
            r = urllib.request.urlopen(f"{_SCHEME}://{HOST}:{PORT}/{BUCKET}/{probe}", timeout=10)
            anon_ok = r.status == 200 and r.read() == b"ok"
            print(f"[anon-get-probe] {r.status}")
        except Exception as exc:  # noqa: BLE001
            print(f"[anon-get-probe] FAILED: {exc}")
        request("DELETE", f"/{BUCKET}/{quote(probe)}")
        print("[delete-probe] done")
    if not anon_ok:
        failed.append("匿名读取验证失败")

    if failed:
        print("!! 失败项：")
        for f in failed:
            print("   -", f)
        return 1
    print(f">> 完成：bucket={BUCKET} 可匿名读，endpoint={_SCHEME}://{HOST}:{PORT}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
