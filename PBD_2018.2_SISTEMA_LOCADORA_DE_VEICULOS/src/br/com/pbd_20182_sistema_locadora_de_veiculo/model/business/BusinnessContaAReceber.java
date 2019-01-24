/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContasAPagar;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class BusinnessContaAReceber implements IBusinnessContaAReceber {

    private DAOContaAReceber daocap;

    public BusinnessContaAReceber() {
        daocap = new DAOContaAReceber();
    }

    @Override
    public void salvarContaAReceber(ContaAReceber contaAReceber) throws DAOException {
        daocap.salvar(contaAReceber);

    }

    @Override
    public void excluirContaAReceber(ContaAReceber contaAReceber) throws DAOException {
        daocap.remove(ContaAReceber.class, contaAReceber.getId());
    }

    @Override
    public ArrayList<ContaAReceber> listarTodasContasAReceber() throws DAOException {

        return daocap.findAll();
    }

}
