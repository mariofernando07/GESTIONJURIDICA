/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CfgTipoProceso;
import entities.CfgTipoSoporteTipoProceso;
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
public class CfgTipoSoporteTipoProcesoFacade extends AbstractFacade<CfgTipoSoporteTipoProceso> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CfgTipoSoporteTipoProcesoFacade() {
        super(CfgTipoSoporteTipoProceso.class);
    }

    public List<CfgTipoSoporteTipoProceso> buscarTipoSoportePorTipoProceso(CfgTipoProceso tipoProceso) {
        try {
            Query query = em.createQuery("SELECT ts FROM CfgTipoSoporteTipoProceso ts WHERE ts.cfgTipoProceso = ?1");
            query.setParameter(1, tipoProceso);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public CfgTipoSoporteTipoProceso buscarPorIdTipoSoporteAndIdTipoProceso(int tipoSoporte, int tipoProceso) {
        try {
            Query query = em.createQuery("SELECT sp FROM CfgTipoSoporteTipoProceso sp WHERE sp.cfgTipoProceso.idTipoProceso = ?1 AND sp.cfgTipoSoporte.idTipoSoporte = ?2");
            query.setParameter(1, tipoProceso);
            query.setParameter(2, tipoSoporte);
            return (CfgTipoSoporteTipoProceso) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
