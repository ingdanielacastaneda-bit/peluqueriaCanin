package com.mycompany.peluqueriacanina.repository;

import com.mycompany.peluqueriacanina.model.Duenio;
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
 * Repositorio que encapsula el acceso a datos de la entidad Duenio.
 * Maneja directamente las operaciones JPA mediante EntityManager.
 * Proporciona una interfaz limpia y traduce excepciones de JPA a excepciones de dominio.
 * 
 * Usa EntityManagerFactoryProvider para obtener una instancia compartida,
 * evitando múltiples pools de conexiones.
 */
public class DuenioRepository {
    
    private static final Logger LOGGER = Logger.getLogger(DuenioRepository.class.getName());
    private final EntityManagerFactory emf;
    
    public DuenioRepository() {
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
     * Guarda un nuevo dueño en la base de datos.
     * 
     * @param duenio El dueño a persistir
     * @throws PersistenceException Si ocurre un error al guardar
     */
    public void save(Duenio duenio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(duenio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.SEVERE, "Error al guardar dueño", ex);
            throw new PersistenceException("Error al guardar el dueño", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Actualiza un dueño existente en la base de datos.
     * 
     * @param duenio El dueño con los datos actualizados
     * @throws EntityNotFoundException Si el dueño no existe
     * @throws PersistenceException Si ocurre un error al actualizar
     */
    public void update(Duenio duenio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            // Verificar que el dueño existe
            Duenio existing = em.find(Duenio.class, duenio.getId_duenio());
            if (existing == null) {
                throw new EntityNotFoundException("El dueño con ID " + duenio.getId_duenio() + " no existe");
            }
            
            em.merge(duenio);
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
            LOGGER.log(Level.SEVERE, "Error al actualizar dueño con ID: " + duenio.getId_duenio(), ex);
            throw new PersistenceException("Error al actualizar el dueño", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Elimina un dueño de la base de datos.
     * 
     * @param id ID del dueño a eliminar
     * @throws EntityNotFoundException Si el dueño no existe
     * @throws PersistenceException Si ocurre un error al eliminar
     */
    public void delete(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Duenio duenio = em.find(Duenio.class, id);
            if (duenio == null) {
                throw new EntityNotFoundException("El dueño con ID " + id + " no existe");
            }
            
            em.remove(duenio);
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
            LOGGER.log(Level.SEVERE, "Error al eliminar dueño con ID: " + id, ex);
            throw new PersistenceException("Error al eliminar el dueño", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Busca un dueño por su ID.
     * 
     * @param id ID del dueño
     * @return El dueño encontrado o null si no existe
     */
    public Duenio findById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Duenio.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Obtiene todos los dueños registrados.
     * 
     * @return Lista de todos los dueños
     */
    public List<Duenio> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Duenio> cq = em.getCriteriaBuilder().createQuery(Duenio.class);
            Root<Duenio> root = cq.from(Duenio.class);
            cq.select(root);
            TypedQuery<Duenio> q = em.createQuery(cq);
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
        LOGGER.warning("close() llamado en DuenioRepository. El EntityManagerFactory es compartido y no debe cerrarse aquí.");
    }
}
