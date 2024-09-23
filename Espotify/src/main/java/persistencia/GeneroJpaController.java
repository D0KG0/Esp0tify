/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import logica.Album;
import logica.Genero;

/**
 *
 * @author dokgo
 */
public class GeneroJpaController implements Serializable {
    
    public GeneroJpaController(){
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }
    
    private EntityManagerFactory emf = null;

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            genero = em.merge(genero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String nom) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero genero;
            try {
                genero = em.getReference(Genero.class, nom);
                genero.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("No existe un genero con ese nombre.", enfe);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Genero findGenero(String nombre) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, nombre);
        } finally {
            em.close();
        }
    }
    
    public Set<Genero> listaGeneros(){
        EntityManager em = getEntityManager();
        Set<Genero> g = new HashSet<>();
        return g;
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }
    
    public List<Genero> findPadres() {
        EntityManager em = getEntityManager();
        TypedQuery<Genero> query = em.createQuery("SELECT g FROM Genero g WHERE g.padre = 'N/A'", Genero.class);
        return query.getResultList();
    }
    
    public Album getAlbum(String nombre, Genero gen) {
        EntityManager em = null;
        em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Album a JOIN a.Generos g WHERE a.nombre = :nombre AND g = :genero");
        query.setParameter("nombre", nombre);
        query.setParameter("genero", gen);
        try {
            return (Album) query.getSingleResult();
        } catch (NoResultException e) {
            return null;  // Si no se encuentra ningún álbum
        }
    }
    
    public List<Genero> findAllGeneros() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Genero c", Genero.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}