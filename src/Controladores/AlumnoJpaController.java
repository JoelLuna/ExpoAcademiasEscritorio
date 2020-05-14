/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Equipo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jesus
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ExpoAcademiasEscritorioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo IDEquipo = alumno.getIDEquipo();
            if (IDEquipo != null) {
                IDEquipo = em.getReference(IDEquipo.getClass(), IDEquipo.getIDEquipo());
                alumno.setIDEquipo(IDEquipo);
            }
            em.persist(alumno);
            if (IDEquipo != null) {
                IDEquipo.getAlumnoList().add(alumno);
                IDEquipo = em.merge(IDEquipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getIDAlumno());
            Equipo IDEquipoOld = persistentAlumno.getIDEquipo();
            Equipo IDEquipoNew = alumno.getIDEquipo();
            if (IDEquipoNew != null) {
                IDEquipoNew = em.getReference(IDEquipoNew.getClass(), IDEquipoNew.getIDEquipo());
                alumno.setIDEquipo(IDEquipoNew);
            }
            alumno = em.merge(alumno);
            if (IDEquipoOld != null && !IDEquipoOld.equals(IDEquipoNew)) {
                IDEquipoOld.getAlumnoList().remove(alumno);
                IDEquipoOld = em.merge(IDEquipoOld);
            }
            if (IDEquipoNew != null && !IDEquipoNew.equals(IDEquipoOld)) {
                IDEquipoNew.getAlumnoList().add(alumno);
                IDEquipoNew = em.merge(IDEquipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = alumno.getIDAlumno();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
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
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getIDAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            Equipo IDEquipo = alumno.getIDEquipo();
            if (IDEquipo != null) {
                IDEquipo.getAlumnoList().remove(alumno);
                IDEquipo = em.merge(IDEquipo);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
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

    public Alumno findAlumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
