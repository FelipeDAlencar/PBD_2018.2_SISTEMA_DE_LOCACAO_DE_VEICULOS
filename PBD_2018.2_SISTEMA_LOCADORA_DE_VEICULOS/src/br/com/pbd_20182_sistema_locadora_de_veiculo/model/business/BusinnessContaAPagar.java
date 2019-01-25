/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContasAPagar;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public class BusinnessContaAPagar implements IBusinnessContasAPagar {

    private DAOContasAPagar daocap;

    public BusinnessContaAPagar() {
        daocap = new DAOContasAPagar();
    }

    @Override
    public void salvarContaAPagar(ContaAPagar contaAPagar) throws DAOException {
        daocap.salvar(contaAPagar);

    }

    @Override
    public void excluirContaAPagar(ContaAPagar contaAPagar) throws DAOException {
        daocap.remove(ContaAPagar.class, contaAPagar.getId());
    }

    @Override
    public ArrayList<ContaAPagar> listarTodasContasAPagar() throws DAOException {

        return daocap.findAll();
    }

    public ArrayList<ContaAPagar> buscarContaAPagarPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {

        return daocap.buscarContaAPagarPorPeriodo(dataInicial, dataFinal);
    }

}
