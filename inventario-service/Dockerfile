# Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar

# Copia el JAR del target al contenedor
COPY ${JAR_FILE} app.jar

# Usa variables de entorno para el puerto
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
