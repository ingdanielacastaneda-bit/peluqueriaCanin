package com.mycompany.peluqueriacanina.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utilidad para inicializar la base de datos automáticamente.
 * Crea la base de datos si no existe antes de que JPA intente conectarse.
 * Usa un patrón singleton para asegurar que solo se inicialice una vez.
 * 
 * Las credenciales se leen desde database.properties para evitar
 * hardcodear información sensible en el código fuente.
 */
public class DatabaseInitializer {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());
    private static final String PROPERTIES_FILE = "/database.properties";
    
    private static volatile boolean initialized = false;
    private static final Object lock = new Object();
    
    /**
     * Carga las propiedades de configuración de base de datos desde el archivo properties.
     * 
     * @return Properties con la configuración de BD
     * @throws RuntimeException Si no se puede cargar el archivo de propiedades
     */
    private static Properties loadDatabaseProperties() {
        Properties props = new Properties();
        try (InputStream input = DatabaseInitializer.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo " + PROPERTIES_FILE + 
                    " en el classpath. Asegúrate de que esté en src/main/resources/");
            }
            props.load(input);
            LOGGER.info("Propiedades de base de datos cargadas desde " + PROPERTIES_FILE);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar propiedades de base de datos", e);
            throw new RuntimeException("Error al cargar configuración de base de datos: " + e.getMessage(), e);
        }
        return props;
    }
    
    /**
     * Verifica y crea la base de datos si no existe.
     * Se conecta primero sin especificar la base de datos, crea la BD si no existe,
     * y luego permite que JPA se conecte normalmente.
     * Usa sincronización para asegurar que solo se ejecute una vez.
     * 
     * Las credenciales se leen desde database.properties.
     * 
     * @return true si la base de datos existe o se creó exitosamente, false en caso contrario
     */
    public static boolean initializeDatabase() {
        // Double-check locking pattern para evitar inicialización múltiple
        if (initialized) {
            return true;
        }
        
        synchronized (lock) {
            if (initialized) {
                return true;
            }
            
            // Cargar propiedades de configuración
            Properties props = loadDatabaseProperties();
            String dbName = props.getProperty("db.name", "peluqueria_canina");
            String dbUrl = props.getProperty("db.url", "jdbc:mysql://localhost:3306");
            String dbUser = props.getProperty("db.user", "root");
            String dbPassword = props.getProperty("db.password", "");
            String timezone = props.getProperty("db.timezone", "UTC");
            
            Connection conn = null;
            Statement stmt = null;
            
            try {
                System.out.println("=== Inicializando Base de Datos ===");
                System.out.println("Conectando a MySQL (usuario: " + dbUser + ")...");
                
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Conectarse sin especificar la base de datos
                String connectionUrl = dbUrl + "?serverTimezone=" + timezone + "&allowPublicKeyRetrieval=true";
                conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
                System.out.println("✓ Conexión a MySQL establecida");
                
                // Crear la base de datos si no existe
                System.out.println("Creando base de datos '" + dbName + "' si no existe...");
                stmt = conn.createStatement();
                String createDbSql = "CREATE DATABASE IF NOT EXISTS " + dbName;
                stmt.executeUpdate(createDbSql);
                System.out.println("✓ Base de datos '" + dbName + "' verificada/creada");
                
                // Verificar que la base de datos existe y está accesible
                stmt.execute("USE " + dbName);
                System.out.println("✓ Base de datos '" + dbName + "' está lista para usar");
                
                // Verificación adicional: intentar una consulta simple para asegurar que la BD está lista
                stmt.execute("SELECT 1");
                
                System.out.println("=== Inicialización completada ===\n");
                
                LOGGER.info("Base de datos '" + dbName + "' verificada/creada exitosamente");
                initialized = true;
                return true;
                
            } catch (ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Driver de MySQL no encontrado", e);
                System.err.println("\n❌ ERROR: Driver de MySQL no encontrado");
                System.err.println("Asegúrate de que MySQL Connector esté en el classpath.");
                return false;
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al crear la base de datos", e);
                System.err.println("\n❌ ERROR: No se pudo crear la base de datos");
                System.err.println("Verifica:");
                System.err.println("  1. MySQL está ejecutándose");
                System.err.println("  2. Usuario: " + dbUser + " (configurado en database.properties)");
                System.err.println("  3. Tienes permisos para crear bases de datos");
                System.err.println("  4. El puerto 3306 está disponible");
                System.err.println("\nMensaje de error: " + e.getMessage());
                System.err.println("Código de error: " + e.getErrorCode());
                return false;
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inesperado al inicializar base de datos", e);
                System.err.println("\n❌ ERROR inesperado: " + e.getMessage());
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error al cerrar conexión", e);
                }
            }
        }
    }
}
