FROM openjdk:11

VOLUME /tmp

COPY target/one-0.0.1-SNAPSHOT.jar /one-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/one-0.0.1-SNAPSHOT.jar"]