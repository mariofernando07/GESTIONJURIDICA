/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.seguridad;

import entities.CfgDepartamento;
import entities.CfgEstadoCivil;
import entities.CfgGenero;
import entities.CfgMunicipio;
import entities.CfgTipoDemanda;
import entities.CfgTipoDireccion;
import entities.CfgTipoIdent;
import entities.CfgTipoJuzgado;
import entities.OpeSocializacion;
import entities.SegUsuario;
import facades.CfgDepartamentoFacade;
import facades.CfgEstadoCivilFacade;
import facades.CfgGeneroFacade;
import facades.CfgTipoDemandaFacade;
import facades.CfgTipoDireccionFacade;
import facades.CfgTipoIdentFacade;
import facades.CfgTipoJuzgadoFacade;
import facades.OpeSocializacionFacade;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mario
 */
@ManagedBean
@ApplicationScoped
public class AplicacionMB implements Serializable {

    private List<CfgTipoIdent> listaIdentificacion;
    private List<CfgGenero> listaGenero;
    private List<CfgTipoDireccion> listaTipoDireccion;
    private List<OpeSocializacion> listaSocializacion;
    private List<CfgMunicipio> listaMunicipio;
    private List<CfgEstadoCivil> listaEstadoCivil;
    private List<CfgDepartamento> listaDepartamento;
    private List<CfgTipoDemanda> listaTipoDemanda;
    private List<CfgTipoJuzgado> listaTipoJuzgado;

    private List<SegUsuario> listaUsuariosActivos;
    private List<HttpSession> listaSesiones;

    @EJB
    CfgTipoIdentFacade tipoIdentificacionFacade;
    @EJB
    CfgDepartamentoFacade departamentoFacade;
    @EJB
    CfgEstadoCivilFacade estadoCivilFacade;
    @EJB
    CfgGeneroFacade generoFacade;
    @EJB
    CfgTipoDireccionFacade tipoDireccionFacade;
    @EJB
    OpeSocializacionFacade socializacionFacade;
    @EJB
    CfgTipoDemandaFacade tipoDemandaFacade;
    @EJB
    CfgTipoJuzgadoFacade tipoJuzgadoFacade;

    /**
     * Creates a new instance of AplicacionMB
     */
    public AplicacionMB() {
    }

    @PostConstruct
    private void init() {
        actualizarTipoIdentificacion();
        actualizarDepartamento();
        actualizarEstadoCivil();
        listaGenero = generoFacade.findAll();
        listaTipoDemanda = tipoDemandaFacade.findAll();
        listaTipoDireccion = tipoDireccionFacade.findAll();
        listaUsuariosActivos = new ArrayList();
        listaSesiones = new ArrayList();
        actualizarTipoJuzgado();
        actualizarSocializacion();
    }

    public void insertarUsuario(SegUsuario usuario) {
        getListaUsuariosActivos().add(usuario);
//        System.out.println("inicio sesion => " + usuario.getUsuario() + " idSession => " + usuario.getRememberToken());
    }

    public void descartarUsuario(SegUsuario usuario) {
        getListaUsuariosActivos().remove(usuario);
//        System.out.println("cerro sesion => " + usuario.getUsuario() + " idSession => " + usuario.getRememberToken());
    }

    public String descartarSession(SegUsuario usuario) {
        SegUsuario user = buscarUsuario(usuario);
        if (user == null) {
            return "";
        }
        user.getSession().invalidate();
        return "home?faces-redirect=true";
    }

    public void insertarHttpSession(HttpSession session) {
        getListaSesiones().add(session);
//        System.out.println("sesiones activas =>" + listaSesiones.size());
    }

    public void descartarHttpSession(HttpSession session) {
        getListaSesiones().remove(session);
//        System.out.println("sesiones activas =>" + listaSesiones.size());
    }

    private SegUsuario buscarUsuario(SegUsuario usuario) {
        SegUsuario user = null;
        for (SegUsuario u : getListaUsuariosActivos()) {
            if (u.equals(usuario)) {
                user = u;
                break;
            }
        }
        return user;
    }

    public void actualizarTipoIdentificacion() {
        listaIdentificacion = tipoIdentificacionFacade.findAll();
    }

    public void actualizarEstadoCivil() {
        listaEstadoCivil = estadoCivilFacade.findAll();
    }

    public void actualizarSocializacion() {
        listaSocializacion = socializacionFacade.findAll();
    }

    public void actualizarDepartamento() {
        listaDepartamento = departamentoFacade.findAll();
    }

    public void actualizarTipoJuzgado() {
        listaTipoJuzgado = tipoJuzgadoFacade.findAll();
    }
    
    public List<CfgTipoIdent> getListaIdentificacion() {
        return listaIdentificacion;
    }

    public List<CfgGenero> getListaGenero() {
        return listaGenero;
    }

    public List<CfgTipoDireccion> getListaTipoDireccion() {
        return listaTipoDireccion;
    }

    public List<OpeSocializacion> getListaSocializacion() {
        return listaSocializacion;
    }

    public List<CfgMunicipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public List<CfgEstadoCivil> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public List<CfgDepartamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public List<CfgTipoDemanda> getListaTipoDemanda() {
        return listaTipoDemanda;
    }

    public List<SegUsuario> getListaUsuariosActivos() {
        return listaUsuariosActivos;
    }

    public void setListaUsuariosActivos(List<SegUsuario> listaUsuariosActivos) {
        this.listaUsuariosActivos = listaUsuariosActivos;
    }

    public List<HttpSession> getListaSesiones() {
        return listaSesiones;
    }

    public List<CfgTipoJuzgado> getListaTipoJuzgado() {
        return listaTipoJuzgado;
    }

}
