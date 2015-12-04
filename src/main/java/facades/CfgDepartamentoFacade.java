/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CfgDepartamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mario
 */
@Stateless
public class CfgDepartamentoFacade extends AbstractFacade<CfgDepartamento> {
    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CfgDepartamentoFacade() {
        super(CfgDepartamento.class);
    }
    
}
