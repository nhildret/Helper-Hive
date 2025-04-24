FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# mvn clean package OR ./mvnw clean package
# java -jar target/capstone-0.0.1-SNAPSHOT.jar
# docker build -t capstone .
# docker tag capstone USER-NAME/helperhive
# docker push USER-NAME/helperhive