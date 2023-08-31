FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/project-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]