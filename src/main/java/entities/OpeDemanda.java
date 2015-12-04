/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "ope_demanda", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeDemanda.findAll", query = "SELECT o FROM OpeDemanda o"),
    @NamedQuery(name = "OpeDemanda.findByIdDemanda", query = "SELECT o FROM OpeDemanda o WHERE o.idDemanda = :idDemanda"),
    @NamedQuery(name = "OpeDemanda.findByCodDemanda", query = "SELECT o FROM OpeDemanda o WHERE o.codDemanda = :codDemanda"),
    @NamedQuery(name = "OpeDemanda.findByDescDemanda", query = "SELECT o FROM OpeDemanda o WHERE o.descDemanda = :descDemanda"),
    @NamedQuery(name = "OpeDemanda.findByFecDemanda", query = "SELECT o FROM OpeDemanda o WHERE o.fecDemanda = :fecDemanda"),
    @NamedQuery(name = "OpeDemanda.findByJuez", query = "SELECT o FROM OpeDemanda o WHERE o.juez = :juez")})
public class OpeDemanda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_demanda", nullable = false)
    private Long idDemanda;
    @Size(max = 30)
    @Column(name = "cod_demanda", length = 30)
    private String codDemanda;
    @Size(max = 2147483647)
    @Column(name = "desc_demanda", length = 2147483647)
    private String descDemanda;
    @Column(name = "fec_demanda")
    @Temporal(TemporalType.DATE)
    private Date fecDemanda;
    @Size(max = 200)
    @Column(length = 200)
    private String juez;
    @JoinColumn(name = "id_juzgado", referencedColumnName = "id_juzgado")
    @ManyToOne
    private CfgJuzgado idJuzgado;
    @JoinColumn(name = "id_tipo_demanda", referencedColumnName = "id_tipo_demanda")
    @ManyToOne
    private CfgTipoDemanda idTipoDemanda;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne
    private OpeProceso idProceso;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private SegUsuario idUsuario;
    @OneToMany(mappedBy = "idDemanda")
    private List<OpeEtapaDemanda> opeEtapaDemandaList;

    public OpeDemanda() {
    }

    public OpeDemanda(Long idDemanda) {
        this.idDemanda = idDemanda;
    }

    public Long getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(Long idDemanda) {
        this.idDemanda = idDemanda;
    }

    public String getCodDemanda() {
        return codDemanda;
    }

    public void setCodDemanda(String codDemanda) {
        this.codDemanda = codDemanda;
    }

    public String getDescDemanda() {
        return descDemanda;
    }

    public void setDescDemanda(String descDemanda) {
        this.descDemanda = descDemanda;
    }

    public Date getFecDemanda() {
        return fecDemanda;
    }

    public void setFecDemanda(Date fecDemanda) {
        this.fecDemanda = fecDemanda;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public CfgJuzgado getIdJuzgado() {
        return idJuzgado;
    }

    public void setIdJuzgado(CfgJuzgado idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public CfgTipoDemanda getIdTipoDemanda() {
        return idTipoDemanda;
    }

    public void setIdTipoDemanda(CfgTipoDemanda idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public OpeProceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(OpeProceso idProceso) {
        this.idProceso = idProceso;
    }

    public SegUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SegUsuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idDemanda != null ? idDemanda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeDemanda)) {
            return false;
        }
        OpeDemanda other = (OpeDemanda) object;
        if ((this.idDemanda == null && other.idDemanda != null) || (this.idDemanda != null && !this.idDemanda.equals(other.idDemanda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeDemanda[ idDemanda=" + idDemanda + " ]";
    }
    
}
