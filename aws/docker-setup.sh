sudo yum install -y yum-utils  device-mapper-persistent-data lvm2

#Use the following command to set up the stable repository.
sudo yum-config-manager  --add-repo   https://download.docker.com/linux/centos/docker-ce.repo

#Install the latest version of Docker CE and containerd - for specific version (list the available versions in the repo, then select and install) (nobest as an alternate option)
sudo yum install docker-ce docker-ce-cli containerd.io --nobest

#Start Docker
sudo systemctl start docker

#add user to docker group
sudo usermod -aG docker ec2-user

#uinstall docker
#sudo yum remove docker-ce

#remove docker images, containers, volumes
#sudo rm -rf /var/lib/docker

~
~
~
~
~
~

