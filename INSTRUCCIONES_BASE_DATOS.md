# Instrucciones para Crear la Base de Datos

## Problema
Error: `Unknown database 'peluqueria_canina'`

## Solución

### Opción 1: Ejecutar el script SQL (Recomendado)

1. Abre MySQL Workbench o el cliente MySQL que uses
2. Conéctate a tu servidor MySQL (usuario: root, contraseña: 1234 según tu configuración)
3. Ejecuta el script `crear_base_datos.sql` que está en la raíz del proyecto

O ejecuta manualmente estos comandos:

```sql
CREATE DATABASE IF NOT EXISTS peluqueria_canina;
USE peluqueria_canina;
```

### Opción 2: Crear manualmente desde la línea de comandos

```bash
mysql -u root -p1234 -e "CREATE DATABASE IF NOT EXISTS peluqueria_canina;"
```

### Verificación

Para verificar que la base de datos se creó correctamente:

```sql
SHOW DATABASES;
```

Deberías ver `peluqueria_canina` en la lista.

### Nota sobre las tablas

Las tablas (`duenio` y `mascota`) se crearán automáticamente la primera vez que ejecutes la aplicación gracias a la configuración `eclipselink.ddl-generation=create-tables` en `persistence.xml`.

Si prefieres crearlas manualmente, puedes ejecutar el resto del script SQL en `crear_base_datos.sql`.

### Configuración de conexión

Si tu usuario o contraseña de MySQL son diferentes, edita el archivo:
`src\main\resources\META-INF\persistence.xml`

Y cambia:
- `javax.persistence.jdbc.user` (actualmente: root)
- `javax.persistence.jdbc.password` (actualmente: 1234)


