From centos:7

RUN yum -y update

RUN curl -sL https://rpm.nodesource.com/setup_13.x | bash -

RUN yum -y install java-1.8.0-openjdk wget zip nodejs

RUN  wget http://apache.spinellicreations.com/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz
RUN tar xzvf apache-maven-3.6.2-bin.tar.gz

#set maven path
ENV PATH="/apache-maven-3.6.2/bin:${PATH}"

#set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/jre-openjdk

#RUN curl -sL https://rpm.nodesource.com/setup_13.x | bash -
#RUN yum -y install nodejs

#RUN wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.34.0/install.sh | bash
#ENV NVM_DIR=/root/.nvm

#activate nvm and install node in the same layer
#RUN . "$NVM_DIR/nvm.sh" && nvm install node

#ENV PATH="/root/.nvm/versions/node/v13.1.0/bin:${PATH}"

