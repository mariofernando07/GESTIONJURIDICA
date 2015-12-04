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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "ope_proceso", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeProceso.findAll", query = "SELECT o FROM OpeProceso o"),
    @NamedQuery(name = "OpeProceso.findByIdProceso", query = "SELECT o FROM OpeProceso o WHERE o.idProceso = :idProceso"),
    @NamedQuery(name = "OpeProceso.findByNomProceso", query = "SELECT o FROM OpeProceso o WHERE o.nomProceso = :nomProceso"),
    @NamedQuery(name = "OpeProceso.findByFecProceso", query = "SELECT o FROM OpeProceso o WHERE o.fecProceso = :fecProceso")})
public class OpeProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proceso", nullable = false)
    private Long idProceso;
    @Size(max = 2147483647)
    @Column(name = "nom_proceso", length = 2147483647)
    private String nomProceso;
    @Column(name = "fec_proceso")
    @Temporal(TemporalType.DATE)
    private Date fecProceso;
    @ManyToMany(mappedBy = "opeProcesoList")
    private List<OpeCliente> opeClienteList;
    @OneToMany(mappedBy = "idProceso")
    private List<OpeDemanda> opeDemandaList;
    @JoinColumn(name = "id_estado_proceso", referencedColumnName = "id_estado_proceso")
    @ManyToOne
    private CfgEstadoProceso idEstadoProceso;
    @JoinColumn(name = "id_tipo_proceso", referencedColumnName = "id_tipo_proceso")
    @ManyToOne
    private CfgTipoProceso idTipoProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opeProceso")
    private List<OpeProcesoSoporte> opeProcesoSoporteList;

    public OpeProceso() {
    }

    public OpeProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public String getNomProceso() {
        return nomProceso;
    }

    public void setNomProceso(String nomProceso) {
        this.nomProceso = nomProceso;
    }

    public Date getFecProceso() {
        return fecProceso;
    }

    public void setFecProceso(Date fecProceso) {
        this.fecProceso = fecProceso;
    }

    @XmlTransient
    public List<OpeCliente> getOpeClienteList() {
        return opeClienteList;
    }

    public void setOpeClienteList(List<OpeCliente> opeClienteList) {
        this.opeClienteList = opeClienteList;
    }

    @XmlTransient
    public List<OpeDemanda> getOpeDemandaList() {
        return opeDemandaList;
    }

    public void setOpeDemandaList(List<OpeDemanda> opeDemandaList) {
        this.opeDemandaList = opeDemandaList;
    }

    public CfgEstadoProceso getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(CfgEstadoProceso idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public CfgTipoProceso getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(CfgTipoProceso idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    @XmlTransient
    public List<OpeProcesoSoporte> getOpeProcesoSoporteList() {
        return opeProcesoSoporteList;
    }

    public void setOpeProcesoSoporteList(List<OpeProcesoSoporte> opeProcesoSoporteList) {
        this.opeProcesoSoporteList = opeProcesoSoporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeProceso)) {
            return false;
        }
        OpeProceso other = (OpeProceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeProceso[ idProceso=" + idProceso + " ]";
    }
    
}
