#! /bin/sh

#> /home/ec2-user/mod_jk.conf
#> /home/ec2-user/workers.properties

sudo yum update -y
sudo yum install httpd httpd-devel gcc wget git make redhat-rpm-config libtool java-1.8.0-openjdk-devel -y

##download tomcat
wget http://mirror.olnevhost.net/pub/apache/tomcat/tomcat-9/v9.0.21/bin/apache-tomcat-9.0.21.tar.gz

##extract tomcat to /opt/tomcat
sudo tar -zxvf apache-tomcat-9.0.21.tar.gz -C /opt/tomcat --strip-components=1

##download maven
wget http://mirror.reverse.net/pub/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz

#extract maven tar
tar xvf apache-maven-3.6.1-bin.tar.gz

#set mvn path
PATH="$HOME/.local/bin:$HOME/bin:$PATH:apache-maven-3.6.1/bin"
export PATH

##download tomcat connectors (mod_jk)
wget http://mirrors.sonic.net/apache/tomcat/tomcat-connectors/jk/tomcat-connectors-1.2.46-src.tar.gz

#build mod_jk connector module..
tar -zxvf tomcat-connectors-1.2.46-src.tar.gz
cd tomcat-connectors-1.2.46-src
./configure --with-apxs=/usr/bin/apxs
make
libtool --finish /usr/lib64/httpd/modules
sudo make install


## setup tomcat
sudo mkdir /opt/tomcat
sudo groupadd tomcat
sudo useradd -s /bin/nologin -g tomcat -d /opt/tomcat tomcat
sudo chmod 770 /opt/tomcat
chown -R tomcat:root /opt/tomcat

##create mod_jk.conf file and define workers
#sudo echo "LoadModule jk_module modules/mod_jk.so" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkWorkersFile /etc/httpd/conf/workers.properties" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkLogFile logs/mod_jk.log" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkShmFile logs/mod_jk.shm" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkLogLevel info" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkMount /services/* ajworker" >> /home/ec2-user/mod_jk.conf
#sudo echo "JkMount /services1/* ajworker" >> /home/ec2-user/mod_jk.conf

##create workers.properties
#sudo echo "worker.list=ajworker" >> /home/ec2-user/workers.properties
#sudo echo "worker.ajworker.type=ajp13" >> /home/ec2-user/workers.properties
#sudo echo "worker.ajworker.host=localhost" >> /home/ec2-user/workers.properties
#sudo echo "worker.ajworker.port=8009" >> /home/ec2-user/workers.properties
#sudo echo "worker.ajworker.socket_timeout=300" >> /home/ec2-user/workers.properties

#Clone webservices - note: the password is hard-coded. has to be modified to use a read-only user instead
sudo mkdir -P /opt/git_repo
sudo chmod 777 /opt/git_repo

git clone https://tamil_ts:Selvan%401315@bitbucket.org/bnsouthdev/restapi.git /opt/git_repo/
git clone https://tamil_ts:Selvan%401315@bitbucket.org/bnsouthdev/internal_tools.git /opt/git_repo/

#copy mod_jk and worker.properties to httpd
sudo cp /opt/git_repo/internal_tools/mod_jk.conf /etc/httpd/conf.d
sudo cp /opt/git_repo/internal_tools/workers.properties /etc/httpd/conf

#to hold the tomcat pid file
sudo mkdir -P /opt/data
sudo chmod 777 /opt/data

#copy tomcat start/stop and build scripts
sudo mkdir -P /opt/scripts
sudo chmod 777 /opt/scripts
cp /opt/git_repo/internal_tools/tomcat.sh /opt/scripts
cp /opt/git_repo/internal_tools/build.sh /opt/scripts

sh build.sh

#disable SELinux temporarily
setenforce 0
#disable SELinux permanently
sudo sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' config
sudo service httpd restart

