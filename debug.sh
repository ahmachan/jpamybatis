#!/bin/bash

debug_port="8081"
project_version="0.0.1-SNAPSHOT"
jar_file="./target/jpa-mybatis-${project_version}.jar"

echo "on eclipse,Remote Java Application -> new -> Connection Type:Standard(Socket Attach);Host:127.0.0.1;Port:8096(docker 8096:8081)"
echo "postman:http://127.0.0.1:8096/stores/detail/2"

java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=$debug_port -jar $jar_file

#java -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=$debug_port -jar $jar_file

###EBUG选项参数的意思：### -Djava.compiler=NONE
#-XDebug 启用调试；
#-Xrunjdwp 加载JDWP的JPDA参考执行实例；
#transport 用于在调试程序和VM使用的进程之间通讯；
#dt_socket 套接字传输；
#server=y/n VM是否需要作为调试服务器执行；
#address=7899 调试服务器监听的端口号；
#suspend=y/n 是否在调试客户端建立连接之后启动 VM (如果在docker中不能使用n,必须使用y,默认也是y)


