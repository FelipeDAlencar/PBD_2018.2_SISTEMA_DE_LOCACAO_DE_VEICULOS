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
public class ViewLocacaoPorPeriodo implements Serializable {

    @Column(name = "locacaoID")
    @Id
    private Integer locacaoID;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "dataIda")
    @Temporal(TemporalType.DATE)
    private Date dataIda;

    @Column(name = "dataVolta")
    @Temporal(TemporalType.DATE)
    private Date dataVolta;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "finalizada")
    private Boolean finalizada;

    
   
    
    public Integer getLocacaoID() {
        return locacaoID;
    }

    public void setLocacaoID(Integer locacaoID) {
        this.locacaoID = locacaoID;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }


    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataIda() {
        return dataIda;
    }

    public void setDataIda(Date dataIda) {
        this.dataIda = dataIda;
    }

    public Date getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(Date dataVolta) {
        this.dataVolta = dataVolta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

  
    
    
}
