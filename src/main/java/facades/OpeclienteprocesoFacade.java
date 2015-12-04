/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.OpeProceso;
import entities.Opeclienteproceso;
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
public class OpeclienteprocesoFacade extends AbstractFacade<Opeclienteproceso> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpeclienteprocesoFacade() {
        super(Opeclienteproceso.class);
    }
   
    public List<Integer> buscarClientesPorProceso(long proceso) {
        try {
            Query query = em.createQuery("SELECT o.opeclienteprocesoPK.idCliente FROM Opeclienteproceso o WHERE o.opeclienteprocesoPK.idProceso = ?1", Integer.class);
            query.setParameter(1, proceso);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }   
    }

    public List<Integer> buscarProcesosPorCliente(int cliente) {
        try {
            Query query = em.createQuery("SELECT o.opeclienteprocesoPK.idProceso FROM Opeclienteproceso o WHERE o.opeclienteprocesoPK.idCliente = ?1", Integer.class);
            query.setParameter(1, cliente);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }   
    }
    
    public Opeclienteproceso buscarPorClienteAndProceso(int cliente, long proceso){
        try {
            Query query = em.createQuery("SELECT o FROM Opeclienteproceso o WHERE o.opeclienteprocesoPK.idCliente = ?1 AND o.opeclienteprocesoPK.idProceso = ?2");
            query.setParameter(1, cliente);
            query.setParameter(2, proceso);
            return (Opeclienteproceso) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
}
