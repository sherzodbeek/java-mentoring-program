FROM openjdk:11

VOLUME /tmp

COPY target/two-0.0.1-SNAPSHOT.jar /two-0.0.1-SNAPSHOT.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/two-0.0.1-SNAPSHOT.jar"]