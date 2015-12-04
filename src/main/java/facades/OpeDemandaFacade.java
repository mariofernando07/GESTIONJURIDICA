/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeDemanda;
import entities.OpeProceso;
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
public class OpeDemandaFacade extends AbstractFacade<OpeDemanda> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeDemandaFacade() {
        super(OpeDemanda.class);
    }

    public OpeDemanda buscarDemandaPorProceso(OpeProceso proceso) {
        try {
            Query query = em.createQuery("SELECT d FROM OpeDemanda d WHERE d.idProceso = ?1");
            query.setParameter(1, proceso);
            return (OpeDemanda) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<OpeDemanda> buscarDemandasPorProcesos(List<Integer> idProcesos) {
        try {
            Query query = em.createQuery("SELECT d FROM OpeDemanda d WHERE d.idProceso.idProceso IN ?1");
            query.setParameter(1, idProcesos);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

}
