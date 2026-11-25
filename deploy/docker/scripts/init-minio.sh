#!/usr/bin/env bash
# 初始化 xftt 素材桶（公共读）——共享栈对象存储
#
# 现状：共享栈的 minio 容器已改用 RustFS（S3 兼容，镜像内无 mc），
# 端口/凭据/桶名不变：http://192.168.20.88:9000、minioadmin/itxinfei123、bucket=xftt。
# 因此不再依赖 mc 镜像，直接用 stdlib S3(SigV4) 脚本完成建桶+策略+匿名读验证。
set -euo pipefail

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
export MINIO_ENDPOINT="${MINIO_ENDPOINT:-http://${MW_HOST:-192.168.20.88}:9000}"
export MINIO_ACCESS_KEY="${MINIO_ROOT_USER:-minioadmin}"
export MINIO_SECRET_KEY="${MINIO_ROOT_PASSWORD:-itxinfei123}"
export MINIO_BUCKET="${XFTT_BUCKET:-xftt}"

echo ">>> 对象存储 ${MINIO_ENDPOINT} | bucket=${MINIO_BUCKET}"
python3 "$HERE/s3_public_bucket.py"
