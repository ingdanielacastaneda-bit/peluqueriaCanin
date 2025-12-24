package com.mycompany.peluqueriacanina.service;

import com.mycompany.peluqueriacanina.model.Mascota;
import com.mycompany.peluqueriacanina.model.Duenio;
import com.mycompany.peluqueriacanina.repository.MascotaRepository;
import com.mycompany.peluqueriacanina.repository.DuenioRepository;
import com.mycompany.peluqueriacanina.exception.EntityNotFoundException;
import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio relacionada con mascotas.
 * Coordina las operaciones entre mascotas y dueños, aplicando validaciones de dominio.
 */
public class MascotaService {
    
    private final MascotaRepository mascotaRepository;
    private final DuenioRepository duenioRepository;
    
    public MascotaService() {
        this.mascotaRepository = new MascotaRepository();
        this.duenioRepository = new DuenioRepository();
    }
    
    /**
     * Crea una nueva mascota junto con su dueño.
     * Valida los datos antes de persistir.
     * 
     * @param nombreMascota Nombre de la mascota
     * @param raza Raza de la mascota
     * @param color Color de la mascota
     * @param observaciones Observaciones sobre la mascota
     * @param alergico Indica si es alérgica ("SI", "NO" o "-")
     * @param atencionEspecial Indica si requiere atención especial ("SI", "NO" o "-")
     * @param direccion Dirección del dueño
     * @param nombreDuenio Nombre del dueño
     * @param celularDuenio Teléfono del dueño
     * @throws IllegalArgumentException Si algún campo obligatorio está vacío
     */
    public void crearMascota(String nombreMascota, String raza, String color, String observaciones,
                            String alergico, String atencionEspecial, String direccion, 
                            String nombreDuenio, String celularDuenio) {
        
        validarDatosMascota(nombreMascota, raza, color);
        validarDatosDuenio(nombreDuenio, celularDuenio, direccion);
        
        Duenio duenio = new Duenio();
        duenio.setNombre(nombreDuenio.trim());
        duenio.setCelDuenio(celularDuenio.trim());
        duenio.setDireccion(direccion.trim());
        
        Mascota mascota = new Mascota();
        mascota.setNombre(nombreMascota.trim());
        mascota.setRaza(raza.trim());
        mascota.setColor(color.trim());
        mascota.setObservaciones(observaciones != null ? observaciones.trim() : "");
        mascota.setAlergico(alergico != null ? alergico : "-");
        mascota.setAtencion_especial(atencionEspecial != null ? atencionEspecial : "-");
        mascota.setUnDuenio(duenio);
        
        // Guardar primero el dueño
        duenioRepository.save(duenio);
        
        // Si guardar la mascota falla, intentar eliminar el dueño huérfano
        try {
            mascotaRepository.save(mascota);
        } catch (Exception e) {
            // Si falla al guardar la mascota, eliminar el dueño huérfano
            try {
                if (duenio.getId_duenio() > 0) {
                    duenioRepository.delete(duenio.getId_duenio());
                }
            } catch (Exception cleanupEx) {
                // Log pero no lanzar, el error original es más importante
                System.err.println("Error al limpiar dueño huérfano: " + cleanupEx.getMessage());
            }
            throw e; // Re-lanzar el error original
        }
    }
    
    /**
     * Obtiene todas las mascotas registradas.
     * 
     * @return Lista de todas las mascotas
     */
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }
    
    /**
     * Busca una mascota por su número de cliente.
     * 
     * @param numCliente Número de cliente de la mascota
     * @return La mascota encontrada
     * @throws EntityNotFoundException Si la mascota no existe
     */
    public Mascota obtenerMascotaPorId(int numCliente) {
        Mascota mascota = mascotaRepository.findById(numCliente);
        if (mascota == null) {
            throw new EntityNotFoundException("No se encontró la mascota con número de cliente: " + numCliente);
        }
        return mascota;
    }
    
    /**
     * Elimina una mascota del sistema.
     * 
     * @param numCliente Número de cliente de la mascota a eliminar
     * @throws EntityNotFoundException Si la mascota no existe
     */
    public void eliminarMascota(int numCliente) {
        mascotaRepository.delete(numCliente);
    }
    
    /**
     * Actualiza los datos de una mascota y su dueño.
     * 
     * @param mascota Mascota existente a actualizar
     * @param nombreMascota Nuevo nombre
     * @param raza Nueva raza
     * @param color Nuevo color
     * @param observaciones Nuevas observaciones
     * @param alergico Nuevo estado de alergia
     * @param atencionEspecial Nuevo estado de atención especial
     * @param direccion Nueva dirección del dueño
     * @param nombreDuenio Nuevo nombre del dueño
     * @param celularDuenio Nuevo teléfono del dueño
     * @throws IllegalArgumentException Si la mascota es nula o algún campo obligatorio está vacío
     * @throws EntityNotFoundException Si la mascota no existe
     */
    public void actualizarMascota(Mascota mascota, String nombreMascota, String raza, String color,
                                 String observaciones, String alergico, String atencionEspecial,
                                 String direccion, String nombreDuenio, String celularDuenio) {
        
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        
        if (mascota.getUnDuenio() == null) {
            throw new IllegalArgumentException("La mascota debe tener un dueño asociado");
        }
        
        validarDatosMascota(nombreMascota, raza, color);
        validarDatosDuenio(nombreDuenio, celularDuenio, direccion);
        
        mascota.setNombre(nombreMascota.trim());
        mascota.setRaza(raza.trim());
        mascota.setColor(color.trim());
        mascota.setObservaciones(observaciones != null ? observaciones.trim() : "");
        mascota.setAlergico(alergico != null ? alergico : "-");
        mascota.setAtencion_especial(atencionEspecial != null ? atencionEspecial : "-");
        
        // Actualizar primero la mascota
        mascotaRepository.update(mascota);
        
        // Actualizar el dueño
        Duenio duenio = duenioRepository.findById(mascota.getUnDuenio().getId_duenio());
        if (duenio == null) {
            throw new EntityNotFoundException("El dueño asociado a la mascota no existe");
        }
        
        duenio.setNombre(nombreDuenio.trim());
        duenio.setCelDuenio(celularDuenio.trim());
        duenio.setDireccion(direccion.trim());
        duenioRepository.update(duenio);
    }
    
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
}

