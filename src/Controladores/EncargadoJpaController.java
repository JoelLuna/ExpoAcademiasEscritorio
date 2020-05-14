/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Encargado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jesus
 */
public class EncargadoJpaController implements Serializable {

    public EncargadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ExpoAcademiasEscritorioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encargado encargado) {
        if (encargado.getProyectoList() == null) {
            encargado.setProyectoList(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : encargado.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getIDProyecto());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            encargado.setProyectoList(attachedProyectoList);
            em.persist(encargado);
            for (Proyecto proyectoListProyecto : encargado.getProyectoList()) {
                Encargado oldIDEncargadoOfProyectoListProyecto = proyectoListProyecto.getIDEncargado();
                proyectoListProyecto.setIDEncargado(encargado);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldIDEncargadoOfProyectoListProyecto != null) {
                    oldIDEncargadoOfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldIDEncargadoOfProyectoListProyecto = em.merge(oldIDEncargadoOfProyectoListProyecto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encargado encargado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encargado persistentEncargado = em.find(Encargado.class, encargado.getIDEncargado());
            List<Proyecto> proyectoListOld = persistentEncargado.getProyectoList();
            List<Proyecto> proyectoListNew = encargado.getProyectoList();
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getIDProyecto());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }
            proyectoListNew = attachedProyectoListNew;
            encargado.setProyectoList(proyectoListNew);
            encargado = em.merge(encargado);
            for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    proyectoListOldProyecto.setIDEncargado(null);
                    proyectoListOldProyecto = em.merge(proyectoListOldProyecto);
                }
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Encargado oldIDEncargadoOfProyectoListNewProyecto = proyectoListNewProyecto.getIDEncargado();
                    proyectoListNewProyecto.setIDEncargado(encargado);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldIDEncargadoOfProyectoListNewProyecto != null && !oldIDEncargadoOfProyectoListNewProyecto.equals(encargado)) {
                        oldIDEncargadoOfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldIDEncargadoOfProyectoListNewProyecto = em.merge(oldIDEncargadoOfProyectoListNewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encargado.getIDEncargado();
                if (findEncargado(id) == null) {
                    throw new NonexistentEntityException("The encargado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encargado encargado;
            try {
                encargado = em.getReference(Encargado.class, id);
                encargado.getIDEncargado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encargado with id " + id + " no longer exists.", enfe);
            }
            List<Proyecto> proyectoList = encargado.getProyectoList();
            for (Proyecto proyectoListProyecto : proyectoList) {
                proyectoListProyecto.setIDEncargado(null);
                proyectoListProyecto = em.merge(proyectoListProyecto);
            }
            em.remove(encargado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encargado> findEncargadoEntities() {
        return findEncargadoEntities(true, -1, -1);
    }

    public List<Encargado> findEncargadoEntities(int maxResults, int firstResult) {
        return findEncargadoEntities(false, maxResults, firstResult);
    }

    private List<Encargado> findEncargadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encargado.class));
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

    public Encargado findEncargado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encargado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncargadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encargado> rt = cq.from(Encargado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
