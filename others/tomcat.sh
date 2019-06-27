#!/bin/sh

CATALINA_HOME=/opt/tomcat
DAEMON_HOME=$CATALINA_HOME/bin
LOG_DIR=/var/log/tomcat

TOMCAT_USER=tomcat
EXECUTABLE=catalina.sh

#export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.201.b09-2.el7_6.x86_64
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.212.b04-0.el7_6.x86_64
export JRE_HOME=$JAVA_HOME/jre
#export CATALINA_OUT=$LOG_DIR/catalina.out
export CATALINA_PID=/opt/data/tomcat.pid
export CATALINA_TMPDIR=/tmp

export JAVA_OPTS="\
  -XX:+UseConcMarkSweepGC \
  -cp $CATALINA_HOME/bin/bootstrap.jar \
  -Djava.endorsed.dirs=$CATALINA_HOME/common/endorsed \
  -Dcatalina.home=$CATALINA_HOME \
  -Djava.io.tmpdir=$CATALINA_TMPDIR \
  -Daj.logdir=$LOG_DIR \
  -Damazon.dynamodb.accesskey=AKIARM3D3C7PWORCOSFP \
  -Damazon.dynamodb.secretkey=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 \
  -Dspring.profiles.active=dev \
  -Djava.awt.headless=true \
"

if [ -f "$JAVA_OPTS_FILE" ]; then
  . $JAVA_OPTS_FILE
fi

cd $CATALINA_HOME

stopTomcat() {
	su -s /bin/sh -m $TOMCAT_USER -c "$DAEMON_HOME/$EXECUTABLE stop 60 -force"	
}

startTomcat() {
	su -s /bin/sh -m $TOMCAT_USER -c "$DAEMON_HOME/$EXECUTABLE start"

}

case "$1" in
    start)
        startTomcat
        ;;

    stop)
        stopTomcat
        ;;

    restart)
        stopTomcat
        startTomcat
        ;;
    *)
        echo "Usage $0 start/stop/restart/status/stopEraseWorkDir/restartEraseWorkDir"
        exit 1;;
esac


