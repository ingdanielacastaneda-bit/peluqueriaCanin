# Solución: Error de Base de Datos

## Problema
Los errores indican que la base de datos `peluqueria_canina` no existe. Por eso no se pueden guardar ni ver los datos.

## Solución Rápida

### Opción 1: Usando MySQL Workbench (Recomendado)

1. Abre **MySQL Workbench**
2. Conéctate a tu servidor MySQL (usuario: `root`, contraseña: `1234`)
3. Abre el archivo `crear_base_datos_simple.sql` que está en la raíz del proyecto
4. Ejecuta el script (botón ⚡ o F5)
5. Verifica que la base de datos se creó: deberías ver `peluqueria_canina` en la lista de bases de datos

### Opción 2: Desde la línea de comandos de MySQL

Si tienes MySQL en el PATH, ejecuta:

```bash
mysql -u root -p1234 -e "CREATE DATABASE IF NOT EXISTS peluqueria_canina;"
```

### Opción 3: Manualmente en MySQL Workbench

1. Abre MySQL Workbench
2. Conéctate al servidor
3. En la pestaña "Query", escribe:
   ```sql
   CREATE DATABASE IF NOT EXISTS peluqueria_canina;
   ```
4. Ejecuta la consulta (⚡ o F5)

## Verificación

Después de crear la base de datos, verifica que existe:

```sql
SHOW DATABASES LIKE 'peluqueria_canina';
```

Deberías ver `peluqueria_canina` en los resultados.

## Importante

- **Las tablas se crearán automáticamente** cuando ejecutes la aplicación por primera vez
- Esto es gracias a la configuración `eclipselink.ddl-generation=create-tables` en `persistence.xml`
- Solo necesitas crear la **base de datos**, no las tablas

## Configuración de Conexión

Si tu usuario o contraseña de MySQL son diferentes, edita:
`src/main/resources/META-INF/persistence.xml`

Y cambia:
- `javax.persistence.jdbc.user` (actualmente: `root`)
- `javax.persistence.jdbc.password` (actualmente: `1234`)

## Después de Crear la Base de Datos

1. Cierra la aplicación si está abierta
2. Recompila: `mvn clean compile`
3. Ejecuta la aplicación nuevamente
4. Ahora deberías poder guardar y ver datos sin problemas


