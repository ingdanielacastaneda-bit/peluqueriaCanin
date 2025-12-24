@echo off
echo ============================================
echo COPIAR IMAGENES AL PROYECTO
echo ============================================
echo.
echo Este script te ayudara a copiar las imagenes al proyecto.
echo.
echo Las imagenes deben llamarse:
echo   1. golden_retriever_peluqueria.png
echo   2. gato_jugando_lana.png
echo.
echo Presiona cualquier tecla para abrir la carpeta de destino...
pause >nul

REM Abrir la carpeta de destino
start "" "%~dp0src\main\resources\images"

echo.
echo ============================================
echo INSTRUCCIONES:
echo ============================================
echo 1. La carpeta de destino se ha abierto
echo 2. Copia las imagenes a esa carpeta
echo 3. Asegurate de que los nombres sean exactos:
echo    - golden_retriever_peluqueria.png
echo    - gato_jugando_lana.png
echo.
echo Presiona cualquier tecla cuando hayas copiado las imagenes...
pause >nul

echo.
echo Verificando imagenes...
if exist "src\main\resources\images\golden_retriever_peluqueria.png" (
    echo [OK] golden_retriever_peluqueria.png encontrada
) else (
    echo [FALTA] golden_retriever_peluqueria.png NO encontrada
)

if exist "src\main\resources\images\gato_jugando_lana.png" (
    echo [OK] gato_jugando_lana.png encontrada
) else (
    echo [FALTA] gato_jugando_lana.png NO encontrada
)

echo.
echo Ahora ejecuta: mvn clean compile
echo.
pause


