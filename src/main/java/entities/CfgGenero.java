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
@Table(name = "cfg_genero", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgGenero.findAll", query = "SELECT c FROM CfgGenero c"),
    @NamedQuery(name = "CfgGenero.findByIdGenero", query = "SELECT c FROM CfgGenero c WHERE c.idGenero = :idGenero"),
    @NamedQuery(name = "CfgGenero.findByNomGenero", query = "SELECT c FROM CfgGenero c WHERE c.nomGenero = :nomGenero")})
public class CfgGenero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero", nullable = false)
    private Integer idGenero;
    @Size(max = 20)
    @Column(name = "nom_genero", length = 20)
    private String nomGenero;
    @OneToMany(mappedBy = "idGenero")
    private List<OpeCliente> opeClienteList;

    public CfgGenero() {
    }

    public CfgGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNomGenero() {
        return nomGenero;
    }

    public void setNomGenero(String nomGenero) {
        this.nomGenero = nomGenero;
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
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgGenero)) {
            return false;
        }
        CfgGenero other = (CfgGenero) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgGenero[ idGenero=" + idGenero + " ]";
    }
    
}
