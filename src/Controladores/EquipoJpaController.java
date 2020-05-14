/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Proyecto;
import java.util.ArrayList;
import java.util.List;
import Entidades.Alumno;
import Entidades.Equipo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jesus
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ExpoAcademiasEscritorioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void create(Equipo equipo) throws PreexistingEntityException, Exception {
        if (equipo.getProyectoList() == null) {
            equipo.setProyectoList(new ArrayList<Proyecto>());
        }
        if (equipo.getAlumnoList() == null) {
            equipo.setAlumnoList(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Proyecto> attachedProyectoList = new ArrayList<Proyecto>();
            for (Proyecto proyectoListProyectoToAttach : equipo.getProyectoList()) {
                proyectoListProyectoToAttach = em.getReference(proyectoListProyectoToAttach.getClass(), proyectoListProyectoToAttach.getIDProyecto());
                attachedProyectoList.add(proyectoListProyectoToAttach);
            }
            equipo.setProyectoList(attachedProyectoList);
            List<Alumno> attachedAlumnoList = new ArrayList<Alumno>();
            for (Alumno alumnoListAlumnoToAttach : equipo.getAlumnoList()) {
                alumnoListAlumnoToAttach = em.getReference(alumnoListAlumnoToAttach.getClass(), alumnoListAlumnoToAttach.getIDAlumno());
                attachedAlumnoList.add(alumnoListAlumnoToAttach);
            }
            equipo.setAlumnoList(attachedAlumnoList);
            em.persist(equipo);
            for (Proyecto proyectoListProyecto : equipo.getProyectoList()) {
                Equipo oldIDEquipoOfProyectoListProyecto = proyectoListProyecto.getIDEquipo();
                proyectoListProyecto.setIDEquipo(equipo);
                proyectoListProyecto = em.merge(proyectoListProyecto);
                if (oldIDEquipoOfProyectoListProyecto != null) {
                    oldIDEquipoOfProyectoListProyecto.getProyectoList().remove(proyectoListProyecto);
                    oldIDEquipoOfProyectoListProyecto = em.merge(oldIDEquipoOfProyectoListProyecto);
                }
            }
            for (Alumno alumnoListAlumno : equipo.getAlumnoList()) {
                Equipo oldIDEquipoOfAlumnoListAlumno = alumnoListAlumno.getIDEquipo();
                alumnoListAlumno.setIDEquipo(equipo);
                alumnoListAlumno = em.merge(alumnoListAlumno);
                if (oldIDEquipoOfAlumnoListAlumno != null) {
                    oldIDEquipoOfAlumnoListAlumno.getAlumnoList().remove(alumnoListAlumno);
                    oldIDEquipoOfAlumnoListAlumno = em.merge(oldIDEquipoOfAlumnoListAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipo(equipo.getIDEquipo()) != null) {
                throw new PreexistingEntityException("Equipo " + equipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getIDEquipo());
            List<Proyecto> proyectoListOld = persistentEquipo.getProyectoList();
            List<Proyecto> proyectoListNew = equipo.getProyectoList();
            List<Alumno> alumnoListOld = persistentEquipo.getAlumnoList();
            List<Alumno> alumnoListNew = equipo.getAlumnoList();
            List<String> illegalOrphanMessages = null;
            for (Alumno alumnoListOldAlumno : alumnoListOld) {
                if (!alumnoListNew.contains(alumnoListOldAlumno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Alumno " + alumnoListOldAlumno + " since its IDEquipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Proyecto> attachedProyectoListNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoListNewProyectoToAttach : proyectoListNew) {
                proyectoListNewProyectoToAttach = em.getReference(proyectoListNewProyectoToAttach.getClass(), proyectoListNewProyectoToAttach.getIDProyecto());
                attachedProyectoListNew.add(proyectoListNewProyectoToAttach);
            }
            proyectoListNew = attachedProyectoListNew;
            equipo.setProyectoList(proyectoListNew);
            List<Alumno> attachedAlumnoListNew = new ArrayList<Alumno>();
            for (Alumno alumnoListNewAlumnoToAttach : alumnoListNew) {
                alumnoListNewAlumnoToAttach = em.getReference(alumnoListNewAlumnoToAttach.getClass(), alumnoListNewAlumnoToAttach.getIDAlumno());
                attachedAlumnoListNew.add(alumnoListNewAlumnoToAttach);
            }
            alumnoListNew = attachedAlumnoListNew;
            equipo.setAlumnoList(alumnoListNew);
            equipo = em.merge(equipo);
            for (Proyecto proyectoListOldProyecto : proyectoListOld) {
                if (!proyectoListNew.contains(proyectoListOldProyecto)) {
                    proyectoListOldProyecto.setIDEquipo(null);
                    proyectoListOldProyecto = em.merge(proyectoListOldProyecto);
                }
            }
            for (Proyecto proyectoListNewProyecto : proyectoListNew) {
                if (!proyectoListOld.contains(proyectoListNewProyecto)) {
                    Equipo oldIDEquipoOfProyectoListNewProyecto = proyectoListNewProyecto.getIDEquipo();
                    proyectoListNewProyecto.setIDEquipo(equipo);
                    proyectoListNewProyecto = em.merge(proyectoListNewProyecto);
                    if (oldIDEquipoOfProyectoListNewProyecto != null && !oldIDEquipoOfProyectoListNewProyecto.equals(equipo)) {
                        oldIDEquipoOfProyectoListNewProyecto.getProyectoList().remove(proyectoListNewProyecto);
                        oldIDEquipoOfProyectoListNewProyecto = em.merge(oldIDEquipoOfProyectoListNewProyecto);
                    }
                }
            }
            for (Alumno alumnoListNewAlumno : alumnoListNew) {
                if (!alumnoListOld.contains(alumnoListNewAlumno)) {
                    Equipo oldIDEquipoOfAlumnoListNewAlumno = alumnoListNewAlumno.getIDEquipo();
                    alumnoListNewAlumno.setIDEquipo(equipo);
                    alumnoListNewAlumno = em.merge(alumnoListNewAlumno);
                    if (oldIDEquipoOfAlumnoListNewAlumno != null && !oldIDEquipoOfAlumnoListNewAlumno.equals(equipo)) {
                        oldIDEquipoOfAlumnoListNewAlumno.getAlumnoList().remove(alumnoListNewAlumno);
                        oldIDEquipoOfAlumnoListNewAlumno = em.merge(oldIDEquipoOfAlumnoListNewAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipo.getIDEquipo();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getIDEquipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Alumno> alumnoListOrphanCheck = equipo.getAlumnoList();
            for (Alumno alumnoListOrphanCheckAlumno : alumnoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Alumno " + alumnoListOrphanCheckAlumno + " in its alumnoList field has a non-nullable IDEquipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Proyecto> proyectoList = equipo.getProyectoList();
            for (Proyecto proyectoListProyecto : proyectoList) {
                proyectoListProyecto.setIDEquipo(null);
                proyectoListProyecto = em.merge(proyectoListProyecto);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
