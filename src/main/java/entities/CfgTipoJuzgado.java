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
@Table(name = "cfg_tipo_juzgado", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoJuzgado.findAll", query = "SELECT c FROM CfgTipoJuzgado c"),
    @NamedQuery(name = "CfgTipoJuzgado.findByIdTipoJuzgado", query = "SELECT c FROM CfgTipoJuzgado c WHERE c.idTipoJuzgado = :idTipoJuzgado"),
    @NamedQuery(name = "CfgTipoJuzgado.findByNomTipoJuzgado", query = "SELECT c FROM CfgTipoJuzgado c WHERE c.nomTipoJuzgado = :nomTipoJuzgado")})
public class CfgTipoJuzgado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_juzgado", nullable = false)
    private Integer idTipoJuzgado;
    @Size(max = 100)
    @Column(name = "nom_tipo_juzgado", length = 100)
    private String nomTipoJuzgado;
    @OneToMany(mappedBy = "idTipoJuzgado")
    private List<CfgJuzgado> cfgJuzgadoList;

    public CfgTipoJuzgado() {
    }

    public CfgTipoJuzgado(Integer idTipoJuzgado) {
        this.idTipoJuzgado = idTipoJuzgado;
    }

    public Integer getIdTipoJuzgado() {
        return idTipoJuzgado;
    }

    public void setIdTipoJuzgado(Integer idTipoJuzgado) {
        this.idTipoJuzgado = idTipoJuzgado;
    }

    public String getNomTipoJuzgado() {
        return nomTipoJuzgado;
    }

    public void setNomTipoJuzgado(String nomTipoJuzgado) {
        this.nomTipoJuzgado = nomTipoJuzgado;
    }

    @XmlTransient
    public List<CfgJuzgado> getCfgJuzgadoList() {
        return cfgJuzgadoList;
    }

    public void setCfgJuzgadoList(List<CfgJuzgado> cfgJuzgadoList) {
        this.cfgJuzgadoList = cfgJuzgadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoJuzgado != null ? idTipoJuzgado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoJuzgado)) {
            return false;
        }
        CfgTipoJuzgado other = (CfgTipoJuzgado) object;
        if ((this.idTipoJuzgado == null && other.idTipoJuzgado != null) || (this.idTipoJuzgado != null && !this.idTipoJuzgado.equals(other.idTipoJuzgado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoJuzgado[ idTipoJuzgado=" + idTipoJuzgado + " ]";
    }
    
}
