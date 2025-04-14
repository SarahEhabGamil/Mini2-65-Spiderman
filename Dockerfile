FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY ./target/ target/

ENTRYPOINT ["java", "-jar", "target/miniapp-0.0.1-SNAPSHOT.jar"]