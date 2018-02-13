FROM openjdk:8
LABEL maintainer="bogdevich96@gmail.com"
ADD target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 9090
ENTRYPOINT ["java","-Dspring.profiles.active=container","-Djava.security.egd=file:/dev/./urandom","-jar","gateway.jar"]