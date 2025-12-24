package com.mycompany.peluqueriacanina.exception;

/**
 * Excepción lanzada cuando se intenta acceder a una entidad que no existe en la base de datos.
 */
public class EntityNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

