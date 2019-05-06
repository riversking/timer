FROM java:8-jre

RUN mkdir /oauth-server
WORKDIR /oauth-server
COPY build/libs/oauth-server.jar /oauth-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/oauth-server/oauth-server.jar", "--spring.profiles.active=docker"]
EXPOSE 9080