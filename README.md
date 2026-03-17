<div align="center">

#  LogiTrack S.A.
**Sistema de Gestión y Auditoría de Bodegas**

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

---

**Sistema backend centralizado en Spring Boot para controlar movimientos de inventario entre bodegas, registrar auditorías automáticas y proteger la información con autenticación JWT.**

**2026 — Proyecto Spring Boot + MySQL + JWT + Swagger**

</div>

---

##  Descripción General

**LogiTrack S.A.** es un sistema de gestión de bodegas que reemplaza el control manual en hojas de cálculo por una solución backend robusta y centralizada. Permite:

-  Gestión completa de bodegas y productos (CRUD)
-  Registro de movimientos: entradas, salidas y transferencias
-  Auditoría automática de todos los cambios realizados
-  Autenticación segura con Spring Security + JWT
-  Reportes avanzados con filtros por fecha, usuario y tipo
-  Documentación interactiva con Swagger/OpenAPI 3

---

##  Tecnologías Utilizadas

| Capa | Tecnología |
|------|------------|
| **Lenguaje** | Java 17+ |
| **Framework** | Spring Boot 3.x |
| **Seguridad** | Spring Security + JWT (jjwt) |
| **Persistencia** | Spring Data JPA + Hibernate |
| **Base de Datos** | MySQL 8.0 |
| **Documentación** | Swagger / OpenAPI 3 (springdoc) |
| **Validaciones** | Jakarta Bean Validation |
| **Frontend básico** | HTML + CSS + JavaScript (Vanilla) |

---

##  Creación de la Base de Datos

### 1. Requisitos previos

- MySQL 8.0 o superior instalado y corriendo
- Usuario con privilegios para crear bases de datos

### 2. Crear la base de datos manualmente

Conéctate a tu cliente MySQL (terminal, MySQL Workbench, DBeaver, etc.) y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS logitrack
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'logitrack_user'@'localhost' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON logitrack.* TO 'logitrack_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Ejecutar los scripts SQL incluidos

El proyecto incluye dos scripts en la carpeta `/src/main/resources/`:

```bash
# Opción A: Desde terminal MySQL
mysql -u root -p logitrack < src/main/resources/schema.sql
mysql -u root -p logitrack < src/main/resources/data.sql

# Opción B: Desde cliente MySQL Workbench
# File > Open SQL Script > seleccionar schema.sql > ejecutar
# File > Open SQL Script > seleccionar data.sql  > ejecutar
```

| Script | Descripción |
|--------|-------------|
| `schema.sql` | Crea todas las tablas con sus relaciones e índices |
| `data.sql` | Inserta datos de prueba: bodegas, productos, usuarios y movimientos |

### 4. Estructura de tablas generada

```
┌──────────┐        ┌────────────┐        ┌─────────────────┐
│  usuario │──────< │ movimiento │ >──────│ detalle_movim.  │
└──────────┘        └────────────┘        └─────────────────┘
                          │                        │
                    ┌─────┴──────┐           ┌─────┴──────┐
                    │   bodega   │           │  producto  │
                    └────────────┘           └────────────┘
                          │
                    ┌─────┴──────┐
                    │ auditoria  │
                    └────────────┘
```

### 5. Configurar conexión en `application.properties`

Edita el archivo `src/main/resources/application.properties` con tus credenciales:

```properties
# ── Base de Datos ──────────────────────────────────────────
spring.datasource.url=jdbc:mysql://localhost:3306/logitrack?useSSL=false&serverTimezone=UTC
spring.datasource.username=logitrack_user
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ── JPA / Hibernate ────────────────────────────────────────
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# ── Scripts SQL (ejecutar al iniciar si ddl-auto=create) ───
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql
spring.sql.init.data-locations=classpath:data.sql

# ── JWT ────────────────────────────────────────────────────
jwt.secret=clave_secreta_logitrack_2026_muy_larga_y_segura
jwt.expiration=86400000

# ── Puerto del servidor ────────────────────────────────────
server.port=8080
```

>  **Importante:** Cambia `spring.jpa.hibernate.ddl-auto` a `create` solo la primera vez para que Hibernate genere las tablas automáticamente. Luego cámbialo a `validate` o `none`.

---

##  Autenticación — Login desde el Frontend

El sistema incluye un frontend básico en `src/main/resources/static/` para probar el login y las consultas principales.

### Flujo de autenticación JWT

