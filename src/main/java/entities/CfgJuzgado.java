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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "cfg_juzgado", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgJuzgado.findAll", query = "SELECT c FROM CfgJuzgado c"),
    @NamedQuery(name = "CfgJuzgado.findByIdJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.idJuzgado = :idJuzgado"),
    @NamedQuery(name = "CfgJuzgado.findByCodJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.codJuzgado = :codJuzgado"),
    @NamedQuery(name = "CfgJuzgado.findByNomJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.nomJuzgado = :nomJuzgado"),
    @NamedQuery(name = "CfgJuzgado.findByDireccion", query = "SELECT c FROM CfgJuzgado c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CfgJuzgado.findByJuez", query = "SELECT c FROM CfgJuzgado c WHERE c.juez = :juez"),
    @NamedQuery(name = "CfgJuzgado.findByTelJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.telJuzgado = :telJuzgado"),
    @NamedQuery(name = "CfgJuzgado.findByFaxJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.faxJuzgado = :faxJuzgado"),
    @NamedQuery(name = "CfgJuzgado.findByEmailJuzgado", query = "SELECT c FROM CfgJuzgado c WHERE c.emailJuzgado = :emailJuzgado")})
public class CfgJuzgado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_juzgado", nullable = false)
    private Integer idJuzgado;
    @Size(max = 30)
    @Column(name = "cod_juzgado", length = 30)
    private String codJuzgado;
    @Size(max = 200)
    @Column(name = "nom_juzgado", length = 200)
    private String nomJuzgado;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String direccion;
    @Size(max = 150)
    @Column(length = 150)
    private String juez;
    @Size(max = 10)
    @Column(name = "tel_juzgado", length = 10)
    private String telJuzgado;
    @Size(max = 10)
    @Column(name = "fax_juzgado", length = 10)
    private String faxJuzgado;
    @Size(max = 2147483647)
    @Column(name = "email_juzgado", length = 2147483647)
    private String emailJuzgado;
    @OneToMany(mappedBy = "idJuzgado")
    private List<OpeDemanda> opeDemandaList;
    @JoinColumns({
        @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento"),
        @JoinColumn(name = "cod_municipio", referencedColumnName = "cod_municipio")})
    @ManyToOne
    private CfgMunicipio cfgMunicipio;
    @JoinColumn(name = "id_tipo_juzgado", referencedColumnName = "id_tipo_juzgado")
    @ManyToOne
    private CfgTipoJuzgado idTipoJuzgado;

    public CfgJuzgado() {
    }

    public CfgJuzgado(Integer idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public Integer getIdJuzgado() {
        return idJuzgado;
    }

    public void setIdJuzgado(Integer idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public String getCodJuzgado() {
        return codJuzgado;
    }

    public void setCodJuzgado(String codJuzgado) {
        this.codJuzgado = codJuzgado;
    }

    public String getNomJuzgado() {
        return nomJuzgado;
    }

    public void setNomJuzgado(String nomJuzgado) {
        this.nomJuzgado = nomJuzgado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public String getTelJuzgado() {
        return telJuzgado;
    }

    public void setTelJuzgado(String telJuzgado) {
        this.telJuzgado = telJuzgado;
    }

    public String getFaxJuzgado() {
        return faxJuzgado;
    }

    public void setFaxJuzgado(String faxJuzgado) {
        this.faxJuzgado = faxJuzgado;
    }

    public String getEmailJuzgado() {
        return emailJuzgado;
    }

    public void setEmailJuzgado(String emailJuzgado) {
        this.emailJuzgado = emailJuzgado;
    }

    @XmlTransient
    public List<OpeDemanda> getOpeDemandaList() {
        return opeDemandaList;
    }

    public void setOpeDemandaList(List<OpeDemanda> opeDemandaList) {
        this.opeDemandaList = opeDemandaList;
    }

    public CfgMunicipio getCfgMunicipio() {
        return cfgMunicipio;
    }

    public void setCfgMunicipio(CfgMunicipio cfgMunicipio) {
        this.cfgMunicipio = cfgMunicipio;
    }

    public CfgTipoJuzgado getIdTipoJuzgado() {
        return idTipoJuzgado;
    }

    public void setIdTipoJuzgado(CfgTipoJuzgado idTipoJuzgado) {
        this.idTipoJuzgado = idTipoJuzgado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJuzgado != null ? idJuzgado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgJuzgado)) {
            return false;
        }
        CfgJuzgado other = (CfgJuzgado) object;
        if ((this.idJuzgado == null && other.idJuzgado != null) || (this.idJuzgado != null && !this.idJuzgado.equals(other.idJuzgado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgJuzgado[ idJuzgado=" + idJuzgado + " ]";
    }
    
}
