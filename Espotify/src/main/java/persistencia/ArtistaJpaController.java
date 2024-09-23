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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import logica.Album;
import logica.Artista;

/**
 *
 * @author dokgo
 */
public class ArtistaJpaController implements Serializable {
    
    public ArtistaJpaController(){
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }
    
    private EntityManagerFactory emf = null;

    public ArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artista artista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artista artista) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            artista = em.merge(artista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String email) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista artista;
            try {
                artista = em.getReference(Artista.class, email);
                artista.getEmail();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("El artista con email " + email + " no existe.", enfe);
            }
            em.remove(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Artista findArtista(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artista.class, email);
        } finally {
            em.close();
        }
    }
    
    public Artista findArtistaByNickname(String nickname) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Artista u WHERE u.nickname = :nickname");
            query.setParameter("nickname", nickname);
            return (Artista) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //obtener artistas
        public List<Artista> findAllArtistas() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Artista c", Artista.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Album getAlbum(String nombre, Artista ar) {
        EntityManager em = null;
        em = getEntityManager();
        Query query = em.createQuery("SELECT a FROM Album a WHERE a.nombre = :nombre AND a.artista = :artista");
        query.setParameter("nombre", nombre);
        query.setParameter("artista", ar);
        try {
            return (Album) query.getSingleResult();
        } catch (NoResultException e) {
            return null;  // Si no se encuentra ningún álbum
        }
    }   
    
    public void addAlbum(){
        EntityManager em = null;
        em = getEntityManager();
        
    }
}
