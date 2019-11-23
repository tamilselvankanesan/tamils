
install modssl (this will install ssl.conf file conf.d)..
create a new ssl.conf for app specific and setup virtual hosts for both 80 and 443 ports...
create self-signed certificates:
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout mydoamin.name.key -out mydoamin.name.crt
apache verify configuration: sudo apachectl configtest
update app-ssl.conf with certificates and key file
test ssl using commandline: openssl s_client -CApath /etc/ssl/certs/ -connect mydoamin.name:443

<VirtualHost *:80>
  ServerName 192.168.1.1
  Redirect / https://tamils.rocks/
</VirtualHost>

<VirtualHost *:443>
	SSLEngine on
	ServerName 192.168.1.1
	SSLCertificateFile /etc/ssl/certs/mydoamin.name.crt
	SSLCertificateKeyFile /etc/ssl/certs/mydoamin.name.key
</VirtualHost>

