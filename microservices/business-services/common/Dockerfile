FROM openjdk:11

VOLUME /tmp

COPY target/common-0.0.1-SNAPSHOT.jar /common-0.0.1-SNAPSHOT.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/common-0.0.1-SNAPSHOT.jar"]