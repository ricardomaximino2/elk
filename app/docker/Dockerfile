# This is the Dockerfile reference for Spring based microservices
# 
# REQUIRED FILES TO BUILD THIS IMAGE
# ----------------------------------
# (1) JAR file containing the microservice compiled code.
#
FROM openjdk:8-jre-alpine
# Maintainer
# ----------
LABEL maintainer="Ricardo Maximino <ricardomaximino@hotmail.com>"

VOLUME /tmp

# Install filebeat
RUN echo "Start the operation" \
	&& mv /etc/apk/repositories /etc/apk/repositories-old \
	&& echo "http://dl-cdn.alpinelinux.org/alpine/edge/testing" > /etc/apk/newline \
	&& cat /etc/apk/repositories-old /etc/apk/newline > /etc/apk/repositories \
	&& apk update \
	&& apk add filebeat \
	&& rm /etc/apk/repositories \
	&& rm /etc/apk/newline \
	&& mv /etc/apk/repositories-old /etc/apk/repositories \
	&& apk update
	
# Change Mode of the execute file
RUN chmod 777 /usr/bin/filebeat


# Create verisure group and user and give permissions over logs folder.
RUN addgroup brasajava\
    && adduser -s /bin/sh -D -G brasajava brasajava\
    && mkdir /logs \
    && chown brasajava:brasajava /logs \
    && chmod a+rw /logs
USER brasajava

# Required build arguments
ARG NAME
ARG VERSION

# Set environment variables
ENV ARTIFACT_NAME=$NAME \
    SERVER_PORT=8080 \
    SPRING_PROFILES_ACTIVE=cloud \
    JAVA_TOOL_OPTIONS=-Duser.timezone="Europe/Madrid" \
    TZ=CET-1CEST,M3.5.0,M10.5.0/3 \
    JWT_KEY=123456

EXPOSE ${SERVER_PORT} ${MANAGEMENT_PORT}

#Add EXECUTABLE JAR
COPY ./app/target/$NAME-$VERSION.jar $NAME.jar


ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Xms64m -Xmx512m -jar /$ARTIFACT_NAME.jar -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE

