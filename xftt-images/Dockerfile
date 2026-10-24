# xftt-images 运行镜像（jar 方式）
# 构建前提：项目根执行 mvn package 产出 xftt-images/target/xftt-images-1.0-SNAPSHOT.jar
FROM eclipse-temurin:25-jre
COPY xftt-images/target/xftt-images-1.0-SNAPSHOT.jar /app.jar
EXPOSE 9010
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app.jar --spring.cloud.nacos.discovery.server-addr=${NACOS_SERVER} --spring.cloud.nacos.config.server-addr=${NACOS_SERVER} --spring.cloud.nacos.config.namespace=${NAMESPACE} --spring.profiles.active=${PROFILES}"]
