#!/usr/bin/env bash
#export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILE"

exec java $JAVA_OPTS -jar /app.jar $@
