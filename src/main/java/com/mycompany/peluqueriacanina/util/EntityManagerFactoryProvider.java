package com.mycompany.peluqueriacanina.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Proveedor singleton de EntityManagerFactory.
 * Garantiza que toda la aplicación use una única instancia compartida,
 * evitando múltiples pools de conexiones y mejorando la gestión de recursos.
 * 
 * Esta clase resuelve el problema crítico de tener múltiples instancias
 * de EntityManagerFactory creadas por cada repository.
 */
public class EntityManagerFactoryProvider {
    
    private static final Logger LOGGER = Logger.getLogger(EntityManagerFactoryProvider.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "PeluCaninaPU";
    
    private static volatile EntityManagerFactory instance;
    private static final Object lock = new Object();
    
    /**
     * Obtiene la instancia única de EntityManagerFactory.
     * Usa double-check locking para garantizar thread-safety.
     * 
     * @return La instancia única de EntityManagerFactory
     */
    public static EntityManagerFactory getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    // Asegurar que la base de datos esté inicializada antes de crear EntityManagerFactory
                    boolean dbReady = DatabaseInitializer.initializeDatabase();
                    if (!dbReady) {
                        LOGGER.severe("No se pudo inicializar la base de datos. La aplicación puede fallar.");
                    }
                    
                    try {
                        instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                        LOGGER.info("EntityManagerFactory creado exitosamente");
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error al crear EntityManagerFactory", e);
                        throw new RuntimeException("No se pudo crear EntityManagerFactory", e);
                    }
                }
            }
        }
        return instance;
    }
    
    /**
     * Cierra el EntityManagerFactory si está abierto.
     * Debe ser llamado al cerrar la aplicación para liberar recursos.
     */
    public static void close() {
        synchronized (lock) {
            if (instance != null && instance.isOpen()) {
                instance.close();
                instance = null;
                LOGGER.info("EntityManagerFactory cerrado");
            }
        }
    }
    
    /**
     * Verifica si el EntityManagerFactory está inicializado.
     * 
     * @return true si está inicializado, false en caso contrario
     */
    public static boolean isInitialized() {
        return instance != null && instance.isOpen();
    }
}


