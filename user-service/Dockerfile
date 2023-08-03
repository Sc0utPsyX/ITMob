FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /users
COPY . /users/.
RUN mvn -f /users/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /users
COPY --from=builder /users/target/*.jar /users/*.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "/users/*.jar"]