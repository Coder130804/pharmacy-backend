FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 👇 ADD THIS LINE (fix permission issue)
RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/pharmacy-billing-0.0.1-SNAPSHOT.jar"]