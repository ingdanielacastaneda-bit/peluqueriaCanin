package com.mycompany.peluqueriacanina.exception;

/**
 * Excepción lanzada cuando se intenta crear una entidad que ya existe en la base de datos.
 */
public class EntityAlreadyExistsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
    
    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

