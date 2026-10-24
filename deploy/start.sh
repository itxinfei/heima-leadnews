#!/usr/bin/env bash
# 心飞头条（xftt）一键启动脚本（jar 方式，不依赖 Docker）
# 用法：bash deploy/start.sh   （先在各模块执行 mvn package 产出 jar）
set -uo pipefail

# =================== 可配置区 ===================
NACOS_SERVER="${NACOS_SERVER:-192.168.20.130:8848}"
NAMESPACE="${NAMESPACE:-xftt}"
PROFILES="${PROFILES:-prod}"
JAVA_OPTS="${JAVA_OPTS:--Xms256m -Xmx512m}"
# ================================================

BASE_DIR="$(cd "$(dirname "$0")/.." && pwd)"
RUN_DIR="$BASE_DIR/deploy/run"
LOG_DIR="$BASE_DIR/deploy/logs"
mkdir -p "$RUN_DIR" "$LOG_DIR"

# 启动顺序：先业务服务，最后网关。格式：模块目录|jar名|端口
SERVICES=(
  "xftt-login|xftt-login-1.0-SNAPSHOT.jar|9001"
  "xftt-article|xftt-article-1.0-SNAPSHOT.jar|9003"
  "xftt-behavior|xftt-behavior-1.0-SNAPSHOT.jar|9004"
  "xftt-user|xftt-user-1.0-SNAPSHOT.jar|9005"
  "xftt-media|xftt-media-1.0-SNAPSHOT.jar|9006"
  "xftt-admin|xftt-admin-1.0-SNAPSHOT.jar|9007"
  "xftt-crawler|xftt-crawler-1.0-SNAPSHOT.jar|9008"
  "xftt-images|xftt-images-1.0-SNAPSHOT.jar|9010"
  "xftt-migration|xftt-migration-1.0-SNAPSHOT.jar|9012"
  "xftt-gateway|xftt-gateway-1.0-SNAPSHOT.jar|10000"
)

start_one() {
  local module="$1" jar="$2" port="$3"
  local pidfile="$RUN_DIR/$module.pid"
  if [[ -f "$pidfile" ]] && kill -0 "$(cat "$pidfile")" 2>/dev/null; then
    echo "[SKIP] $module 已运行 (pid $(cat "$pidfile"))"; return 0
  fi
  local jarpath="$BASE_DIR/$module/target/$jar"
  if [[ ! -f "$jarpath" ]]; then
    echo "[WARN] $module jar 不存在: $jarpath （请先 mvn package）"; return 0
  fi
  echo "[START] $module :$port"
  nohup java $JAVA_OPTS -jar "$jarpath" \
    --spring.cloud.nacos.discovery.server-addr="$NACOS_SERVER" \
    --spring.cloud.nacos.config.server-addr="$NACOS_SERVER" \
    --spring.cloud.nacos.config.namespace="$NAMESPACE" \
    --spring.profiles.active="$PROFILES" \
    > "$LOG_DIR/$module.log" 2>&1 &
  echo $! > "$pidfile"
  echo "  -> pid $(cat "$pidfile"), log $LOG_DIR/$module.log"
}

for s in "${SERVICES[@]}"; do
  IFS='|' read -r module jar port <<< "$s"
  start_one "$module" "$jar" "$port"
  sleep 2
done

echo "启动指令已全部发出（业务先行、网关最后）。健康检查："
for s in "${SERVICES[@]}"; do
  IFS='|' read -r module jar port <<< "$s"
  echo "  curl -s http://localhost:$port/actuator/health"
done
echo "日志目录：$LOG_DIR  PID 目录：$RUN_DIR"
