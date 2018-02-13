FROM openjdk:8
LABEL maintainer="bogdevich96@gmail.com"
ADD target/profile-0.0.1-SNAPSHOT.jar profile.jar
EXPOSE 9096
ENTRYPOINT ["java","-Dspring.profiles.active=container","-Djava.security.egd=file:/dev/./urandom","-jar","profile.jar"]