/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "ope_etapa_demanda", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeEtapaDemanda.findAll", query = "SELECT o FROM OpeEtapaDemanda o"),
    @NamedQuery(name = "OpeEtapaDemanda.findByIdEtapaDemanda", query = "SELECT o FROM OpeEtapaDemanda o WHERE o.idEtapaDemanda = :idEtapaDemanda"),
    @NamedQuery(name = "OpeEtapaDemanda.findByIdCfgEtapaDemanda", query = "SELECT o FROM OpeEtapaDemanda o WHERE o.idCfgEtapaDemanda = :idCfgEtapaDemanda"),
    @NamedQuery(name = "OpeEtapaDemanda.findByFecha", query = "SELECT o FROM OpeEtapaDemanda o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "OpeEtapaDemanda.findByDescripcionEtapa", query = "SELECT o FROM OpeEtapaDemanda o WHERE o.descripcionEtapa = :descripcionEtapa")})
public class OpeEtapaDemanda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etapa_demanda", nullable = false)
    private Long idEtapaDemanda;
    @Column(name = "id_cfg_etapa_demanda")
    private Integer idCfgEtapaDemanda;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 2147483647)
    @Column(name = "descripcion_etapa", length = 2147483647)
    private String descripcionEtapa;
    @JoinColumns({
        @JoinColumn(name = "id_tipo_demanda", referencedColumnName = "id_tipo_demanda"),
        @JoinColumn(name = "id_etapa_demanda", referencedColumnName = "id_etapa_demanda", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CfgEtapaDemanda cfgEtapaDemanda;
    @JoinColumn(name = "id_demanda", referencedColumnName = "id_demanda")
    @ManyToOne
    private OpeDemanda idDemanda;

    public OpeEtapaDemanda() {
    }

    public OpeEtapaDemanda(Long idEtapaDemanda) {
        this.idEtapaDemanda = idEtapaDemanda;
    }

    public Long getIdEtapaDemanda() {
        return idEtapaDemanda;
    }

    public void setIdEtapaDemanda(Long idEtapaDemanda) {
        this.idEtapaDemanda = idEtapaDemanda;
    }

    public Integer getIdCfgEtapaDemanda() {
        return idCfgEtapaDemanda;
    }

    public void setIdCfgEtapaDemanda(Integer idCfgEtapaDemanda) {
        this.idCfgEtapaDemanda = idCfgEtapaDemanda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionEtapa() {
        return descripcionEtapa;
    }

    public void setDescripcionEtapa(String descripcionEtapa) {
        this.descripcionEtapa = descripcionEtapa;
    }

    public CfgEtapaDemanda getCfgEtapaDemanda() {
        return cfgEtapaDemanda;
    }

    public void setCfgEtapaDemanda(CfgEtapaDemanda cfgEtapaDemanda) {
        this.cfgEtapaDemanda = cfgEtapaDemanda;
    }

    public OpeDemanda getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(OpeDemanda idDemanda) {
        this.idDemanda = idDemanda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtapaDemanda != null ? idEtapaDemanda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeEtapaDemanda)) {
            return false;
        }
        OpeEtapaDemanda other = (OpeEtapaDemanda) object;
        if ((this.idEtapaDemanda == null && other.idEtapaDemanda != null) || (this.idEtapaDemanda != null && !this.idEtapaDemanda.equals(other.idEtapaDemanda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeEtapaDemanda[ idEtapaDemanda=" + idEtapaDemanda + " ]";
    }
    
}
