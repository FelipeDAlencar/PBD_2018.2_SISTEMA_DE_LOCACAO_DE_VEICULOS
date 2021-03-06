/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public interface IDAOContaAReceber {

    public ArrayList<ContaAReceber> findAll() throws DAOException;

    public ArrayList<ContaAReceber> buscarContaAReceberPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException;

    public ArrayList<ContaAReceber> buscarPorBuscaContaAReceber(String busca) throws DAOException;
}
