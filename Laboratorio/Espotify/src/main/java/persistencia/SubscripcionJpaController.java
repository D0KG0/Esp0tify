/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.List;
import logica.Subscripcion;

/**
 *
 * @author frank
 */
public class SubscripcionJpaController implements Serializable {
    
    public SubscripcionJpaController(){
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }
    
    private EntityManagerFactory emf = null;

    public SubscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subscripcion sub) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subscripcion sub) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sub = em.merge(sub);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Subscripcion> findAllSubscripciones() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Subscripcion s", Subscripcion.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
