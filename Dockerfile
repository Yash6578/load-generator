FROM openjdk:8
EXPOSE 8083
COPY /target/load-generator-0.0.1-SNAPSHOT.jar load-generator-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "load-generator-0.0.1-SNAPSHOT.jar"]
