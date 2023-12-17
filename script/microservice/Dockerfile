FROM  openjdk:17-oracle
LABEL authors="yanjingsheng"
ADD microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=/conf/application.yaml"]