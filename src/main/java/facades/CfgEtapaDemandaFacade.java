/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CfgEtapaDemanda;
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
public class CfgEtapaDemandaFacade extends AbstractFacade<CfgEtapaDemanda> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CfgEtapaDemandaFacade() {
        super(CfgEtapaDemanda.class);
    }

    public CfgEtapaDemanda buscarPorTipoDemandaAndEtapaDemanda(int idTipoDemanda, int idEtapaDemanda) {
        try {
            Query query = em.createQuery("SELECT e FROM CfgEtapaDemanda e WHERE e.cfgEtapaDemandaPK.idTipoDemanda = ?1 AND e.cfgEtapaDemandaPK.idEtapaDemanda = ?2");
            query.setParameter(1, idTipoDemanda);
            query.setParameter(2, idEtapaDemanda);
            return (CfgEtapaDemanda) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<CfgEtapaDemanda> buscarPorTipoDemanda(int idTipoDemanda) {
        try {
            Query query = em.createQuery("SELECT e FROM CfgEtapaDemanda e WHERE e.cfgEtapaDemandaPK.idTipoDemanda = ?1");
            query.setParameter(1, idTipoDemanda);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
