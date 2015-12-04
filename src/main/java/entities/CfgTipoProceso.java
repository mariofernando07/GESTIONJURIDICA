/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cfg_tipo_proceso", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoProceso.findAll", query = "SELECT c FROM CfgTipoProceso c"),
    @NamedQuery(name = "CfgTipoProceso.findByIdTipoProceso", query = "SELECT c FROM CfgTipoProceso c WHERE c.idTipoProceso = :idTipoProceso"),
    @NamedQuery(name = "CfgTipoProceso.findByNomTipoProceso", query = "SELECT c FROM CfgTipoProceso c WHERE c.nomTipoProceso = :nomTipoProceso")})
public class CfgTipoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_proceso", nullable = false)
    private Integer idTipoProceso;
    @Size(max = 100)
    @Column(name = "nom_tipo_proceso", length = 100)
    private String nomTipoProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgTipoProceso")
    private List<CfgTipoSoporteTipoProceso> cfgTipoSoporteTipoProcesoList;
    @OneToMany(mappedBy = "idTipoProceso")
    private List<OpeProceso> opeProcesoList;

    public CfgTipoProceso() {
    }

    public CfgTipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public Integer getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public String getNomTipoProceso() {
        return nomTipoProceso;
    }

    public void setNomTipoProceso(String nomTipoProceso) {
        this.nomTipoProceso = nomTipoProceso;
    }

    @XmlTransient
    public List<CfgTipoSoporteTipoProceso> getCfgTipoSoporteTipoProcesoList() {
        return cfgTipoSoporteTipoProcesoList;
    }

    public void setCfgTipoSoporteTipoProcesoList(List<CfgTipoSoporteTipoProceso> cfgTipoSoporteTipoProcesoList) {
        this.cfgTipoSoporteTipoProcesoList = cfgTipoSoporteTipoProcesoList;
    }

    @XmlTransient
    public List<OpeProceso> getOpeProcesoList() {
        return opeProcesoList;
    }

    public void setOpeProcesoList(List<OpeProceso> opeProcesoList) {
        this.opeProcesoList = opeProcesoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProceso != null ? idTipoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoProceso)) {
            return false;
        }
        CfgTipoProceso other = (CfgTipoProceso) object;
        if ((this.idTipoProceso == null && other.idTipoProceso != null) || (this.idTipoProceso != null && !this.idTipoProceso.equals(other.idTipoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoProceso[ idTipoProceso=" + idTipoProceso + " ]";
    }
    
}
