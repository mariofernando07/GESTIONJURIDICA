/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "cfg_estado_proceso", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgEstadoProceso.findAll", query = "SELECT c FROM CfgEstadoProceso c"),
    @NamedQuery(name = "CfgEstadoProceso.findByIdEstadoProceso", query = "SELECT c FROM CfgEstadoProceso c WHERE c.idEstadoProceso = :idEstadoProceso"),
    @NamedQuery(name = "CfgEstadoProceso.findByNomEstadoProceso", query = "SELECT c FROM CfgEstadoProceso c WHERE c.nomEstadoProceso = :nomEstadoProceso")})
public class CfgEstadoProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_proceso", nullable = false)
    private Integer idEstadoProceso;
    @Size(max = 20)
    @Column(name = "nom_estado_proceso", length = 20)
    private String nomEstadoProceso;
    @OneToMany(mappedBy = "idEstadoProceso")
    private List<OpeProceso> opeProcesoList;

    public CfgEstadoProceso() {
    }

    public CfgEstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public Integer getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public String getNomEstadoProceso() {
        return nomEstadoProceso;
    }

    public void setNomEstadoProceso(String nomEstadoProceso) {
        this.nomEstadoProceso = nomEstadoProceso;
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
        hash += (idEstadoProceso != null ? idEstadoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgEstadoProceso)) {
            return false;
        }
        CfgEstadoProceso other = (CfgEstadoProceso) object;
        if ((this.idEstadoProceso == null && other.idEstadoProceso != null) || (this.idEstadoProceso != null && !this.idEstadoProceso.equals(other.idEstadoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgEstadoProceso[ idEstadoProceso=" + idEstadoProceso + " ]";
    }
    
}
