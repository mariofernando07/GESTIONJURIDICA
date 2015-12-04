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
@Table(name = "cfg_tipo_soporte", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoSoporte.findAll", query = "SELECT c FROM CfgTipoSoporte c"),
    @NamedQuery(name = "CfgTipoSoporte.findByIdTipoSoporte", query = "SELECT c FROM CfgTipoSoporte c WHERE c.idTipoSoporte = :idTipoSoporte"),
    @NamedQuery(name = "CfgTipoSoporte.findByNomTipoSoporte", query = "SELECT c FROM CfgTipoSoporte c WHERE c.nomTipoSoporte = :nomTipoSoporte"),
    @NamedQuery(name = "CfgTipoSoporte.findByActivo", query = "SELECT c FROM CfgTipoSoporte c WHERE c.activo = :activo")})
public class CfgTipoSoporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_soporte", nullable = false)
    private Integer idTipoSoporte;
    @Size(max = 100)
    @Column(name = "nom_tipo_soporte", length = 100)
    private String nomTipoSoporte;
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgTipoSoporte")
    private List<CfgTipoSoporteTipoProceso> cfgTipoSoporteTipoProcesoList;

    public CfgTipoSoporte() {
    }

    public CfgTipoSoporte(Integer idTipoSoporte) {
        this.idTipoSoporte = idTipoSoporte;
    }

    public Integer getIdTipoSoporte() {
        return idTipoSoporte;
    }

    public void setIdTipoSoporte(Integer idTipoSoporte) {
        this.idTipoSoporte = idTipoSoporte;
    }

    public String getNomTipoSoporte() {
        return nomTipoSoporte;
    }

    public void setNomTipoSoporte(String nomTipoSoporte) {
        this.nomTipoSoporte = nomTipoSoporte;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<CfgTipoSoporteTipoProceso> getCfgTipoSoporteTipoProcesoList() {
        return cfgTipoSoporteTipoProcesoList;
    }

    public void setCfgTipoSoporteTipoProcesoList(List<CfgTipoSoporteTipoProceso> cfgTipoSoporteTipoProcesoList) {
        this.cfgTipoSoporteTipoProcesoList = cfgTipoSoporteTipoProcesoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSoporte != null ? idTipoSoporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoSoporte)) {
            return false;
        }
        CfgTipoSoporte other = (CfgTipoSoporte) object;
        if ((this.idTipoSoporte == null && other.idTipoSoporte != null) || (this.idTipoSoporte != null && !this.idTipoSoporte.equals(other.idTipoSoporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoSoporte[ idTipoSoporte=" + idTipoSoporte + " ]";
    }
    
}
