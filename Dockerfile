# Estágio 1: Build da aplicação com Maven
FROM ubuntu:latest AS build
LABEL authors="Gustavo"

RUN apt-get update && apt-get install -y openjdk-25-jdk maven

WORKDIR /app
COPY . .

# Gera o .jar pulando testes se quiser agilizar o build no Render (opcional: remova -DskipTests se quiser rodar testes)
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final de execução
FROM ubuntu:latest
WORKDIR /app

RUN apt-get update && apt-get install -y openjdk-25-jdk && rm -rf /var/lib/apt/lists/*

# Copia o .jar gerado do estágio 'build' para a imagem final
COPY --from=build /app/target/elovital-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]