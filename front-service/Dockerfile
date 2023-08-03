FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /front
COPY . /front/.
RUN mvn -f /front/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /front
COPY --from=builder /front/target/*.jar /front/*.jar
EXPOSE 8191
ENTRYPOINT ["java", "-jar", "/front/*.jar"]