```
Usuario          Frontend (HTML/JS)        Backend Spring Boot
   │                    │                          │
   │── ingresa datos ──>│                          │
   │                    │── POST /auth/login ──────>│
   │                    │                          │── valida credenciales
   │                    │                          │── genera JWT token
   │                    │<── { token: "eyJ..." } ──│
   │                    │── guarda token (localStorage)
   │                    │                          │
   │                    │── GET /bodegas ───────────>│
   │                    │   Header: Bearer eyJ...   │── verifica token
   │                    │<── [ lista de bodegas ] ──│
```

### Endpoints de autenticación

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| `POST` | `/auth/register` | Registrar nuevo usuario | ❌ Pública |
| `POST` | `/auth/login` | Iniciar sesión, obtiene JWT | ❌ Pública |

#### Ejemplo: Registrar usuario

```json
POST /auth/register
Content-Type: application/json

{
  "nombre": "Carlos Rodríguez",
  "email": "carlos@logitrack.com",
  "password": "contraseña123",
  "rol": "EMPLEADO"
}
```

#### Ejemplo: Login

```json
POST /auth/login
Content-Type: application/json

{
  "email": "carlos@logitrack.com",
  "password": "contraseña123"
}
```

**Respuesta exitosa:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "email": "carlos@logitrack.com",
  "rol": "EMPLEADO"
}
```

### Usar el token en peticiones protegidas

Una vez obtenido el token, inclúyelo en el encabezado `Authorization` de cada petición:

```http
GET /bodegas
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Login desde el frontend HTML incluido

1. Abre el navegador en `http://localhost:8080`
2. Ingresa tu **email** y **contraseña** en el formulario de login
3. El sistema almacena el token automáticamente en `localStorage`
4. Puedes navegar por las secciones: Bodegas, Productos, Movimientos, Reportes
5. Para cerrar sesión, haz clic en **"Salir"** — el token se elimina del storage

>  **Usuarios de prueba incluidos en `data.sql`:**
>
> | Email | Contraseña | Rol |
> |-------|-----------|-----|
> | `admin@logitrack.com` | `admin123` | ADMIN |
> | `empleado@logitrack.com` | `emp123` | EMPLEADO |

---

##  Conexión y Uso de Swagger

LogiTrack expone su documentación interactiva mediante **Swagger UI / OpenAPI 3**, generada automáticamente por `springdoc-openapi`.

### Acceder a Swagger UI

Con la aplicación corriendo, abre en tu navegador:

```
http://localhost:8080/swagger-ui.html
```

O también puedes acceder al JSON de la especificación OpenAPI en:

```
http://localhost:8080/v3/api-docs
```

### Dependencia requerida (`pom.xml`)

Asegúrate de tener esta dependencia en tu `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

### Configuración de Swagger en `application.properties`

```properties
# ── Swagger / OpenAPI ──────────────────────────────────────
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true
springdoc.swagger-ui.filter=true
```

### Autenticarse en Swagger con JWT

Los endpoints protegidos requieren que envíes el token JWT. Sigue estos pasos dentro de Swagger UI:

**Paso 1 — Obtener el token**

1. Busca el endpoint `POST /auth/login` en la sección **auth-controller**
2. Haz clic en **"Try it out"**
3. Ingresa las credenciales en el cuerpo JSON:
```json
{
  "email": "admin@logitrack.com",
  "password": "admin123"
}
```
4. Haz clic en **"Execute"**
5. Copia el valor del campo `token` de la respuesta

**Paso 2 — Configurar el token en Swagger**

1. Haz clic en el botón  **"Authorize"** (arriba a la derecha en Swagger UI)
2. En el campo `bearerAuth (http, Bearer)`, escribe:
```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
3. Haz clic en **"Authorize"** y luego en **"Close"**
4. Ahora todos los endpoints protegidos incluirán el token automáticamente

**Paso 3 — Probar endpoints protegidos**

- Los endpoints con  ya estarán autorizados
- Haz clic en cualquier endpoint (ej. `GET /bodegas`), luego **"Try it out"** → **"Execute"**

