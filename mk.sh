#!/bin/bash
#bridge composeup_default

default_network="composeup_default"
target_container="test01"
link_mysql="mysql5.6.16"

docker stop ${target_container} && docker rm ${target_container}
docker run --name=${target_container} --network=${default_network} --link=${link_mysql} -idt -p 8096:8081 -p 8095:8080 -v `pwd`/../:/opt/temp frolvlad/alpine-oraclejdk8:slim sh
