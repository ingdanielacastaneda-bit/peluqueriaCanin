Sistema de Gestión de Peluquería Canina

Descripción
Sistema de gestión desarrollado en Java para administrar información de mascotas y sus dueños en una peluquería canina.
Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) mediante una interfaz gráfica en Java Swing, con persistencia en MySQL usando JPA.

El proyecto está orientado a demostrar arquitectura por capas, buenas prácticas básicas y comprensión de JPA sin frameworks como Spring.

Funcionalidades

Registrar mascotas y sus dueños

Visualizar registros en tabla

Modificar información existente

Eliminar registros

Validaciones básicas de campos obligatorios

Manejo de excepciones personalizadas


Arquitectura

El proyecto sigue una arquitectura por capas, separando responsabilidades de forma clara:

com.mycompany.peluqueriacanina/
├── model/        # Entidades JPA (Mascota, Duenio)
├── repository/   # Acceso a datos con JPA
├── service/      # Lógica de negocio y validaciones
├── exception/    # Excepciones del dominio
└── ui/           # Interfaz gráfica (Swing)


Capas

Modelo: Define las entidades y relaciones JPA. No contiene lógica.

Repositorio: Encapsula el acceso a datos y manejo de JPA.

Servicio: Aplica reglas de negocio y validaciones.

UI: Maneja la interacción con el usuario y eventos gráficos.

Excepción: Excepciones personalizadas del dominio.

Tecnologías Utilizadas

Java 21

Java Swing

JPA (EclipseLink)

MySQL 8

Maven

Requisitos

JDK 21 o superior

Maven 3.6+

MySQL 8+

IDE Java (NetBeans, IntelliJ, Eclipse)

Configuración de Base de Datos

Las credenciales se configuran en:

src/main/resources/database.properties


Ejemplo:

db.name=peluqueria_canina
db.url=jdbc:mysql://localhost:3306
db.user=root
db.password=1234
db.port=3306
db.timezone=UTC


NOTA: No se deben subir credenciales reales de producción al repositorio.

Las tablas se generan automáticamente al iniciar la aplicación mediante configuración JPA.

Compilación
mvn clean compile

Ejecución
Desde Maven
mvn exec:java -Dexec.mainClass="com.mycompany.peluqueriacanina.PeluqueriaCanina"

Desde IDE

Ejecutar la clase:

com.mycompany.peluqueriacanina.PeluqueriaCanina

Base de Datos
Tabla: duenio

id_duenio (PK)

nombre

cel_duenio
direccion
Tabla: mascota
num_cliente (PK)
nombre
raza
color
alergico
atencion_especial
observaciones
id_duenio (FK)


Validaciones

Nombre, raza y color de mascota obligatorios

Nombre, celular y dirección del dueño obligatorios


Limitaciones Conocidas

Relación Mascota–Dueño modelada como OneToOne por simplicidad didáctica

Sin inyección de dependencias (Spring/CDI)

Validaciones básicas sin validación de formato

Sin autenticación ni control de usuarios

Sin pruebas automatizadas

Estas decisiones son intencionales y acordes al alcance del proyecto.


Decisiones de Diseño

El proyecto prioriza:

Claridad de arquitectura

Separación de responsabilidades

Comprensión de JPA y transacciones manuales

Simplicidad sin sobreingeniería

En un entorno productivo se utilizarían frameworks como Spring Boot y herramientas adicionales.

Autor

Proyecto desarrollado con fines educativos y de portafolio.

Licencia

Uso educativo.
