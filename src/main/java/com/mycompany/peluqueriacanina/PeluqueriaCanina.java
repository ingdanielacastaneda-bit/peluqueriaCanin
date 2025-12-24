package com.mycompany.peluqueriacanina;

import com.mycompany.peluqueriacanina.ui.Principal;
import com.mycompany.peluqueriacanina.util.DatabaseInitializer;
import com.mycompany.peluqueriacanina.util.EntityManagerFactoryProvider;

/**
 * Clase principal del sistema de gestión de peluquería canina.
 * Inicia la aplicación mostrando la ventana principal.
 */
public class PeluqueriaCanina {

    /**
     * Método principal que inicia la aplicación.
     * Crea y muestra la ventana principal centrada en la pantalla.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
       // Registrar shutdown hook para cerrar EntityManagerFactory al finalizar
       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
           EntityManagerFactoryProvider.close();
       }));
       
       // Inicializar la base de datos automáticamente si no existe
       boolean dbReady = DatabaseInitializer.initializeDatabase();
       
       if (!dbReady) {
           System.err.println("\n⚠️ ADVERTENCIA: No se pudo inicializar la base de datos.");
           System.err.println("La aplicación puede no funcionar correctamente.");
           System.err.println("Por favor, crea la base de datos manualmente ejecutando:");
           System.err.println("CREATE DATABASE peluqueria_canina;");
           System.err.println("\nPresiona Enter para continuar de todos modos...");
           try {
               System.in.read();
           } catch (Exception e) {
               // Ignorar
           }
       }
       
       // Iniciar la interfaz gráfica
       Principal princ = new Principal();
       princ.setVisible(true);
       princ.setLocationRelativeTo(null);
    }
}
