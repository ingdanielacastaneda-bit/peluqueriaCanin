package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 * Controladora de la lógica de negocio del sistema de peluquería canina.
 * Gestiona las operaciones CRUD de mascotas y dueños, incluyendo validaciones
 * de datos antes de persistir en la base de datos.
 */
public class Controladora {
   
    private final ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    /**
     * Guarda una nueva mascota y su dueño en la base de datos.
     * Valida que todos los campos obligatorios estén presentes antes de persistir.
     * 
     * @param nombreMasco Nombre de la mascota
     * @param raza Raza de la mascota
     * @param color Color de la mascota
     * @param observaciones Observaciones sobre la mascota
     * @param alergico Indica si la mascota es alérgica ("SI", "NO" o "-")
     * @param atenEsp Indica si requiere atención especial ("SI", "NO" o "-")
     * @param direccion Dirección del dueño
     * @param nombreDueño Nombre del dueño
     * @param celDueño Teléfono celular del dueño
     * @throws IllegalArgumentException Si algún campo obligatorio está vacío o es nulo
     */
    public void guardar(String nombreMasco, String raza, String color, String observaciones, 
            String alergico, String atenEsp, String direccion, String nombreDueño, String celDueño) {
        
        validarDatosMascota(nombreMasco, raza, color);
        validarDatosDuenio(nombreDueño, celDueño, direccion);
        
        Duenio duenio = new Duenio();
        duenio.setCelDuenio(celDueño.trim());
        duenio.setNombre(nombreDueño.trim());
        duenio.setDireccion(direccion.trim());
        
        Mascota masco = new Mascota();
        masco.setNombre(nombreMasco.trim());
        masco.setRaza(raza.trim());
        masco.setColor(color.trim());
        masco.setAlergico(alergico != null ? alergico : "-");
        masco.setAtencion_especial(atenEsp != null ? atenEsp : "-");
        masco.setObservaciones(observaciones != null ? observaciones.trim() : "");
        masco.setUnDuenio(duenio);
        
        controlPersis.guardar(duenio, masco);
    }
    
    /**
     * Valida que los datos obligatorios de la mascota no estén vacíos.
     * 
     * @param nombre Nombre de la mascota
     * @param raza Raza de la mascota
     * @param color Color de la mascota
     * @throws IllegalArgumentException Si algún campo está vacío o es nulo
     */
    private void validarDatosMascota(String nombre, String raza, String color) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota es obligatorio");
        }
        if (raza == null || raza.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza es obligatoria");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color es obligatorio");
        }
    }
    
    /**
     * Valida que los datos obligatorios del dueño no estén vacíos.
     * 
     * @param nombre Nombre del dueño
     * @param celular Teléfono celular del dueño
     * @param direccion Dirección del dueño
     * @throws IllegalArgumentException Si algún campo está vacío o es nulo
     */
    private void validarDatosDuenio(String nombre, String celular, String direccion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del dueño es obligatorio");
        }
        if (celular == null || celular.trim().isEmpty()) {
            throw new IllegalArgumentException("El celular del dueño es obligatorio");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
    }

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     * 
     * @return Lista de todas las mascotas
     */
    public List<Mascota> traerMascotas() {
         return controlPersis.traerMascotas();       
    }

    /**
     * Elimina una mascota de la base de datos por su número de cliente.
     * 
     * @param num_cliente Número de cliente de la mascota a eliminar
     */
    public void borrarMascota(int num_cliente) {
       controlPersis.borrarMascota(num_cliente);
    }

    /**
     * Obtiene una mascota específica por su número de cliente.
     * 
     * @param num_cliente Número de cliente de la mascota
     * @return La mascota encontrada o null si no existe
     */
    public Mascota traerMascota(int num_cliente) {
        return controlPersis.traerMascota(num_cliente);
    }

    /**
     * Modifica los datos de una mascota existente y su dueño.
     * Valida todos los campos antes de actualizar la información.
     * 
     * @param masco Objeto Mascota a modificar
     * @param nombreMasco Nuevo nombre de la mascota
     * @param raza Nueva raza de la mascota
     * @param color Nuevo color de la mascota
     * @param observaciones Nuevas observaciones
     * @param alergico Nuevo estado de alergia
     * @param atenEsp Nuevo estado de atención especial
     * @param direccion Nueva dirección del dueño
     * @param nombreDueño Nuevo nombre del dueño
     * @param celDueño Nuevo teléfono del dueño
     * @throws IllegalArgumentException Si la mascota es nula o algún campo obligatorio está vacío
     */
    public void modificarMascota(Mascota masco, String nombreMasco, String raza, String color, String observaciones,
            String alergico, String atenEsp, String direccion, String nombreDueño, String celDueño) {
        
        if (masco == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        
        validarDatosMascota(nombreMasco, raza, color);
        validarDatosDuenio(nombreDueño, celDueño, direccion);
        
        masco.setNombre(nombreMasco.trim());
        masco.setRaza(raza.trim());
        masco.setColor(color.trim());
        masco.setObservaciones(observaciones != null ? observaciones.trim() : "");
        masco.setAlergico(alergico != null ? alergico : "-");
        masco.setAtencion_especial(atenEsp != null ? atenEsp : "-");

        controlPersis.modificarMascota(masco);
        
        Duenio duenio = buscarDuenio(masco.getUnDuenio().getId_duenio());
        if (duenio != null) {
            duenio.setNombre(nombreDueño.trim());
            duenio.setCelDuenio(celDueño.trim());
            duenio.setDireccion(direccion.trim());
            modificarDuenio(duenio);
        }
    }

    /**
     * Busca un dueño por su ID en la base de datos.
     * 
     * @param id_duenio ID del dueño a buscar
     * @return El dueño encontrado o null si no existe
     */
    private Duenio buscarDuenio(int id_duenio) {
        return controlPersis.traerDuenio(id_duenio);
    }

    /**
     * Modifica los datos de un dueño en la base de datos.
     * 
     * @param duenio Objeto Duenio con los datos actualizados
     */
    private void modificarDuenio(Duenio duenio) {
        controlPersis.modificarDuenio(duenio);
    }
}
