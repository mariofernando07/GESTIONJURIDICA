/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SegUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mario
 */
@Stateless
public class SegUsuarioFacade extends AbstractFacade<SegUsuario> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegUsuarioFacade() {
        super(SegUsuario.class);
    }

    public List<SegUsuario> buscarAbogadosActivos() {
        try {
            Query query = em.createQuery("SELECT a FROM SegUsuario a WHERE a.abogado = TRUE AND a.activo = TRUE");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public List<SegUsuario> buscarAbogados() {
        try {
            Query query = em.createQuery("SELECT a FROM SegUsuario a WHERE a.abogado = TRUE");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
    
    public SegUsuario buscarUsuario(String usuario, String pasword) {
        try {
            Query query = em.createQuery("SELECT u FROM SegUsuario u WHERE u.usuario = ?1 AND u.password = ?2 AND u.activo = TRUE");
            query.setParameter(1, usuario);
            query.setParameter(2, pasword);
            return (SegUsuario) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }    

}
