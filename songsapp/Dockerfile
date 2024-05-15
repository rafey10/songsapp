FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} songsapp.jar
ENTRYPOINT ["java","-jar","/songsapp.jar"]