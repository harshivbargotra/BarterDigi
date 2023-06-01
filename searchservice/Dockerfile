FROM openjdk:11
EXPOSE 9988
ADD target/searchservice.jar searchservice.jar
ENTRYPOINT ["java","-jar","searchservice.jar"]