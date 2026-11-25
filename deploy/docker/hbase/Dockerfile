# HBase 2.6.7（部署手册 §2 定版 2.6.x，用户确认 2.6.7）
# 镜像源无社区 2.6.x tag，改为本地固定版本构建：
# 基础镜像 eclipse-temurin:17.0.15_6-jre（已验证存在）；
# 二进制 tar 包在宿主机预下载（mirrors.aliyun.com/apache/hbase/2.6.7），构建期零外网依赖。
FROM eclipse-temurin:17.0.15_6-jre

ENV HBASE_VERSION=2.6.7 \
    HBASE_HOME=/opt/hbase

COPY hbase-2.6.7-bin.tar.gz /tmp/hbase.tar.gz

RUN mkdir -p "${HBASE_HOME}" \
 && tar -xzf /tmp/hbase.tar.gz -C "${HBASE_HOME}" --strip-components=1 \
 && rm -f /tmp/hbase.tar.gz

ENV PATH="${HBASE_HOME}/bin:${PATH}"

EXPOSE 16000 16010 16020 16030

# 注意：standalone 模式下 Master/RS 绑定容器 hostname（如 172.22.0.2），不是 localhost，
# 因此健康检查必须用 $(hostname)，否则永远 Connection refused。
HEALTHCHECK --interval=30s --timeout=15s --retries=10 \
  CMD timeout 5 bash -c 'exec 3<>/dev/tcp/$(hostname)/16020' || exit 1

# standalone 模式：容器内自带一个本地 ZK（不映射主机 2181，避免与共享 zookeeper:2181 冲突）
CMD ["bash", "-lc", "${HBASE_HOME}/bin/start-hbase.sh && exec tail -F ${HBASE_HOME}/logs/*.log"]
