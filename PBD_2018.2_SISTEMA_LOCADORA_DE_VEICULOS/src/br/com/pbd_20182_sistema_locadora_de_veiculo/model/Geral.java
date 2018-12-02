/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "geral_sequence", sequenceName = "geral_seq",initialValue = 1, allocationSize = 1)
public class Geral  implements Serializable, EntidadeBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geral_sequence")
    private Integer id;
    @Column(name = "taxa_higienizacao")
    private double taxaHigienizacao;
    @Column(name = "taxa_combustivel")
    private double taxaCombustivel;
    @Column(name = "hora_de_backup")
    @Temporal(TemporalType.TIME)
    private Date horaDeBackup;
    @Column(name = "metade_da_primeira_diaria")
    private double metadePrimeiraDiaria;
    
    
    
    
    
    public double getTaxaHigienizacao() {
        return taxaHigienizacao;
    }

    public void setTaxaHigienizacao(double taxaHigienizacao) {
        this.taxaHigienizacao = taxaHigienizacao;
    }

    public double getTaxaCombustivel() {
        return taxaCombustivel;
    }

    public void setTaxaCombustivel(double taxaCombustivel) {
        this.taxaCombustivel = taxaCombustivel;
    }

    public Date getHoraDeBackup() {
        return horaDeBackup;
    }

    public void setHoraDeBackup(Date horaDeBackup) {
        this.horaDeBackup = horaDeBackup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMetadePrimeiraDiaria() {
        return metadePrimeiraDiaria;
    }

    public void setMetadePrimeiraDiaria(double metadePrimeiraDiaria) {
        this.metadePrimeiraDiaria = metadePrimeiraDiaria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taxaHigienizacao) ^ (Double.doubleToLongBits(this.taxaHigienizacao) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taxaCombustivel) ^ (Double.doubleToLongBits(this.taxaCombustivel) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.horaDeBackup);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.metadePrimeiraDiaria) ^ (Double.doubleToLongBits(this.metadePrimeiraDiaria) >>> 32));
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
        final Geral other = (Geral) obj;
        if (Double.doubleToLongBits(this.taxaHigienizacao) != Double.doubleToLongBits(other.taxaHigienizacao)) {
            return false;
        }
        if (Double.doubleToLongBits(this.taxaCombustivel) != Double.doubleToLongBits(other.taxaCombustivel)) {
            return false;
        }
        if (Double.doubleToLongBits(this.metadePrimeiraDiaria) != Double.doubleToLongBits(other.metadePrimeiraDiaria)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.horaDeBackup, other.horaDeBackup)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
