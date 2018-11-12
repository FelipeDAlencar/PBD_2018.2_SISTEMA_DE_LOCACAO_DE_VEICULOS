/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessFuncionario {
    
    public void salvar(Funcionario funcionario)throws BusinessExpection;
    public ArrayList<Funcionario> listarTodos()throws BusinessExpection;
    public Funcionario buscarPorId(int id)throws BusinessExpection;
    public void alterar(Funcionario funcionario)throws BusinessExpection;
}
