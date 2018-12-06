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
        return dAOPessoa.findById(Pessoa.class, id);
    }

    @Override
    public Pessoa buscarLogin(Pessoa pessoa) throws DAOException {
        return dAOPessoa.buscarLogin(pessoa);
    }

    @Override
    public String buscarUltimoCodigo() throws DAOException {
        return dAOPessoa.buscarUltimoCodigo();
    }

    @Override
    public ArrayList<Pessoa> buscarPorBusca(String texto) throws DAOException {
        return dAOPessoa.buscarPorBusca(texto);
    }

    @Override
    public String criptografarSenha(String senha) throws DAOException {
        return dAOPessoa.criptografarSenha(senha);
    }

    @Override
    public int buscarUltimoID() throws DAOException {
        return dAOPessoa.buscarUltimoID();
    }
    
}
