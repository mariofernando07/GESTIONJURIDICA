/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cfg_tipo_soporte_tipo_proceso", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoSoporteTipoProceso.findAll", query = "SELECT c FROM CfgTipoSoporteTipoProceso c"),
    @NamedQuery(name = "CfgTipoSoporteTipoProceso.findByIdTipoProceso", query = "SELECT c FROM CfgTipoSoporteTipoProceso c WHERE c.cfgTipoSoporteTipoProcesoPK.idTipoProceso = :idTipoProceso"),
    @NamedQuery(name = "CfgTipoSoporteTipoProceso.findByActivo", query = "SELECT c FROM CfgTipoSoporteTipoProceso c WHERE c.activo = :activo"),
    @NamedQuery(name = "CfgTipoSoporteTipoProceso.findByReqDocumento", query = "SELECT c FROM CfgTipoSoporteTipoProceso c WHERE c.reqDocumento = :reqDocumento"),
    @NamedQuery(name = "CfgTipoSoporteTipoProceso.findByIdTipoSoporte", query = "SELECT c FROM CfgTipoSoporteTipoProceso c WHERE c.cfgTipoSoporteTipoProcesoPK.idTipoSoporte = :idTipoSoporte")})
public class CfgTipoSoporteTipoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CfgTipoSoporteTipoProcesoPK cfgTipoSoporteTipoProcesoPK;
    private Boolean activo;
    @Column(name = "req_documento")
    private Boolean reqDocumento;
    @JoinColumn(name = "id_tipo_proceso", referencedColumnName = "id_tipo_proceso", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CfgTipoProceso cfgTipoProceso;
    @JoinColumn(name = "id_tipo_soporte", referencedColumnName = "id_tipo_soporte", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CfgTipoSoporte cfgTipoSoporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgTipoSoporteTipoProceso")
    private List<OpeProcesoSoporte> opeProcesoSoporteList;

    public CfgTipoSoporteTipoProceso() {
    }

    public CfgTipoSoporteTipoProceso(CfgTipoSoporteTipoProcesoPK cfgTipoSoporteTipoProcesoPK) {
        this.cfgTipoSoporteTipoProcesoPK = cfgTipoSoporteTipoProcesoPK;
    }

    public CfgTipoSoporteTipoProceso(int idTipoProceso, int idTipoSoporte) {
        this.cfgTipoSoporteTipoProcesoPK = new CfgTipoSoporteTipoProcesoPK(idTipoProceso, idTipoSoporte);
    }

    public CfgTipoSoporteTipoProcesoPK getCfgTipoSoporteTipoProcesoPK() {
        return cfgTipoSoporteTipoProcesoPK;
    }

    public void setCfgTipoSoporteTipoProcesoPK(CfgTipoSoporteTipoProcesoPK cfgTipoSoporteTipoProcesoPK) {
        this.cfgTipoSoporteTipoProcesoPK = cfgTipoSoporteTipoProcesoPK;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getReqDocumento() {
        return reqDocumento;
    }

    public void setReqDocumento(Boolean reqDocumento) {
        this.reqDocumento = reqDocumento;
    }

    public CfgTipoProceso getCfgTipoProceso() {
        return cfgTipoProceso;
    }

    public void setCfgTipoProceso(CfgTipoProceso cfgTipoProceso) {
        this.cfgTipoProceso = cfgTipoProceso;
    }

    public CfgTipoSoporte getCfgTipoSoporte() {
        return cfgTipoSoporte;
    }

    public void setCfgTipoSoporte(CfgTipoSoporte cfgTipoSoporte) {
        this.cfgTipoSoporte = cfgTipoSoporte;
    }

    @XmlTransient
    public List<OpeProcesoSoporte> getOpeProcesoSoporteList() {
        return opeProcesoSoporteList;
    }

    public void setOpeProcesoSoporteList(List<OpeProcesoSoporte> opeProcesoSoporteList) {
        this.opeProcesoSoporteList = opeProcesoSoporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cfgTipoSoporteTipoProcesoPK != null ? cfgTipoSoporteTipoProcesoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoSoporteTipoProceso)) {
            return false;
        }
        CfgTipoSoporteTipoProceso other = (CfgTipoSoporteTipoProceso) object;
        if ((this.cfgTipoSoporteTipoProcesoPK == null && other.cfgTipoSoporteTipoProcesoPK != null) || (this.cfgTipoSoporteTipoProcesoPK != null && !this.cfgTipoSoporteTipoProcesoPK.equals(other.cfgTipoSoporteTipoProcesoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoSoporteTipoProceso[ cfgTipoSoporteTipoProcesoPK=" + cfgTipoSoporteTipoProcesoPK + " ]";
    }
    
}
