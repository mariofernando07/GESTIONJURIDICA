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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cfg_etapa_demanda", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgEtapaDemanda.findAll", query = "SELECT c FROM CfgEtapaDemanda c"),
    @NamedQuery(name = "CfgEtapaDemanda.findByIdTipoDemanda", query = "SELECT c FROM CfgEtapaDemanda c WHERE c.cfgEtapaDemandaPK.idTipoDemanda = :idTipoDemanda"),
    @NamedQuery(name = "CfgEtapaDemanda.findByIdEtapaDemanda", query = "SELECT c FROM CfgEtapaDemanda c WHERE c.cfgEtapaDemandaPK.idEtapaDemanda = :idEtapaDemanda"),
    @NamedQuery(name = "CfgEtapaDemanda.findByNomEtapa", query = "SELECT c FROM CfgEtapaDemanda c WHERE c.nomEtapa = :nomEtapa"),
    @NamedQuery(name = "CfgEtapaDemanda.findByNumEtapaSig", query = "SELECT c FROM CfgEtapaDemanda c WHERE c.numEtapaSig = :numEtapaSig")})
public class CfgEtapaDemanda implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CfgEtapaDemandaPK cfgEtapaDemandaPK;
    @Size(max = 100)
    @Column(name = "nom_etapa", length = 100)
    private String nomEtapa;
    @Column(name = "num_etapa_sig")
    private Integer numEtapaSig;
    @JoinColumn(name = "id_tipo_demanda", referencedColumnName = "id_tipo_demanda", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CfgTipoDemanda cfgTipoDemanda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cfgEtapaDemanda")
    private List<OpeEtapaDemanda> opeEtapaDemandaList;

    public CfgEtapaDemanda() {
    }

    public CfgEtapaDemanda(CfgEtapaDemandaPK cfgEtapaDemandaPK) {
        this.cfgEtapaDemandaPK = cfgEtapaDemandaPK;
    }

    public CfgEtapaDemanda(int idTipoDemanda, int idEtapaDemanda) {
        this.cfgEtapaDemandaPK = new CfgEtapaDemandaPK(idTipoDemanda, idEtapaDemanda);
    }

    public CfgEtapaDemandaPK getCfgEtapaDemandaPK() {
        return cfgEtapaDemandaPK;
    }

    public void setCfgEtapaDemandaPK(CfgEtapaDemandaPK cfgEtapaDemandaPK) {
        this.cfgEtapaDemandaPK = cfgEtapaDemandaPK;
    }

    public String getNomEtapa() {
        return nomEtapa;
    }

    public void setNomEtapa(String nomEtapa) {
        this.nomEtapa = nomEtapa;
    }

    public Integer getNumEtapaSig() {
        return numEtapaSig;
    }

    public void setNumEtapaSig(Integer numEtapaSig) {
        this.numEtapaSig = numEtapaSig;
    }

    public CfgTipoDemanda getCfgTipoDemanda() {
        return cfgTipoDemanda;
    }

    public void setCfgTipoDemanda(CfgTipoDemanda cfgTipoDemanda) {
        this.cfgTipoDemanda = cfgTipoDemanda;
    }

    @XmlTransient
    public List<OpeEtapaDemanda> getOpeEtapaDemandaList() {
        return opeEtapaDemandaList;
    }

    public void setOpeEtapaDemandaList(List<OpeEtapaDemanda> opeEtapaDemandaList) {
        this.opeEtapaDemandaList = opeEtapaDemandaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cfgEtapaDemandaPK != null ? cfgEtapaDemandaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgEtapaDemanda)) {
            return false;
        }
        CfgEtapaDemanda other = (CfgEtapaDemanda) object;
        if ((this.cfgEtapaDemandaPK == null && other.cfgEtapaDemandaPK != null) || (this.cfgEtapaDemandaPK != null && !this.cfgEtapaDemandaPK.equals(other.cfgEtapaDemandaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgEtapaDemanda[ cfgEtapaDemandaPK=" + cfgEtapaDemandaPK + " ]";
    }
    
}
