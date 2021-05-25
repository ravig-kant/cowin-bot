FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S bot && adduser -S bot -G bot
USER bot:bot
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]