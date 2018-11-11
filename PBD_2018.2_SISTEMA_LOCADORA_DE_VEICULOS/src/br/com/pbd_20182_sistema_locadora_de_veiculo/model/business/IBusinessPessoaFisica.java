/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessPessoaFisica {
    
    public void salvar(PessoaFisica pessoaFisica)throws BusinessExpection;
    public ArrayList<PessoaFisica> listarTodos()throws BusinessExpection;
    public PessoaFisica buscarPorId(int id)throws BusinessExpection;
    public void alterar(PessoaFisica pessoaFisica)throws BusinessExpection;
}
