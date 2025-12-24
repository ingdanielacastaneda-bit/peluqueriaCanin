# 📸 Instrucciones para Agregar las Imágenes

## Imágenes Requeridas

El proyecto necesita dos imágenes que deben colocarse en la carpeta:
```
src/main/resources/images/
```

### 1. Imagen Principal (Vista Principal)
- **Nombre del archivo:** `golden_retriever_peluqueria.png`
- **Descripción:** Golden retriever sonriente con herramientas de peluquería alrededor
- **Ubicación:** Se mostrará en la ventana principal (`Principal.java`)
- **Ubicación del archivo:** `src/main/resources/images/golden_retriever_peluqueria.png`

### 2. Imagen Cargar Datos
- **Nombre del archivo:** `gato_jugando_lana.png`
- **Descripción:** Gato naranja y blanco jugando con lana
- **Ubicación:** Se mostrará en la ventana de cargar datos (`CargarDatos.java`)
- **Ubicación del archivo:** `src/main/resources/images/gato_jugando_lana.png`

## Pasos para Agregar las Imágenes

### Opción 1: Desde el Explorador de Archivos

1. Abre el Explorador de Windows
2. Navega a: `C:\Users\Usuario\Documents\NetBeansProjects\peluqueriaCanina\src\main\resources\images\`
3. Copia las imágenes a esta carpeta con los nombres:
   - `golden_retriever_peluqueria.png`
   - `gato_jugando_lana.png`

### Opción 2: Desde NetBeans

1. En NetBeans, expande el proyecto
2. Ve a: `Source Packages` → `src/main/resources` → `images`
3. Clic derecho en la carpeta `images` → `Add Files...`
4. Selecciona las imágenes y renómbralas según los nombres indicados arriba

## Verificación

Después de agregar las imágenes:

1. **Recompila el proyecto:**
   ```bash
   mvn clean compile
   ```

2. **Ejecuta la aplicación:**
   - Deberías ver la imagen del golden retriever en la ventana principal
   - Deberías ver la imagen del gato en la ventana de cargar datos

## Nota sobre el Repositorio

Las imágenes **SÍ se subirán al repositorio** porque:
- La carpeta `src/main/resources/images/` NO está en `.gitignore`
- Las imágenes son parte de los recursos del proyecto
- Son necesarias para que la aplicación funcione correctamente

## Formatos Soportados

- ✅ PNG (recomendado)
- ✅ JPG/JPEG
- ✅ GIF

## Tamaño Recomendado

- **Imagen Principal:** 300-400px de ancho (se escalará automáticamente)
- **Imagen Cargar Datos:** 300-400px de ancho (se escalará automáticamente)

## Si las Imágenes No se Muestran

1. Verifica que los archivos estén en: `src/main/resources/images/`
2. Verifica que los nombres sean exactamente:
   - `golden_retriever_peluqueria.png`
   - `gato_jugando_lana.png`
3. Recompila el proyecto: `mvn clean compile`
4. Si aún no funcionan, revisa la consola para mensajes de error


