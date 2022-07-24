FROM openjdk:8
EXPOSE 80
ADD target/sample-1.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar"]