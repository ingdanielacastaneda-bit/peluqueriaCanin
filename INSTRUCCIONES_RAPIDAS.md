# ⚠️ SOLUCIÓN RÁPIDA - Error de Base de Datos

## El Problema
La aplicación no puede guardar ni ver datos porque la base de datos `peluqueria_canina` **NO EXISTE**.

## Solución en 3 Pasos

### Paso 1: Abre MySQL Workbench
- Busca "MySQL Workbench" en el menú de inicio
- Ábrelo

### Paso 2: Conéctate al Servidor
- Usuario: `root`
- Contraseña: `1234`
- Haz clic en "OK" o "Connect"

### Paso 3: Ejecuta este Comando SQL

**Copia y pega esto en la pestaña de consultas:**

```sql
CREATE DATABASE IF NOT EXISTS peluqueria_canina;
```

**Luego presiona:**
- Botón ⚡ (rayo) en la barra de herramientas, O
- `F5`, O
- Menú: Query → Execute (All) or Execute (Selection)

### Verificación
Después de ejecutar, deberías ver un mensaje de éxito. Verifica que la base de datos existe:

```sql
SHOW DATABASES;
```

Deberías ver `peluqueria_canina` en la lista.

## Después de Crear la Base de Datos

1. **Cierra la aplicación** si está abierta
2. **Vuelve a ejecutarla**
3. Ahora deberías poder:
   - ✅ Guardar datos (CARGAR DATOS)
   - ✅ Ver datos (VER DATOS)
   - ✅ Editar datos
   - ✅ Eliminar datos

## Nota Importante

- **Las tablas se crean automáticamente** la primera vez que ejecutes la aplicación
- Solo necesitas crear la **base de datos**, no las tablas manualmente
- Si tu contraseña de MySQL no es `1234`, edita el archivo:
  `src/main/resources/META-INF/persistence.xml`

## Archivo SQL Listo para Usar

También puedes abrir el archivo `CREAR_BASE_DATOS_AHORA.sql` que está en la raíz del proyecto y ejecutarlo directamente en MySQL Workbench.


