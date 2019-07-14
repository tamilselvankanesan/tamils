Discovery service:
	Keeps tracks of all available services, does load balancing.
	@EnableEurekaServer
	When using config server, make sure to use bootstrap.yml instead of default applicaiton.properties (it didn't work with app.properties)

Gateway service:
	All requests go thru gateway and gateway with the help of discovery distributes the load to the available services. (round-robin). 
	Make sure *not* to extend SpringBootServletInitializer and make sure spring-web dependency is not there in pom.xml.
	
Person and address services:
	Are the actual services and register with discovery service. Use ${PORT:0} to assign dynamic port
	
Config service:
	To maintain the configuration in one place.
	The config files should be in git repo for prod deployments. But for development this can be stored in the local file storage. But if local file storage is used then 
	the native profile has to be activated.
	Security:
		1. can be enabled by including spring-boot-starter-security dependency. This will enable basic authentication. The default user name will be user and password will be printed in the console during config server startup.
		2. To have custom credentials, include the username and password in properties file.
			spring.cloud.config.username=tamil
			spring.cloud.config.password=somepassword 
	  
	
Communication b/w services:
	Use Feign clients. Make sure to enable Feign clients in the application class.
	
Proxy service: 
	To load balance. make sure to add an entry to fetch service registry from eureka (fetchRegistry: true)

1. Two ways to refresh configuration changes in the config client services.
	1. using /refresh endpoint provided by spring boot actuator. a manual process.
	2. with /bus/refresh with spring-cloud-bus and in this case all the instances subscribe to an event and whenever this event is triggered, all the config properties will be automatically refreshed via spring cloud bus broadcasting.
		Setup a queue (e.g. RabbitMQ and add the configuration properties in config-server and config-clients). Now, the clients will have another end point /bus/refresh.  Calling this endpoint will cause:
			1. get the latest configuration from the config server and update its configuration annotated by @RefreshScope
			2. send a message to AMQP exchange informing about refresh event
			3. all subscribed nodes will update their configuration as well 
		
Include maven build plugin and package code using mvn package command. (this will create an executable jar)
Start spring boot using java command. port can be dynamically changed using vm arguments.
<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>2.1.3.RELEASE</version>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>

mvn package

If you do not include the <execution/> configuration, as shown in the prior example, you can run the plugin on its own (but only if the package goal is used as well), as shown in the following example:
mvn package spring-boot:repackage

java -jar -Dserver.port=80823 abc.jar

Docker notes:
Using localhost to lookup config and eureka servers doesn't work. have to use container-id in the url. for e.g  

Links:
https://dzone.com/articles/buiding-microservice-using-springboot-and-docker


1. connect containers using container-name (using links)
2. Also, network needs to be included and all services should be linked to the same n/w to allow inter communications
3. config and eurkea server needs to be up before other services are available. can be achieved using custom sh script.

