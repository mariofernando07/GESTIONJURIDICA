/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mario
 */
@Embeddable
public class CfgEtapaDemandaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_demanda", nullable = false)
    private int idTipoDemanda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etapa_demanda", nullable = false)
    private int idEtapaDemanda;

    public CfgEtapaDemandaPK() {
    }

    public CfgEtapaDemandaPK(int idTipoDemanda, int idEtapaDemanda) {
        this.idTipoDemanda = idTipoDemanda;
        this.idEtapaDemanda = idEtapaDemanda;
    }

    public int getIdTipoDemanda() {
        return idTipoDemanda;
    }

    public void setIdTipoDemanda(int idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public int getIdEtapaDemanda() {
        return idEtapaDemanda;
    }

    public void setIdEtapaDemanda(int idEtapaDemanda) {
        this.idEtapaDemanda = idEtapaDemanda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoDemanda;
        hash += (int) idEtapaDemanda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgEtapaDemandaPK)) {
            return false;
        }
        CfgEtapaDemandaPK other = (CfgEtapaDemandaPK) object;
        if (this.idTipoDemanda != other.idTipoDemanda) {
            return false;
        }
        if (this.idEtapaDemanda != other.idEtapaDemanda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgEtapaDemandaPK[ idTipoDemanda=" + idTipoDemanda + ", idEtapaDemanda=" + idEtapaDemanda + " ]";
    }
    
}
