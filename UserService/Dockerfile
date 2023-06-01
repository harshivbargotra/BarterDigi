FROM openjdk:11
EXPOSE 9090
ADD target/userservice.jar userservice.jar
ENTRYPOINT ["java","-jar","userservice.jar"]