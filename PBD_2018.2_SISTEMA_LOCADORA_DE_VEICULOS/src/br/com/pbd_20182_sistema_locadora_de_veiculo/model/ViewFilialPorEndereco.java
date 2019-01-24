/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Felipe
 */
@Entity
public class ViewFilialPorEndereco implements Serializable{

    @Column(name = "filialId")
    @Id
    private Integer filialId;
    
    @Column(name = "ruaEnd")
    private String ruaEnd;
  
    @Column(name = "ufEnd")
    private String ufEnd;
    
    public Integer getFilialId() {
        return filialId;
    }

    public void setFilialId(Integer filialId) {
        this.filialId = filialId;
    }

    public String getRuaEnd() {
        return ruaEnd;
    }

    public void setRuaEnd(String ruaEnd) {
        this.ruaEnd = ruaEnd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.filialId);
        hash = 23 * hash + Objects.hashCode(this.ruaEnd);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewFilialPorEndereco other = (ViewFilialPorEndereco) obj;
        if (!Objects.equals(this.ruaEnd, other.ruaEnd)) {
            return false;
        }
        if (!Objects.equals(this.filialId, other.filialId)) {
            return false;
        }
        return true;
    }


    

    
}
