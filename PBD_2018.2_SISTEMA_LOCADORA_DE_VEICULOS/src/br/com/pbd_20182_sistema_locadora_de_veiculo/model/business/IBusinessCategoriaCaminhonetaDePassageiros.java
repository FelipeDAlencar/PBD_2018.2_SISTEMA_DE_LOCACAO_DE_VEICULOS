/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessCategoriaCaminhonetaDePassageiros {

    public void salvar(CaminhonetaDePassageiros caminhonetaDePassageiros)throws DAOException,BusinessExpection;

    public ArrayList<CaminhonetaDePassageiros> listarTodos()throws DAOException;

    public CaminhonetaDePassageiros buscarPorId(int id)throws DAOException;

    public void alterar(CaminhonetaDePassageiros caminhonetaDePassageiros)throws DAOException;

    public String buscarUltimoNomeCaminhonetaDePassageiros()throws DAOException;

}
