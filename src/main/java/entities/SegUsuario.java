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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "seg_usuario", catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SegUsuario.findAll", query = "SELECT s FROM SegUsuario s"),
    @NamedQuery(name = "SegUsuario.findByIdUsuario", query = "SELECT s FROM SegUsuario s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SegUsuario.findByNombres", query = "SELECT s FROM SegUsuario s WHERE s.nombres = :nombres"),
    @NamedQuery(name = "SegUsuario.findByApellidos", query = "SELECT s FROM SegUsuario s WHERE s.apellidos = :apellidos"),
    @NamedQuery(name = "SegUsuario.findByNumeroDocumento", query = "SELECT s FROM SegUsuario s WHERE s.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "SegUsuario.findByDireccion", query = "SELECT s FROM SegUsuario s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "SegUsuario.findByTelefono1", query = "SELECT s FROM SegUsuario s WHERE s.telefono1 = :telefono1"),
    @NamedQuery(name = "SegUsuario.findByTelefono2", query = "SELECT s FROM SegUsuario s WHERE s.telefono2 = :telefono2"),
    @NamedQuery(name = "SegUsuario.findByEmail", query = "SELECT s FROM SegUsuario s WHERE s.email = :email"),
    @NamedQuery(name = "SegUsuario.findByAbogado", query = "SELECT s FROM SegUsuario s WHERE s.abogado = :abogado"),
    @NamedQuery(name = "SegUsuario.findByUsuario", query = "SELECT s FROM SegUsuario s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "SegUsuario.findByPassword", query = "SELECT s FROM SegUsuario s WHERE s.password = :password"),
    @NamedQuery(name = "SegUsuario.findByActivo", query = "SELECT s FROM SegUsuario s WHERE s.activo = :activo")})
public class SegUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
    @Size(max = 100)
    @Column(length = 100)
    private String nombres;
    @Size(max = 100)
    @Column(length = 100)
    private String apellidos;
    @Size(max = 20)
    @Column(name = "numero_documento", length = 20)
    private String numeroDocumento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String direccion;
    @Size(max = 10)
    @Column(length = 10)
    private String telefono1;
    @Size(max = 10)
    @Column(length = 10)
    private String telefono2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String email;
    private Boolean abogado;
    @Size(max = 10)
    @Column(length = 10)
    private String usuario;
    @Size(max = 10)
    @Column(length = 10)
    private String password;
    private Boolean activo;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_identificacion")
    @ManyToOne
    private CfgTipoIdent idTipoDocumento;
    @OneToMany(mappedBy = "idUsuario")
    private List<OpeDemanda> opeDemandaList;
    @Transient
    private HttpSession session;

    public SegUsuario() {
    }

    public SegUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAbogado() {
        return abogado;
    }

    public void setAbogado(Boolean abogado) {
        this.abogado = abogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public CfgTipoIdent getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(CfgTipoIdent idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @XmlTransient
    public List<OpeDemanda> getOpeDemandaList() {
        return opeDemandaList;
    }

    public void setOpeDemandaList(List<OpeDemanda> opeDemandaList) {
        this.opeDemandaList = opeDemandaList;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegUsuario)) {
            return false;
        }
        SegUsuario other = (SegUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SegUsuario[ idUsuario=" + idUsuario + " ]";
    }

}
