# Despliegue de Microservicios con Docker Compose

Este proyecto contiene dos microservicios desarrollados en Spring Boot (`productos-service` e `inventario-service`) junto con una base de datos PostgreSQL, todo orquestado mediante `docker-compose`.

## Estructura del Proyecto

```
proyecto-root/
│
├── docker-compose.yml
├── .env
├── productos-service/
│   ├── Dockerfile
│   └── target/productos-service.jar
├── inventario-service/
    ├── Dockerfile
    └── target/inventario-service.jar
```

## Requisitos

- Docker
- Docker Compose
- JDK 17 (para compilar los JAR antes del despliegue)
- Maven

## Instrucciones de Uso

1. Compila los JAR de los dos servicios:

```
cd productos-service
mvn clean package

cd ../inventario-service
mvn clean package
```

2. Regresa al directorio raíz y ejecuta:

```
docker-compose up --build
```

3. Los servicios quedarán disponibles en:

- **productos-service**: http://localhost:8081
- **inventario-service**: http://localhost:8082

## Variables de entorno

Ver archivo `.env` para personalizar credenciales de la base de datos y URL de conexión.
