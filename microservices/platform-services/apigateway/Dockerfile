FROM openjdk:11

VOLUME /tmp

COPY target/apigateway-0.0.1-SNAPSHOT.jar /apigateway-0.0.1-SNAPSHOT.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "/apigateway-0.0.1-SNAPSHOT.jar"]