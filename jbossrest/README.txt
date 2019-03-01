Springboot ->	use the correct JDK (boot 2 min requirement is jdk 8)
Tomcat deployment ->	Make sure to extend SpringBootServletInitializer
pom.xml	-> make sure to exclude tomcat server dependency
make sure to add a dependency for servlet-api and provided scope
final version is a war

for Jboss	-> make sure to specify the context-root in application.properties
			-> also, some modules needs to be enabled in the jboss module.xml.. Depends on the error.. May not be applicable as well"
			in the sun/jdk/module.xml for spring jdbc ->
					<path name="com/sun/rowset"/>
					<path name="com/sun/rowset/providers"/>
for datasource	-> Configure the datasource in the server and use the data source in the spring configuration
1. create a module.xml (modules\com\mysql\main\) and place the jar here
	<module xmlns="urn:jboss:module:1.0" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.47.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>
2. define datasource and driver class in standalone.xml
<datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <datasource jndi-name="java:/jdbc/myndb" pool-name="MySQLPool" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://dddasdsdsdsd:3306/dbname</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>usernae</user-name>
                        <password>password</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                    <driver name="mysql" module="com.mysql">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
                </drivers>
            </datasources>
