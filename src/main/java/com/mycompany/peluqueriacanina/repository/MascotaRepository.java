package com.mycompany.peluqueriacanina.repository;

import com.mycompany.peluqueriacanina.model.Mascota;
import com.mycompany.peluqueriacanina.exception.EntityNotFoundException;
import com.mycompany.peluqueriacanina.exception.PersistenceException;
import com.mycompany.peluqueriacanina.util.EntityManagerFactoryProvider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Repositorio que encapsula el acceso a datos de la entidad Mascota.
 * Maneja directamente las operaciones JPA mediante EntityManager.
 * Proporciona una interfaz limpia y traduce excepciones de JPA a excepciones de dominio.
 * 
 * Usa EntityManagerFactoryProvider para obtener una instancia compartida,
 * evitando múltiples pools de conexiones.
 */
public class MascotaRepository {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaRepository.class.getName());
    private final EntityManagerFactory emf;
    
    public MascotaRepository() {
        // Obtener la instancia compartida de EntityManagerFactory
        this.emf = EntityManagerFactoryProvider.getInstance();
    }
    
    /**
     * Obtiene un EntityManager para realizar operaciones de base de datos.
     * 
     * @return EntityManager configurado
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Guarda una nueva mascota en la base de datos.
     * 
     * @param mascota La mascota a persistir
     * @throws PersistenceException Si ocurre un error al guardar
     */
    public void save(Mascota mascota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mascota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.SEVERE, "Error al guardar mascota", ex);
            throw new PersistenceException("Error al guardar la mascota", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Actualiza una mascota existente en la base de datos.
     * 
     * @param mascota La mascota con los datos actualizados
     * @throws EntityNotFoundException Si la mascota no existe
     * @throws PersistenceException Si ocurre un error al actualizar
     */
    public void update(Mascota mascota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            // Verificar que la mascota existe
            Mascota existing = em.find(Mascota.class, mascota.getNum_cliente());
            if (existing == null) {
                throw new EntityNotFoundException("La mascota con ID " + mascota.getNum_cliente() + " no existe");
            }
            
            em.merge(mascota);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.SEVERE, "Error al actualizar mascota con ID: " + mascota.getNum_cliente(), ex);
            throw new PersistenceException("Error al actualizar la mascota", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Elimina una mascota de la base de datos.
     * 
     * @param id Número de cliente de la mascota a eliminar
     * @throws EntityNotFoundException Si la mascota no existe
     * @throws PersistenceException Si ocurre un error al eliminar
     */
    public void delete(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Mascota mascota = em.find(Mascota.class, id);
            if (mascota == null) {
                throw new EntityNotFoundException("La mascota con ID " + id + " no existe");
            }
            
            em.remove(mascota);
            em.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.SEVERE, "Error al eliminar mascota con ID: " + id, ex);
            throw new PersistenceException("Error al eliminar la mascota", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Busca una mascota por su número de cliente.
     * 
     * @param id Número de cliente de la mascota
     * @return La mascota encontrada o null si no existe
     */
    public Mascota findById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Obtiene todas las mascotas registradas.
     * 
     * @return Lista de todas las mascotas
     */
    public List<Mascota> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Mascota> cq = em.getCriteriaBuilder().createQuery(Mascota.class);
            Root<Mascota> root = cq.from(Mascota.class);
            cq.select(root);
            TypedQuery<Mascota> q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Nota: Este método ya no cierra el EntityManagerFactory porque es compartido.
     * El cierre debe hacerse a través de EntityManagerFactoryProvider.close()
     * cuando la aplicación termine.
     * 
     * @deprecated El EntityManagerFactory es compartido y no debe cerrarse aquí.
     * Use EntityManagerFactoryProvider.close() al finalizar la aplicación.
     */
    @Deprecated
    public void close() {
        // No hacer nada - el EntityManagerFactory es compartido
        LOGGER.warning("close() llamado en MascotaRepository. El EntityManagerFactory es compartido y no debe cerrarse aquí.");
    }
}
