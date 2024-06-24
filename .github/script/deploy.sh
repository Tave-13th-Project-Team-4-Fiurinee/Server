#!/bin/bash

# 현재 실행 중인 JAR 프로세스를 찾고 종료합니다.
CURRENT_PID=$(pgrep -f .jar)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
        echo "no process"
else
        echo "kill $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 3
fi

# 새로운 JAR 파일의 경로를 설정하고 실행 권한을 부여합니다.
JAR_PATH="/home/ec2-user/cicd/*.jar"
echo "jar path : $JAR_PATH"
chmod +x $JAR_PATH

# 새로운 JAR 파일을 백그라운드에서 실행합니다.
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
echo "jar file deploy success"
