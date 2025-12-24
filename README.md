# Sistema de Gestión de Peluquería Canina

## Descripción

Sistema de gestión desarrollado en Java para administrar información de mascotas y sus dueños en una peluquería canina. La aplicación permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los registros de mascotas y dueños mediante una interfaz gráfica de usuario (GUI) desarrollada con Java Swing.

## Propósito

Este sistema facilita la gestión administrativa de una peluquería canina, permitiendo:
- Registrar nuevas mascotas con sus datos y la información de contacto de sus dueños
- Visualizar todos los registros de mascotas en una tabla
- Modificar datos existentes de mascotas y dueños
- Eliminar registros de mascotas del sistema

## Arquitectura

El proyecto sigue una arquitectura por capas que separa claramente las responsabilidades, priorizando la claridad conceptual y las buenas prácticas para un proyecto Java de portafolio junior–semi profesional.

```
com.mycompany.peluqueriacanina/
├── model/              # Entidades JPA (Duenio, Mascota)
├── repository/         # Capa de acceso a datos (maneja JPA directamente)
├── service/            # Lógica de negocio y validaciones
├── exception/          # Excepciones del dominio
└── ui/                 # Interfaz de usuario (Principal, CargarDatos, VerDatos, ModificarDatos)
```

### Capas del Sistema

#### 1. **Model (Entidades JPA)**
- `Duenio`: Representa al dueño de una mascota
- `Mascota`: Representa una mascota con relación OneToOne con Duenio
- **Responsabilidad**: Solo contienen la estructura de datos y anotaciones JPA. Sin lógica de negocio.

#### 2. **Repository**
- `DuenioRepository`: Maneja el acceso a datos de dueños
- `MascotaRepository`: Maneja el acceso a datos de mascotas
- **Responsabilidad**: 
  - Encapsula todas las operaciones JPA mediante EntityManager
  - Gestiona transacciones y EntityManager
  - Traduce excepciones de JPA a excepciones de dominio
  - Proporciona una interfaz limpia sin exponer detalles de JPA

#### 3. **Service**
- `MascotaService`: Contiene la lógica de negocio relacionada con mascotas
- **Responsabilidad**:
  - Aplica validaciones de dominio
  - Coordina operaciones entre entidades relacionadas
  - NO conoce detalles de JPA ni EntityManager
  - Solo interactúa con los repositories

#### 4. **UI (Interfaz de Usuario)**
- `Principal`: Ventana principal con menú de opciones
- `CargarDatos`: Formulario para registrar nuevas mascotas
- `VerDatos`: Visualización de todas las mascotas en tabla
- `ModificarDatos`: Formulario para editar datos existentes
- **Responsabilidad**:
  - Orquestación de interacción con el usuario
  - SOLO interactúa con la capa service
  - Maneja la presentación y eventos de usuario

#### 5. **Exception**
- `EntityNotFoundException`: Cuando se intenta acceder a una entidad que no existe
- `PersistenceException`: Para errores genéricos de persistencia
- `EntityAlreadyExistsException`: Cuando se intenta crear una entidad duplicada

### Principios de Diseño Aplicados

- **Separación de Responsabilidades (SRP)**: Cada capa tiene una única razón de cambio
- **Inversión de Dependencias**: Las capas superiores dependen de abstracciones (repositories), no de implementaciones concretas
- **Encapsulación**: Los detalles de JPA están encapsulados en la capa repository
- **Simplicidad**: Se evita la sobre-ingeniería, priorizando claridad y mantenibilidad

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación
- **Java Swing**: Framework para interfaz gráfica
- **JPA (Java Persistence API)**: Para persistencia de datos
- **EclipseLink**: Implementación de JPA
- **MySQL**: Base de datos relacional
- **Maven**: Gestión de dependencias y construcción del proyecto

## Requisitos Previos

- Java Development Kit (JDK) 21 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- IDE compatible con Java (NetBeans, IntelliJ IDEA, Eclipse)

## Configuración de la Base de Datos

### Configuración de Credenciales

Las credenciales de base de datos se configuran en el archivo `src/main/resources/database.properties`:

```properties
db.name=peluqueria_canina
db.url=jdbc:mysql://localhost:3306
db.user=root
db.password=1234
db.port=3306
db.timezone=UTC
```

