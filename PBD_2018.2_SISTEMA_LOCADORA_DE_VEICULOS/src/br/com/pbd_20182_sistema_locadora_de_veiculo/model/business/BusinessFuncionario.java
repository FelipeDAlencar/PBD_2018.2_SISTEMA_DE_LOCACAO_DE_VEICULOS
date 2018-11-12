/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOFuncionario;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessFuncionario implements IBusinessFuncionario{
    
    private DAOFuncionario daof;

    public BusinessFuncionario() {
        daof = new DAOFuncionario();
    
    }

    @Override
    public void salvar(Funcionario funcionario)throws BusinessExpection{

        try {
            if(validar()){
                daof.salvar(funcionario);
             }
        } catch (DAOException ex) {
            throw  new BusinessExpection("ERRO NO BUS");
        }
    }

    @Override
    public ArrayList<Funcionario> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Funcionario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean validar() {
        return true;
    }
    
    
   
    
    
    
    
    
    
}
