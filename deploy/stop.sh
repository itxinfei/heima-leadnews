#!/usr/bin/env bash
# 心飞头条（xftt）一键停止脚本（优雅停止：SIGTERM → 等待 → kill -9 兜底）
# 用法：bash deploy/stop.sh
set -uo pipefail

BASE_DIR="$(cd "$(dirname "$0")/.." && pwd)"
RUN_DIR="$BASE_DIR/deploy/run"

stop_one() {
  local module="$1"
  local pidfile="$RUN_DIR/$module.pid"
  if [[ ! -f "$pidfile" ]]; then echo "[SKIP] $module 无 pid 文件"; return 0; fi
  local pid; pid="$(cat "$pidfile")"
  if kill -0 "$pid" 2>/dev/null; then
    echo "[STOP] $module (pid $pid) 发送 SIGTERM"
    kill "$pid" 2>/dev/null || true
    for _ in $(seq 1 30); do
      kill -0 "$pid" 2>/dev/null || { echo "  -> 已停止"; rm -f "$pidfile"; return 0; }
      sleep 1
    done
    echo "  -> 超时未退出，kill -9"
    kill -9 "$pid" 2>/dev/null || true
  else
    echo "[SKIP] $module (pid $pid) 未运行"
  fi
  rm -f "$pidfile"
}

# 停止顺序：先网关，再业务（启动反序）
MODULES=(xftt-gateway xftt-migration xftt-images xftt-crawler xftt-admin xftt-media xftt-user xftt-behavior xftt-article xftt-login)
for m in "${MODULES[@]}"; do stop_one "$m"; done
echo "停止完成"
