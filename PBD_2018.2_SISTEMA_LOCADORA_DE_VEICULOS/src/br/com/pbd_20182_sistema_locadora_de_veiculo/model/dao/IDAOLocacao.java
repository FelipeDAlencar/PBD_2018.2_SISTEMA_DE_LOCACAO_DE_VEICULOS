/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
public interface IDAOLocacao {

    public ArrayList<Locacao> findAll();

    public Time procedureCalcularIntervaloDeAtraso(Calendar dataFinal, Integer id) throws DAOException;

    public boolean verificarVencimentoCNH(Calendar dataIda, Calendar dataVolta, Integer id) throws DAOException;

    public int calcularIdade(Integer id) throws DAOException;

}
