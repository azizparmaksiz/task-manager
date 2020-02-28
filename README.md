#Task Manager
Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

This project generate randomly scheduled task and send them to client over websocket session.

With rest api of this project you can:
- Receive all Task
- Delete a Task
- Update a Task
- Postpone Due Date of Task

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

#Build
Run `mvn clean install -DskipTests` to build jar. Build is hold under src/target folder.

## Running the application locally
Run `java -jar task-manager.jar` in same directory of jar file 
or  
Run `java -jar <relative-path-to-here>/task-manager.jar`
You may replace <relative-path-to-here> with your path to jar folder.

After running application, you can access rest api of this system from https://localhost:9090/swagger-ui.html

# Build Docker image
## Requirements
- [Docker](https://docs.docker.com/install)

Run `docker build -f Dockerfile -t docker-task-manager .` to build docker image.
Instead "docker-task-manager" you can give your own docker image name.

## Running Docker Image locally

Run `docker run -p 9090:9090 docker-task-manager`

## Run Docker image from docker hub

You can pull generated image of this project `docker pull azizparmaksiz/docker-task-manager`.

Run `docker run -p 9090:9090 azizparmaksiz/docker-task-manager`

# Test Project With Angular UI

From terminal run  `docker run -p 4200:80 azizparmaksiz/docker-task-manager-ui` and from `http://localhost:4200/` you see the
angular web application.

For more information please go to  [Task-manager-ui](https://github.com/azizparmaksiz/task-manager-ui).
