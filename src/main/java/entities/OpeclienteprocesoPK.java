/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mario
 */
@Embeddable
public class OpeclienteprocesoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente", nullable = false)
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proceso", nullable = false)
    private long idProceso;

    public OpeclienteprocesoPK() {
    }

    public OpeclienteprocesoPK(int idCliente, long idProceso) {
        this.idCliente = idCliente;
        this.idProceso = idProceso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(long idProceso) {
        this.idProceso = idProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idProceso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpeclienteprocesoPK)) {
            return false;
        }
        OpeclienteprocesoPK other = (OpeclienteprocesoPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idProceso != other.idProceso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OpeclienteprocesoPK[ idCliente=" + idCliente + ", idProceso=" + idProceso + " ]";
    }
    
}
