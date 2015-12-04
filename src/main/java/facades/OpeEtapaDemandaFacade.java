/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeDemanda;
import entities.OpeEtapaDemanda;
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
public class OpeEtapaDemandaFacade extends AbstractFacade<OpeEtapaDemanda> {
    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeEtapaDemandaFacade() {
        super(OpeEtapaDemanda.class);
    }
    
    public List<OpeEtapaDemanda> buscarPorDemanda(OpeDemanda demanda){
        try {
            Query query = em.createQuery("SELECT e FROM OpeEtapaDemanda e WHERE e.idDemanda = ?1");
            query.setParameter(1, demanda);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
