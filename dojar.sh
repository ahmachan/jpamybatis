#!/bin/bash

jar_file="$(pwd)/jpamybatis-0.0.1-SNAPSHOT.jar"

java -Djava.security.egd=file:/dev/./urandom -jar $jar_file
