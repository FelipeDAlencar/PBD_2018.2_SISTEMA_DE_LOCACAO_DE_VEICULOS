/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class BusinessPessoa implements IBusinessPessoa{
    
    private DAOPessoa dAOPessoa;

    public BusinessPessoa() {
        dAOPessoa = new DAOPessoa();
    }
    
    
    
    @Override
    public void salvar(Pessoa pessoa) throws DAOException, BusinessExpection {
        dAOPessoa.salvar(pessoa);
    }

    @Override
    public ArrayList<Pessoa> listarTodos() throws DAOException {
        return dAOPessoa.listarTodos();
    }

    @Override
    public Pessoa buscarPorId(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
