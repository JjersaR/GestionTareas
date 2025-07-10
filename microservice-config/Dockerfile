# MODEL IMAGE
FROM eclipse-temurin:21.0.3_9-jdk

# CONTAINER PORT
EXPOSE 8888

# CONTAINER ROOT DIRECTORY
WORKDIR /app

# COPY THE JAR
COPY microservice-config/target/microservice-config-0.0.1-SNAPSHOT.jar app.jar

# lift application
ENTRYPOINT [ "java", "-jar", "app.jar" ]
