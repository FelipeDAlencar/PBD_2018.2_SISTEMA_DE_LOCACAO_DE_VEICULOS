/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewReservasPorPeriodo;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public interface IDAOReservaPessoasCategorias {

    public ArrayList<ReservaPessoasCategorias> findAll() throws DAOException;

    public ArrayList<ReservaPessoasCategorias> buscarPorBuscaReserva(String busca) throws DAOException;

    public ArrayList<ViewReservasPorPeriodo> buscarReservaPorPeriodo(Date dataIncial, Date dataFinal) 
            throws DAOException;

    }
