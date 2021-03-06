/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IDAOVeiculos {

    public ArrayList<Veiculo> findAll();

    public ArrayList<Veiculo> buscarPorCategoria(Categoria categoria) throws DAOException;

    public ArrayList<Veiculo> buscarPorCategoriaVeiculosIndisponiveis(Categoria categoria) throws DAOException;

    public ArrayList<Veiculo> buscarPorBuscaVeiculos(String busca)throws DAOException;
        
    
}
