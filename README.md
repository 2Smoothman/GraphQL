# GraphQL Spring Boot User Management System

## Overview
A robust user management system built with Spring Boot and GraphQL, providing comprehensive functionality for user authentication, profile management, and user relationships. The system uses JWT for secure authentication and PostgreSQL for data persistence.

## Features
- 🔐 JWT Authentication
- 👤 User Profile Management
- 📝 Bio Information
- 🔄 GraphQL API
- 🗄️ PostgreSQL Database
- 🔍 User Search
- 🤝 User Connections
- 📊 User Recommendations

## Tech Stack
- ☕ Java 17
- 🌱 Spring Boot 3.x
- 🔄 GraphQL
- 🐘 PostgreSQL
- 🔑 JWT Authentication
- 🔄 Spring Data JPA
- 🔄 Lombok

## API Documentation

### Authentication Endpoints (REST)

http
POST /api/auth/signup
POST /api/auth/signin

# GraphQL Endpoints
http
POST /graphql # Main GraphQL endpoint
GET /graphiql # GraphQL IDE interface

## GraphQL Operations

### Queries

#### Get Current User

graphql
query GetMe {
me {
id
username
email
profile {
firstName
lastName
bio {
description
interests
}
}
}
}
#### Get User by ID

graphql
query GetUserById($id: ID!) {
userById(id: $id) {
id
username
email
profile {
firstName
lastName
bio {
description
interests
}
}
}
}
#### Get Profile

graphql
query GetMyProfile {
myProfile {
id
firstName
lastName
bio {
description
interests
}
}
}

### Mutations

#### Create User
graphql
mutation CreateUser {
createUser(input: {
username: "username"
email: "email@example.com"
password: "password"
profile: {
firstName: "First"
lastName: "Last"
bio: {
description: "Description"
interests: "Interests"
}
}
}) {
id
username
email
}
}
## Setup Instructions

### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Installation Steps

1. Clone the repository
2. Configure database in `application.properties`
3. Run the application with `./mvnw spring-boot:run`

## Security
- JWT-based authentication
- Password encryption
- Role-based access control
- CORS configuration

## Database Schema
- Users
- Profiles
- Bios
- Connections
- Recommendations

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Support
For support, email your.email@example.com or create an issue in the repository.