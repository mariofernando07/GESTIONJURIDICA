/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeProceso;
import entities.OpeProcesoSoporte;
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
public class OpeProcesoSoporteFacade extends AbstractFacade<OpeProcesoSoporte> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeProcesoSoporteFacade() {
        super(OpeProcesoSoporte.class);
    }

    public List<OpeProcesoSoporte> buscarSoportesPorProceso(OpeProceso proceso) {
        try {
            Query query = em.createQuery("SELECT s FROM OpeProcesoSoporte s WHERE s.opeProceso = ?1");
            query.setParameter(1, proceso);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
