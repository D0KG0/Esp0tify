package persistencia;

import java.io.Serializable;
import jakarta.persistence.TypedQuery;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import logica.ListaParticular;
import logica.Tema;

public class ListaParticularJpaController implements Serializable {

    public ListaParticularJpaController() {
        emf = Persistence.createEntityManagerFactory("ServidorPU");
    }

    private EntityManagerFactory emf = null;

    public ListaParticularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaParticular lr) {
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

    public void edit(ListaParticular lr) throws Exception {
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
            ListaParticular lr;
            try {
                lr = em.getReference(ListaParticular.class, nombre);
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

    public ListaParticular findListaDef(String nombre) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaParticular.class, nombre);
        } finally {
            em.close();
        }
    }
    
    public ListaParticular findListaParticularById(int id) {
        EntityManager em = emf.createEntityManager();
        ListaParticular lista = null;

        try {
            // Inicia la transacción
            em.getTransaction().begin();

            // Busca la lista de reproducción por ID
            lista = em.find(ListaParticular.class, id);

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

    // Método agregado para encontrar todas las ListasParticulares
    public List<ListaParticular> findAllListasParticularesCliente(String nickname) {
       EntityManager em = getEntityManager();
        try {
            // JPQL query para obtener las listas por el nickname del cliente
            return em.createQuery("SELECT l FROM ListaParticular l WHERE l.cliente = :nickname", ListaParticular.class)
                     .setParameter("nickname", nickname)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
        public List<ListaParticular> findListasParticularesPublicas() {
        EntityManager em = getEntityManager();
        try {
            // Consulta JPQL para obtener las listas con publica = true
            return em.createQuery("SELECT l FROM ListaParticular l WHERE l.publica = true", ListaParticular.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Tema> findTemasByListaParticularId(int listaParticularId) {
        EntityManager em = getEntityManager();
        try {
            // Consulta JPQL para obtener los temas de una lista particular por ID
            return em.createQuery(
                "SELECT t FROM Tema t WHERE t.listaReproduccion.id = :idLista AND t.listaReproduccion INSTANCE OF ListaParticular", Tema.class)
                     .setParameter("idLista", listaParticularId)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<ListaParticular> findListaParticularEntities() {
        return findListaParticularEntities(true, -1, -1);
    }

    public List<ListaParticular> findListaParticularEntities(int maxResults, int firstResult) {
        return findListaParticularEntities(false, maxResults, firstResult);
    }

    private List<ListaParticular> findListaParticularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaParticular.class));
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
}