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
public class CfgTipoSoporteTipoProcesoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_proceso", nullable = false)
    private int idTipoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_soporte", nullable = false)
    private int idTipoSoporte;

    public CfgTipoSoporteTipoProcesoPK() {
    }

    public CfgTipoSoporteTipoProcesoPK(int idTipoProceso, int idTipoSoporte) {
        this.idTipoProceso = idTipoProceso;
        this.idTipoSoporte = idTipoSoporte;
    }

    public int getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(int idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public int getIdTipoSoporte() {
        return idTipoSoporte;
    }

    public void setIdTipoSoporte(int idTipoSoporte) {
        this.idTipoSoporte = idTipoSoporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoProceso;
        hash += (int) idTipoSoporte;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoSoporteTipoProcesoPK)) {
            return false;
        }
        CfgTipoSoporteTipoProcesoPK other = (CfgTipoSoporteTipoProcesoPK) object;
        if (this.idTipoProceso != other.idTipoProceso) {
            return false;
        }
        if (this.idTipoSoporte != other.idTipoSoporte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoSoporteTipoProcesoPK[ idTipoProceso=" + idTipoProceso + ", idTipoSoporte=" + idTipoSoporte + " ]";
    }
    
}
