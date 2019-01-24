/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;

/**
 *
 * @author Felipe
 */

@Entity
@SequenceGenerator(name = "revisao_sequencia", allocationSize = 1,initialValue = 1,sequenceName = "revisao_seq")

public class Revisao implements Serializable,EntidadeBase{
    
    
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "revisao_sequencia",strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @ManyToOne
    private Veiculo veiculo;
    
    @Column(name = "data_de_revisao")
    @Temporal(TemporalType.DATE)
    private Date dataRevisao;

    
    public Revisao(Veiculo veiculo, Date data){
        this.veiculo = veiculo;
        this.dataRevisao = data;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataRevisao() {
        return dataRevisao;
    }

    public void setDataRevisao(Date dataRevisao) {
        this.dataRevisao = dataRevisao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.veiculo);
        hash = 17 * hash + Objects.hashCode(this.dataRevisao);
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
        final Revisao other = (Revisao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.dataRevisao, other.dataRevisao)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