### Ejemplo: Configuración OpenAPI en Java

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI logiTrackOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("LogiTrack API")
                .description("Sistema de Gestión y Auditoría de Bodegas")
                .version("1.0.0"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .name("bearerAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}
```

### Resumen de endpoints documentados en Swagger

| Módulo | Métodos disponibles |
|--------|---------------------|
| **Auth** | `POST /auth/login`, `POST /auth/register` |
| **Bodegas** | `GET`, `POST`, `PUT`, `DELETE /bodegas/{id}` |
| **Productos** | `GET`, `POST`, `PUT`, `DELETE /productos/{id}` |
| **Movimientos** | `GET`, `POST /movimientos`, filtros por fecha |
| **Auditoría** | `GET /auditorias`, filtros por usuario/operación |
| **Reportes** | `GET /reportes/stock-bajo`, `GET /reportes/resumen` |

---

##  Instalación y Ejecución

```bash
# 1. Clonar el repositorio
git clone https://github.com/usuario/LogiTrack.git
cd LogiTrack

# 2. Crear la base de datos (ver sección anterior)
mysql -u root -p < src/main/resources/schema.sql
mysql -u root -p logitrack < src/main/resources/data.sql

# 3. Configurar credenciales en application.properties

# 4. Compilar y ejecutar con Maven
./mvnw spring-boot:run

# O compilar el JAR y ejecutarlo
./mvnw clean package
java -jar target/logitrack-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en `http://localhost:8080`

---

##  Estructura del Proyecto

```
 LogiTrack/
├──  src/main/java/com/logitrack/
│   ├──  config/
│   │   ├── SecurityConfig.java       # Spring Security + JWT filter
│   │   └── SwaggerConfig.java        # Configuración OpenAPI 3
│   ├──  controller/
│   │   ├── AuthController.java       # /auth/login y /auth/register
│   │   ├── BodegaController.java     # CRUD bodegas
│   │   ├── ProductoController.java   # CRUD productos
│   │   ├── MovimientoController.java # Movimientos de inventario
│   │   ├── AuditoriaController.java  # Consulta de auditorías
│   │   └── ReporteController.java    # Reportes y estadísticas
│   ├──  model/
│   │   ├── Usuario.java
│   │   ├── Bodega.java
│   │   ├── Producto.java
│   │   ├── Movimiento.java
│   │   ├── DetalleMovimiento.java
│   │   └── Auditoria.java
│   ├──  repository/
│   │   └── [*Repository.java]        # Interfaces JPA Repository
│   ├──  service/
│   │   └── [*Service.java]           # Lógica de negocio
│   ├──  security/
│   │   ├── JwtUtil.java              # Generación y validación JWT
│   │   └── JwtAuthFilter.java        # Filtro de autenticación
│   ├──  exception/
│   │   ├── GlobalExceptionHandler.java  # @ControllerAdvice
│   │   └── [excepciones personalizadas]
│   └──  audit/
│       └── AuditListener.java        # EntityListener JPA
│
├──  src/main/resources/
│   ├── application.properties        # Configuración principal
│   ├── schema.sql                    # Creación de tablas
│   ├── data.sql                      # Datos de prueba
│   └──  static/                    # Frontend HTML/CSS/JS
│       ├── index.html                # Login
│       ├── dashboard.html            # Panel principal
│       └── js/app.js                 # Lógica JS con fetch API
│
└── pom.xml
```

---

##  Roles y Permisos

| Endpoint | ADMIN | EMPLEADO |
|----------|-------|----------|
| `GET /bodegas` | ✅ | ✅ |
| `POST /bodegas` | ✅ | ❌ |
| `DELETE /bodegas` | ✅ | ❌ |
| `GET /movimientos` | ✅ | ✅ |
| `POST /movimientos` | ✅ | ✅ |
| `GET /auditorias` | ✅ | ❌ |
| `GET /reportes/**` | ✅ | ✅ |

---

##  Manejo de Errores

El sistema responde con JSON estructurado para todos los errores:

```json
{
  "timestamp": "2026-03-16T10:30:00",
  "status": 404,
  "error": "Not Found",
  "mensaje": "Bodega con ID 99 no encontrada",
  "path": "/bodegas/99"
}
```

| Código | Situación |
|--------|-----------|
| `400` | Datos de entrada inválidos |
| `401` | Token JWT ausente o expirado |
| `403` | Sin permisos para el recurso |
| `404` | Recurso no encontrado |
| `500` | Error interno del servidor |

---

<div align="center">

**Desarrollado con dedicación para LogiTrack S.A. • 2026**

[![Spring Boot](https://img.shields.io/badge/Powered_by-Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/Database-MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com)

</div>
