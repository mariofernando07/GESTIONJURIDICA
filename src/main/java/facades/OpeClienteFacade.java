/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeCliente;
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
public class OpeClienteFacade extends AbstractFacade<OpeCliente> {
    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeClienteFacade() {
        super(OpeCliente.class);
    }
    
    public List<OpeCliente> buscarClientesPorIdentificadores(List<Integer> identificadores){
        try {
            Query query = em.createQuery("SELECT c FROM OpeCliente c WHERE c.idCliente IN ?1");
            query.setParameter(1, identificadores);
            return query.getResultList();            
        } catch (Exception e) {
            return null;
        }
    }
    
}
