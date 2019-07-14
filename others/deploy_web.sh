#!/bin/sh
## deploy AppliedJobsWeb -react 

#stop tomcat
echo "Stopping tomcat...."
sh /opt/scripts/tomcat.sh stop
sleep 3
echo "Coping files from/tmp to webapps..."

rm -rf /opt/tomcat/webapps/AppliedJobsWeb
cp -r /tmp/AppliedJobsWeb/ /opt/tomcat/webapps/

chown tomcat:root /opt/tomcat/webapps/AppliedJobsWeb
chown tomcat:root /opt/tomcat/webapps/AppliedJobsWeb/*

sleep 2

echo "Starting tomcat..."

#start tomcat
sh /opt/scripts/tomcat.sh start

