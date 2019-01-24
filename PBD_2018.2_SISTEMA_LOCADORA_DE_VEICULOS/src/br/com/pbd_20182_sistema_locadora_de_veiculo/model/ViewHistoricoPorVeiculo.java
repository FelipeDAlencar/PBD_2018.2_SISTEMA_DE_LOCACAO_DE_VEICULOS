/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Entity
public class ViewHistoricoPorVeiculo implements Serializable{
    
    @Column(name = "revisaoId")
    @Id
    private Integer revisaoId;
    
    @Column(name = "veiculoId")
    private Integer veiculoId;
    
    @Column(name = "modeloVeiculo")
    private String modeloVeiculo;
    
    @Column(name = "dataDeRevisao")
    @Temporal(TemporalType.DATE)
    private Date dataDeRevisao;
    
    @Column(name = "placaVeiculo")
    private String placaVeiculo;

    public Integer getRevisaoId() {
        return revisaoId;
    }

    public void setRevisaoId(Integer revisaoId) {
        this.revisaoId = revisaoId;
    }

    public Integer getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Integer veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Date getDataDeRevisao() {
        return dataDeRevisao;
    }

    public void setDataDeRevisao(Date dataDeRevisao) {
        this.dataDeRevisao = dataDeRevisao;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }
    
    
    
    
    
}
