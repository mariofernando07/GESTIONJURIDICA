/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(catalog = "gestjur", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opeclienteproceso.findAll", query = "SELECT o FROM Opeclienteproceso o"),
    @NamedQuery(name = "Opeclienteproceso.findByIdCliente", query = "SELECT o FROM Opeclienteproceso o WHERE o.opeclienteprocesoPK.idCliente = :idCliente"),
    @NamedQuery(name = "Opeclienteproceso.findByIdProceso", query = "SELECT o FROM Opeclienteproceso o WHERE o.opeclienteprocesoPK.idProceso = :idProceso")})
public class Opeclienteproceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OpeclienteprocesoPK opeclienteprocesoPK;

    public Opeclienteproceso() {
    }

    public Opeclienteproceso(OpeclienteprocesoPK opeclienteprocesoPK) {
        this.opeclienteprocesoPK = opeclienteprocesoPK;
    }

    public Opeclienteproceso(int idCliente, long idProceso) {
        this.opeclienteprocesoPK = new OpeclienteprocesoPK(idCliente, idProceso);
    }

    public OpeclienteprocesoPK getOpeclienteprocesoPK() {
        return opeclienteprocesoPK;
    }

    public void setOpeclienteprocesoPK(OpeclienteprocesoPK opeclienteprocesoPK) {
        this.opeclienteprocesoPK = opeclienteprocesoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opeclienteprocesoPK != null ? opeclienteprocesoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opeclienteproceso)) {
            return false;
        }
        Opeclienteproceso other = (Opeclienteproceso) object;
        if ((this.opeclienteprocesoPK == null && other.opeclienteprocesoPK != null) || (this.opeclienteprocesoPK != null && !this.opeclienteprocesoPK.equals(other.opeclienteprocesoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Opeclienteproceso[ opeclienteprocesoPK=" + opeclienteprocesoPK + " ]";
    }
    
}
