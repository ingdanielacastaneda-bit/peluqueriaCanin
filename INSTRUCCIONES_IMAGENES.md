# Instrucciones para Configurar las Imágenes

## Problema
Las imágenes no se ven porque están referenciadas con rutas absolutas que no funcionan cuando se ejecuta la aplicación.

## Solución

### Paso 1: Copiar las imágenes a la carpeta de recursos

Copia las siguientes imágenes desde `C:\Users\Usuario\Downloads\` a la carpeta `src\main\resources\images\` del proyecto:

1. `LOGO_PELUQUERIA-removebg-preview.png` → `src\main\resources\images\LOGO_PELUQUERIA-removebg-preview.png`
2. `logo_de_un_gato_amigable_feliz_jugando-removebg-preview.png` → `src\main\resources\images\logo_de_un_gato_amigable_feliz_jugando-removebg-preview.png`
3. `icono editar.png` → `src\main\resources\images\icono editar.png`
4. `icono eliminar.png` → `src\main\resources\images\icono eliminar.png`

### Paso 2: Verificar la estructura

La estructura de carpetas debe quedar así:

```
src/
  main/
    resources/
      images/
        LOGO_PELUQUERIA-removebg-preview.png
        logo_de_un_gato_amigable_feliz_jugando-removebg-preview.png
        icono editar.png
        icono eliminar.png
      META-INF/
        persistence.xml
```

### Paso 3: Recompilar

Después de copiar las imágenes, recompila el proyecto:

```bash
mvn clean compile
```

### Nota
Si alguna imagen no existe o tiene un nombre diferente, la aplicación funcionará normalmente pero sin mostrar esa imagen específica.


