/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Ciclo;
import Entidades.Encargado;
import Entidades.Equipo;
import Entidades.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jesus
 */
public class ProyectoJpaController implements Serializable {

     public ProyectoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ExpoAcademiasEscritorioPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciclo IDCiclo = proyecto.getIDCiclo();
            if (IDCiclo != null) {
                IDCiclo = em.getReference(IDCiclo.getClass(), IDCiclo.getIDCiclo());
                proyecto.setIDCiclo(IDCiclo);
            }
            Encargado IDEncargado = proyecto.getIDEncargado();
            if (IDEncargado != null) {
                IDEncargado = em.getReference(IDEncargado.getClass(), IDEncargado.getIDEncargado());
                proyecto.setIDEncargado(IDEncargado);
            }
            Equipo IDEquipo = proyecto.getIDEquipo();
            if (IDEquipo != null) {
                IDEquipo = em.getReference(IDEquipo.getClass(), IDEquipo.getIDEquipo());
                proyecto.setIDEquipo(IDEquipo);
            }
            em.persist(proyecto);
            if (IDCiclo != null) {
                IDCiclo.getProyectoList().add(proyecto);
                IDCiclo = em.merge(IDCiclo);
            }
            if (IDEncargado != null) {
                IDEncargado.getProyectoList().add(proyecto);
                IDEncargado = em.merge(IDEncargado);
            }
            if (IDEquipo != null) {
                IDEquipo.getProyectoList().add(proyecto);
                IDEquipo = em.merge(IDEquipo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProyecto(proyecto.getIDProyecto()) != null) {
                throw new PreexistingEntityException("Proyecto " + proyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getIDProyecto());
            Ciclo IDCicloOld = persistentProyecto.getIDCiclo();
            Ciclo IDCicloNew = proyecto.getIDCiclo();
            Encargado IDEncargadoOld = persistentProyecto.getIDEncargado();
            Encargado IDEncargadoNew = proyecto.getIDEncargado();
            Equipo IDEquipoOld = persistentProyecto.getIDEquipo();
            Equipo IDEquipoNew = proyecto.getIDEquipo();
            if (IDCicloNew != null) {
                IDCicloNew = em.getReference(IDCicloNew.getClass(), IDCicloNew.getIDCiclo());
                proyecto.setIDCiclo(IDCicloNew);
            }
            if (IDEncargadoNew != null) {
                IDEncargadoNew = em.getReference(IDEncargadoNew.getClass(), IDEncargadoNew.getIDEncargado());
                proyecto.setIDEncargado(IDEncargadoNew);
            }
            if (IDEquipoNew != null) {
                IDEquipoNew = em.getReference(IDEquipoNew.getClass(), IDEquipoNew.getIDEquipo());
                proyecto.setIDEquipo(IDEquipoNew);
            }
            proyecto = em.merge(proyecto);
            if (IDCicloOld != null && !IDCicloOld.equals(IDCicloNew)) {
                IDCicloOld.getProyectoList().remove(proyecto);
                IDCicloOld = em.merge(IDCicloOld);
            }
            if (IDCicloNew != null && !IDCicloNew.equals(IDCicloOld)) {
                IDCicloNew.getProyectoList().add(proyecto);
                IDCicloNew = em.merge(IDCicloNew);
            }
            if (IDEncargadoOld != null && !IDEncargadoOld.equals(IDEncargadoNew)) {
                IDEncargadoOld.getProyectoList().remove(proyecto);
                IDEncargadoOld = em.merge(IDEncargadoOld);
            }
            if (IDEncargadoNew != null && !IDEncargadoNew.equals(IDEncargadoOld)) {
                IDEncargadoNew.getProyectoList().add(proyecto);
                IDEncargadoNew = em.merge(IDEncargadoNew);
            }
            if (IDEquipoOld != null && !IDEquipoOld.equals(IDEquipoNew)) {
                IDEquipoOld.getProyectoList().remove(proyecto);
                IDEquipoOld = em.merge(IDEquipoOld);
            }
            if (IDEquipoNew != null && !IDEquipoNew.equals(IDEquipoOld)) {
                IDEquipoNew.getProyectoList().add(proyecto);
                IDEquipoNew = em.merge(IDEquipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyecto.getIDProyecto();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getIDProyecto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            Ciclo IDCiclo = proyecto.getIDCiclo();
            if (IDCiclo != null) {
                IDCiclo.getProyectoList().remove(proyecto);
                IDCiclo = em.merge(IDCiclo);
            }
            Encargado IDEncargado = proyecto.getIDEncargado();
            if (IDEncargado != null) {
                IDEncargado.getProyectoList().remove(proyecto);
                IDEncargado = em.merge(IDEncargado);
            }
            Equipo IDEquipo = proyecto.getIDEquipo();
            if (IDEquipo != null) {
                IDEquipo.getProyectoList().remove(proyecto);
                IDEquipo = em.merge(IDEquipo);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
