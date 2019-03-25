FROM java:8-jre

RUN mkdir /gateway
WORKDIR /gateway
COPY build/libs/gateway.jar /gateway
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/gateway/gateway.jar", "--spring.profiles.active=docker"]
EXPOSE 10500