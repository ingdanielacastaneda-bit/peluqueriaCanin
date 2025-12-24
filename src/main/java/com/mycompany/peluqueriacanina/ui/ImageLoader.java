package com.mycompany.peluqueriacanina.ui;

import javax.swing.ImageIcon;
import java.net.URL;

/**
 * Utilidad para cargar imágenes de forma segura.
 * Si la imagen no existe, retorna null sin lanzar excepciones.
 */
public class ImageLoader {
    
    /**
     * Carga una imagen desde recursos de forma segura.
     * 
     * @param imagePath Ruta de la imagen relativa a /images/ (ej: "logo.png")
     * @return ImageIcon si la imagen existe, null en caso contrario
     */
    public static ImageIcon loadImage(String imagePath) {
        try {
            // Intentar múltiples rutas posibles
            String[] paths = {
                "/images/" + imagePath,
                "images/" + imagePath,
                "/" + imagePath,
                imagePath
            };
            
            for (String resourcePath : paths) {
                URL imgUrl = ImageLoader.class.getResource(resourcePath);
                if (imgUrl == null) {
                    imgUrl = ImageLoader.class.getClassLoader().getResource(resourcePath);
                }
                
                if (imgUrl != null) {
                    ImageIcon icon = new ImageIcon(imgUrl);
                    // Verificar que la imagen se cargó correctamente
                    if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                        System.out.println("✓ Imagen cargada: " + resourcePath);
                        return icon;
                    }
                }
            }
            System.out.println("⚠ Imagen no encontrada: " + imagePath);
        } catch (Exception e) {
            System.err.println("✗ Error al cargar imagen " + imagePath + ": " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Carga una imagen y la escala al tamaño especificado.
     * 
     * @param imagePath Ruta de la imagen
     * @param width Ancho deseado
     * @param height Alto deseado
     * @return ImageIcon escalado o null si no se puede cargar
     */
    public static ImageIcon loadImage(String imagePath, int width, int height) {
        ImageIcon icon = loadImage(imagePath);
        if (icon != null && width > 0 && height > 0) {
            return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        }
        return icon;
    }
}

