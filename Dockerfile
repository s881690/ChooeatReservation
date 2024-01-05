# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17

# 创建目录以存储复制的文件
RUN mkdir /app

# 将等待脚本复制到容器中
COPY wait-for-it.sh /wait-for-it.sh
# 使脚本可执行
RUN chmod +x /wait-for-it.sh

# 将您的 Spring Boot JAR 文件复制到容器中
COPY ./out/artifacts/ChooeatReservation.jar /app/app.jar

# 当容器启动时，首先执行等待脚本，然后启动您的应用
# 请替换 'mysql:3306' 为您的数据库服务的主机名和端口
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "/app.jar"]
