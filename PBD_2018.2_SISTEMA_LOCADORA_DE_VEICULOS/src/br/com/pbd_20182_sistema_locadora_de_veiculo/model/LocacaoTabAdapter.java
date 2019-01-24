/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class LocacaoTabAdapter extends Locacao{
    
    private String numeroCNH;
    private Date vencimentoCNH;

    public LocacaoTabAdapter() {
       
    }
    
    
    

    public String getNumeroCNH() {
        return numeroCNH;
    }

    public void setNumeroCNH(String numeroCNH) {
        this.numeroCNH = numeroCNH;
    }

    public Date getVencimentoCNH() {
        return vencimentoCNH;
    }

    public void setVencimentoCNH(Date vencimentoCNH) {
        this.vencimentoCNH = vencimentoCNH;
    }
    
    
    
}
