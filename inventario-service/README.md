
📦 Inventario Service - Microservicio de Gestión de Inventario

Este microservicio forma parte del sistema de productos e inventario. Permite realizar operaciones sobre el inventario de productos como:

- Consultar stock por producto
- Actualizar la cantidad en inventario
- Registrar compras (validando producto y stock)

🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- JUnit 4 + Mockito
- Maven
- OpenAPI (Swagger 3)
- Lombok
- Log4j2
- PostgreSQL (opcional)
- Docker (opcional)

🗂️ Estructura del proyecto

inventario-service/
├── crosscutting/                 # DTOs, errores, utilidades comunes
├── modules/
│   ├── compra/                   # Lógica de compra
│   └── inventario/              # Lógica de inventario
├── infrastructure/              # Filtros y configuración
├── application/                 # Aplicación principal
├── resources/                   # application.yml, logs, etc.
└── test/                        # Pruebas unitarias con JUnit y Mockito

📌 Endpoints disponibles

🔄 Inventario

Método | Endpoint               | Descripción
-------|------------------------|---------------------------
GET    | /api/inventario/{id}   | Retorna la información del inventario y datos del producto correspondiente
PUT    | /api/inventario        | Actualiza o registra un nuevo producto en el inventario

🛒 Compra

Método | Endpoint      | Descripción
-------|---------------|----------------------------------------------
POST   | /api/compra   | Permite realizar la compra de un producto verificando inventario y retornando los detalles

🔐 Seguridad

- API Key requerida en todos los endpoints.
- Header requerido:
  X-API-KEY: LINK_TIC

🌐 Swagger

Puedes probar los endpoints desde Swagger en la siguiente URL:

http://localhost:8082/swagger-ui/index.html

🧪 Pruebas Unitarias

Incluye pruebas unitarias para:

- InventarioService
- CompraService
- InventarioController
- CompraController
- ResponseEntityUtil
- Filtro de API Key (ApiKeyFilter)

mvn clean test

> Las pruebas utilizan Mockito para simular dependencias como ProductoClient e interfaces de persistencia.

🚀 Cómo ejecutar el servicio localmente

1. Clona el repositorio

   git clone https://github.com/mangelmorenomoreno/inventario-service.git
   cd inventario-service

2. Ejecuta localmente

   mvn spring-boot:run

3. Accede a Swagger

   http://localhost:8082/swagger-ui/index.html

📌 Autor

- 👨‍💻 Miguel Ángel Moreno
- 📧 mangelmorenomoreno@gmail.com
