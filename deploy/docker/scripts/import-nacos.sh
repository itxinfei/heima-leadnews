#!/usr/bin/env bash
# 在 Nacos（共享栈，手册定版 3.3.0）中初始化命名空间并导入 xftt 配置集
#
# Data ID 与服务名一致（9 个），分组 DEFAULT_GROUP，格式 properties：
#   xftt-{login,article,behavior,user,media,admin,crawler,images,migration}.properties
#   xftt-gateway.yml 按既定约定不录入（网关用本地 yml）。
# 目标内容来自 nacos/xftt-config-template.properties。
#
# Nacos 3.x Open API 前缀为 /nacos/v3/admin（旧 /nacos/v1/console|cs 已不可用）：
#   命名空间：POST/GET /nacos/v3/admin/core/namespace[/list]
#   配置读写：POST /nacos/v3/admin/cs/config、GET /nacos/v3/admin/cs/config
#
# 鉴权两种模式自适应：
#   1) NACOS_AUTH_ENABLE=true：用 NACOS_USER/NACOS_PASSWORD 登录取 accessToken，随 URL query 携带
#      （使用本项目自建账号 xftt/123456；未改动实例内既有用户）；
#   2) 登录不可用时退回共享栈服务身份头（默认 xfwms: 123456）。
set -euo pipefail

NACOS_HOST="${NACOS_HOST:-192.168.20.88}"
NACOS_PORT="${NACOS_PORT:-8848}"
NAMESPACE_ID="${NAMESPACE_ID:-xftt}"
GROUP="${GROUP:-DEFAULT_GROUP}"
NACOS_USER="${NACOS_USER:-xftt}"
NACOS_PASSWORD="${NACOS_PASSWORD:-123456}"
IDENTITY_KEY="${NACOS_IDENTITY_KEY:-xfwms}"
IDENTITY_VALUE="${NACOS_IDENTITY_VALUE:-123456}"

BASE_URL="http://${NACOS_HOST}:${NACOS_PORT}/nacos/v3/admin"
TEMPLATE="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/nacos/xftt-config-template.properties"

if [ ! -f "$TEMPLATE" ]; then
    echo "找不到模板文件: $TEMPLATE" >&2
    exit 1
fi

AUTH_QUERY=""                 # 形如 accessToken=xxx
AUTH_HEADERS=()               # 身份头模式使用
with_auth() {                 # 输出带鉴权参数的完整 URL
    local u="$1"
    if [ -n "$AUTH_QUERY" ]; then
        if [[ "$u" == *\?* ]]; then
            echo "${u}&${AUTH_QUERY}"
        else
            echo "${u}?${AUTH_QUERY}"
        fi
    else
        echo "$u"
    fi
}

login() {
    local resp token
    resp=$(curl -s -m 10 -X POST "http://${NACOS_HOST}:${NACOS_PORT}/nacos/v1/auth/login" \
        --data-urlencode "username=${NACOS_USER}" \
        --data-urlencode "password=${NACOS_PASSWORD}" || true)
    token=$(echo "$resp" | sed -n 's/.*"accessToken":"\([^"]*\)".*/\1/p')
    if [ -n "$token" ]; then
        echo ">>> Nacos 登录成功（${NACOS_USER}），使用 accessToken"
        AUTH_QUERY="accessToken=${token}"
    else
        echo ">>> 未获取到 accessToken，改用服务身份头 ${IDENTITY_KEY}: ***"
        AUTH_HEADERS=(-H "${IDENTITY_KEY}: ${IDENTITY_VALUE}")
    fi
}

# v3 需要 namespaceId 参数（非 customNamespaceId）
ensure_namespace() {
    local url body resp code
    url="$(with_auth "${BASE_URL}/core/namespace")"
    body="namespaceId=${NAMESPACE_ID}&namespaceName=xftt&namespaceDesc=%E5%BF%83%E9%A3%9E%E5%A4%B4%E6%9D%A1%E4%B8%9A%E5%8A%A1%E9%85%8D%E7%BD%AE"
    resp=$(curl -s -m 10 -X POST "$url" -H 'Content-Type: application/x-www-form-urlencoded' "${AUTH_HEADERS[@]}" --data "$body")
    code=$(echo "$resp" | sed -n 's/.*"code":\([0-9]*\).*/\1/p')
    if [ "$code" = "0" ]; then
        echo ">>> 命名空间 ${NAMESPACE_ID} 就绪"
    else
        # 已存在时忽略
        echo ">>> 创建命名空间返回: $(echo "$resp" | head -c 200)（已存在可忽略）"
    fi
}

publish() {
    local data_id="$1" url resp code check
    url="$(with_auth "${BASE_URL}/cs/config")"
    resp=$(curl -s -m 20 -X POST "$url" "${AUTH_HEADERS[@]}" \
        --data-urlencode "dataId=${data_id}" \
        --data-urlencode "groupName=${GROUP}" \
        --data-urlencode "namespaceId=${NAMESPACE_ID}" \
        --data-urlencode "type=properties" \
        --data-urlencode "content@${TEMPLATE}")
    code=$(echo "$resp" | sed -n 's/.*"code":\([0-9]*\).*/\1/p')
    if [ "$code" != "0" ]; then
        echo "!!! 发布 ${data_id} 失败: $(echo "$resp" | head -c 300)" >&2
        return 1
    fi
    # 注意：本实例 v3 读取接口要求携带 content 参数（空值即可），否则报 parameter missing
    check=$(curl -s -m 10 "$(with_auth "${BASE_URL}/cs/config?dataId=${data_id}&groupName=${GROUP}&namespaceId=${NAMESPACE_ID}&content=")" "${AUTH_HEADERS[@]}")
    if echo "$check" | grep -q "mysql.core.jdbc.url"; then
        echo "     OK ${data_id}"
    else
        echo "!!! ${data_id} 回读校验失败: $(echo "$check" | head -c 200)" >&2
        return 1
    fi
}

login
ensure_namespace

SERVICES=(login article behavior user media admin crawler images migration)

echo ">>> 开始导入 ${#SERVICES[@]} 个配置集 ..."
failed=0
for svc in "${SERVICES[@]}"; do
    publish "xftt-${svc}.properties" || failed=$((failed + 1))
done

if [ "$failed" -gt 0 ]; then
    echo "!!! 有 ${failed} 个配置发布失败" >&2
    exit 1
fi

echo ">>> 全部配置发布完成（命名空间 ${NAMESPACE_ID}）"
