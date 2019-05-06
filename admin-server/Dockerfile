FROM java:8-jre

RUN mkdir /admin-server
WORKDIR /admin-server
COPY build/libs/admin-server.jar /admin-server
VOLUME /admin-server/admin-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/admin-server/admin-server.jar", "--spring.profiles.active=docker"]
EXPOSE 7086