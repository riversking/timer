FROM java:8-jre

RUN mkdir /configcmd
WORKDIR /config
COPY build/libs/config.jar /config
#VOLUME /config/config
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/config/config.jar", "--spring.profiles.active=docker"]
EXPOSE 8888