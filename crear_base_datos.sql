-- Script para crear la base de datos de Peluquería Canina
-- Ejecutar este script en MySQL antes de ejecutar la aplicación

CREATE DATABASE IF NOT EXISTS peluqueria_canina;

USE peluqueria_canina;

-- Las tablas se crearán automáticamente por JPA con la configuración
-- eclipselink.ddl-generation=create-tables
-- Pero si prefieres crearlas manualmente, aquí están las estructuras:

-- Tabla duenio
CREATE TABLE IF NOT EXISTS duenio (
    id_duenio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cel_duenio VARCHAR(20) NOT NULL,
    direccion VARCHAR(200) NOT NULL
);

-- Tabla mascota
CREATE TABLE IF NOT EXISTS mascota (
    num_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    raza VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    alergico VARCHAR(10),
    atencion_especial VARCHAR(10),
    observaciones VARCHAR(500),
    id_duenio INT NOT NULL,
    FOREIGN KEY (id_duenio) REFERENCES duenio(id_duenio)
);


