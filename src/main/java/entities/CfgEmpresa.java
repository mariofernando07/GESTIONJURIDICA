/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "cfg_empresa", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CfgEmpresa.findAll", query = "SELECT c FROM CfgEmpresa c"),
    @NamedQuery(name = "CfgEmpresa.findByIdEmpresa", query = "SELECT c FROM CfgEmpresa c WHERE c.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "CfgEmpresa.findByNomEmpresa", query = "SELECT c FROM CfgEmpresa c WHERE c.nomEmpresa = :nomEmpresa"),
    @NamedQuery(name = "CfgEmpresa.findByIdentificadorEmp", query = "SELECT c FROM CfgEmpresa c WHERE c.identificadorEmp = :identificadorEmp"),
    @NamedQuery(name = "CfgEmpresa.findByTelefono", query = "SELECT c FROM CfgEmpresa c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "CfgEmpresa.findBySitioWeb", query = "SELECT c FROM CfgEmpresa c WHERE c.sitioWeb = :sitioWeb"),
    @NamedQuery(name = "CfgEmpresa.findByDireccion", query = "SELECT c FROM CfgEmpresa c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CfgEmpresa.findByEmail", query = "SELECT c FROM CfgEmpresa c WHERE c.email = :email"),
    @NamedQuery(name = "CfgEmpresa.findByFax", query = "SELECT c FROM CfgEmpresa c WHERE c.fax = :fax")})
public class CfgEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Size(max = 100)
    @Column(name = "nom_empresa", length = 100)
    private String nomEmpresa;
    @Size(max = 2147483647)
    @Column(name = "identificador_emp", length = 2147483647)
    private String identificadorEmp;
    @Size(max = 10)
    @Column(length = 10)
    private String telefono;
    @Size(max = 2147483647)
    @Column(name = "sitio_web", length = 2147483647)
    private String sitioWeb;
    @Size(max = 200)
    @Column(length = 200)
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(length = 200)
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(length = 10)
    private String fax;

    public CfgEmpresa() {
    }

    public CfgEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getIdentificadorEmp() {
        return identificadorEmp;
    }

    public void setIdentificadorEmp(String identificadorEmp) {
        this.identificadorEmp = identificadorEmp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgEmpresa)) {
            return false;
        }
        CfgEmpresa other = (CfgEmpresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgEmpresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
