#!/bin/bash

project_version="0.0.1-SNAPSHOT"
jar_file="$(pwd)/target/jpa-mybatis-${project_version}.jar"

java -Djava.security.egd=file:/dev/./urandom -jar $jar_file
