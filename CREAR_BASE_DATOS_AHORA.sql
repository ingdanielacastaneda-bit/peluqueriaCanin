-- ============================================
-- SCRIPT PARA CREAR LA BASE DE DATOS
-- ============================================
-- INSTRUCCIONES:
-- 1. Abre MySQL Workbench
-- 2. Conéctate al servidor (usuario: root, contraseña: 1234)
-- 3. Copia y pega este script completo
-- 4. Ejecuta el script (botón ⚡ o F5)
-- ============================================

CREATE DATABASE IF NOT EXISTS peluqueria_canina;

USE peluqueria_canina;

-- Verificar que se creó correctamente
SELECT 'Base de datos peluqueria_canina creada exitosamente' AS Resultado;

-- Las tablas se crearán automáticamente cuando ejecutes la aplicación
-- gracias a la configuración: eclipselink.ddl-generation=create-tables


