# 基于OpenJDK 8的镜像
FROM openjdk:8

# 设置工作目录
WORKDIR /app

# 将应用程序jar文件复制到容器中
COPY containers1-1.0-SNAPSHOT.jar /app/containers1-1.0-SNAPSHOT.jar

# 运行应用程序
CMD ["java", "-jar", "/app/containers1-1.0-SNAPSHOT.jar"]
