# Simple SpringBoot Microservices Project

**E-Commerce Microservices Project** built with **Java 17**, **Spring Boot**, **Spring Cloud**, **JWT Security**, **MySQL**,  **Kafka (KRaft)**, **Feign Client**, **API Gateway**, and **Eureka**.

## Modules
- `eureka-server`
- `api-gateway`
- `user-service`
- `product-service`
- `order-service`
- `notification-service`

## Architecture Summary
- **user-service**: Registration, login, JWT issuance, CRUD on users and addresses. Uses **MySQL**.
- **product-service**: Product and inventory CRUD. Uses 
- **order-service**: Order and order item CRUD, validates user/product through **Feign clients**, publishes events to **Kafka**. Uses **MySQL**.
- **notification-service**: Notification and template CRUD, consumes order-created Kafka events and stores notifications in 
- **api-gateway**: Single entry point for all services. Basic JWT validation filter for downstream routing.
- **eureka-server**: Service discovery.

## Tech Stack
- Java 17
- Maven
- Spring Boot 3.3.2
- Spring Cloud 2023.0.3
- Spring Security
- JWT (io.jsonwebtoken)
- BCrypt password hashing
- Spring Data JPA + MySQL
- Spring Cloud OpenFeign
- Spring Cloud Gateway
- Netflix Eureka Client / Server
- Spring for Apache Kafka

## Local Ports
- Eureka Server: `8761`
- API Gateway: `8080`
- User Service: `8081`
- Product Service: `8082`
- Order Service: `8083`
- Notification Service: `8084`
- MySQL: `3306`
- Kafka: `9092`

## Run Order
1. Start **MySQL**
2.Start **Kafka KRaft** using files under 
3.Start `eureka-server`
4.Start `api-gateway`
5.Start `user-service`
6.Start `product-service`
7.Start `order-service`
8.Start `notification-service`

## Required Local Configuration
### 1) MySQL
Create two databases:
- `simple_user_db`
- `simple_order_db`
- `simple_product_db`
- `simple_notification_db`

Then run:
- `db/mysql/user_service_schema.sql`
- `db/mysql/order_service_schema.sql`


## Default Credentials
Seeded admin user:
- username: `admin`
- email: `admin@simple.com`
- password: `Admin@123`

> Password in DB is BCrypt encoded. Login endpoint returns JWT.

## Core Endpoint Flow
### Authentication Flow
1. `POST /api/users/auth/register` creates a new user.
2. `POST /api/users/auth/login` validates credentials and returns JWT.
3. Add `Authorization: Bearer <token>` header for protected APIs.

### Product + Inventory Flow
1. Create product via `POST /api/products`
2. Update stock via `PUT /api/products/{id}/inventory`
3. Fetch products via `GET /api/products` or `GET /api/products/{id}`

### Order Flow
1. Create user and product first.
2. `POST /api/orders` receives userId + order items.
3. Order service calls:
    - User service via Feign to validate user.
    - Product service via Feign to validate product and price.
4. Order is stored in MySQL.
5. Kafka event `order.created` is published.
6. Notification service consumes the event and stores notification in MongoDB.

### Notification Flow
1. Notifications are auto-created from Kafka events.
2. Templates can be managed with CRUD APIs.
3. Notifications can also be created manually via REST.

## Gateway Routes
Use gateway URLs:
- `/api/users/**`
- `/api/products/**`
- `/api/orders/**`
- `/api/notifications/**`

