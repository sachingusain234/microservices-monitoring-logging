# Use minimal Java image
FROM openjdk:21

# Set working directory
WORKDIR /service

# Copy the built jar file into the container
COPY target/service-0.0.1-SNAPSHOT.jar service-0.0.1-SNAPSHOT.jar
# Expose the app's port


EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "service-0.0.1-SNAPSHOT.jar"]
