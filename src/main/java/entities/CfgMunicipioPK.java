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
import javax.validation.constraints.Size;

/**
 *
 * @author Mario
 */
@Embeddable
public class CfgMunicipioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_departamento", nullable = false, length = 2)
    private String codDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "cod_municipio", nullable = false, length = 5)
    private String codMunicipio;

    public CfgMunicipioPK() {
    }

    public CfgMunicipioPK(String codDepartamento, String codMunicipio) {
        this.codDepartamento = codDepartamento;
        this.codMunicipio = codMunicipio;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDepartamento != null ? codDepartamento.hashCode() : 0);
        hash += (codMunicipio != null ? codMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CfgMunicipioPK)) {
            return false;
        }
        CfgMunicipioPK other = (CfgMunicipioPK) object;
        if ((this.codDepartamento == null && other.codDepartamento != null) || (this.codDepartamento != null && !this.codDepartamento.equals(other.codDepartamento))) {
            return false;
        }
        if ((this.codMunicipio == null && other.codMunicipio != null) || (this.codMunicipio != null && !this.codMunicipio.equals(other.codMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CfgMunicipioPK[ codDepartamento=" + codDepartamento + ", codMunicipio=" + codMunicipio + " ]";
    }
    
}
