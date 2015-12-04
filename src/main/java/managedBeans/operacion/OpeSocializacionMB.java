/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.operacion;

import entities.CfgMunicipio;
import entities.OpeSocializacion;
import facades.CfgMunicipioFacade;
import facades.OpeSocializacionFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import managedBeans.seguridad.AplicacionMB;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class OpeSocializacionMB implements Serializable {

    private String nombreSocializacion;
    private String codDepartamento;
    private String codMunicipio;
    private Date fecha;
    private String descripcionSocializacion;

    private OpeSocializacion opeSocializacionSeleccionada;

    private List<OpeSocializacion> listaSocializacion;
    private List<CfgMunicipio> listaMunicipio;

    @EJB
    CfgMunicipioFacade municipioFacade;
    @EJB
    OpeSocializacionFacade socializacionFacade;

    public OpeSocializacionMB() {
    }

    public void cargarSocializaciones() {
        listaSocializacion = socializacionFacade.findAll();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccinarSocializacion");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarSocializacion').show()");
    }

    public void cargarMunicipios() {
        codMunicipio = null;
        listaMunicipio = municipioFacade.buscarMunicipiosPorDepartamento(codDepartamento);
        RequestContext.getCurrentInstance().update("IdFormGestionSocializacion:IdListaMunicipios");
    }

    public void accion() {
        if (opeSocializacionSeleccionada != null) {
            actualizarSocializacion();
        } else {
            crearSocializacion();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        AplicacionMB aplicacionMB = context.getApplication().evaluateExpressionGet(context, "#{aplicacionMB}", AplicacionMB.class);
        aplicacionMB.actualizarSocializacion();
    }

    private void crearSocializacion() {
        try {
            OpeSocializacion opeSocializacion = new OpeSocializacion();
            opeSocializacion.setNomSocializacion(nombreSocializacion);
            opeSocializacion.setFecSocializacion(fecha);
            opeSocializacion.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
            opeSocializacion.setDescripcion(descripcionSocializacion);
            socializacionFacade.create(opeSocializacion);
            opeSocializacionSeleccionada = opeSocializacion;
            cargarSocializacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarSocializacion() {
        try {
            opeSocializacionSeleccionada.setNomSocializacion(nombreSocializacion);
            opeSocializacionSeleccionada.setFecSocializacion(fecha);
            opeSocializacionSeleccionada.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
            opeSocializacionSeleccionada.setDescripcion(descripcionSocializacion);
            socializacionFacade.edit(opeSocializacionSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    public void cargarSocializacion() {
        if (opeSocializacionSeleccionada != null) {
            nombreSocializacion = opeSocializacionSeleccionada.getNomSocializacion();
            fecha = opeSocializacionSeleccionada.getFecSocializacion();
            CfgMunicipio cfgMunicipio =  opeSocializacionSeleccionada.getCfgMunicipio();
            codDepartamento = cfgMunicipio.getCfgMunicipioPK().getCodDepartamento();
            cargarMunicipios();
            codMunicipio = cfgMunicipio.getCfgMunicipioPK().getCodMunicipio();
            descripcionSocializacion = opeSocializacionSeleccionada.getDescripcion();
            RequestContext.getCurrentInstance().update("IdFormGestionSocializacion");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarSocializacion').hide()");
    }

    public void deseleccionarSocializacion() {
        nombreSocializacion = null;
        fecha = null;
        codMunicipio = null;
        codDepartamento = null;
        listaMunicipio = null;
        descripcionSocializacion = null;
        RequestContext.getCurrentInstance().update("IdFormGestionSocializacion");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarSocializacion').hide()");
    }

    public String getNombreSocializacion() {
        return nombreSocializacion;
    }

    public void setNombreSocializacion(String nombreSocializacion) {
        this.nombreSocializacion = nombreSocializacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionSocializacion() {
        return descripcionSocializacion;
    }

    public void setDescripcionSocializacion(String descripcionSocializacion) {
        this.descripcionSocializacion = descripcionSocializacion;
    }

    public List<OpeSocializacion> getListaSocializacion() {
        return listaSocializacion;
    }

    public OpeSocializacion getOpeSocializacionSeleccionada() {
        return opeSocializacionSeleccionada;
    }

    public void setOpeSocializacionSeleccionada(OpeSocializacion opeSocializacionSeleccionada) {
        this.opeSocializacionSeleccionada = opeSocializacionSeleccionada;
    }

    public List<CfgMunicipio> getListaMunicipio() {
        return listaMunicipio;
    }

}
