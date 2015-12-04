/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.operacion;

import entities.CfgJuzgado;
import entities.CfgTipoDemanda;
import entities.OpeCliente;
import entities.OpeDemanda;
import entities.OpeProceso;
import entities.OpeProcesoSoporte;
import entities.SegUsuario;
import facades.CfgJuzgadoFacade;
import facades.CfgTipoDemandaFacade;
import facades.OpeClienteFacade;
import facades.OpeclienteprocesoFacade;
import facades.OpeDemandaFacade;
import facades.OpeProcesoFacade;
import facades.OpeProcesoSoporteFacade;
import facades.SegUsuarioFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class OpeDemandaMB implements Serializable {

    private List<OpeCliente> listaCliente;
    private List<SegUsuario> listaAbogado;
    private List<CfgJuzgado> listaJuzgado;
    private OpeCliente opeClienteSeleccionado;
    private String numIdentificacionCliente;
    private String nombreCompletoCliente;
    private List<OpeProceso> listaProceso;
    private OpeProceso opeProcesoSeleccionado;
    private OpeDemanda opeDemandaSeleccionada;
    private String numDemanda;
    private int idTipoDemanda;
    private String descripcionDemanda;
    private Date fecha;
    private long numProceso;
    private int idAbogado;
    private int idJuzgado;
    private String juez;

    private StreamedContent fileDownload;

    private List<OpeProcesoSoporte> listaOpeProcesoSoporte;
    private OpeProcesoSoporte opeProcesoSoporteSeleccionado;

    @EJB
    CfgTipoDemandaFacade tipoDemandaFacade;
    @EJB
    SegUsuarioFacade usuarioFacade;
    @EJB
    CfgJuzgadoFacade juzgadoFacade;
    @EJB
    OpeClienteFacade clienteFacade;
    @EJB
    OpeclienteprocesoFacade opeClienteProcesoFacade;
    @EJB
    OpeProcesoFacade opeProcesoFacade;
    @EJB
    OpeDemandaFacade opeDemandaFacade;
    @EJB
    OpeProcesoSoporteFacade opeProcesoSoporteFacade;

    public OpeDemandaMB() {

    }

    @PostConstruct
    private void init() {
        fecha = new Date();
    }

    //carga el listado de abogados y jueces
    public void cargarListados() {
        listaAbogado = usuarioFacade.buscarAbogadosActivos();
        listaJuzgado = juzgadoFacade.findAll();
    }

    public void cargarClientes() {
        listaCliente = clienteFacade.findAll();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccionarCliente");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').show()");
    }

    public void cargarInformacionCliente() {
        if (opeClienteSeleccionado != null) {
            numIdentificacionCliente = opeClienteSeleccionado.getNumIdentificacion();
            nombreCompletoCliente = opeClienteSeleccionado.nombreCompleto();
            List<Integer> idProcesos = opeClienteProcesoFacade.buscarProcesosPorCliente(opeClienteSeleccionado.getIdCliente());
            listaProceso = null;
            if (!idProcesos.isEmpty()) {
                listaProceso = opeProcesoFacade.buscarProcesosAprobadosPorIdentificadores(idProcesos);
            }
            limpiarPanelGestionDemanda();
            RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelCliente");
            RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdTablaProcesos");
            RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelDemanda");
            RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').hide()");
        }
    }

    public void deseleccionarCliente() {
        opeClienteSeleccionado = null;
        numIdentificacionCliente = null;
        nombreCompletoCliente = null;
        listaProceso = null;
        listaOpeProcesoSoporte = null;
        limpiarPanelGestionDemanda();
        RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelCliente");
        RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdTablaProcesos");
        RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelDemanda");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccionarCliente').hide()");

    }

    public void buscarJuez() {
        CfgJuzgado cfgJuzgado = juzgadoFacade.find(idJuzgado);
        juez = null;
        if (cfgJuzgado != null) {
            juez = cfgJuzgado.getJuez();
        }
        RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdJuez");
    }

    private void limpiarPanelGestionDemanda() {
        numDemanda = null;
        idTipoDemanda = 0;
        descripcionDemanda = null;
        fecha = new Date();
        numProceso = 0;
        idAbogado = 0;
        idJuzgado = 0;
        juez = null;
    }

    public void buscarDemanda() {
        opeDemandaSeleccionada = null;
        if (opeProcesoSeleccionado != null) {
            opeDemandaSeleccionada = opeDemandaFacade.buscarDemandaPorProceso(opeProcesoSeleccionado);
            listaOpeProcesoSoporte = opeProcesoSoporteFacade.buscarSoportesPorProceso(opeProcesoSeleccionado);
        }
        cargarDemanda(false);
    }

    public String cargarDemanda(boolean ban) {
        limpiarPanelGestionDemanda();
        String URL = "";
        if (opeDemandaSeleccionada != null) {
            numDemanda = opeDemandaSeleccionada.getCodDemanda();
            idTipoDemanda = opeDemandaSeleccionada.getIdTipoDemanda().getIdTipoDemanda();
            descripcionDemanda = opeDemandaSeleccionada.getDescDemanda();
            fecha = opeDemandaSeleccionada.getFecDemanda();
            numProceso = opeDemandaSeleccionada.getIdProceso().getIdProceso();
            idAbogado = opeDemandaSeleccionada.getIdUsuario().getIdUsuario();
            idJuzgado = opeDemandaSeleccionada.getIdJuzgado().getIdJuzgado();
            juez = opeDemandaSeleccionada.getJuez();
            URL = "opeDemanda?faces-redirect=true";
        } else {
            if (opeProcesoSeleccionado != null) {
                numProceso = opeProcesoSeleccionado.getIdProceso();
            }
        }
        if (!ban) {
            RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelDemanda");
            RequestContext.getCurrentInstance().update("IdFormGestionDemanda:IdPanelSoporte");
        }
        return URL;
    }

    public void crearDownloadSoporte() throws FileNotFoundException, MagicParseException, MagicMatchNotFoundException, MagicException {
        if (opeProcesoSoporteSeleccionado != null) {
            File f = new File(opeProcesoSoporteSeleccionado.getOpeProcesoSoportePK().getRuta());
            InputStream stream = new FileInputStream(f);
            String mimeType = Magic.getMagicMatch(f, true).getMimeType();
            setFileDownload(new DefaultStreamedContent(stream, mimeType, f.getName()));
        }
    }

    public void accion() {
        if (opeDemandaSeleccionada != null) {
            actualizarDemanda();
        } else {
            crearDemanda();
        }
    }

    private void crearDemanda() {
        if (opeProcesoSeleccionado == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Falta Seleccionar un proceso"));
            return;
        }
        try {
            OpeDemanda opeDemanda = new OpeDemanda();
            opeDemanda.setCodDemanda(numDemanda);
            CfgTipoDemanda tipoDemanda = tipoDemandaFacade.find(idTipoDemanda);
            opeDemanda.setIdTipoDemanda(tipoDemanda);
            opeDemanda.setIdProceso(opeProcesoSeleccionado);
            opeDemanda.setFecDemanda(fecha);
            opeDemanda.setDescDemanda(descripcionDemanda);
            opeDemanda.setIdUsuario(usuarioFacade.find(idAbogado));
            opeDemanda.setIdJuzgado(juzgadoFacade.find(idJuzgado));
            opeDemanda.setJuez(juez);
            opeDemandaFacade.create(opeDemanda);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarDemanda() {
        try {
            opeDemandaSeleccionada.setCodDemanda(numDemanda);
            CfgTipoDemanda tipoDemanda = tipoDemandaFacade.find(idTipoDemanda);
            opeDemandaSeleccionada.setIdTipoDemanda(tipoDemanda);
            opeDemandaSeleccionada.setFecDemanda(fecha);
            opeDemandaSeleccionada.setDescDemanda(descripcionDemanda);
            opeDemandaSeleccionada.setIdUsuario(usuarioFacade.find(idAbogado));
            opeDemandaSeleccionada.setIdJuzgado(juzgadoFacade.find(idJuzgado));
            opeDemandaSeleccionada.setJuez(juez);
            opeDemandaFacade.edit(opeDemandaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }

    }

    public List<OpeCliente> getListaCliente() {
        return listaCliente;
    }

    public List<SegUsuario> getListaAbogado() {
        return listaAbogado;
    }

    public List<CfgJuzgado> getListaJuzgado() {
        return listaJuzgado;
    }

    public OpeCliente getOpeClienteSeleccionado() {
        return opeClienteSeleccionado;
    }

    public void setOpeClienteSeleccionado(OpeCliente opeClienteSeleccionado) {
        this.opeClienteSeleccionado = opeClienteSeleccionado;
    }

    public List<OpeProceso> getListaProceso() {
        return listaProceso;
    }

    public OpeProceso getOpeProcesoSeleccionado() {
        return opeProcesoSeleccionado;
    }

    public void setOpeProcesoSeleccionado(OpeProceso opeProcesoSeleccionado) {
        this.opeProcesoSeleccionado = opeProcesoSeleccionado;
    }

    public OpeDemanda getOpeDemandaSeleccionada() {
        return opeDemandaSeleccionada;
    }

    public void setOpeDemandaSeleccionada(OpeDemanda opeDemandaSeleccionada) {
        this.opeDemandaSeleccionada = opeDemandaSeleccionada;
    }

    public String getNumDemanda() {
        return numDemanda;
    }

    public void setNumDemanda(String numDemanda) {
        this.numDemanda = numDemanda;
    }

    public int getIdTipoDemanda() {
        return idTipoDemanda;
    }

    public void setIdTipoDemanda(int idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public String getDescripcionDemanda() {
        return descripcionDemanda;
    }

    public void setDescripcionDemanda(String descripcionDemanda) {
        this.descripcionDemanda = descripcionDemanda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getNumProceso() {
        return numProceso;
    }

    public int getIdAbogado() {
        return idAbogado;
    }

    public void setIdAbogado(int idAbogado) {
        this.idAbogado = idAbogado;
    }

    public int getIdJuzgado() {
        return idJuzgado;
    }

    public void setIdJuzgado(int idJuzgado) {
        this.idJuzgado = idJuzgado;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public String getNumIdentificacionCliente() {
        return numIdentificacionCliente;
    }

    public String getNombreCompletoCliente() {
        return nombreCompletoCliente;
    }

    public List<OpeProcesoSoporte> getListaOpeProcesoSoporte() {
        return listaOpeProcesoSoporte;
    }

    public void setListaOpeProcesoSoporte(List<OpeProcesoSoporte> listaOpeProcesoSoporte) {
        this.listaOpeProcesoSoporte = listaOpeProcesoSoporte;
    }

    public OpeProcesoSoporte getOpeProcesoSoporteSeleccionado() {
        return opeProcesoSoporteSeleccionado;
    }

    public void setOpeProcesoSoporteSeleccionado(OpeProcesoSoporte opeProcesoSoporteSeleccionado) {
        this.opeProcesoSoporteSeleccionado = opeProcesoSoporteSeleccionado;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

}
