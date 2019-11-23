#!/bin/sh
## build webservices
cd /opt/git_repo/tamils/ndb
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
sudo -H -u tomcat bash -c 'cp /opt/git_repo/tamils/ndb/target/ndb-1.0-SNAPSHOT.war /opt/tomcat/webapps/ndb.war'
#sudo -H -u tomcat bash -c 'cp /opt/git_repo/webapp/ajweb/target/ajweb.war /opt/tomcat/webapps/ajweb.war'
sleep 2

#start tomcat
sudo sh /opt/scripts/tomcat.sh start

