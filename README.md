# 📦 Sistema de Productos e Inventario

Este sistema está compuesto por dos microservicios desarrollados con Java y Spring Boot:

- **productos-service**: microservicio para la gestión de productos.
- **inventario-service**: microservicio para la gestión del inventario y procesamiento de compras.

La arquitectura sigue un enfoque basado en microservicios con comunicación REST entre servicios, base de datos independiente para cada uno, autenticación por API Key y despliegue mediante Docker Compose.

---

## 🧩 Microservicios

### 1️⃣ productos-service

Gestiona productos disponibles para compra:

- Crear nuevos productos
- Consultar productos por ID
- Listar productos

**Tecnologías:** Java 17, Spring Boot 3, PostgreSQL, Swagger, Docker

### 2️⃣ inventario-service

Administra el inventario de productos y permite registrar compras:

- Actualizar inventario de productos
- Consultar stock de producto
- Realizar compras (verificando stock y restando del inventario)

**Tecnologías:** Java 17, Spring Boot 3, PostgreSQL, Swagger, Docker

---

## ⚙️ Diagrama de Arquitectura

```
                                +---------------------+
                                |  Usuario/Cliente    |
                                +---------+-----------+
                                          |
                                          v
                    +---------------------+----------------------+
                    |                                            |
                    v                                            v
         +----------+-----------+                    +-----------+----------+
         |  productos-service   | <-- REST -->       | inventario-service   |
         +----------+-----------+                    +-----------+----------+
                    |                                            |
                    v                                            v
         +----------+-----------+                    +-----------+----------+
         | productos-db (Postgres) |                | inventario-db (Postgres) |
         +-----------------------+                +--------------------------+
```

---

## 🔐 Seguridad

Todos los endpoints están protegidos mediante autenticación por API Key.

- Encabezado: `X-API-KEY`
- Valor esperado: `LINK_TIC`

---

## 📄 Documentación Swagger

Cada microservicio expone su documentación OpenAPI mediante Swagger:

- **productos-service**: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **inventario-service**: [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

### Endpoints Clave

#### Productos

- `POST /api/productos` → Crear producto
- `GET /api/productos/{id}` → Consultar producto por ID
- `GET /api/productos` → Listar productos

#### Inventario

- `PUT /api/inventario` → Actualizar inventario
- `GET /api/inventario/{id}` → Consultar inventario con info de producto
- `POST /api/compra` → Realizar compra (valida inventario y actualiza stock)

---

## ✅ Pruebas

### 🧪 Pruebas Unitarias

Se incluyen pruebas unitarias con **JUnit 4** y **Mockito** para:

- Creación de productos (`ProductoService`, `ProductoController`)
- Gestión de inventario (`InventarioService`, `InventarioController`)
- Proceso de compra (`CompraService`, `CompraController`)
- Manejo de errores con `CustomException`
- Filtro de API Key `ApiKeyFilter`

### 🔄 Pruebas de Integración

Cada microservicio incluye al menos una prueba de integración:

- Comunicación entre servicios con `ProductoClient`
- Validación de proceso completo de compra

---

## 🧰 Configuración Local 

1. ejecutar con Maven:

```bash
cd productos-service && mvn spring-boot:run
cd inventario-service && mvn spring-boot:run
```

---

# 🚀 Despliegue de Microservicios con Docker Compose

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

```bash
cd productos-service
mvn clean package

cd ../inventario-service
mvn clean package
```

2. Regresa al directorio raíz y ejecuta:

```bash
docker-compose up --build
```

3. Los servicios quedarán disponibles en:

- **productos-service**: [http://localhost:8081](http://localhost:8081)
- **inventario-service**: [http://localhost:8082](http://localhost:8082)

## Variables de entorno

Ver archivo `.env` para personalizar credenciales de la base de datos y URL de conexión.

---

## 🧪 Colección Postman

También se incluye una colección Postman exportada (`productos_inventario.postman_collection.json`) con todos los endpoints, encabezado de seguridad y ejemplos de prueba.

---

## 🧠 Mejores Prácticas y Recomendaciones 

- Separación en capas: domain, application, infrastructure, crosscutting.
- Uso de ResponseEntityUtil para respuestas consistentes.
- Validaciones con DTOs.
- Pruebas con buena cobertura.
- Logs con Log4j2.
- Docker Compose para orquestación.
- Configuración externa (`.env`) para portabilidad.

---

## 📌 Autor

- 👨‍💻 Miguel Ángel Moreno
- 📧 [mangelmorenomoreno@gmail.com](mailto\:mangelmorenomoreno@gmail.com)


