/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.seguridad;

import entities.SegUsuario;
import facades.SegUsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mario
 */
@ManagedBean
@ViewScoped
public class LoginMB implements Serializable {

    private String usuario;
    private String password;

    @EJB
    SegUsuarioFacade usuarioFacade;

    public LoginMB() {
    }

    public String iniciarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
//        ExternalContext econtext = FacesContext.getCurrentInstance().getExternalContext();
        AplicacionMB aplicacionMB = context.getApplication().evaluateExpressionGet(context, "#{aplicacionMB}", AplicacionMB.class);
        if (usuario.trim().isEmpty()) {
//            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese el usuario"));
            RequestContext.getCurrentInstance().update("message");
            return "";
        }
        if (password.trim().isEmpty()) {
//            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese contraseña"));
            RequestContext.getCurrentInstance().update("message");
            return "";
        }
        SegUsuario usuarioActual = usuarioFacade.buscarUsuario(usuario.trim().toLowerCase(), password.trim());

        if (usuarioActual == null) {
//                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Revise las credenciales"));
            RequestContext.getCurrentInstance().update("message");
            return "";
        } else {

            if (!aplicacionMB.getListaUsuariosActivos().contains(usuarioActual)) {
//                        usuarioActual.setRememberToken(econtext.getSessionId(false));
                aplicacionMB.insertarUsuario(usuarioActual);
                SesionMB sesionMB = context.getApplication().evaluateExpressionGet(context, "#{sesionMB}", SesionMB.class);
                sesionMB.setUsuarioActual(usuarioActual);
                sesionMB.tokenSession();
                sesionMB.setAutenticado(true);
//                        sesionMB.insertarItemSession(usuarioActual);
                return "home?faces-redirect=true";
            } else {
                SesionMB sesionMB = context.getApplication().evaluateExpressionGet(context, "#{sesionMB}", SesionMB.class);
                if (sesionMB.getUsuarioActual() != null) {
                    return "home?faces-redirect=true";
                } else {
                    String path = aplicacionMB.descartarSession(usuarioActual);
                    if (!path.isEmpty()) {
                        sesionMB.setUsuarioActual(usuarioActual);
                        sesionMB.tokenSession();
                        sesionMB.setAutenticado(true);
//                                sesionMB.insertarItemSession(usuarioActual);
                        aplicacionMB.insertarUsuario(usuarioActual);
                    }
                    return path;
                }
            }
//        System.out.println("creada sesion " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));

        }
//            }
//        }
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

}
