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
@Table(name = "cfg_tipo_direccion", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgTipoDireccion.findAll", query = "SELECT c FROM CfgTipoDireccion c"),
    @NamedQuery(name = "CfgTipoDireccion.findByIdTipoDireccion", query = "SELECT c FROM CfgTipoDireccion c WHERE c.idTipoDireccion = :idTipoDireccion"),
    @NamedQuery(name = "CfgTipoDireccion.findByNomTipoDireccion", query = "SELECT c FROM CfgTipoDireccion c WHERE c.nomTipoDireccion = :nomTipoDireccion")})
public class CfgTipoDireccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_direccion", nullable = false)
    private Integer idTipoDireccion;
    @Size(max = 50)
    @Column(name = "nom_tipo_direccion", length = 50)
    private String nomTipoDireccion;
    @OneToMany(mappedBy = "idTipoDireccion")
    private List<OpeCliente> opeClienteList;

    public CfgTipoDireccion() {
    }

    public CfgTipoDireccion(Integer idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public Integer getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(Integer idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public String getNomTipoDireccion() {
        return nomTipoDireccion;
    }

    public void setNomTipoDireccion(String nomTipoDireccion) {
        this.nomTipoDireccion = nomTipoDireccion;
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
        hash += (idTipoDireccion != null ? idTipoDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgTipoDireccion)) {
            return false;
        }
        CfgTipoDireccion other = (CfgTipoDireccion) object;
        if ((this.idTipoDireccion == null && other.idTipoDireccion != null) || (this.idTipoDireccion != null && !this.idTipoDireccion.equals(other.idTipoDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgTipoDireccion[ idTipoDireccion=" + idTipoDireccion + " ]";
    }
    
}
