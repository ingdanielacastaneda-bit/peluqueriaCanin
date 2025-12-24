# 📸 Cómo Agregar las Imágenes al Proyecto

## ⚠️ IMPORTANTE: Las imágenes aún no están en el proyecto

Necesitas agregar las imágenes manualmente. Sigue estos pasos:

## Paso 1: Ubicación de las Imágenes

Las imágenes deben estar en esta carpeta:
```
src/main/resources/images/
```

## Paso 2: Nombres de los Archivos

Debes guardar las imágenes con estos nombres **EXACTOS**:

1. **`golden_retriever_peluqueria.png`**
   - Imagen del golden retriever con herramientas de peluquería
   - Se mostrará en la ventana principal

2. **`gato_jugando_lana.png`**
   - Imagen del gato naranja jugando con lana
   - Se mostrará en la ventana de cargar datos

## Paso 3: Cómo Agregar las Imágenes

### Opción A: Desde el Explorador de Windows

1. Abre el Explorador de Windows
2. Navega a: `C:\Users\Usuario\Documents\NetBeansProjects\peluqueriaCanina\src\main\resources\images\`
3. Copia y pega las imágenes en esa carpeta
4. Asegúrate de que los nombres sean exactamente:
   - `golden_retriever_peluqueria.png`
   - `gato_jugando_lana.png`

### Opción B: Desde NetBeans

1. En NetBeans, en el panel de proyectos, expande:
   - `Source Packages` → `src/main/resources` → `images`
2. Clic derecho en la carpeta `images` → `Add Files...`
3. Selecciona las imágenes
4. Renómbralas según los nombres indicados arriba

## Paso 4: Verificar que se Copiaron

Después de agregar las imágenes, verifica que estén en la carpeta:

```bash
# Desde PowerShell en la raíz del proyecto:
Get-ChildItem src\main\resources\images
```

Deberías ver:
- `golden_retriever_peluqueria.png`
- `gato_jugando_lana.png`
- `LEEME_IMAGENES.txt`

## Paso 5: Recompilar y Ejecutar

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.mycompany.peluqueriacanina.PeluqueriaCanina"
```

## Verificación en la Consola

Cuando ejecutes la aplicación, deberías ver en la consola:

```
Cargando imagen para vista principal...
✓ Imagen cargada: /images/golden_retriever_peluqueria.png
✓ Imagen cargada exitosamente en vista principal
```

Si ves:
```
⚠ Imagen no encontrada: /images/golden_retriever_peluqueria.png
```

Significa que la imagen no está en la ubicación correcta.

## Estructura Final

```
peluqueriaCanina/
  └── src/
      └── main/
          └── resources/
              └── images/
                  ├── golden_retriever_peluqueria.png  ← DEBE ESTAR AQUÍ
                  ├── gato_jugando_lana.png           ← DEBE ESTAR AQUÍ
                  └── LEEME_IMAGENES.txt
```

## Nota sobre Git

Las imágenes **SÍ se subirán al repositorio** porque la carpeta `src/main/resources/images/` NO está en `.gitignore`. Esto es correcto.

## Si las Imágenes Siguen Sin Aparecer

1. Verifica que los nombres sean exactos (case-sensitive)
2. Verifica que estén en `src/main/resources/images/` (no en otra carpeta)
3. Recompila: `mvn clean compile`
4. Revisa la consola para mensajes de depuración
5. Verifica que las imágenes no estén corruptas


