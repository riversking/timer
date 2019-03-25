FROM java:8-jre

RUN mkdir /file-server
WORKDIR /file-server
COPY build/libs/file-server.jar /file-server
#VOLUME /admin-server/admin-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/file-server/file-server.jar", "--spring.profiles.active=docker"]
EXPOSE 5080