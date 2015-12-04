/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cfg_municipio", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgMunicipio.findAll", query = "SELECT c FROM CfgMunicipio c"),
    @NamedQuery(name = "CfgMunicipio.findByCodDepartamento", query = "SELECT c FROM CfgMunicipio c WHERE c.cfgMunicipioPK.codDepartamento = :codDepartamento"),
    @NamedQuery(name = "CfgMunicipio.findByCodMunicipio", query = "SELECT c FROM CfgMunicipio c WHERE c.cfgMunicipioPK.codMunicipio = :codMunicipio"),
    @NamedQuery(name = "CfgMunicipio.findByNomMunicipio", query = "SELECT c FROM CfgMunicipio c WHERE c.nomMunicipio = :nomMunicipio")})
public class CfgMunicipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CfgMunicipioPK cfgMunicipioPK;
    @Size(max = 100)
    @Column(name = "nom_municipio", length = 100)
    private String nomMunicipio;
    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CfgDepartamento cfgDepartamento;
    @OneToMany(mappedBy = "cfgMunicipio")
    private List<CfgJuzgado> cfgJuzgadoList;
    @OneToMany(mappedBy = "cfgMunicipio")
    private List<OpeSocializacion> opeSocializacionList;
    @OneToMany(mappedBy = "cfgMunicipio")
    private List<OpeCliente> opeClienteList;

    public CfgMunicipio() {
    }

    public CfgMunicipio(CfgMunicipioPK cfgMunicipioPK) {
        this.cfgMunicipioPK = cfgMunicipioPK;
    }

    public CfgMunicipio(String codDepartamento, String codMunicipio) {
        this.cfgMunicipioPK = new CfgMunicipioPK(codDepartamento, codMunicipio);
    }

    public CfgMunicipioPK getCfgMunicipioPK() {
        return cfgMunicipioPK;
    }

    public void setCfgMunicipioPK(CfgMunicipioPK cfgMunicipioPK) {
        this.cfgMunicipioPK = cfgMunicipioPK;
    }

    public String getNomMunicipio() {
        return nomMunicipio;
    }

    public void setNomMunicipio(String nomMunicipio) {
        this.nomMunicipio = nomMunicipio;
    }

    public CfgDepartamento getCfgDepartamento() {
        return cfgDepartamento;
    }

    public void setCfgDepartamento(CfgDepartamento cfgDepartamento) {
        this.cfgDepartamento = cfgDepartamento;
    }

    @XmlTransient
    public List<CfgJuzgado> getCfgJuzgadoList() {
        return cfgJuzgadoList;
    }

    public void setCfgJuzgadoList(List<CfgJuzgado> cfgJuzgadoList) {
        this.cfgJuzgadoList = cfgJuzgadoList;
    }

    @XmlTransient
    public List<OpeSocializacion> getOpeSocializacionList() {
        return opeSocializacionList;
    }

    public void setOpeSocializacionList(List<OpeSocializacion> opeSocializacionList) {
        this.opeSocializacionList = opeSocializacionList;
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
        hash += (cfgMunicipioPK != null ? cfgMunicipioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgMunicipio)) {
            return false;
        }
        CfgMunicipio other = (CfgMunicipio) object;
        if ((this.cfgMunicipioPK == null && other.cfgMunicipioPK != null) || (this.cfgMunicipioPK != null && !this.cfgMunicipioPK.equals(other.cfgMunicipioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgMunicipio[ cfgMunicipioPK=" + cfgMunicipioPK + " ]";
    }
    
}