**IMPORTANTE**: 
- Modifica `database.properties` con tus credenciales de MySQL antes de ejecutar la aplicación.
- Las credenciales en `persistence.xml` deben coincidir con las de `database.properties`.
- **NO commitees** el archivo `database.properties` con credenciales de producción al repositorio.

### Inicialización Automática

El sistema crea automáticamente la base de datos si no existe al iniciar la aplicación. Las tablas se crean automáticamente mediante `eclipselink.ddl-generation=create-tables` configurado en `persistence.xml`.

## Compilación

Para compilar el proyecto, ejecute en la raíz del proyecto:

```bash
mvn clean compile
```

## Ejecución

### Desde Maven

```bash
mvn exec:java -Dexec.mainClass="com.mycompany.peluqueriacanina.PeluqueriaCanina"
```

### Desde el IDE

Ejecutar la clase `com.mycompany.peluqueriacanina.PeluqueriaCanina` que contiene el método `main`.

### Generar JAR ejecutable

```bash
mvn clean package
```

El JAR se generará en `target/peluqueriaCanina-1.0-SNAPSHOT.jar` y puede ejecutarse con:

```bash
java -jar target/peluqueriaCanina-1.0-SNAPSHOT.jar
```

## Estructura de la Base de Datos

### Tabla: duenio
- `id_duenio` (PK, auto-incremental mediante IDENTITY)
- `nombre` (VARCHAR, NOT NULL)
- `cel_duenio` (VARCHAR, NOT NULL)
- `direccion` (VARCHAR, NOT NULL)

### Tabla: mascota
- `num_cliente` (PK, auto-incremental mediante IDENTITY)
- `nombre` (VARCHAR, NOT NULL)
- `raza` (VARCHAR, NOT NULL)
- `color` (VARCHAR, NOT NULL)
- `alergico` (VARCHAR)
- `atencion_especial` (VARCHAR)
- `observaciones` (VARCHAR)
- `id_duenio` (FK, NOT NULL)

## Validaciones de Dominio

El sistema valida los siguientes campos obligatorios:

**Mascota:**
- Nombre (no puede estar vacío)
- Raza (no puede estar vacío)
- Color (no puede estar vacío)

**Dueño:**
- Nombre (no puede estar vacío)
- Celular (no puede estar vacío)
- Dirección (no puede estar vacío)

## Manejo de Excepciones

El sistema utiliza excepciones personalizadas:

- `EntityNotFoundException`: Cuando se intenta acceder a una entidad que no existe
- `PersistenceException`: Para errores genéricos de persistencia
- `EntityAlreadyExistsException`: Cuando se intenta crear una entidad duplicada

Las excepciones se propagan desde la capa de repository hasta la UI, donde se muestran mensajes de error al usuario.

## Limitaciones del Sistema

Esta arquitectura está diseñada para un proyecto de portafolio junior–semi profesional. Las siguientes limitaciones son intencionales y reflejan decisiones de diseño apropiadas para el alcance del proyecto:

### Limitaciones Técnicas

1. **Gestión de EntityManagerFactory**: Cada repository crea su propia instancia de EntityManagerFactory. En un sistema grande, esto se gestionaría mediante inyección de dependencias o un patrón Singleton.

2. **Transacciones**: Las transacciones se manejan a nivel de operación individual. No hay soporte para transacciones distribuidas o transacciones que abarquen múltiples repositories.

3. **Relación OneToOne**: Cada mascota tiene un único dueño y cada dueño puede tener una única mascota. Esta es una limitación del modelo de dominio actual.

4. **Validaciones**: Las validaciones son básicas y no incluyen validación de formato (ej: formato de teléfono, email).

5. **Seguridad**: No incluye autenticación ni autorización de usuarios.

6. **Backup**: No incluye funcionalidad de respaldo automático de datos.

7. **Caché**: No hay implementación de caché de segundo nivel de JPA.

8. **Pool de Conexiones**: La configuración de pool de conexiones es básica.

### Limitaciones de Arquitectura

1. **Sin Inyección de Dependencias**: Los servicios y repositories se instancian directamente. En un sistema grande se usaría un framework como Spring o CDI.

2. **Sin Capa de DTOs**: Se trabajan directamente con entidades JPA. En sistemas grandes se usarían DTOs para separar el modelo de persistencia del modelo de negocio.

