/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import logica.ListaPorDefecto;

/**
 *
 * @author frank
 */
public class ListaPorDefectoJpaController implements Serializable {
    
    public ListaPorDefectoJpaController(){
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }
    
    private EntityManagerFactory emf = null;

    public ListaPorDefectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaPorDefecto lr) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaPorDefecto lr) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lr = em.merge(lr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String nombre) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaPorDefecto lr;
            try {
                lr = em.getReference(ListaPorDefecto.class, nombre);
            } catch (EntityNotFoundException enfe) {
                throw new Exception("No existe una lista con ese nombre", enfe);
            }
            em.remove(lr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public ListaPorDefecto findListaDef(String nombre) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaPorDefecto.class, nombre);
        } finally {
            em.close();
        }
    }  
    
    public List<ListaPorDefecto> findListaPorDefectoEntities() {
        return findListaPorDefectoEntities(true, -1, -1);
    }

    public List<ListaPorDefecto> findListaPorDefectoEntities(int maxResults, int firstResult) {
        return findListaPorDefectoEntities(false, maxResults, firstResult);
    }

    private List<ListaPorDefecto> findListaPorDefectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaPorDefecto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<ListaPorDefecto> findAllListaDef(){
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT l FROM ListaPorDefecto l", ListaPorDefecto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    List<ListaPorDefecto> findAllListaDefectoGenero(String genero) {
       EntityManager em = getEntityManager();
        try {
            // JPQL query para obtener las listas por el nickname del cliente
            Query query = em.createQuery("SELECT l FROM ListaPorDefecto l WHERE l.genero = :genero", ListaPorDefecto.class);
            query.setParameter("genero", genero);
            return (List<ListaPorDefecto>) query.getResultList();
        } finally {
            em.close();
        } 
    }
    
    
    public ListaPorDefecto findListaPorDefectoById(int id) {
        EntityManager em = emf.createEntityManager();
        ListaPorDefecto lista = null;

        try {
            // Inicia la transacción
            em.getTransaction().begin();

            // Busca la lista de reproducción por ID
            lista = em.find(ListaPorDefecto.class, id);

            // Confirma la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Reversa la transacción en caso de error
            }
            e.printStackTrace(); // Manejo de errores
        } finally {
            em.close(); // Cierra el EntityManager
        }

        return lista; // Retorna la lista encontrada o null si no se encontró
    }
}
