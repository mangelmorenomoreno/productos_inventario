# 🛒 Componente Productos – Microservicio

Este microservicio se encarga de la gestión de productos, incluyendo creación, consulta individual y listado general. Está protegido mediante autenticación por API Key y documentado con Swagger.

---

## 📦 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- JPA + H2 / PostgreSQL
- Lombok
- Swagger (OpenAPI 3)
- JUnit 4 + Mockito

---

## 🚀 Endpoints expuestos

| Método | Ruta              | Descripción               | Seguridad |
|--------|-------------------|---------------------------|-----------|
| POST   | `/product`        | Crea un nuevo producto    | ✅ API Key |
| GET    | `/product/{id}`   | Consulta producto por ID  | ✅ API Key |
| GET    | `/product`        | Lista todos los productos | ✅ API Key |

---

## 🔐 Autenticación por API Key

Todos los endpoints bajo `/product/**` requieren una API Key enviada por encabezado HTTP:

```
X-API-KEY: LINK_TIC
```

> La clave se define en el archivo `application.yml` bajo la propiedad `security.api-key`.

---

## 📘 Documentación Swagger

Accede a la documentación interactiva desde:

```
http://localhost:8081/swagger-ui/index.html
```

En la parte superior derecha da clic en **Authorize**, e ingresa tu API Key.

---

## 🧪 Pruebas Unitarias

Incluye pruebas para:

- `ProductController`
- `ProductService`
- `ProductDataProvider`
- `ApiKeyFilter`
- `ResponseEntityUtil`

Ejecución:

```bash
mvn test
```

---

## ⚙️ Configuración

### application.yml (fragmento)
```yaml
server:
  port: 8081

security:
  api-key: LINK_TIC
```

---

## 🏗️ Estructura del paquete

```
com.linktic.productos_service
│
├── crosscutting                # Entidades, DTOs, proyecciones
├── infrastructure
│   └── configuration           # Filtro API Key y seguridad
├── modules.product
│   ├── api                     # Controlador REST
│   ├── dataproviders           # Implementación de acceso a datos
│   └── usecase                 # Lógica de negocio
└── utils                       # Utilitarios como ResponseEntityUtil
```

---

## 👤 Autor

**Miguel Ángel Moreno**  
Ingeniero de Sistemas   
📅 Fecha de creación: 07-08-2025

---