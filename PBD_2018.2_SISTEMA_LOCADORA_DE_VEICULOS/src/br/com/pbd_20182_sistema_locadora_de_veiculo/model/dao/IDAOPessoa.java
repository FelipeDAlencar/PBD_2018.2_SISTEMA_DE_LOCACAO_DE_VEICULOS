/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public interface IDAOPessoa {

    public Pessoa buscarLogin(Pessoa pessoa) throws DAOException;

    public ArrayList<Pessoa> buscarTodos() throws DAOException;

    public String buscarUltimoCodigo() throws DAOException;

    public ArrayList<Pessoa> buscarPorBusca(String texto) throws DAOException;
    
    
    public String criptografarSenha(String senha)throws DAOException; 
    public int buscarUltimoID()throws DAOException;
}
