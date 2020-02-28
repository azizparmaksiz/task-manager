FROM openjdk:8-alpine
EXPOSE 9090
ADD target/task-manager.jar task-manager.jar
ENTRYPOINT ["java","-jar","/task-manager.jar"]
