FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /gateway
COPY . /gateway/.
RUN mvn -f /gateway/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /gateway
COPY --from=builder /gateway/target/*.jar /gateway/*.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/gateway/*.jar"]