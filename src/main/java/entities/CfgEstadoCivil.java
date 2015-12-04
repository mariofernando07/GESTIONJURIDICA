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
@Table(name = "cfg_estado_civil", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgEstadoCivil.findAll", query = "SELECT c FROM CfgEstadoCivil c"),
    @NamedQuery(name = "CfgEstadoCivil.findByIdEstadoCivil", query = "SELECT c FROM CfgEstadoCivil c WHERE c.idEstadoCivil = :idEstadoCivil"),
    @NamedQuery(name = "CfgEstadoCivil.findByNomEstadoCivil", query = "SELECT c FROM CfgEstadoCivil c WHERE c.nomEstadoCivil = :nomEstadoCivil")})
public class CfgEstadoCivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_civil", nullable = false)
    private Integer idEstadoCivil;
    @Size(max = 100)
    @Column(name = "nom_estado_civil", length = 100)
    private String nomEstadoCivil;
    @OneToMany(mappedBy = "idEstadoCivil")
    private List<OpeCliente> opeClienteList;

    public CfgEstadoCivil() {
    }

    public CfgEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getNomEstadoCivil() {
        return nomEstadoCivil;
    }

    public void setNomEstadoCivil(String nomEstadoCivil) {
        this.nomEstadoCivil = nomEstadoCivil;
    }

    @XmlTransient
    public List<OpeCliente> getOpeClienteList() {
        return opeClienteList;
    }

    public void setOpeClienteList(List<OpeCliente> opeClienteList) {
        this.opeClienteList = opeClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCivil != null ? idEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgEstadoCivil)) {
            return false;
        }
        CfgEstadoCivil other = (CfgEstadoCivil) object;
        if ((this.idEstadoCivil == null && other.idEstadoCivil != null) || (this.idEstadoCivil != null && !this.idEstadoCivil.equals(other.idEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgEstadoCivil[ idEstadoCivil=" + idEstadoCivil + " ]";
    }
    
}
