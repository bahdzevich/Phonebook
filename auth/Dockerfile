FROM openjdk:8
LABEL maintainer="bogdevich96@gmail.com"
ADD target/auth-0.0.1-SNAPSHOT.jar auth.jar
EXPOSE 10000
ENTRYPOINT ["java","-Dspring.profiles.active=container","-Djava.security.egd=file:/dev/./urandom","-jar","auth.jar"]