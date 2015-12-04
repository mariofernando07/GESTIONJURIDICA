/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.configuracion;

import entities.CfgJuzgado;
import entities.CfgMunicipio;
import facades.CfgJuzgadoFacade;
import facades.CfgMunicipioFacade;
import facades.CfgTipoJuzgadoFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
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
public class ConfJuzgadoMB implements Serializable {

    private String codigoJuzgado;
    private int idTipoJuzgado;
    private String nombreJuzgado;
    private String juez;
    private String codDepartamento;
    private String codMunicipio;
    private String direccion;
    private String telefono;
    private String fax;
    private String email;

    private CfgJuzgado juzgadoSeleccionado;

    private List<CfgMunicipio> listaMunicipio;
    private List<CfgJuzgado> listaJuzgado;

    @EJB
    CfgMunicipioFacade municipioFacade;
    @EJB
    CfgTipoJuzgadoFacade tipoJuzgadoFacade;
    @EJB
    CfgJuzgadoFacade juzgadoFacade;

    public ConfJuzgadoMB() {
    }

    public void cargarJuzgados() {
        listaJuzgado = juzgadoFacade.findAll();
        RequestContext.getCurrentInstance().update("IdFormModalJuzgado");
        RequestContext.getCurrentInstance().execute("PF('dlgModalSeleccionarJuzgado').show()");
    }

    public void cargarInformacionJuzgado() {
        if (juzgadoSeleccionado != null) {
            codigoJuzgado = juzgadoSeleccionado.getCodJuzgado();
            if (juzgadoSeleccionado.getIdTipoJuzgado() != null) {
                idTipoJuzgado = juzgadoSeleccionado.getIdTipoJuzgado().getIdTipoJuzgado();
            } else {
                idTipoJuzgado = 0;
            }
            nombreJuzgado = juzgadoSeleccionado.getNomJuzgado();
            CfgMunicipio municipio = juzgadoSeleccionado.getCfgMunicipio();
            if (municipio != null) {
                codDepartamento = municipio.getCfgMunicipioPK().getCodDepartamento();
                codMunicipio = municipio.getCfgMunicipioPK().getCodMunicipio();
            } else {
                codDepartamento = null;
                codMunicipio = null;
            }
            direccion = juzgadoSeleccionado.getDireccion();
            telefono = juzgadoSeleccionado.getTelJuzgado();
            fax = juzgadoSeleccionado.getFaxJuzgado();
            email = juzgadoSeleccionado.getEmailJuzgado();
        } else {
            deseleccionarJuzgado();
        }
        RequestContext.getCurrentInstance().execute("PF('dlgModalSeleccionarJuzgado').hide()");
        RequestContext.getCurrentInstance().update("IdFormGestionJuzgados");
    }

    public void deseleccionarJuzgado() {
        codigoJuzgado = null;
        idTipoJuzgado = 0;
        nombreJuzgado = null;
        codDepartamento = null;
        listaMunicipio = null;
        codMunicipio = null;
        direccion = null;
        juez = null;
        telefono = null;
        fax = null;
        email = null;
        RequestContext.getCurrentInstance().execute("PF('dlgModalSeleccionarJuzgado').hide()");
        RequestContext.getCurrentInstance().update("IdFormGestionJuzgados");
    }

    public void cargarMunicipios() {
        codMunicipio = null;
        listaMunicipio = municipioFacade.buscarMunicipiosPorDepartamento(codDepartamento);
        RequestContext.getCurrentInstance().update("IdFormGestionJuzgados:IdListaMunicipios");
    }

    public void accion() {
        if (juzgadoSeleccionado != null) {
            actualizarJuzgado();
        } else {
            crearJuzgado();
        }
    }

    private void crearJuzgado() {
        try {
            CfgJuzgado juzgado = new CfgJuzgado();
            juzgado.setCodJuzgado(codigoJuzgado);
            juzgado.setIdTipoJuzgado(tipoJuzgadoFacade.find(idTipoJuzgado));
            juzgado.setNomJuzgado(nombreJuzgado);
            juzgado.setJuez(juez);
            juzgado.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
            juzgado.setDireccion(direccion);
            juzgado.setTelJuzgado(telefono);
            juzgado.setFaxJuzgado(fax);
            juzgado.setEmailJuzgado(email);
            juzgadoFacade.create(juzgado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarJuzgado() {
        try {
            juzgadoSeleccionado.setCodJuzgado(codigoJuzgado);
            juzgadoSeleccionado.setIdTipoJuzgado(tipoJuzgadoFacade.find(idTipoJuzgado));
            juzgadoSeleccionado.setNomJuzgado(nombreJuzgado);
            juzgadoSeleccionado.setJuez(juez);
            juzgadoSeleccionado.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
            juzgadoSeleccionado.setDireccion(direccion);
            juzgadoSeleccionado.setTelJuzgado(telefono);
            juzgadoSeleccionado.setFaxJuzgado(fax);
            juzgadoSeleccionado.setEmailJuzgado(email);
            juzgadoFacade.edit(juzgadoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    public String getCodigoJuzgado() {
        return codigoJuzgado;
    }

    public void setCodigoJuzgado(String codigoJuzgado) {
        this.codigoJuzgado = codigoJuzgado;
    }

    public int getIdTipoJuzgado() {
        return idTipoJuzgado;
    }

    public void setIdTipoJuzgado(int idTipoJuzgado) {
        this.idTipoJuzgado = idTipoJuzgado;
    }

    public String getNombreJuzgado() {
        return nombreJuzgado;
    }

    public void setNombreJuzgado(String nombreJuzgado) {
        this.nombreJuzgado = nombreJuzgado;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CfgJuzgado getJuzgadoSeleccionado() {
        return juzgadoSeleccionado;
    }

    public void setJuzgadoSeleccionado(CfgJuzgado juzgadoSeleccionado) {
        this.juzgadoSeleccionado = juzgadoSeleccionado;
    }

    public List<CfgMunicipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public List<CfgJuzgado> getListaJuzgado() {
        return listaJuzgado;
    }

}
