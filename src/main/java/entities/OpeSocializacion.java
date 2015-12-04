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
import javax.persistence.JoinColumns;
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
@Table(name = "ope_socializacion", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeSocializacion.findAll", query = "SELECT o FROM OpeSocializacion o"),
    @NamedQuery(name = "OpeSocializacion.findByIdSocializacion", query = "SELECT o FROM OpeSocializacion o WHERE o.idSocializacion = :idSocializacion"),
    @NamedQuery(name = "OpeSocializacion.findByNomSocializacion", query = "SELECT o FROM OpeSocializacion o WHERE o.nomSocializacion = :nomSocializacion"),
    @NamedQuery(name = "OpeSocializacion.findByFecSocializacion", query = "SELECT o FROM OpeSocializacion o WHERE o.fecSocializacion = :fecSocializacion"),
    @NamedQuery(name = "OpeSocializacion.findByDescripcion", query = "SELECT o FROM OpeSocializacion o WHERE o.descripcion = :descripcion")})
public class OpeSocializacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_socializacion", nullable = false)
    private Integer idSocializacion;
    @Size(max = 200)
    @Column(name = "nom_socializacion", length = 200)
    private String nomSocializacion;
    @Column(name = "fec_socializacion")
    @Temporal(TemporalType.DATE)
    private Date fecSocializacion;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descripcion;
    @JoinColumns({
        @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento"),
        @JoinColumn(name = "cod_municipio", referencedColumnName = "cod_municipio")})
    @ManyToOne
    private CfgMunicipio cfgMunicipio;
    @OneToMany(mappedBy = "idSocializacion")
    private List<OpeCliente> opeClienteList;

    public OpeSocializacion() {
    }

    public OpeSocializacion(Integer idSocializacion) {
        this.idSocializacion = idSocializacion;
    }

    public Integer getIdSocializacion() {
        return idSocializacion;
    }

    public void setIdSocializacion(Integer idSocializacion) {
        this.idSocializacion = idSocializacion;
    }

    public String getNomSocializacion() {
        return nomSocializacion;
    }

    public void setNomSocializacion(String nomSocializacion) {
        this.nomSocializacion = nomSocializacion;
    }

    public Date getFecSocializacion() {
        return fecSocializacion;
    }

    public void setFecSocializacion(Date fecSocializacion) {
        this.fecSocializacion = fecSocializacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CfgMunicipio getCfgMunicipio() {
        return cfgMunicipio;
    }

    public void setCfgMunicipio(CfgMunicipio cfgMunicipio) {
        this.cfgMunicipio = cfgMunicipio;
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
        hash += (idSocializacion != null ? idSocializacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeSocializacion)) {
            return false;
        }
        OpeSocializacion other = (OpeSocializacion) object;
        if ((this.idSocializacion == null && other.idSocializacion != null) || (this.idSocializacion != null && !this.idSocializacion.equals(other.idSocializacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeSocializacion[ idSocializacion=" + idSocializacion + " ]";
    }
    
}
