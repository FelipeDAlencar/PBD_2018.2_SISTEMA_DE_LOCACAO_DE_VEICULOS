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
public class BusinessFuncionario implements IBusinessFuncionario {

    private DAOFuncionario dAOFuncionario;

    public BusinessFuncionario() {
        dAOFuncionario = new DAOFuncionario();

    }

    @Override
    public void salvar(Funcionario funcionario) throws DAOException, BusinessExpection {

        validar(funcionario);
        dAOFuncionario.salvar(funcionario);

    }

    @Override
    public ArrayList<Funcionario> listarTodos() throws DAOException {
        return dAOFuncionario.findAll();
    }

    @Override
    public Funcionario buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public ArrayList<Funcionario> buscarPorBusca(String busca) throws DAOException {
        return dAOFuncionario.buscarPorBuscaFuncionario(busca);
    }
    
    private void validar(Funcionario funcionario)throws BusinessExpection{
        
        String erroMessage = "";
        
        
        System.out.println("Aqui " + funcionario.getCargo());
        
        if(funcionario.getCargo().length() == 0){
            erroMessage = "Por favor, informe o cargo.";
        }
        
        if(funcionario.getLogin().length() == 0){
            erroMessage = "Por favor, informe o seu login.";
        }
        if(funcionario.getMatricula().length() == 0){
            erroMessage = "Por favor, informe sua matricula.";
            
        }
        if(funcionario.getNome().length() <= 0){
            erroMessage = "Por favor, informe um nome valido nome";
        }
        if(funcionario.getEndereco().getBairro().length() == 0){
            erroMessage = "Por favor, informe o bairro.";
        }
        if(funcionario.getEndereco().getCidade().length() == 0){
            erroMessage = "Por favor, informe a cidade.";
           
        }
        if(funcionario.getEndereco().getRua().length() == 0){
            erroMessage = "Por favor, informe a Rua.";
           
        }
        
        if(funcionario.getEndereco().getNumero() <= 0){
            erroMessage = "Por favor, informe um número válido.";
           
        }
        
        if(erroMessage.length() != 0){
            throw new BusinessExpection(erroMessage);
        }
        
        
        
    }

        
        
    

}
