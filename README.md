# GraphQL Spring Application

## <span style="color:rgb(189, 198, 193)">Table of Contents</span>

[📖 About](#about) <br/>
[💻 Installation](#installation) <br/>
[📍API Endpoints](#api-endpoints) <br/>
[🔧 Versions](#versions) <br/>
[👤 Contributors](#contributors)

## <span style="color:rgb(189, 198, 193)">About</span>

This project implements a GraphQL API using Spring Boot, demonstrating how to build and manage a robust GraphQL server with Java. The application showcases CRUD operations, data relationships, and efficient query resolution. <br/><br/>
The app provides a flexible API layer that allows clients to request exactly the data they need, reducing over-fetching and under-fetching of data.<br/>

> The project uses Spring Boot with GraphQL and includes comprehensive test coverage.

## <span style="color:rgb(189, 198, 193)">Installation</span>

Follow these steps to get your development environment set up and running:

### Step 1: Clone the repository

```bash
git clone https://gitea.kood.tech/tiger/match-me
```

### Step 2: Navigate to project directory

```bash
cd GraphQl-Spring
```

### Step 3: Build the project

Using Maven:

```bash
mvn clean install
```

### Step 4: Run the application

> You can run the application in either development or production mode:

#### Development Mode

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Production Mode

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Step 5: Access GraphQL Playground

> Once the application is running, you can access the GraphQL playground at:

```
http://localhost:8080/graphiql
```

## <span style="color:rgb(189, 198, 193)">API Endpoints</span>

> Full database functionality with Swager:

```bash

http://localhost:8080/swagger-ui/index.html#/

```

> The GraphQL API provides the following operations:

### Queries

- Fetch all items
- Get item by ID
- Search items by criteria

### Mutations

- Create new item
- Update existing item
- Delete item

Example query:

```graphql
query MyProfile {
  myProfile {
    aboutMe
    firstName
    id
    lastName
    pictureUrl
  }
}
```

Example query response:

```graphql
{
  "data": {
    "myProfile": {
      "aboutMe": "I'm Software developer",
      "firstName": "John",
      "id": "19",
      "lastName": "Doe",
      "pictureUrl": "http://www.random-image.com/doe.png"
    }
  }
}
```

## <span style="color:rgb(189, 198, 193)">Versions</span>

| Dependency         | Version |
| ------------------ | ------- |
| **Spring Boot**    | 3.2.0   |
| **Java**           | 17      |
| **GraphQL Spring** | 1.2.0   |
| **H2 Database**    | 2.1.214 |

## <span style="color:rgb(189, 198, 193)">Contributors</span>

![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2?style=flat&logo=linkedin&logoColor=white) <br/>**[Timofey Babisashvili](https://www.linkedin.com/in/timofey-tech)**

![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2?style=flat&logo=linkedin&logoColor=white) <br/>**[Denis Lomakin](https://www.linkedin.com/in/dkartik123/)**<br/><br/>

