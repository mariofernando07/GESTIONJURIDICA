/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.operacion;

import entities.CfgEstadoProceso;
import entities.CfgTipoProceso;
import entities.CfgTipoSoporteTipoProceso;
import entities.OpeCliente;
import entities.OpeProceso;
import entities.OpeProcesoSoporte;
import entities.OpeProcesoSoportePK;
import entities.Opeclienteproceso;
import facades.CfgEstadoProcesoFacade;
import facades.CfgTipoProcesoFacade;
import facades.CfgTipoSoporteFacade;
import facades.CfgTipoSoporteTipoProcesoFacade;
import facades.OpeClienteFacade;
import facades.OpeclienteprocesoFacade;
import facades.OpeProcesoFacade;
import facades.OpeProcesoSoporteFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class OpeProcesoMB implements Serializable {

    private Long idProceso;
    private String proceso;//descripcion
    private Date fecha;
    private int idTipoProceso;
    private int idEstadoProceso;

    //INFORMACION DE SOPORTES
    private int tipoSoporte;
    private String descripcionSoporte;
    private Date fechaSoporte;
    private UploadedFile file;
    private String ruta;

    private StreamedContent fileDownload;

    private OpeProceso opeProcesoSeleccionado;
    private OpeCliente clienteSeleccionado;
    private OpeProcesoSoporte opeProcesoSoporteSeleccionado;
    private List<CfgTipoProceso> listaTipoProceso;
    private List<CfgEstadoProceso> listaEstadoProceso;
    private List<OpeCliente> listaCliente;
    private List<OpeCliente> listaClienteProceso;
//    private List<OpeCliente> listaClienteProcesoAuxiliarRemove;//lista usada para eliminar cliente de un proceso previamente creado
//    private List<OpeCliente> listaClienteProcesoAuxiliarAdd;//lista usada para ingresar nuevos clientes de un proceso previamente creado
    private List<CfgTipoSoporteTipoProceso> listaSoporteProceso;
    private List<OpeProcesoSoporte> ListaOpeProcesoSoporte;

    @EJB
    OpeClienteFacade clienteFacade;
    @EJB
    CfgTipoProcesoFacade tipoProcesoFacade;
    @EJB
    CfgEstadoProcesoFacade estadoProcesoFacade;
    @EJB
    OpeProcesoFacade opeProcesoFacade;
    @EJB
    OpeclienteprocesoFacade opeClienteProcesoFacade;
    @EJB
    CfgTipoSoporteTipoProcesoFacade tipoSoporteTipoProcesoFacade;
    @EJB
    CfgTipoSoporteFacade tipoSoporteFacade;
    @EJB
    OpeProcesoSoporteFacade opeProcesoSoporteFacade;

    public OpeProcesoMB() {
    }

    @PostConstruct
    private void init() {
        listaClienteProceso = new ArrayList();
        ListaOpeProcesoSoporte = new ArrayList();
        listaEstadoProceso = estadoProcesoFacade.findAll();
        setIdEstadoProceso(1);
        listaTipoProceso = tipoProcesoFacade.findAll();
        setIdTipoProceso(1);
        ruta = "C:/soporteproceso";
    }

    public void deseleccionarProceso() {
        listaClienteProceso.clear();
        idProceso = null;
        proceso = null;
        fecha = null;
        idTipoProceso = 1;
        cargarTipoSoporte();
        idEstadoProceso = 1;
        listaClienteProceso.clear();
        ListaOpeProcesoSoporte.clear();
        ListaOpeProcesoSoporte = new ArrayList();
        RequestContext.getCurrentInstance().update("IdFormGestionProceso");
    }

    public void cargarClientes() {
        listaCliente = clienteFacade.findAll();
        RequestContext.getCurrentInstance().update("IdFormModalSeleccinarCliente");
        RequestContext.getCurrentInstance().execute("PF('dlgSeleccinarCliente').show()");
    }

    public void asociarClientesAListaProceso() {
        if (opeProcesoSeleccionado != null && clienteSeleccionado != null) {
            Opeclienteproceso opeClienteProceso = new Opeclienteproceso(clienteSeleccionado.getIdCliente(), opeProcesoSeleccionado.getIdProceso());
//            opeClienteProceso.setOpeCliente(clienteSeleccionado);
//            opeClienteProceso.setOpeProceso(opeProcesoSeleccionado);
            opeClienteProcesoFacade.create(opeClienteProceso);
            List<Integer> identificadoresClientes = opeClienteProcesoFacade.buscarClientesPorProceso(opeProcesoSeleccionado.getIdProceso());
            listaClienteProceso = clienteFacade.buscarClientesPorIdentificadores(identificadoresClientes);
        }
        RequestContext.getCurrentInstance().update("IdFormGestionProceso");
    }

    public void quitarCliente(ActionEvent event) {
        OpeCliente opeCliente = (OpeCliente) event.getComponent().getAttributes().get("cliente");
        List<Opeclienteproceso> aux = opeClienteProcesoFacade.findAll();
        if (opeCliente != null) {
            if (opeProcesoSeleccionado != null) {
                try {
                    Opeclienteproceso opeClienteProceso = opeClienteProcesoFacade.buscarPorClienteAndProceso(opeCliente.getIdCliente(), opeProcesoSeleccionado.getIdProceso());
                    opeClienteProcesoFacade.remove(opeClienteProceso);
                    List<Integer> identificadoresClientes = opeClienteProcesoFacade.buscarClientesPorProceso(opeProcesoSeleccionado.getIdProceso());
                    listaClienteProceso = clienteFacade.buscarClientesPorIdentificadores(identificadoresClientes);
                    RequestContext.getCurrentInstance().update("IdFormGestionProceso");
                } catch (Exception e) {
                }

            }
        }
    }

    private boolean buscarClienteEnLista() {
        boolean aux = false;
        for (OpeCliente cliente : listaClienteProceso) {
            if (clienteSeleccionado.equals(cliente)) {
                aux = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Este cliente ya fue asociado al proceso"));
                break;
            }
        }
        return aux;
    }

    //CARGA EL TIPO DE SOPORTE POR TIPO PROCESO
    public void cargarTipoSoporte() {
        CfgTipoProceso cfgTipoProceso = tipoProcesoFacade.find(idTipoProceso);
        listaSoporteProceso = tipoSoporteTipoProcesoFacade.buscarTipoSoportePorTipoProceso(cfgTipoProceso);
//        RequestContext.getCurrentInstance().update("IdFormModalAdicionarSoporte:IdItemsTipoSoporte");
    }

    //Sube el soporte y guarda en base de datos el registro del soporte agregado
    public void handleFileUpload(FileUploadEvent event) {
        try {
            file = event.getFile();
            String path = null;
            if (file != null) {
                String nombreArchvio = file.getFileName();
                path = ruta + "/proceso_" + opeProcesoSeleccionado.getIdProceso() + "/" + nombreArchvio;
                uploadArchivo(file, path);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Soporte cargado"));
                RequestContext.getCurrentInstance().execute("PF('dlgLogo').hide()");
            }
            OpeProcesoSoporte procesoSoporte = new OpeProcesoSoporte();
            procesoSoporte.setFecha(new Date());
            procesoSoporte.setDescripcion(descripcionSoporte);
            procesoSoporte.setOpeProceso(opeProcesoSeleccionado);
            CfgTipoSoporteTipoProceso tipoSoporteTipoProceso = tipoSoporteTipoProcesoFacade.buscarPorIdTipoSoporteAndIdTipoProceso(tipoSoporte, idTipoProceso);
            procesoSoporte.setCfgTipoSoporteTipoProceso(tipoSoporteTipoProceso);
            if (tipoSoporteTipoProceso != null) {
                procesoSoporte.setOpeProcesoSoportePK(new OpeProcesoSoportePK(opeProcesoSeleccionado.getIdProceso(), idTipoProceso, tipoSoporte, path));
            } else {
                procesoSoporte.setOpeProcesoSoportePK(null);
            }
            opeProcesoSoporteFacade.create(procesoSoporte);
            ListaOpeProcesoSoporte = opeProcesoSoporteFacade.buscarSoportesPorProceso(opeProcesoSeleccionado);
            RequestContext.getCurrentInstance().update("IdFormGestionProceso");
        } catch (Exception e) {

        }
    }

    public boolean uploadArchivo(UploadedFile archivoCargado, String rutaDestino) {
        File fichero = new java.io.File(rutaDestino);
        if (fichero.exists()) {//si existe se borra
            fichero.delete();
        }
        fichero = new java.io.File(rutaDestino);
        try (FileOutputStream fileOutput = new FileOutputStream(fichero)) {
            InputStream inputStream = archivoCargado.getInputstream();
            byte[] buffer = new byte[1024];
            int bufferLength;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
        } catch (Exception e) {
            System.out.println("Error 01: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void crearDownloadSoporte() throws FileNotFoundException, MagicParseException, MagicMatchNotFoundException, MagicException {
        if (opeProcesoSoporteSeleccionado != null) {
            File f = new File(opeProcesoSoporteSeleccionado.getOpeProcesoSoportePK().getRuta());
            InputStream stream = new FileInputStream(f);
            String mimeType = Magic.getMagicMatch(f, true).getMimeType();
            fileDownload = new DefaultStreamedContent(stream, mimeType, f.getName());
        }
    }

    public void accion() {
        if (opeProcesoSeleccionado != null) {
            actualizarProceso();
        } else {
            crearProceso();
        }
    }

    private void crearProceso() {
        try {
            OpeProceso opeProceso = new OpeProceso();
            opeProceso.setNomProceso(proceso);
            opeProceso.setFecProceso(fecha);
            opeProceso.setIdTipoProceso(tipoProcesoFacade.find(idTipoProceso));
            opeProceso.setIdEstadoProceso(estadoProcesoFacade.find(idEstadoProceso));
            opeProcesoFacade.create(opeProceso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
            File directorio = new File("C:\\soporteproceso\\proceso_" + opeProceso.getIdProceso());
            directorio.mkdir();
            opeProcesoSeleccionado = opeProceso;
            cargarProceso();
            RequestContext.getCurrentInstance().update("IdFormGestionProceso");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }
    }

    private void actualizarProceso() {
        try {
            opeProcesoSeleccionado.setNomProceso(proceso);
            opeProcesoSeleccionado.setFecProceso(fecha);
            opeProcesoSeleccionado.setIdTipoProceso(tipoProcesoFacade.find(idTipoProceso));
            opeProcesoSeleccionado.setIdEstadoProceso(estadoProcesoFacade.find(idEstadoProceso));
            opeProcesoFacade.edit(opeProcesoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Guardado"));
        }

    }

    public String cargarProceso() {
        if (opeProcesoSeleccionado != null) {
            listaClienteProceso.clear();
            ListaOpeProcesoSoporte.clear();
            idProceso = opeProcesoSeleccionado.getIdProceso();
            proceso = opeProcesoSeleccionado.getNomProceso();
            fecha = opeProcesoSeleccionado.getFecProceso();
            idTipoProceso = opeProcesoSeleccionado.getIdTipoProceso().getIdTipoProceso();
            cargarTipoSoporte();
            idEstadoProceso = opeProcesoSeleccionado.getIdEstadoProceso().getIdEstadoProceso();
            List<Integer> identificadoresClientes = opeClienteProcesoFacade.buscarClientesPorProceso(opeProcesoSeleccionado.getIdProceso());
            listaClienteProceso = clienteFacade.buscarClientesPorIdentificadores(identificadoresClientes);
            ListaOpeProcesoSoporte = opeProcesoSoporteFacade.buscarSoportesPorProceso(opeProcesoSeleccionado);
            return "opeProceso?faces-redirect=true";
        } else {
            return "";
        }

    }

    private void eliminarProceso() {
        if (opeProcesoSeleccionado != null) {
            try {
                opeProcesoFacade.remove(opeProcesoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro Eliminado"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Registro no Eliminado"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione el registro a eliminar"));
        }
    }

    public List<OpeCliente> getListaCliente() {
        return listaCliente;
    }

    public List<OpeCliente> getListaClienteProceso() {
        return listaClienteProceso;
    }

    public OpeCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(OpeCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<CfgTipoProceso> getListaTipoProceso() {
        return listaTipoProceso;
    }

    public void setListaTipoProceso(List<CfgTipoProceso> listaTipoProceso) {
        this.listaTipoProceso = listaTipoProceso;
    }

    public List<CfgEstadoProceso> getListaEstadoProceso() {
        return listaEstadoProceso;
    }

    public void setListaEstadoProceso(List<CfgEstadoProceso> listaEstadoProceso) {
        this.listaEstadoProceso = listaEstadoProceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(int idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public int getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(int idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public OpeProceso getOpeProcesoSeleccionado() {
        return opeProcesoSeleccionado;
    }

    public void setOpeProcesoSeleccionado(OpeProceso opeProcesoSeleccionado) {
        this.opeProcesoSeleccionado = opeProcesoSeleccionado;
    }

    public List<CfgTipoSoporteTipoProceso> getListaSoporteProceso() {
        return listaSoporteProceso;
    }

    public int getTipoSoporte() {
        return tipoSoporte;
    }

    public void setTipoSoporte(int tipoSoporte) {
        this.tipoSoporte = tipoSoporte;
    }

    public String getDescripcionSoporte() {
        return descripcionSoporte;
    }

    public void setDescripcionSoporte(String descripcionSoporte) {
        this.descripcionSoporte = descripcionSoporte;
    }

    public Date getFechaSoporte() {
        return fechaSoporte;
    }

    public void setFechaSoporte(Date fechaSoporte) {
        this.fechaSoporte = fechaSoporte;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public List<OpeProcesoSoporte> getListaOpeProcesoSoporte() {
        return ListaOpeProcesoSoporte;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public OpeProcesoSoporte getOpeProcesoSoporteSeleccionado() {
        return opeProcesoSoporteSeleccionado;
    }

    public void setOpeProcesoSoporteSeleccionado(OpeProcesoSoporte opeProcesoSoporteSeleccionado) {
        this.opeProcesoSoporteSeleccionado = opeProcesoSoporteSeleccionado;
    }

}
