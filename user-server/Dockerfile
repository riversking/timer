FROM java:8-jre

RUN mkdir /user-server
WORKDIR /user-server
COPY build/libs/user-server.jar /user-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/user-server/user-server.jar", "--spring.profiles.active=docker"]
EXPOSE 6080