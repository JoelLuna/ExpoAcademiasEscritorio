/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Ciclo;
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
public class CicloJpaController implements Serializable {

    public CicloJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ExpoAcademiasEscritorioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciclo ciclo) {
        if (ciclo.getProyectoList() == null) {
            ciclo.setProyectoList(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : ciclo.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getIDProyecto());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            ciclo.setProyectoList(attachedProyectoList);
            em.persist(ciclo);
            for (Proyecto proyectoListProyecto : ciclo.getProyectoList()) {
                Ciclo oldIDCicloOfProyectoListProyecto = proyectoListProyecto.getIDCiclo();
                proyectoListProyecto.setIDCiclo(ciclo);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldIDCicloOfProyectoListProyecto != null) {
                    oldIDCicloOfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldIDCicloOfProyectoListProyecto = em.merge(oldIDCicloOfProyectoListProyecto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciclo ciclo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciclo persistentCiclo = em.find(Ciclo.class, ciclo.getIDCiclo());
            List<Proyecto> proyectoListOld = persistentCiclo.getProyectoList();
            List<Proyecto> proyectoListNew = ciclo.getProyectoList();
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getIDProyecto());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }
            proyectoListNew = attachedProyectoListNew;
            ciclo.setProyectoList(proyectoListNew);
            ciclo = em.merge(ciclo);
            for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    proyectoListOldProyecto.setIDCiclo(null);
                    proyectoListOldProyecto = em.merge(proyectoListOldProyecto);
                }
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Ciclo oldIDCicloOfProyectoListNewProyecto = proyectoListNewProyecto.getIDCiclo();
                    proyectoListNewProyecto.setIDCiclo(ciclo);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldIDCicloOfProyectoListNewProyecto != null && !oldIDCicloOfProyectoListNewProyecto.equals(ciclo)) {
                        oldIDCicloOfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldIDCicloOfProyectoListNewProyecto = em.merge(oldIDCicloOfProyectoListNewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciclo.getIDCiclo();
                if (findCiclo(id) == null) {
                    throw new NonexistentEntityException("The ciclo with id " + id + " no longer exists.");
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
            Ciclo ciclo;
            try {
                ciclo = em.getReference(Ciclo.class, id);
                ciclo.getIDCiclo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciclo with id " + id + " no longer exists.", enfe);
            }
            List<Proyecto> proyectoList = ciclo.getProyectoList();
            for (Proyecto proyectoListProyecto : proyectoList) {
                proyectoListProyecto.setIDCiclo(null);
                proyectoListProyecto = em.merge(proyectoListProyecto);
            }
            em.remove(ciclo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciclo> findCicloEntities() {
        return findCicloEntities(true, -1, -1);
    }

    public List<Ciclo> findCicloEntities(int maxResults, int firstResult) {
        return findCicloEntities(false, maxResults, firstResult);
    }

    private List<Ciclo> findCicloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciclo.class));
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

    public Ciclo findCiclo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciclo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCicloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciclo> rt = cq.from(Ciclo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
