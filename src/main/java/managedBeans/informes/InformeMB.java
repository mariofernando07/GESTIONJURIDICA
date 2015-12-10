/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.informes;

import entities.CfgEstadoProceso;
import entities.CfgJuzgado;
import entities.CfgTipoProceso;
import entities.OpeCliente;
import entities.OpeDemanda;
import entities.OpeProceso;
import entities.SegUsuario;
import facades.CfgEstadoProcesoFacade;
import facades.CfgJuzgadoFacade;
import facades.CfgTipoProcesoFacade;
import facades.OpeClienteFacade;
import facades.OpeDemandaFacade;
import facades.OpeProcesoFacade;
import facades.SegUsuarioFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Mario
 */
@ManagedBean
@SessionScoped
public class InformeMB implements Serializable {

    private List<OpeCliente> listaCliente;
    private List<OpeDemanda> listaDemanda;
    private List<OpeProceso> listaProceso;
    
    
    private List<CfgTipoProceso> listaTipoProceso;
    private List<CfgEstadoProceso> listaEstadoProceso;
    private List<SegUsuario> listaAbogados;
    private List<CfgJuzgado> listaJuzgado;

    @EJB
    OpeClienteFacade clienteFacade;
    @EJB
    OpeProcesoFacade procesoFacade;
    @EJB
    OpeDemandaFacade demandaFacade;
    @EJB
    CfgTipoProcesoFacade tipoProcesoFacade;
    @EJB
    CfgEstadoProcesoFacade estadoProcesoFacade;
    @EJB
    SegUsuarioFacade usuarioFacade;
    @EJB
    CfgJuzgadoFacade juzgadoFacade;
    
    public InformeMB() {
    }

    public void cargarClientes() {
        listaCliente = clienteFacade.findAll();
    }

    public void cargarProcesos() {
        listaProceso = procesoFacade.findAll();
        listaTipoProceso = tipoProcesoFacade.findAll();
        listaEstadoProceso = estadoProcesoFacade.findAll();
    }
    
    public void cargarEstadoProceso(){
        listaEstadoProceso = estadoProcesoFacade.findAll();
    }

    public void cargarDemandas() {
        listaDemanda = demandaFacade.findAll();
        listaAbogados = usuarioFacade.buscarAbogados();
        listaJuzgado = juzgadoFacade.findAll();
    }

    public List<OpeCliente> getListaCliente() {
        return listaCliente;
    }

    public List<OpeProceso> getListaProceso() {
        return listaProceso;
    }

    public List<OpeDemanda> getListaDemanda() {
        return listaDemanda;
    }

    public List<CfgTipoProceso> getListaTipoProceso() {
        return listaTipoProceso;
    }

    public List<CfgEstadoProceso> getListaEstadoProceso() {
        return listaEstadoProceso;
    }

    public List<SegUsuario> getListaAbogados() {
        return listaAbogados;
    }

    public List<CfgJuzgado> getListaJuzgado() {
        return listaJuzgado;
    }

}
