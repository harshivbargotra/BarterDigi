FROM openjdk:11
EXPOSE 8092
ADD target/notificationservice.jar notificationservice.jar
ENTRYPOINT ["java","-jar","/notificationservice.jar"]