# E-Commerce API

## 🚀 Project Overview
This is a **Spring Boot 3** application that provides a RESTful API for an e-commerce platform. It uses **PostgreSQL** for database management and runs inside a **Docker** container. The API follows **OpenAPI 3.0** standards and provides endpoints for managing products, orders, and order items.

---

## 📦 Installation Instructions

### **1. Clone the Repository**
```sh
git clone https://github.com/JuanMelendres/programming-challenge-ecommerce-api.git
cd ecommerce-api
```

### **2. Set Up the Database**
#### **Option 1: Using Docker (Recommended)**
Ensure you have Docker installed and running, then execute:
```sh
docker-compose up -d
```
This will start a PostgreSQL container and create a database named `ecommerce`.

#### **Option 2: Using Local PostgreSQL**
If you prefer to install PostgreSQL manually:
```sh
psql -U postgres
```
Then run:
```sql
CREATE DATABASE ecommerce;
CREATE USER user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE ecommerce TO user;
```

### **3. Configure the Application**
Modify `src/main/resources/application.properties` if necessary:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

### **4. Build and Run the Application**
#### **Option 1: Running with Maven**
```sh
mvn spring-boot:run
```
#### **Option 2: Running with Docker**
```sh
docker-compose up --build
```

Once the application is running, it will be available at:
```
http://localhost:8080
```

---

## 📖 Usage Guide

### **API Endpoints**

#### **Product API**
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product

#### **Order API**
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update an order
- `DELETE /api/orders/{id}` - Delete an order

#### **Order Item API**
- `GET /api/order-items` - Get all order items
- `GET /api/order-items/{id}` - Get order item by ID
- `POST /api/order-items` - Create an order item
- `PUT /api/order-items/{id}` - Update an order item
- `DELETE /api/order-items/{id}` - Delete an order item

---

## 📑 API Documentation (Swagger)
The application includes **Swagger UI** for interactive API documentation.

### **Access Swagger UI**
Once the application is running, open:
```
http://localhost:8080/swagger-ui.html
```
This provides a web-based UI to test and explore API endpoints.

Additional Swagger URLs
 * API Docs: http://localhost:8080/v3/api-docs
 * Swagger UI: http://localhost:8080/swagger-ui/swagger-ui/index.html

These links provide access to the OpenAPI specifications and Swagger UI.

---

## ✅ Running Tests
To run unit tests, execute:
```sh
mvn test
```
This will run **JUnit & Mockito** tests to verify the application's functionality.

---

## 📌 Deployment
If you want to deploy the application, you can build a JAR file:
```sh
mvn clean package
```
Then run it manually with:
```sh
java -jar target/ecommerce-app.jar
```
Or deploy the **Docker container** using:
```sh
docker-compose up --build -d
```

---

## 📬 Contact
For any issues or questions, feel free to reach out via **GitHub Issues** or email **juan.mele97@gmail.com**.

Happy coding! 🚀

