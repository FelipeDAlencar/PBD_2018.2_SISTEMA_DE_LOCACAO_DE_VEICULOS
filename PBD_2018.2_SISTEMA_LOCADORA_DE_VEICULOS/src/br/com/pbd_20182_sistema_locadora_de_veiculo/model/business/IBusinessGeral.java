/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;

/**
 *
 * @author Felipe
 */
public interface IBusinessGeral {
    
    public void salvar(Geral geral);
    public void alterar(Geral geral);
}