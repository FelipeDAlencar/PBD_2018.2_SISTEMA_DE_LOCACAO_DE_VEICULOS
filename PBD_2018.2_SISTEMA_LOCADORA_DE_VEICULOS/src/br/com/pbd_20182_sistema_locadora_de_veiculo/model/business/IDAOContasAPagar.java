/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public interface IDAOContasAPagar {

    public ArrayList<ContaAPagar> findAll() throws DAOException;

    public ArrayList<ContaAPagar> buscarContaAPagarPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException;

    public ArrayList<ContaAPagar> buscarPorBuscaContaAPagar(String busca)throws DAOException;

}
