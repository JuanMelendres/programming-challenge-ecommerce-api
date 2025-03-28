FROM openjdk:21
WORKDIR /app
COPY target/ecommerce-api-0.0.1-SNAPSHOT.jar ecommerce-app.jar
ENTRYPOINT ["java", "-jar", "ecommerce-app.jar"]
EXPOSE 8080