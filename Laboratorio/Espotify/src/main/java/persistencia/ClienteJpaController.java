/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import logica.Cliente;

/**
 *
 * @author dokgo
 */
public class ClienteJpaController implements Serializable{
    
    public ClienteJpaController(){
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }
    
    private EntityManagerFactory emf = null;

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, email);
                cliente.getEmail();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("El cliente con email " + email + " no existe.", enfe);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Cliente findCliente(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, email);
        } finally {
            em.close();
        }
    }
    
    public List<Cliente> findAllClientes() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
     public Cliente findClienteByNickname(String nickname) {
    EntityManager em = getEntityManager();
    try {
        Query query = em.createQuery("SELECT u FROM Cliente u WHERE u.nickname = :nickname");
        query.setParameter("nickname", nickname);
        return (Cliente) query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
    }
}
