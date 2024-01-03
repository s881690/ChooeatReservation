# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17

# 将您的 Spring Boot JAR 文件复制到 Docker 容器的根目录，并重命名为 app.jar
# 确保路径 './out/artifacts/ChooeatReservation.jar' 是相对于 Dockerfile 位置的正确路径
COPY ./out/artifacts/ChooeatReservation.jar /app.jar

# 当容器启动时，执行 Java 命令来启动您的应用
ENTRYPOINT ["java", "-jar", "/app.jar"]
