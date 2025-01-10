# ToDoListJavaSpringBoot
web API of a To Do List done with Java, Spring boot, Spring data JDBC, PostgresSQL and docker

This is a back-end project.

A PostgresSQL database is used to stock the data and test the API.
The database can be used only with docker.

# To run the project
# If you use the dockerized version
Run the command :
mvnw clean package -DskipTests
Then run the command :
docker compose up to launch the project with docker

# If you use the java version
Run the command :
docker compose up 
Then run the command :
java -jar target/todolist-0.0.1-SNAPSHOT.jar to launch the project with java

# To Test the project
To test this project, you can use the file in the postman folder.
