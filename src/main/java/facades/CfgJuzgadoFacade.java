/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CfgJuzgado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mario
 */
@Stateless
public class CfgJuzgadoFacade extends AbstractFacade<CfgJuzgado> {
    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CfgJuzgadoFacade() {
        super(CfgJuzgado.class);
    }
    
}
