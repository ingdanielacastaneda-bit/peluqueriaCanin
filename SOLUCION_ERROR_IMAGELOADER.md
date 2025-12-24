# Solución: Error "ImageLoader cannot be resolved"

## Problema
NetBeans está ejecutando código compilado antiguo que no incluye la clase `ImageLoader`.

## Solución

### Opción 1: Ejecutar desde Maven (Recomendado)
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.peluqueriacanina.PeluqueriaCanina"
```

### Opción 2: Limpiar y Reconstruir en NetBeans
1. En NetBeans, haz clic derecho en el proyecto
2. Selecciona **"Clean and Build"** (Limpiar y Construir)
3. Espera a que termine la compilación
4. Ejecuta nuevamente

### Opción 3: Limpiar el proyecto manualmente
1. Cierra NetBeans
2. Elimina la carpeta `target` del proyecto
3. Abre NetBeans nuevamente
4. Haz clic derecho en el proyecto → **"Build"** (Construir)
5. Ejecuta la aplicación

## Verificación
Después de limpiar y reconstruir, verifica que `ImageLoader.class` existe en:
`target/classes/com/mycompany/peluqueriacanina/ui/ImageLoader.class`

## Nota
La clase `ImageLoader` está en el mismo paquete que `Principal`, por lo que no necesita import explícito. El problema es solo de compilación/classpath.


