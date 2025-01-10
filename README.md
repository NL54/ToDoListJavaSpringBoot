# ToDoListJavaSpringBoot
This is REST API of a To Do List done with Java, Spring boot, Spring data JDBC, PostgresSQL, Junit and docker.

This is a back-end project.

With this REST  API, you can create, add, delete, modify users of the todolist or todos.

A PostgresSQL database is used to stock the data and test the API.

You need to have docker to launch the project.

## To run the project
### If you use the dockerized version
1. Launch docker

2. Run the command :
`mvnw clean package -DskipTests`

3. Run the command :
`docker compose up` to launch the project with docker

### If you use the version working with java
1. Launch docker

2. Run the command :
`docker compose up`

3. Run the command :
`java -jar target/todolist-0.0.1-SNAPSHOT.jar` to launch the project with java

## To Test the project
To test this project, you can use the file in the postman folder. (use the one present on the last update)

## Initialization of the database
The database of users and todos is initialized with data fetch from the API https://jsonplaceholder.typicode.com .

## API command
### For User
### GET
#### To get all the user
````
http://localhost:8080/api/user
````

#### To get a user by id
````
http://localhost:8080/api/user/id
````

### PUT
#### To update a user by id
````
http://localhost:8080/api/user/11
````

### POST
#### To add a user
````
http://localhost:8080/api/user
````

### DELETE
#### To delete a user by id
````
http://localhost:8080/api/user/id
````

### For Todos
### GET
#### To get all the todos
````
http://localhost:8080/api/toDo
````

#### To get a todo by id
````
http://localhost:8080/api/user/id
````

### PUT
#### To update a todo
````
http://localhost:8080/api/toDo
````
Be carefull, when updating a todo, you need to make sure that you have the same version as the version in the database. (The version will then be automatically incremented when you update the todo).

### POST
#### To add a todo
````
http://localhost:8080/api/toDo
````

### DELETE
#### To delete a todo by id
````
http://localhost:8080/api/toDo/id
````

## Example of user
````
 {
    "id": 11,
    "name": "Chap Graham",
    "username": "TheChapiChapo",
    "email": "double@april.biz",
    "phone": "1-777-736-8031 x56442",
    "website": "okddd.org"
  }
````

## Example of todo
````
 {  
    "userId": 1,
    "id": 3,
    "title": "fugiat veniam minus",
    "completed": false
    "version":0  
  }
````
