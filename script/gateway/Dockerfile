FROM  openjdk:17-oracle
LABEL authors="yanjingsheng"
ADD gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=/conf/application.yaml"]