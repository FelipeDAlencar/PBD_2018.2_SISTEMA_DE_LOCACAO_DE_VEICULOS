/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IDAOFilial {
    
     public ArrayList<Filial> findAll()throws DAOException;
     public ArrayList<Filial> buscarPorBusca(String busca)throws DAOException;
    
}
