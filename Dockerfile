FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app-pedido
COPY . /app-pedido
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app-pedido
COPY --from=build /app-pedido/target/postech-tc4-pedido-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "app.jar"]