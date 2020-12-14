FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-debian

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar
COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh && ln -s /usr/local/bin/docker-entrypoint.sh /

ENTRYPOINT ["docker-entrypoint.sh"]
