package com.mycompany.peluqueriacanina.persistencia;

import com.mycompany.peluqueriacanina.logica.Duenio;
import com.mycompany.peluqueriacanina.logica.Mascota;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controladora de la capa de persistencia.
 * Gestiona todas las operaciones de base de datos para las entidades
 * Mascota y Duenio, manejando excepciones y registrando errores.
 */
public class ControladoraPersistencia {
    
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotaJpaController mascoJpa = new MascotaJpaController();

    /**
     * Guarda un dueño y su mascota en la base de datos.
     * Primero se crea el dueño y luego la mascota para mantener
     * la integridad referencial.
     * 
     * @param duenio Objeto Duenio a persistir
     * @param masco Objeto Mascota a persistir
     */
    public void guardar(Duenio duenio, Mascota masco) {
      duenioJpa.create(duenio);
      mascoJpa.create(masco);
    }

    /**
     * Obtiene todas las mascotas registradas en la base de datos.
     * 
     * @return Lista con todas las mascotas
     */
    public List<Mascota> traerMascotas() {
        return mascoJpa.findMascotaEntities();
    }

    /**
     * Elimina una mascota de la base de datos por su número de cliente.
     * 
     * @param num_cliente Número de cliente de la mascota a eliminar
     * @throws RuntimeException Si la mascota no existe o ocurre un error al eliminar
     */
    public void borrarMascota(int num_cliente) {
        try {
            mascoJpa.destroy(num_cliente);
        } catch (com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error al eliminar mascota con ID: " + num_cliente, ex);
            throw new RuntimeException("No se pudo eliminar la mascota. No existe en la base de datos.", ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error inesperado al eliminar mascota", ex);
            throw new RuntimeException("Error al eliminar la mascota", ex);
        }
    }

    /**
     * Obtiene una mascota específica por su número de cliente.
     * 
     * @param num_cliente Número de cliente de la mascota
     * @return La mascota encontrada o null si no existe
     */
    public Mascota traerMascota(int num_cliente) {
        return mascoJpa.findMascota(num_cliente);
    }

    /**
     * Modifica los datos de una mascota existente en la base de datos.
     * 
     * @param masco Objeto Mascota con los datos actualizados
     * @throws RuntimeException Si la mascota no existe o ocurre un error al modificar
     */
    public void modificarMascota(Mascota masco) {
        try {
            mascoJpa.edit(masco);
        } catch (com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error al modificar mascota. No existe en la base de datos.", ex);
            throw new RuntimeException("No se pudo modificar la mascota. No existe en la base de datos.", ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error inesperado al modificar mascota", ex);
            throw new RuntimeException("Error al modificar la mascota", ex);
        }
    }

    /**
     * Obtiene un dueño específico por su ID.
     * 
     * @param id_duenio ID del dueño a buscar
     * @return El dueño encontrado o null si no existe
     */
    public Duenio traerDuenio(int id_duenio) {
         return duenioJpa.findDuenio(id_duenio);
    }

    /**
     * Modifica los datos de un dueño existente en la base de datos.
     * 
     * @param duenio Objeto Duenio con los datos actualizados
     * @throws RuntimeException Si el dueño no existe o ocurre un error al modificar
     */
    public void modificarDuenio(Duenio duenio) {
        try {
            duenioJpa.edit(duenio);
        } catch (com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error al modificar dueño. No existe en la base de datos.", ex);
            throw new RuntimeException("No se pudo modificar el dueño. No existe en la base de datos.", ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, 
                    "Error inesperado al modificar dueño", ex);
            throw new RuntimeException("Error al modificar el dueño", ex);
        }
    }
    
}
