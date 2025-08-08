
📦 Inventario Service

Microservicio responsable de la gestión del inventario de productos. Se integra con el microservicio de productos para obtener información adicional como nombre, precio y descripción.

--------------------------------------------------

🚀 Funcionalidades principales

- Consultar la cantidad disponible de un producto por su ID.
- Actualizar la cantidad disponible de un producto.
- Crear un registro de inventario si no existe.
- Integración con microservicio de productos vía Feign Client.
- Control de errores personalizado y respuesta estandarizada.

--------------------------------------------------

🧩 Estructura del modelo

Entidad: Inventario

| Campo        | Tipo       | Descripción                                |
|--------------|------------|--------------------------------------------|
| id           | Long       | Identificador del registro de inventario   |
| idProducto   | Long       | ID del producto (único, FK lógica)         |
| cantidad     | Integer    | Cantidad disponible en inventario          |

--------------------------------------------------

📡 Endpoints

1. Consultar detalle de producto con inventario

GET /product/{id}

Respuesta:

{
"productoId": 2,
"nombre": "Producto 1",
"precio": 100,
"descripcion": "Producto 1 descripcion",
"cantidad": 15
}

2. Actualizar cantidad de producto

PUT /update

Body:

{
"productoId": 2,
"cantidad": 20
}

Respuesta:

{
"id": 3,
"idProducto": 2,
"cantidad": 20
}

--------------------------------------------------

🔐 Seguridad

Se utiliza autenticación mediante API Key en los encabezados (X-API-KEY) para consumir los endpoints del microservicio de productos.

--------------------------------------------------

🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- OpenFeign
- Lombok
- PostgreSQL (puede ajustarse)
- Log4j2

--------------------------------------------------

⚙️ Configuración (application.yml)

server:
port: 8082

productos:
service:
url: http://localhost:8081

security:
api-key: LINK_TIC

--------------------------------------------------

📁 Estructura de paquetes

com.linktic.inventario_service
├── api
├── crosscutting
│   ├── domain
│   ├── enums
│   ├── errors
│   ├── response
├── infrastructure
│   └── configuration
├── modules
│   └── inventario
│       ├── usecase
│       └── dataprovider

--------------------------------------------------

✍️ Autor

- Miguel Moreno – Ingeniero de Sistemas

--------------------------------------------------

🧪 Pruebas

Pruebas unitarias cubren escenarios como:

- Consulta exitosa de inventario y producto.
- Respuesta nula o error desde microservicio de productos.
- Actualización de inventario existente.
- Creación automática de inventario si no existe.
