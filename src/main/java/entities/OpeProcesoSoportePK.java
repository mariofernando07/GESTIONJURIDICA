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
import javax.validation.constraints.Size;

/**
 *
 * @author Mario
 */
@Embeddable
public class OpeProcesoSoportePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proceso", nullable = false)
    private long idProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_proceso", nullable = false)
    private int idTipoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_soporte", nullable = false)
    private int idTipoSoporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String ruta;

    public OpeProcesoSoportePK() {
    }

    public OpeProcesoSoportePK(long idProceso, int idTipoProceso, int idTipoSoporte, String ruta) {
        this.idProceso = idProceso;
        this.idTipoProceso = idTipoProceso;
        this.idTipoSoporte = idTipoSoporte;
        this.ruta = ruta;
    }

    public long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(long idProceso) {
        this.idProceso = idProceso;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProceso;
        hash += (int) idTipoProceso;
        hash += (int) idTipoSoporte;
        hash += (ruta != null ? ruta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeProcesoSoportePK)) {
            return false;
        }
        OpeProcesoSoportePK other = (OpeProcesoSoportePK) object;
        if (this.idProceso != other.idProceso) {
            return false;
        }
        if (this.idTipoProceso != other.idTipoProceso) {
            return false;
        }
        if (this.idTipoSoporte != other.idTipoSoporte) {
            return false;
        }
        if ((this.ruta == null && other.ruta != null) || (this.ruta != null && !this.ruta.equals(other.ruta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeProcesoSoportePK[ idProceso=" + idProceso + ", idTipoProceso=" + idTipoProceso + ", idTipoSoporte=" + idTipoSoporte + ", ruta=" + ruta + " ]";
    }
    
}
