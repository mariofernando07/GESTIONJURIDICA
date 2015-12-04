/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.seguridad;

import entities.SegUsuario;
import facades.CfgTipoIdentFacade;
import facades.SegUsuarioFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class SegUsuarioMB implements Serializable {

    private int idTipoDocumento;
    private String numDocumento;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String email;
    private String usuario;
    private String password;
    private String passwordR;
    private boolean activo;
    private boolean abogado;

    private SegUsuario usuarioSeleccionado;

    private List<SegUsuario> listaUsuarios;

    @EJB
    SegUsuarioFacade usuarioFacade;
    @EJB
    CfgTipoIdentFacade tipoIdentFacade;

    public SegUsuarioMB() {
    }

    @PostConstruct
    private void init() {
        setActivo(true);
        setAbogado(false);
    }

    public void cargarUsuarios() {
        listaUsuarios = usuarioFacade.buscarUsuarios();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccionarUsuario");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarUsuario').show()");
    }

    public void cargarInformacionUsuario() {
        if (usuarioSeleccionado != null) {
            idTipoDocumento = usuarioSeleccionado.getIdTipoDocumento().getIdIdentificacion();
            numDocumento = usuarioSeleccionado.getNumeroDocumento();
            nombres = usuarioSeleccionado.getNombres();
            apellidos = usuarioSeleccionado.getApellidos();
            direccion = usuarioSeleccionado.getDireccion();
            telefono1 = usuarioSeleccionado.getTelefono1();
            telefono2 = usuarioSeleccionado.getTelefono2();
            email = usuarioSeleccionado.getEmail();
            activo = usuarioSeleccionado.getActivo();
            abogado = usuarioSeleccionado.getAbogado();
        } else {
            deseleccionarUsuario();
        }
        RequestContext.getCurrentInstance().update("IdFormGestionUsuarios");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarUsuario').hide()");
    }

    public void deseleccionarUsuario() {
        usuarioSeleccionado = null;
        activo = true;
        abogado = false;
        idTipoDocumento = 0;
        numDocumento = null;
        nombres = null;
        apellidos = null;
        direccion = null;
        telefono1 = null;
        telefono2 = null;
        email = null;
        RequestContext.getCurrentInstance().update("IdFormGestionUsuarios");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarUsuario').hide()");
    }

    public void accion() {
        if (usuarioSeleccionado != null) {
            actualizarUsuario();
        } else {
            crearUsuario();
        }
    }

    private void crearUsuario() {
        try {
            SegUsuario user = new SegUsuario();
            user.setIdTipoDocumento(tipoIdentFacade.find(idTipoDocumento));
            user.setNumeroDocumento(numDocumento);
            user.setNombres(nombres);
            user.setApellidos(apellidos);
            user.setDireccion(direccion);
            user.setTelefono1(telefono1);
            user.setTelefono2(telefono2);
            user.setEmail(email);
            user.setAbogado(abogado);
            user.setActivo(activo);
            user.setUsuario(usuario);
            user.setPassword(password);
            usuarioFacade.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarUsuario() {
        try {
            usuarioSeleccionado.setIdTipoDocumento(tipoIdentFacade.find(idTipoDocumento));
            usuarioSeleccionado.setNumeroDocumento(numDocumento);
            usuarioSeleccionado.setNombres(nombres);
            usuarioSeleccionado.setApellidos(apellidos);
            usuarioSeleccionado.setDireccion(direccion);
            usuarioSeleccionado.setTelefono1(telefono1);
            usuarioSeleccionado.setTelefono2(telefono2);
            usuarioSeleccionado.setEmail(email);
            usuarioSeleccionado.setActivo(activo);
            usuarioSeleccionado.setAbogado(abogado);
            usuarioSeleccionado.setUsuario(usuario);
            usuarioSeleccionado.setPassword(password);            
            usuarioFacade.edit(usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }

    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
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

    public String getPasswordR() {
        return passwordR;
    }

    public void setPasswordR(String passwordR) {
        this.passwordR = passwordR;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public SegUsuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(SegUsuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<SegUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public boolean isAbogado() {
        return abogado;
    }

    public void setAbogado(boolean abogado) {
        this.abogado = abogado;
    }

}
