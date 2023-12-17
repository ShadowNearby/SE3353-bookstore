FROM  openjdk:17-oracle
LABEL authors="yanjingsheng"
ADD eureka-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=/conf/application.yaml"]