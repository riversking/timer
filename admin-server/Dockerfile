FROM java:8-jre

RUN mkdir /app
WORKDIR /app
COPY build/libs/admin-server.jar /app
VOLUME /app/admin-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/admin-server.jar", "--spring.profiles.active=docker"]
EXPOSE 7086