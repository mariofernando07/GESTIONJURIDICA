/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.configuracion;

import entities.CfgTipoIdent;
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
public class ConfAbogadoMB implements Serializable {

    private int idTipoDocumento;
    private String numDocumento;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String email;
    private boolean activo;

    private SegUsuario abogadoSeleccionado;

    private List<SegUsuario> listaAbogado;

    @EJB
    SegUsuarioFacade usuarioFacade;
    @EJB
    CfgTipoIdentFacade tipoIdentFacade;

    public ConfAbogadoMB() {
    }

    @PostConstruct
    private void init() {
        setActivo(true);
    }

    public void cargarAbogados() {
        listaAbogado = usuarioFacade.buscarAbogados();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccionarAbogado");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarAbogado').show()");
    }

    public void cargarInformacionAbogado() {
        if (abogadoSeleccionado != null) {
            idTipoDocumento = abogadoSeleccionado.getIdTipoDocumento().getIdIdentificacion();
            numDocumento = abogadoSeleccionado.getNumeroDocumento();
            nombres = abogadoSeleccionado.getNombres();
            apellidos = abogadoSeleccionado.getApellidos();
            direccion = abogadoSeleccionado.getDireccion();
            telefono1 = abogadoSeleccionado.getTelefono1();
            telefono2 = abogadoSeleccionado.getTelefono2();
            email = abogadoSeleccionado.getEmail();
            activo = abogadoSeleccionado.getActivo();
        } else {
            deseleccionarAbogado();
        }
        RequestContext.getCurrentInstance().update("IdFormGestionAbogados");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarAbogado').hide()");
    }

    public void deseleccionarAbogado() {
        abogadoSeleccionado = null;
        activo = true;
        idTipoDocumento = 0;
        numDocumento = null;
        nombres = null;
        apellidos = null;
        direccion = null;
        telefono1 = null;
        telefono2 = null;
        email = null;
        RequestContext.getCurrentInstance().update("IdFormGestionAbogados");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarAbogado').hide()");
    }

    public void accion() {
        if (abogadoSeleccionado != null) {
            actualizarAbogado();
        } else {
            crearAbogado();
        }
    }

    private void crearAbogado() {
        try {
            SegUsuario abogado = new SegUsuario();
            abogado.setIdTipoDocumento(tipoIdentFacade.find(idTipoDocumento));
            abogado.setNumeroDocumento(numDocumento);
            abogado.setNombres(nombres);
            abogado.setApellidos(apellidos);
            abogado.setDireccion(direccion);
            abogado.setTelefono1(telefono1);
            abogado.setTelefono2(telefono2);
            abogado.setEmail(email);
            abogado.setAbogado(true);
            abogado.setActivo(activo);
            usuarioFacade.create(abogado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarAbogado() {
        try {
            abogadoSeleccionado.setIdTipoDocumento(tipoIdentFacade.find(idTipoDocumento));
            abogadoSeleccionado.setNumeroDocumento(numDocumento);
            abogadoSeleccionado.setNombres(nombres);
            abogadoSeleccionado.setApellidos(apellidos);
            abogadoSeleccionado.setDireccion(direccion);
            abogadoSeleccionado.setTelefono1(telefono1);
            abogadoSeleccionado.setTelefono2(telefono2);
            abogadoSeleccionado.setEmail(email);
            abogadoSeleccionado.setActivo(activo);
            usuarioFacade.edit(abogadoSeleccionado);
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<SegUsuario> getListaAbogado() {
        return listaAbogado;
    }

    public void setListaAbogado(List<SegUsuario> listaAbogado) {
        this.listaAbogado = listaAbogado;
    }

    public SegUsuario getAbogadoSeleccionado() {
        return abogadoSeleccionado;
    }

    public void setAbogadoSeleccionado(SegUsuario abogadoSeleccionado) {
        this.abogadoSeleccionado = abogadoSeleccionado;
    }

}
