# Usa una imagen base oficial de OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia el wrapper de Maven y el POM
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# ✅ Da permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Descarga las dependencias offline
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente
COPY src ./src

# Compila la aplicación
RUN ./mvnw package -DskipTests

# Expone el puerto 8080
EXPOSE 8080

# Variables de entorno (opcional, para Render)
ENV DB_URL=your_db_url
ENV DB_USERNAME=your_db_username
ENV DB_PASSWORD=your_db_password

# Ejecuta el archivo JAR
ENTRYPOINT ["java","-jar","/app/target/ecommerce-template-0.0.1-SNAPSHOT.jar"]