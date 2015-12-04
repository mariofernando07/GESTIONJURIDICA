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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "ope_proceso_soporte", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeProcesoSoporte.findAll", query = "SELECT o FROM OpeProcesoSoporte o"),
    @NamedQuery(name = "OpeProcesoSoporte.findByIdProceso", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.opeProcesoSoportePK.idProceso = :idProceso"),
    @NamedQuery(name = "OpeProcesoSoporte.findByIdTipoProceso", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.opeProcesoSoportePK.idTipoProceso = :idTipoProceso"),
    @NamedQuery(name = "OpeProcesoSoporte.findByIdTipoSoporte", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.opeProcesoSoportePK.idTipoSoporte = :idTipoSoporte"),
    @NamedQuery(name = "OpeProcesoSoporte.findByRuta", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.opeProcesoSoportePK.ruta = :ruta"),
    @NamedQuery(name = "OpeProcesoSoporte.findByDescripcion", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "OpeProcesoSoporte.findByFecha", query = "SELECT o FROM OpeProcesoSoporte o WHERE o.fecha = :fecha")})
public class OpeProcesoSoporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OpeProcesoSoportePK opeProcesoSoportePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumns({
        @JoinColumn(name = "id_tipo_proceso", referencedColumnName = "id_tipo_proceso", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id_tipo_soporte", referencedColumnName = "id_tipo_soporte", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CfgTipoSoporteTipoProceso cfgTipoSoporteTipoProceso;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OpeProceso opeProceso;

    public OpeProcesoSoporte() {
    }

    public OpeProcesoSoporte(OpeProcesoSoportePK opeProcesoSoportePK) {
        this.opeProcesoSoportePK = opeProcesoSoportePK;
    }

    public OpeProcesoSoporte(OpeProcesoSoportePK opeProcesoSoportePK, String descripcion) {
        this.opeProcesoSoportePK = opeProcesoSoportePK;
        this.descripcion = descripcion;
    }

    public OpeProcesoSoporte(long idProceso, int idTipoProceso, int idTipoSoporte, String ruta) {
        this.opeProcesoSoportePK = new OpeProcesoSoportePK(idProceso, idTipoProceso, idTipoSoporte, ruta);
    }

    public OpeProcesoSoportePK getOpeProcesoSoportePK() {
        return opeProcesoSoportePK;
    }

    public void setOpeProcesoSoportePK(OpeProcesoSoportePK opeProcesoSoportePK) {
        this.opeProcesoSoportePK = opeProcesoSoportePK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CfgTipoSoporteTipoProceso getCfgTipoSoporteTipoProceso() {
        return cfgTipoSoporteTipoProceso;
    }

    public void setCfgTipoSoporteTipoProceso(CfgTipoSoporteTipoProceso cfgTipoSoporteTipoProceso) {
        this.cfgTipoSoporteTipoProceso = cfgTipoSoporteTipoProceso;
    }

    public OpeProceso getOpeProceso() {
        return opeProceso;
    }

    public void setOpeProceso(OpeProceso opeProceso) {
        this.opeProceso = opeProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opeProcesoSoportePK != null ? opeProcesoSoportePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeProcesoSoporte)) {
            return false;
        }
        OpeProcesoSoporte other = (OpeProcesoSoporte) object;
        if ((this.opeProcesoSoportePK == null && other.opeProcesoSoportePK != null) || (this.opeProcesoSoportePK != null && !this.opeProcesoSoportePK.equals(other.opeProcesoSoportePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeProcesoSoporte[ opeProcesoSoportePK=" + opeProcesoSoportePK + " ]";
    }
    
}
