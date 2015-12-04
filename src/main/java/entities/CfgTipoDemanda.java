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
@Table(name = "cfg_tipo_demanda", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoDemanda.findAll", query = "SELECT c FROM CfgTipoDemanda c"),
    @NamedQuery(name = "CfgTipoDemanda.findByIdTipoDemanda", query = "SELECT c FROM CfgTipoDemanda c WHERE c.idTipoDemanda = :idTipoDemanda"),
    @NamedQuery(name = "CfgTipoDemanda.findByNomTipoDemanda", query = "SELECT c FROM CfgTipoDemanda c WHERE c.nomTipoDemanda = :nomTipoDemanda")})
public class CfgTipoDemanda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_demanda", nullable = false)
    private Integer idTipoDemanda;
    @Size(max = 100)
    @Column(name = "nom_tipo_demanda", length = 100)
    private String nomTipoDemanda;
    @OneToMany(mappedBy = "idTipoDemanda")
    private List<OpeDemanda> opeDemandaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgTipoDemanda")
    private List<CfgEtapaDemanda> cfgEtapaDemandaList;

    public CfgTipoDemanda() {
    }

    public CfgTipoDemanda(Integer idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public Integer getIdTipoDemanda() {
        return idTipoDemanda;
    }

    public void setIdTipoDemanda(Integer idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public String getNomTipoDemanda() {
        return nomTipoDemanda;
    }

    public void setNomTipoDemanda(String nomTipoDemanda) {
        this.nomTipoDemanda = nomTipoDemanda;
    }

    @XmlTransient
    public List<OpeDemanda> getOpeDemandaList() {
        return opeDemandaList;
    }

    public void setOpeDemandaList(List<OpeDemanda> opeDemandaList) {
        this.opeDemandaList = opeDemandaList;
    }

    @XmlTransient
    public List<CfgEtapaDemanda> getCfgEtapaDemandaList() {
        return cfgEtapaDemandaList;
    }

    public void setCfgEtapaDemandaList(List<CfgEtapaDemanda> cfgEtapaDemandaList) {
        this.cfgEtapaDemandaList = cfgEtapaDemandaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDemanda != null ? idTipoDemanda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoDemanda)) {
            return false;
        }
        CfgTipoDemanda other = (CfgTipoDemanda) object;
        if ((this.idTipoDemanda == null && other.idTipoDemanda != null) || (this.idTipoDemanda != null && !this.idTipoDemanda.equals(other.idTipoDemanda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoDemanda[ idTipoDemanda=" + idTipoDemanda + " ]";
    }
    
}
