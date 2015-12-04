/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeCliente;
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
public class OpeProcesoFacade extends AbstractFacade<OpeProceso> {
    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeProcesoFacade() {
        super(OpeProceso.class);
    }
    
    public List<OpeProceso> buscarProcesosPorIdentificadores(List<Integer> identificadores){
        try {
            Query query = em.createQuery("SELECT p FROM OpeProceso p WHERE p.idProceso IN ?1");
            query.setParameter(1, identificadores);
            return query.getResultList();          
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<OpeProceso> buscarProcesosAprobadosPorIdentificadores(List<Integer> identificadores){
        try {
            Query query = em.createQuery("SELECT p FROM OpeProceso p WHERE p.idProceso IN ?1 AND p.idEstadoProceso.idEstadoProceso = 2");
            query.setParameter(1, identificadores);
            return query.getResultList();          
        } catch (Exception e) {
            return null;
        }
    }
    
}
