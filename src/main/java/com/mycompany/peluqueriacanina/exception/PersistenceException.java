package com.mycompany.peluqueriacanina.exception;

/**
 * Excepción genérica para errores de persistencia.
 * Se utiliza cuando ocurre un error inesperado al interactuar con la base de datos.
 */
public class PersistenceException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public PersistenceException(String message) {
        super(message);
    }
    
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

