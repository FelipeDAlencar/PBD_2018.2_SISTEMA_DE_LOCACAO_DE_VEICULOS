/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinessRerservaPessoaCategoria {
    
    public void salvar(ReservaPessoasCategorias reservaPessoasCategorias)throws DAOException, BusinessExpection;
    public ArrayList<ReservaPessoasCategorias> listarTodos()throws  DAOException;
    public ReservaPessoasCategorias buscarPorId(int id)throws DAOException;
    public void alterar(ReservaPessoasCategorias reservaPessoasCategorias)throws DAOException;
    
}
