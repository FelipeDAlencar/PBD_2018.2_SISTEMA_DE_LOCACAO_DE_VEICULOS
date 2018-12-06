/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessPessoa {

    public void salvar(Pessoa pessoa) throws DAOException, BusinessExpection;

    public ArrayList<Pessoa> listarTodos() throws DAOException;

    public Pessoa buscarPorId(int id) throws DAOException;

    public Pessoa buscarLogin(Pessoa pessoa) throws DAOException;

    public String buscarUltimoCodigo() throws DAOException;

    public ArrayList<Pessoa> buscarPorBusca(String texto) throws DAOException;

    public String criptografarSenha(String senha) throws DAOException;

    public int buscarUltimoID() throws DAOException;

}
