FROM openjdk:11-jre-slim-buster
EXPOSE 8083
ADD ./target/OrderService.jar OrderService.jar
ENTRYPOINT ["java","-jar", "OrderService.jar"]
