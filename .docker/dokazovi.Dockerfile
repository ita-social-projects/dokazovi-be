FROM openjdk:13-jdk-alpine
ARG WAR_FILE=build/libs/*.war
COPY ${WAR_FILE} dokazovi.war
ENTRYPOINT ["java","-jar", "-Xmx128M","/dokazovi.war"]