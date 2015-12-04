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
@Table(name = "cfg_tipo_ident", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoIdent.findAll", query = "SELECT c FROM CfgTipoIdent c"),
    @NamedQuery(name = "CfgTipoIdent.findByIdIdentificacion", query = "SELECT c FROM CfgTipoIdent c WHERE c.idIdentificacion = :idIdentificacion"),
    @NamedQuery(name = "CfgTipoIdent.findBySiglaDocumento", query = "SELECT c FROM CfgTipoIdent c WHERE c.siglaDocumento = :siglaDocumento"),
    @NamedQuery(name = "CfgTipoIdent.findByNomDocumento", query = "SELECT c FROM CfgTipoIdent c WHERE c.nomDocumento = :nomDocumento")})
public class CfgTipoIdent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_identificacion", nullable = false)
    private Integer idIdentificacion;
    @Size(max = 2)
    @Column(name = "sigla_documento", length = 2)
    private String siglaDocumento;
    @Size(max = 50)
    @Column(name = "nom_documento", length = 50)
    private String nomDocumento;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<SegUsuario> segUsuarioList;
    @OneToMany(mappedBy = "idTipoIdentificacion")
    private List<OpeCliente> opeClienteList;

    public CfgTipoIdent() {
    }

    public CfgTipoIdent(Integer idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public Integer getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(Integer idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public String getSiglaDocumento() {
        return siglaDocumento;
    }

    public void setSiglaDocumento(String siglaDocumento) {
        this.siglaDocumento = siglaDocumento;
    }

    public String getNomDocumento() {
        return nomDocumento;
    }

    public void setNomDocumento(String nomDocumento) {
        this.nomDocumento = nomDocumento;
    }

    @XmlTransient
    public List<SegUsuario> getSegUsuarioList() {
        return segUsuarioList;
    }

    public void setSegUsuarioList(List<SegUsuario> segUsuarioList) {
        this.segUsuarioList = segUsuarioList;
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
        hash += (idIdentificacion != null ? idIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoIdent)) {
            return false;
        }
        CfgTipoIdent other = (CfgTipoIdent) object;
        if ((this.idIdentificacion == null && other.idIdentificacion != null) || (this.idIdentificacion != null && !this.idIdentificacion.equals(other.idIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoIdent[ idIdentificacion=" + idIdentificacion + " ]";
    }
    
}
