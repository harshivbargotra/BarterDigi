FROM openjdk:11-jre-slim-buster
EXPOSE 8093
ADD target/sellerservicedocker.jar sellerservicedocker.jar
ENTRYPOINT ["java","-jar","sellerservicedocker.jar"] 
