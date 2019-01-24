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
public class ViewMotoristaPorLocacao implements Serializable {

    @Column(name = "locacaoId")
    @Id
    private Integer locacaoId;
    
    @Column(name = "motoId")
    private Integer motoId;

    @Column(name = "numeroCnh")
    private String numeroCnh;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "modeloVeiculo")
    private String modeloVeiculo;

    @Override
    public String toString() {
        return "ViewMotoristaPorLocacao{" + "locacaoId=" + locacaoId + ", motoId=" + motoId + ", numeroCnh=" + numeroCnh + ", nomeCliente=" + nomeCliente + ", modeloVeiculo=" + modeloVeiculo + '}';
    }

    public Integer getLocacaoId() {
        return locacaoId;
    }

    public void setLocacaoId(Integer locacaoId) {
        this.locacaoId = locacaoId;
    }

    public Integer getMotoId() {
        return motoId;
    }

    public void setMotoId(Integer motoId) {
        this.motoId = motoId;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }
    
    

    
   

    
    
  

}
