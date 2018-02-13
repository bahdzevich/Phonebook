FROM openjdk:8
LABEL maintainer="bogdevich96@gmail.com"
ADD target/discovery.jar discovery.jar
EXPOSE 8761
ENTRYPOINT ["java","-Dspring.profiles.active=container","-Djava.security.egd=file:/dev/./urandom","-jar","discovery.jar"]