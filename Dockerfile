FROM openjdk:13-jdk
MAINTAINER Philippe Vancom

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]

ARG JAR_FILE

ADD ./target/${JAR_FILE} /usr/share/app.jar
