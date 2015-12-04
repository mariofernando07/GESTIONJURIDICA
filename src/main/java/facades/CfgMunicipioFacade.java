/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CfgMunicipio;
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
public class CfgMunicipioFacade extends AbstractFacade<CfgMunicipio> {

    @PersistenceContext(unitName = "coesias_GESTIONJURIDICA_war_coesiasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CfgMunicipioFacade() {
        super(CfgMunicipio.class);
    }

    public List<CfgMunicipio> buscarMunicipiosPorDepartamento(String codDepartamento) {
        try {
            Query query = em.createQuery("SELECT m FROM CfgMunicipio m WHERE m.cfgMunicipioPK.codDepartamento LIKE ?1 ORDER BY m.nomMunicipio");
            query.setParameter(1, codDepartamento);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public CfgMunicipio buscarMunicipiosPorDepartamentoAndMunicipio(String codDepartamento, String codMunicipio) {
        try {
            Query query = em.createQuery("SELECT m FROM CfgMunicipio m WHERE m.cfgMunicipioPK.codDepartamento LIKE ?1 AND m.cfgMunicipioPK.codMunicipio LIKE ?2");
            query.setParameter(1, codDepartamento);
            query.setParameter(2, codMunicipio);
            return (CfgMunicipio) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
