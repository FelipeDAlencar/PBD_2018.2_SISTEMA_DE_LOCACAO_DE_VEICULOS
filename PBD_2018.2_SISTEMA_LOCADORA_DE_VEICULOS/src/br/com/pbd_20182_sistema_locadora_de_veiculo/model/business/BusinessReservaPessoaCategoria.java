/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class BusinessReservaPessoaCategoria implements IBusinessRerservaPessoaCategoria {

    private DAOReservaPessoaCategoria dAOReservaPessoaCategoria;

    public BusinessReservaPessoaCategoria() {
        dAOReservaPessoaCategoria = new DAOReservaPessoaCategoria();
    }

    @Override
    public void salvar(ReservaPessoasCategorias reservaPessoasCategorias)throws BusinessExpection{

        try {
            dAOReservaPessoaCategoria.salvar(reservaPessoasCategorias);
        } catch (DAOException ex) {
            throw  new BusinessExpection("Erro ao persistir reserva!");
        }

    }

    @Override
    public ArrayList<ReservaPessoasCategorias> listarTodos() {
        return dAOReservaPessoaCategoria.findAll();
    }

    @Override
    public ReservaPessoasCategorias buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(ReservaPessoasCategorias reservaPessoasCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
