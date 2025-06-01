#!/bin/bash
JAR_FILE=$(ls /app/build/libs/*.jar)
java -jar "$JAR_FILE"