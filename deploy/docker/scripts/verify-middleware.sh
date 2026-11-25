#!/usr/bin/env bash
# xftt 中间件连通性验证（端口/版本按《部署手册-中间件与数据》2026-11 定版）
# MySQL 8.4.13 · Redis 8.10.3 · Nacos 3.3.0 · Kafka 4.4.0 · ES 9.5.7 ·
# MongoDB 8.2.12 · ZooKeeper 3.9.x · MinIO RELEASE.2026-06-06T02-44-06Z · HBase 2.6.7
set -uo pipefail

HOST="${MW_HOST:-192.168.20.88}"
MYSQL_ROOT_PASSWORD="${MYSQL_ROOT_PASSWORD:-123456}"
NACOS_USER="${NACOS_USER:-xftt}"
NACOS_PASSWORD="${NACOS_PASSWORD:-123456}"
IDENTITY_KEY="${NACOS_IDENTITY_KEY:-xfwms}"
IDENTITY_VALUE="${NACOS_IDENTITY_VALUE:-123456}"
ok=0
fail=0

check_port() {
    local name="$1" port="$2"
    if timeout 5 bash -c "</dev/tcp/${HOST}/${port}" >/dev/null 2>&1; then
        echo "OK      ${name} :${port}"
        ok=$((ok + 1))
    else
        echo "FAIL    ${name} :${port}"
        fail=$((fail + 1))
    fi
}

echo "===== 1. 端口连通 ====="
check_port "MySQL(共享)"       3306
check_port "Redis(共享)"       6379
check_port "Nacos(共享)"       8848
check_port "Kafka"             9092
check_port "Elasticsearch"     9200
check_port "MongoDB"           27017
check_port "ZooKeeper"         2181
check_port "MinIO-API(共享)"   9000
check_port "MinIO-Console"     9001
check_port "HBase-Master"      16000
check_port "HBase-RegionServer" 16020

echo "===== 2. Elasticsearch 集群 ====="
ES_HEALTH=$(curl -s --max-time 10 "http://${HOST}:9200/_cluster/health?pretty" || true)
if echo "$ES_HEALTH" | grep -q '"status"'; then
    echo "$ES_HEALTH" | grep -E '"cluster_name"|"status"|"number_of_nodes"' | head -3
    ok=$((ok + 1))
else
    echo "FAIL    ES 健康接口无响应"; fail=$((fail + 1))
fi

echo "===== 3. MinIO（手册凭据 minioadmin/itxinfei123）====="
MINIO_HEALTH=$(curl -s -o /dev/null -w '%{http_code}' --max-time 10 "http://${HOST}:9000/minio/health/live" || true)
if [ "$MINIO_HEALTH" = "200" ]; then
    echo "OK      MinIO liveness 200"
    ok=$((ok + 1))
else
    echo "FAIL    MinIO liveness=${MINIO_HEALTH}"; fail=$((fail + 1))
fi

echo "===== 4. xftt 库表 ====="
TABLE_COUNT=$(docker exec -e MYSQL_PWD="${MYSQL_ROOT_PASSWORD}" mysql mysql -uroot -N \
    -e "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='xftt'" 2>/dev/null || echo 0)
echo "xftt 表数量: ${TABLE_COUNT}"
if [ "${TABLE_COUNT:-0}" -ge 100 ]; then
    ok=$((ok + 1))
else
    echo "FAIL    表数量不足 100，导入未完成"; fail=$((fail + 1))
fi

echo "===== 5. Nacos 命名空间 ====="
NACOS_BASE="http://${HOST}:8848"
TOKEN=$(curl -s --max-time 10 -X POST "${NACOS_BASE}/nacos/v1/auth/login" \
    --data-urlencode "username=${NACOS_USER}" --data-urlencode "password=${NACOS_PASSWORD}" \
    | sed -n 's/.*"accessToken":"\([^"]*\)".*/\1/p')
if [ -n "$TOKEN" ]; then
    NS=$(curl -s --max-time 10 "${NACOS_BASE}/nacos/v3/admin/core/namespace/list?accessToken=${TOKEN}")
else
    # 登录不可用时退回服务身份头
    NS=$(curl -s --max-time 10 -H "${IDENTITY_KEY}: ${IDENTITY_VALUE}" "${NACOS_BASE}/nacos/v3/admin/core/namespace/list")
fi
if echo "$NS" | grep -q '"namespace":"xftt"'; then
    echo "OK      命名空间 xftt 存在"
    ok=$((ok + 1))
else
    echo "FAIL    命名空间 xftt 缺失: $(echo "$NS" | head -c 150)"
    fail=$((fail + 1))
fi

echo "===== 6. HBase 状态 ====="
HBASE_STATUS=$(docker exec hbase bash -c 'echo status | hbase shell 2>/dev/null' | grep -E 'active master|servers' | head -2 || true)
if [ -n "$HBASE_STATUS" ]; then
    echo "OK      ${HBASE_STATUS}"
    ok=$((ok + 1))
else
    echo "FAIL    HBase status 无输出"; fail=$((fail + 1))
fi

echo
echo "汇总: OK=${ok} FAIL=${fail}"
[ "$fail" -eq 0 ]
