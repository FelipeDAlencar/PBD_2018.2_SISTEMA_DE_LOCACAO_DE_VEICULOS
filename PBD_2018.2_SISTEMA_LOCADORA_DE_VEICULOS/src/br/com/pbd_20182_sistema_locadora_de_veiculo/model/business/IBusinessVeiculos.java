/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessVeiculos {
    
    public void salvar(Veiculo veiculo)throws DAOException,BusinessExpection;
    public ArrayList<Veiculo> listarTodos()throws DAOException;
    public Veiculo buscarPorId(int id)throws DAOException;
    public void alterar(Veiculo veiculo)throws DAOException;
    
}