3. **Sin Interfaces de Repository**: Los repositories son clases concretas. En sistemas grandes se usarían interfaces para facilitar testing y mockeo.

4. **Sin Capa de Validación Separada**: Las validaciones están en el service. En sistemas grandes se usaría Bean Validation (JSR-303) o una capa dedicada.

## ¿Qué se haría distinto en un sistema grande?

### Arquitectura

1. **Inyección de Dependencias**: Usar Spring Framework o Jakarta CDI para gestionar el ciclo de vida de los objetos y las dependencias.

2. **Interfaces de Repository**: Definir interfaces para los repositories, facilitando testing y permitiendo múltiples implementaciones.

3. **Capa de DTOs**: Separar el modelo de persistencia (entidades JPA) del modelo de negocio (DTOs), permitiendo versionado de APIs y optimización de consultas.

4. **Servicios Transaccionales**: Usar `@Transactional` de Spring o EJB para gestionar transacciones declarativamente.

5. **Bean Validation**: Usar JSR-303 (Bean Validation) para validaciones declarativas en lugar de validaciones manuales.

6. **Capa de Mapeo**: Usar MapStruct o ModelMapper para convertir entre entidades y DTOs.

### Persistencia

1. **EntityManagerFactory Único**: Gestionar una única instancia de EntityManagerFactory mediante un Singleton o contenedor de Spring.

2. **Caché de Segundo Nivel**: Configurar caché de segundo nivel de JPA para mejorar el rendimiento.

3. **Consultas Optimizadas**: Usar consultas JPQL o nativas optimizadas, lazy loading estratégico, y proyecciones para reducir el consumo de memoria.

4. **Paginación**: Implementar paginación real en lugar de cargar todos los registros en memoria.

5. **Auditoría**: Implementar auditoría automática de entidades (quién y cuándo modificó los datos).

### Seguridad

1. **Autenticación y Autorización**: Implementar Spring Security o Jakarta Security para gestionar usuarios y roles.

2. **Validación de Entrada**: Validar y sanitizar todas las entradas del usuario para prevenir inyecciones SQL y XSS.

3. **Encriptación**: Encriptar datos sensibles como contraseñas y información personal.

### Testing

1. **Tests Unitarios**: Escribir tests unitarios para cada capa usando JUnit y Mockito.

2. **Tests de Integración**: Implementar tests de integración para validar el flujo completo.

3. **Tests de Repositorio**: Usar Testcontainers o H2 en memoria para tests de repositorio.

### Operaciones

1. **Logging Estructurado**: Usar SLF4J con Logback o Log4j2 para logging estructurado.

2. **Manejo de Errores Centralizado**: Implementar un manejador global de excepciones.

3. **Monitoreo**: Integrar herramientas de monitoreo como Prometheus o Micrometer.

4. **Documentación de API**: Si se expone una API REST, usar Swagger/OpenAPI para documentación automática.

### Escalabilidad

1. **Arquitectura de Microservicios**: Para sistemas muy grandes, considerar dividir en microservicios.

2. **Base de Datos Distribuida**: Usar sharding o replicación para bases de datos distribuidas.

3. **Cola de Mensajes**: Implementar colas de mensajes (RabbitMQ, Kafka) para procesamiento asíncrono.

## Correcciones Críticas Implementadas

Este proyecto ha sido auditado y corregido para resolver problemas críticos de diseño y seguridad que lo hacían indefendible como portafolio profesional. Las siguientes correcciones fueron implementadas:

### 1. Proveedor Único de EntityManagerFactory

**Problema Resuelto**: Cada repository creaba su propia instancia de `EntityManagerFactory`, causando:
- Múltiples pools de conexiones innecesarios
- Mayor consumo de recursos
- Riesgo de agotar conexiones de BD
- Dificultad para gestionar transacciones

**Solución Implementada**: 
- Se creó `EntityManagerFactoryProvider` como singleton thread-safe
- Todos los repositories ahora comparten una única instancia
- Se agregó un shutdown hook para cerrar correctamente el `EntityManagerFactory` al finalizar la aplicación

**Archivo**: `src/main/java/com/mycompany/peluqueriacanina/util/EntityManagerFactoryProvider.java`

