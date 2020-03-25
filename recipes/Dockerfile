From openjdk:8u212-jre-alpine

# The application's jar file. The ARG instruction defines a variable with a default value. You can override the default value of the variable by passing it at build time.
ARG JAR_FILE=target/recipes-0.0.1-SNAPSHOT.war

# Add the application's jar to the container
ADD ${JAR_FILE} recipes.jar

# Run the jar file 
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=aws", "/recipes.jar"]