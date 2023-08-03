FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /event
COPY . /event/.
RUN mvn -f /event/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /event
COPY --from=builder /event/target/*.jar /event/*.jar
EXPOSE 8907
ENTRYPOINT ["java", "-jar", "/event/*.jar"]