
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

🧩 Endpoints disponibles

📌 Inventario

- GET /api/inventario/{id}  
  Retorna la información del inventario y datos del producto correspondiente.

- PUT /api/inventario  
  Actualiza o registra un nuevo producto en el inventario.

🛒 Compra

- POST /api/compra  
  Permite realizar una compra, validando existencia y cantidad en inventario. Disminuye el stock.

🧪 Pruebas Unitarias

Incluye pruebas unitarias para:

- InventarioService
- CompraService
- InventarioController
- CompraController
- ResponseEntityUtil
- Filtro de API Key (ApiKeyFilter)

mvn clean install

> Las pruebas utilizan Mockito para simular dependencias como ProductoClient e interfaces de persistencia.

🚀 Cómo ejecutar el servicio localmente

1. Clona el repositorio:

   git clone https://github.com/mangelmorenomoreno/inventario-service.git
   cd inventario-service

2. Ejecuta con Maven:

   mvn spring-boot:run

3. Accede a Swagger:

   http://localhost:8080/swagger-ui.html

🔐 Seguridad

- Autenticación por apiKey en los encabezados (configurable en Swagger).
- Filtro implementado en ApiKeyFilter.

📦 Dependencias clave

- ProductoClient: cliente REST que consulta el microservicio de productos.
- ResponseEntityUtil: utilidad para generar respuestas estándar.
- CustomException: excepción controlada para errores de negocio.

📌 Autor

- 👨‍💻 Miguel Ángel Moreno
- 📧 miguel.moreno@linktic.com
