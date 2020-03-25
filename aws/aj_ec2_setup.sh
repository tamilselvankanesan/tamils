#!/bin/bash
yum update -y
#should be removed - for centos
#yum install ncurses initscripts -y

yum install java-1.8.0-openjdk wget unzip nginx python3 -y

#install nginx
#only for aws linux 2
amazon-linux-extras install nginx1.12

#yum install nginx

#install aws cli
curl -O https://bootstrap.pypa.io/get-pip.py
python3 get-pip.py --user
export PATH=~/.local/bin:$PATH
source ~/.bash_profile
pip3 install awscli --upgrade --user

################ download artifacts from s3 start #####################################
mkdir -p /opt/appdata
cd /opt/appdata
#get log4j.properties
aws s3api get-object --bucket=$1 --key=log4j.properties log4j.properties

#get war file
aws s3api get-object --bucket=$1 --key=services.war services.war

#get UI code
aws s3api get-object --bucket=$1 --key=AppliedJobsWeb.zip AppliedJobsWeb.zip

#get appjob webservice configuration
aws s3api get-object --bucket=$1 --key=applied_jobs_config.sh applied_jobs_config.sh

#get APPJOBS_TOMCAT
aws s3api get-object --bucket=$1 --key=APPJOBS_TOMCAT APPJOBS_TOMCAT

#get nginx.conf
aws s3api get-object --bucket=$1 --key=nginx.conf nginx.conf

################ download from s3 end #####################################

################ configure tomcat start #####################################
##download tomcat
wget http://mirror.olnevhost.net/pub/apache/tomcat/tomcat-9/v9.0.27/bin/apache-tomcat-9.0.27.tar.gz

##extract tomcat to /opt/tomcat9
mkdir -p /opt/tomcat9
tar -zxvf apache-tomcat-9.0.27.tar.gz -C /opt/tomcat9 --strip-components=1

#add tomcat group
groupadd tomcat

#create tomcat user
useradd -s /bin/nologin -g tomcat -d /opt/tomcat9 tomcat

cd /opt
ln -s /opt/tomcat9 /opt/tomcat

cp /opt/appdata/log4j.properties /opt/tomcat/conf
chown tomcat:root /opt/tomcat/conf/log4j.properties

cp /opt/appdata/services.war /opt/tomcat/webapps
chown tomcat:root /opt/tomcat/webapps/services.war

#copy appjob webservice configuration
mkdir /opt/data
cp /opt/appdata/applied_jobs_config.sh /opt/data
chmod 755 /opt/data/applied_jobs_config.sh
chown tomcat:root /opt/data/applied_jobs_config.sh

chmod 775 /opt/tomcat9
chmod 775 /opt/tomcat9/webapps/
chown -R tomcat:root /opt/tomcat9

mkdir -p /var/log/tomcat
chown tomcat:root /var/log/tomcat
chmod 755 /var/log/tomcat

#tomcat start/stop script.. as a service
cp /opt/appdata/APPJOBS_TOMCAT /etc/init.d
cd /etc/init.d
chmod 775 APPJOBS_TOMCAT
chown -R tomcat:root APPJOBS_TOMCAT
chkconfig --add APPJOBS_TOMCAT
chkconfig APPJOBS_TOMCAT on

################ configure tomcat end #####################################

################ configure nginx start #####################################

#copy nginx.conf
mv -f /opt/appdata/nginx.conf /etc/nginx/nginx.conf
chmod 644 /etc/nginx/nginx.conf
cd /opt/appdata
unzip AppliedJobsWeb.zip -d AppliedJobsWeb
rm -rf /var/www/html/AppliedJobsWeb
mkdir -p /var/www/html/AppliedJobsWeb
cp -r /opt/appdata/AppliedJobsWeb/build/* /var/www/html/AppliedJobsWeb
chmod  744 /var/www/html/AppliedJobsWeb
#enable nginx service
systemctl enable nginx

################ configure nginx end #####################################

#start nginx and tomcat
service nginx start
service APPJOBS_TOMCAT start

###################################################################
