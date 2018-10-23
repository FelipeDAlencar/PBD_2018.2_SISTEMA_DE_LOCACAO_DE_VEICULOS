/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;


import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{
    
    @Column(name = "cpf", length = 14, nullable = false)
    private String CPF;
   
    @Temporal(TemporalType.DATE)
    private Calendar data_nascimento;
   
    @Temporal(TemporalType.DATE)
    private Calendar data_vencimentoCNH;
    
    private String identificacao;
   
    
    @Column(name = "numero_cnh", length = 11, nullable = false)
    private String numero_CNH;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Calendar getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Calendar getData_vencimentoCNH() {
        return data_vencimentoCNH;
    }

    public void setData_vencimentoCNH(Calendar data_vencimentoCNH) {
        this.data_vencimentoCNH = data_vencimentoCNH;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNumero_CNH() {
        return numero_CNH;
    }

    public void setNumero_CNH(String numero_CNH) {
        this.numero_CNH = numero_CNH;
    }
    
    
    
}
