FROM gradle:6.3.0-jdk11 as builder
USER root
WORKDIR /builder

# copy project dir and run build
ADD . /builder
RUN gradle bootWar

# used alpine linux for small disk size
FROM openjdk:13-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/dokazovi-0.0.1-SNAPSHOT.war .

ENTRYPOINT [ "java", "-jar", "dokazovi-0.0.1-SNAPSHOT.war" ]