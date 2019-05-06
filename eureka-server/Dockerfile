FROM java:8-jre

RUN mkdir /eureka-server
WORKDIR /eureka-server
COPY build/libs/eureka-server.jar /eureka-server
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eureka-server/eureka-server.jar", "--spring.profiles.active=docker"]
EXPOSE 8761