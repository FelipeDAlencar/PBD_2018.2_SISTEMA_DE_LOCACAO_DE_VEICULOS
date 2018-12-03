/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOFilial;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class BusinessFilial implements IBusinessFilial {

    private DAOFilial dAOFilial;

    public BusinessFilial() {
        dAOFilial = new DAOFilial();
    }

    @Override
    public void salvar(Filial filial) throws DAOException, BusinessExpection{

        validar(filial);
        dAOFilial.salvar(filial);
    }

    @Override
    public ArrayList<Filial> listarTodos()throws DAOException{
        return dAOFilial.findAll();
    }

    @Override
    public Filial buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public ArrayList<Filial> buscarPorBusca(String busca) throws DAOException {
        return dAOFilial.buscarPorBusca(busca);
    }

   
    private void validar(Filial filial)throws BusinessExpection{
        String erroMessage = "";
        
        if(filial.getNome().length() == 0){
            erroMessage += "Por favor, informe o nome da filial.\n";
        }
        if(filial.getEndereco().getBairro().length() == 0){
            erroMessage += "Por favor, informe o bairro.\n";
        }
        if(filial.getEndereco().getCidade().length() == 0){
            erroMessage += "Por favor, informe a cidade.\n";
        }
        if(filial.getEndereco().getNumero() <= 0){
            erroMessage += "Por favor, informe um número válido.\n";
        }
        if(filial.getEndereco().getRua().length() == 0){
            erroMessage += "Por favor, informe o nome da rua.\n";
        }
       
        
        if(erroMessage.length() > 0){
            throw new BusinessExpection("Atenção" + erroMessage );
        }
    }

   

}
