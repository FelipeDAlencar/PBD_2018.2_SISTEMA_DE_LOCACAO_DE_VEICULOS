/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOEndereco;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessEndereco implements IBusinessEndereco {

    private DAOEndereco dAOEndereco;

    public BusinessEndereco() {

        dAOEndereco = new DAOEndereco();
    }

    @Override
    public void salvar(Endereco endereco) throws DAOException, BusinessExpection {

        validacao(endereco);

        dAOEndereco.salvar(endereco);

    }

    @Override
    public ArrayList<Endereco> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Endereco endereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean validacao(Endereco endereco) throws BusinessExpection {
        if (endereco != null) {

        } else {
            throw new BusinessExpection("ERRO DE VALIDAÇÃO");
        }

        return false;
    }

}
