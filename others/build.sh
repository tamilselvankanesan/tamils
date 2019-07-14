#!/bin/sh
## build webservices
cd /opt/git_repo/restapi/services/
git pull
sleep 2
mvn clean install -DskipTests

#build UI
sleep 2
#cd /opt/git_repo/webapp/ajweb
#git pull
#sleep 2
#mvn clean install
#sleep 2 

#stop tomcat
sudo sh /opt/scripts/tomcat.sh stop
sleep 5

#copy webservices and ui war to tomcat webapps
sudo -H -u tomcat bash -c 'cp /opt/git_repo/restapi/services/target/services-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/services.war'
#sudo -H -u tomcat bash -c 'cp /opt/git_repo/webapp/ajweb/target/ajweb.war /opt/tomcat/webapps/ajweb.war'
sleep 2

#start tomcat
sudo sh /opt/scripts/tomcat.sh start

