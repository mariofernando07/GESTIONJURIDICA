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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cfg_departamento", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgDepartamento.findAll", query = "SELECT c FROM CfgDepartamento c"),
    @NamedQuery(name = "CfgDepartamento.findByCodDepartamento", query = "SELECT c FROM CfgDepartamento c WHERE c.codDepartamento = :codDepartamento"),
    @NamedQuery(name = "CfgDepartamento.findByNomDepartamento", query = "SELECT c FROM CfgDepartamento c WHERE c.nomDepartamento = :nomDepartamento")})
public class CfgDepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_departamento", nullable = false, length = 2)
    private String codDepartamento;
    @Size(max = 100)
    @Column(name = "nom_departamento", length = 100)
    private String nomDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgDepartamento")
    private List<CfgMunicipio> cfgMunicipioList;

    public CfgDepartamento() {
    }

    public CfgDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNomDepartamento() {
        return nomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }

    @XmlTransient
    public List<CfgMunicipio> getCfgMunicipioList() {
        return cfgMunicipioList;
    }

    public void setCfgMunicipioList(List<CfgMunicipio> cfgMunicipioList) {
        this.cfgMunicipioList = cfgMunicipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDepartamento != null ? codDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgDepartamento)) {
            return false;
        }
        CfgDepartamento other = (CfgDepartamento) object;
        if ((this.codDepartamento == null && other.codDepartamento != null) || (this.codDepartamento != null && !this.codDepartamento.equals(other.codDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgDepartamento[ codDepartamento=" + codDepartamento + " ]";
    }
    
}
