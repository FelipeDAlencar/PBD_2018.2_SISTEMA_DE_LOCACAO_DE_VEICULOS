/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Felipe
 */
public interface IBusinessLocacao {

    public void salvar(Locacao locacao) throws DAOException, BusinessExpection;

    public ArrayList<Locacao> listarTodos() throws DAOException;

    public Locacao buscarPorId(int id) throws DAOException;

    public void alterar(Locacao locacao) throws DAOException;

    public Time procedureCalcularIntervaloDeAtraso(Calendar dataAtual, Integer id) throws DAOException;

    public boolean verificarVencimentoCNH(Calendar dataIda, Calendar dataVolta, Integer id) throws DAOException;

    public int calcularIdade(Integer id) throws DAOException;

}
