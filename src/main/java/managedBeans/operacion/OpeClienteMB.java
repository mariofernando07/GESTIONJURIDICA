package managedBeans.operacion;

import entities.CfgMunicipio;
import entities.OpeCliente;
import entities.OpeDemanda;
import entities.Opeclienteproceso;
import entities.OpeProceso;
import entities.OpeSocializacion;
import facades.CfgDepartamentoFacade;
import facades.CfgEstadoCivilFacade;
import facades.CfgGeneroFacade;
import facades.CfgMunicipioFacade;
import facades.CfgTipoDireccionFacade;
import facades.CfgTipoIdentFacade;
import facades.OpeClienteFacade;
import facades.OpeDemandaFacade;
import facades.OpeclienteprocesoFacade;
import facades.OpeProcesoFacade;
import facades.OpeSocializacionFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class OpeClienteMB implements Serializable {

    private int idTipoIdentificacion;
    private String numIdentificacion;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int idGenero;
    private int idEstadoCivil;
    private String codDepartamento;
    private String codMunicipio;
    private int idSocializacion;
    private int idTipoDireccion;
    private String direccion;
    private String tel1;
    private String tel2;
    private String docConyuge;
    private String telConyuge;
    private String nombreConyuge;

    private List<CfgMunicipio> listaMunicipio;
    private List<OpeCliente> listaCliente;
    private List<OpeProceso> listaProceso;
    private List<OpeDemanda> listaDemanda;

    private OpeCliente clienteSeleccionado;

    @EJB
    CfgTipoIdentFacade tipoIdentificacionFacade;
    @EJB
    CfgGeneroFacade generoFacade;
    @EJB
    CfgEstadoCivilFacade estadoCivilFacade;
    @EJB
    CfgDepartamentoFacade departamentoFacade;
    @EJB
    CfgMunicipioFacade municipioFacade;
    @EJB
    CfgTipoDireccionFacade tipoDireccionFacade;
    @EJB
    OpeSocializacionFacade socializacionFacade;
    @EJB
    OpeClienteFacade clienteFacade;
    @EJB
    OpeclienteprocesoFacade opeClienteProcesoFacade;
    @EJB
    OpeProcesoFacade opeProcesoFacade;
    @EJB
    OpeDemandaFacade demandaFacade;

    public OpeClienteMB() {
    }

    @PostConstruct
    private void init() {
        codMunicipio = null;
    }

    public void cargarMunicipios() {
        codMunicipio = null;
        listaMunicipio = municipioFacade.buscarMunicipiosPorDepartamento(codDepartamento);
        RequestContext.getCurrentInstance().update("IdFormCliente:IdListaMunicipios");
    }

    public void cargarInformacionCliente() {
        if (clienteSeleccionado != null) {
            idTipoIdentificacion = clienteSeleccionado.getIdTipoIdentificacion().getIdIdentificacion();
            numIdentificacion = clienteSeleccionado.getNumIdentificacion();
            nombre1 = clienteSeleccionado.getPrimerNombre();
            nombre2 = clienteSeleccionado.getSegundoNombre();
            apellido1 = clienteSeleccionado.getPrimerApellido();
            apellido2 = clienteSeleccionado.getSegundoApellido();
            idGenero = clienteSeleccionado.getIdGenero().getIdGenero();
            idEstadoCivil = clienteSeleccionado.getIdEstadoCivil().getIdEstadoCivil();
            codDepartamento = clienteSeleccionado.getCfgMunicipio().getCfgMunicipioPK().getCodDepartamento();
            listaMunicipio = municipioFacade.buscarMunicipiosPorDepartamento(codDepartamento);
            codMunicipio = clienteSeleccionado.getCfgMunicipio().getCfgMunicipioPK().getCodMunicipio();
            if (clienteSeleccionado.getIdSocializacion() != null) {
                idSocializacion = clienteSeleccionado.getIdSocializacion().getIdSocializacion();
            } else {
                idSocializacion = 0;
            }
            if (clienteSeleccionado.getIdSocializacion() != null) {
                idSocializacion = clienteSeleccionado.getIdSocializacion().getIdSocializacion();
            }
            idTipoDireccion = clienteSeleccionado.getIdTipoDireccion().getIdTipoDireccion();
            direccion = clienteSeleccionado.getDirCliente();
            tel1 = clienteSeleccionado.getTelefono1();
            tel2 = clienteSeleccionado.getTelefono2();
            docConyuge = clienteSeleccionado.getDocConyuge();
            telConyuge = clienteSeleccionado.getTelConyuge();
            nombreConyuge = clienteSeleccionado.getNomConyuge();
            List<Integer> idProcesos = opeClienteProcesoFacade.buscarProcesosPorCliente(clienteSeleccionado.getIdCliente());
            listaProceso = null;
            listaDemanda = null;
            if (!idProcesos.isEmpty()) {
                listaProceso = opeProcesoFacade.buscarProcesosPorIdentificadores(idProcesos);
                listaDemanda = demandaFacade.buscarDemandasPorProcesos(idProcesos);
            }
            listaDemanda = demandaFacade.findAll();
            RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').hide()");
            RequestContext.getCurrentInstance().update("IdFormCliente");

        }
    }

    public void cargarClientes() {
        listaCliente = clienteFacade.findAll();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccionarCliente");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').show()");
    }

    public void accion() {
        if (clienteSeleccionado == null) {
            crearCliente();
        } else {
            actualizarCliente();
        }
    }

    private void crearCliente() {
        try {
            OpeCliente cliente = new OpeCliente();
            cliente.setIdTipoIdentificacion(tipoIdentificacionFacade.find(idTipoIdentificacion));
            cliente.setNumIdentificacion(numIdentificacion);
            cliente.setPrimerNombre(nombre1);
            cliente.setSegundoNombre(nombre2);
            cliente.setPrimerApellido(apellido1);
            cliente.setSegundoApellido(apellido2);
            cliente.setIdGenero(generoFacade.find(idGenero));
            cliente.setIdEstadoCivil(estadoCivilFacade.find(idEstadoCivil));
            cliente.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
            OpeSocializacion opeSocializacion = socializacionFacade.find(idSocializacion);
            cliente.setIdSocializacion(opeSocializacion);
            cliente.setIdTipoDireccion(tipoDireccionFacade.find(idTipoDireccion));
            cliente.setDirCliente(direccion);
            cliente.setTelefono1(tel1);
            cliente.setTelefono2(tel2);
            cliente.setDocConyuge(docConyuge);
            cliente.setTelConyuge(telConyuge);
            cliente.setNomConyuge(nombreConyuge);
            clienteFacade.create(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }

    }

    private void actualizarCliente() {
        if (clienteSeleccionado != null) {
            try {
                clienteSeleccionado.setIdTipoIdentificacion(tipoIdentificacionFacade.find(idTipoIdentificacion));
                clienteSeleccionado.setNumIdentificacion(numIdentificacion);
                clienteSeleccionado.setPrimerNombre(nombre1);
                clienteSeleccionado.setSegundoNombre(nombre2);
                clienteSeleccionado.setPrimerApellido(apellido1);
                clienteSeleccionado.setSegundoApellido(apellido2);
                clienteSeleccionado.setIdGenero(generoFacade.find(idGenero));
                clienteSeleccionado.setIdEstadoCivil(estadoCivilFacade.find(idEstadoCivil));
                clienteSeleccionado.setCfgMunicipio(municipioFacade.buscarMunicipiosPorDepartamentoAndMunicipio(codDepartamento, codMunicipio));
                OpeSocializacion opeSocializacion = socializacionFacade.find(idSocializacion);
                clienteSeleccionado.setIdSocializacion(opeSocializacion);
                clienteSeleccionado.setIdTipoDireccion(tipoDireccionFacade.find(idTipoDireccion));
                clienteSeleccionado.setDirCliente(direccion);
                clienteSeleccionado.setTelefono1(tel1);
                clienteSeleccionado.setTelefono2(tel2);
                clienteSeleccionado.setDocConyuge(docConyuge);
                clienteSeleccionado.setTelConyuge(telConyuge);
                clienteSeleccionado.setNomConyuge(nombreConyuge);
                clienteFacade.edit(clienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
            }
        }
    }

    public void deseleccionarCliente() {
        clienteSeleccionado = null;
        idTipoIdentificacion = 0;
        numIdentificacion = null;
        nombre1 = null;
        nombre2 = null;
        apellido1 = null;
        apellido2 = null;
        idGenero = 0;
        idEstadoCivil = 0;
        codDepartamento = null;
        codMunicipio = null;
        idSocializacion = 0;
        idTipoDireccion = 0;
        direccion = null;
        tel1 = null;
        tel2 = null;
        docConyuge = null;
        telConyuge = null;
        nombreConyuge = null;
        listaProceso = null;
        listaDemanda = null;
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').hide()");
        RequestContext.getCurrentInstance().update("IdFormCliente");

    }

    public void eliminarCliente() {
        if (clienteSeleccionado != null) {
            try {
                clienteFacade.remove(clienteSeleccionado);
                deseleccionarCliente();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Eliminado"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Eliminado"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione el registro a eliminar"));
        }
    }

//    public String abrirPaginaProceso(ActionEvent actionEvent) {
//        long idProceso = (long) actionEvent.getComponent().getAttributes().get("idproceso");
//        FacesContext context = FacesContext.getCurrentInstance();
//        OpeProcesoMB opeProcesoMB = context.getApplication().evaluateExpressionGet(context, "#{opeProcesoMB}", OpeProcesoMB.class);
//         return opeProcesoMB.cargarProceso(idProceso);
//    }
    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
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

    public int getIdSocializacion() {
        return idSocializacion;
    }

    public void setIdSocializacion(int idSocializacion) {
        this.idSocializacion = idSocializacion;
    }

    public int getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(int idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDocConyuge() {
        return docConyuge;
    }

    public void setDocConyuge(String docConyuge) {
        this.docConyuge = docConyuge;
    }

    public String getTelConyuge() {
        return telConyuge;
    }

    public void setTelConyuge(String telConyuge) {
        this.telConyuge = telConyuge;
    }

    public String getNombreConyuge() {
        return nombreConyuge;
    }

    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }

    public List<CfgMunicipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public List<OpeCliente> getListaCliente() {
        return listaCliente;
    }

    public OpeCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(OpeCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<OpeProceso> getListaProceso() {
        return listaProceso;
    }

    public List<OpeDemanda> getListaDemanda() {
        return listaDemanda;
    }

}
