#!/bin/sh
cd /opt/git_repo/restapi/services/
git pull
sleep 2
mvn -Dspring.profiles.active=dev clean package 
sudo sh /opt/scripts/tomcat.sh stop
sleep 5
sudo -H -u tomcat bash -c 'cp /opt/git_repo/restapi/services/target/services-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/services.war'
sleep 2
sudo sh /opt/scripts/tomcat.sh start

