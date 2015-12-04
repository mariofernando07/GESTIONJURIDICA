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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "ope_cliente", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeCliente.findAll", query = "SELECT o FROM OpeCliente o"),
    @NamedQuery(name = "OpeCliente.findByIdCliente", query = "SELECT o FROM OpeCliente o WHERE o.idCliente = :idCliente"),
    @NamedQuery(name = "OpeCliente.findByNumIdentificacion", query = "SELECT o FROM OpeCliente o WHERE o.numIdentificacion = :numIdentificacion"),
    @NamedQuery(name = "OpeCliente.findByPrimerNombre", query = "SELECT o FROM OpeCliente o WHERE o.primerNombre = :primerNombre"),
    @NamedQuery(name = "OpeCliente.findBySegundoNombre", query = "SELECT o FROM OpeCliente o WHERE o.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "OpeCliente.findByPrimerApellido", query = "SELECT o FROM OpeCliente o WHERE o.primerApellido = :primerApellido"),
    @NamedQuery(name = "OpeCliente.findBySegundoApellido", query = "SELECT o FROM OpeCliente o WHERE o.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "OpeCliente.findByDirCliente", query = "SELECT o FROM OpeCliente o WHERE o.dirCliente = :dirCliente"),
    @NamedQuery(name = "OpeCliente.findByTelefono1", query = "SELECT o FROM OpeCliente o WHERE o.telefono1 = :telefono1"),
    @NamedQuery(name = "OpeCliente.findByTelefono2", query = "SELECT o FROM OpeCliente o WHERE o.telefono2 = :telefono2"),
    @NamedQuery(name = "OpeCliente.findByDocConyuge", query = "SELECT o FROM OpeCliente o WHERE o.docConyuge = :docConyuge"),
    @NamedQuery(name = "OpeCliente.findByNomConyuge", query = "SELECT o FROM OpeCliente o WHERE o.nomConyuge = :nomConyuge"),
    @NamedQuery(name = "OpeCliente.findByTelConyuge", query = "SELECT o FROM OpeCliente o WHERE o.telConyuge = :telConyuge")})
public class OpeCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;
    @Size(max = 20)
    @Column(name = "num_identificacion", length = 20)
    private String numIdentificacion;
    @Size(max = 50)
    @Column(name = "primer_nombre", length = 50)
    private String primerNombre;
    @Size(max = 50)
    @Column(name = "segundo_nombre", length = 50)
    private String segundoNombre;
    @Size(max = 50)
    @Column(name = "primer_apellido", length = 50)
    private String primerApellido;
    @Size(max = 50)
    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;
    @Size(max = 2147483647)
    @Column(name = "dir_cliente", length = 2147483647)
    private String dirCliente;
    @Size(max = 10)
    @Column(length = 10)
    private String telefono1;
    @Size(max = 10)
    @Column(length = 10)
    private String telefono2;
    @Size(max = 20)
    @Column(name = "doc_conyuge", length = 20)
    private String docConyuge;
    @Size(max = 200)
    @Column(name = "nom_conyuge", length = 200)
    private String nomConyuge;
    @Size(max = 10)
    @Column(name = "tel_conyuge", length = 10)
    private String telConyuge;
    @JoinTable(name = "ope_cliente_proceso", joinColumns = {
        @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso", nullable = false)})
    @ManyToMany
    private List<OpeProceso> opeProcesoList;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne
    private CfgEstadoCivil idEstadoCivil;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne
    private CfgGenero idGenero;
    @JoinColumns({
        @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento"),
        @JoinColumn(name = "cod_municipio", referencedColumnName = "cod_municipio")})
    @ManyToOne
    private CfgMunicipio cfgMunicipio;
    @JoinColumn(name = "id_tipo_direccion", referencedColumnName = "id_tipo_direccion")
    @ManyToOne
    private CfgTipoDireccion idTipoDireccion;
    @JoinColumn(name = "id_tipo_identificacion", referencedColumnName = "id_identificacion")
    @ManyToOne
    private CfgTipoIdent idTipoIdentificacion;
    @JoinColumn(name = "id_socializacion", referencedColumnName = "id_socializacion")
    @ManyToOne
    private OpeSocializacion idSocializacion;

    public OpeCliente() {
    }

    public OpeCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDocConyuge() {
        return docConyuge;
    }

    public void setDocConyuge(String docConyuge) {
        this.docConyuge = docConyuge;
    }

    public String getNomConyuge() {
        return nomConyuge;
    }

    public void setNomConyuge(String nomConyuge) {
        this.nomConyuge = nomConyuge;
    }

    public String getTelConyuge() {
        return telConyuge;
    }

    public void setTelConyuge(String telConyuge) {
        this.telConyuge = telConyuge;
    }

    @XmlTransient
    public List<OpeProceso> getOpeProcesoList() {
        return opeProcesoList;
    }

    public void setOpeProcesoList(List<OpeProceso> opeProcesoList) {
        this.opeProcesoList = opeProcesoList;
    }

    public CfgEstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(CfgEstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public CfgGenero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(CfgGenero idGenero) {
        this.idGenero = idGenero;
    }

    public CfgMunicipio getCfgMunicipio() {
        return cfgMunicipio;
    }

    public void setCfgMunicipio(CfgMunicipio cfgMunicipio) {
        this.cfgMunicipio = cfgMunicipio;
    }

    public CfgTipoDireccion getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(CfgTipoDireccion idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public CfgTipoIdent getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(CfgTipoIdent idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public OpeSocializacion getIdSocializacion() {
        return idSocializacion;
    }

    public void setIdSocializacion(OpeSocializacion idSocializacion) {
        this.idSocializacion = idSocializacion;
    }
    public String nombreCompleto() {
        String strNombre = "";
        if (primerNombre != null) {
            strNombre = primerNombre + " ";
        }
        if (segundoNombre != null) {
            strNombre = strNombre + segundoNombre + " ";
        }
        if (primerApellido != null) {
            strNombre = strNombre + primerApellido + " ";
        }
        if (segundoApellido != null) {
            strNombre = strNombre + segundoApellido;
        }
        return strNombre;
    }        
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeCliente)) {
            return false;
        }
        OpeCliente other = (OpeCliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeCliente[ idCliente=" + idCliente + " ]";
    }
    
}