### 2. Eliminación de Thread.sleep()

**Problema Resuelto**: Uso de `Thread.sleep()` en constructores para "esperar" que la base de datos esté lista:
- Anti-patrón de diseño
- Bloquea hilos innecesariamente
- No garantiza que la BD esté lista
- Indica problemas de sincronización

**Solución Implementada**:
- Se eliminaron todos los `Thread.sleep()` del código
- Se reemplazó por verificación real de disponibilidad mediante consulta SQL (`SELECT 1`)
- La sincronización se maneja correctamente mediante double-check locking

### 3. Externalización de Credenciales

**Problema Resuelto**: Credenciales de base de datos hardcodeadas en el código fuente:
- Violación de seguridad básica
- Imposible usar diferentes entornos sin modificar código
- Riesgo de commit accidental de credenciales

**Solución Implementada**:
- Se creó `src/main/resources/database.properties` para almacenar credenciales
- `DatabaseInitializer` ahora lee las credenciales desde el archivo properties
- Se documentó que `persistence.xml` debe coincidir con `database.properties`
- Se agregó advertencia de no commiteear credenciales de producción

**Archivo**: `src/main/resources/database.properties`

### 4. Gestión Correcta del Ciclo de Vida

**Problema Resuelto**: `EntityManagerFactory` nunca se cerraba, causando:
- Fugas de recursos
- Conexiones no liberadas al cerrar la aplicación

**Solución Implementada**:
- Se agregó shutdown hook en `PeluqueriaCanina.main()` para cerrar el `EntityManagerFactory`
- Se documentó que los métodos `close()` en repositories están deprecados porque el `EntityManagerFactory` es compartido

## Limitaciones que Permanecen Intencionalmente

Las siguientes limitaciones son intencionales y apropiadas para un proyecto de portafolio junior–semi profesional:

1. **Transacciones no atómicas en operaciones complejas**: `crearMascota()` guarda `Duenio` y `Mascota` en transacciones separadas. En un sistema grande se usaría una transacción única o un patrón Unit of Work.

2. **Modelo de dominio OneToOne**: La relación entre `Mascota` y `Duenio` es `OneToOne`, cuando conceptualmente debería ser `ManyToOne`. Se mantiene para no cambiar el modelo de datos existente.

3. **Sin interfaces de Repository**: Los repositories son clases concretas. En sistemas grandes se usarían interfaces para facilitar testing y mockeo.

4. **Validaciones básicas**: No incluyen validación de formato (ej: formato de teléfono, email). Se mantiene simple para el alcance del proyecto.

5. **Sin inyección de dependencias**: Los servicios y repositories se instancian directamente. En sistemas grandes se usaría Spring o CDI.

Estas limitaciones están documentadas y son aceptables para un proyecto de portafolio que demuestra comprensión de arquitectura por capas y JPA sin sobre-ingeniería.

## Decisiones de Diseño

### ¿Por qué esta arquitectura?

Esta arquitectura fue elegida porque:

1. **Claridad**: Cada capa tiene una responsabilidad clara y bien definida, facilitando la comprensión del código.

2. **Mantenibilidad**: Los cambios en una capa no afectan directamente a las otras, facilitando el mantenimiento.

3. **Testabilidad**: Aunque no se implementaron tests en este proyecto, la arquitectura facilita la escritura de tests unitarios.

4. **Simplicidad**: Se evita la sobre-ingeniería, priorizando soluciones simples y directas.

5. **Aprendizaje**: Es una arquitectura que permite entender los conceptos fundamentales antes de pasar a frameworks más complejos.

### ¿Por qué no usar Spring u otros frameworks?

Para un proyecto de portafolio junior–semi profesional, es importante demostrar comprensión de los conceptos fundamentales antes de usar frameworks que abstraen muchos detalles. Esta arquitectura permite:

- Entender cómo funciona JPA a nivel básico
- Comprender la gestión de transacciones manual
- Aprender sobre separación de responsabilidades sin depender de anotaciones mágicas

En un entorno profesional, definitivamente se usarían frameworks como Spring Boot, pero para aprendizaje y demostración de fundamentos, esta aproximación es más educativa.

## Autor

Desarrollado como proyecto educativo de gestión empresarial.

## Licencia

Este proyecto es de uso educativo.
