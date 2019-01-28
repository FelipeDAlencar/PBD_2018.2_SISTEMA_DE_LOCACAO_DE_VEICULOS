/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOContasAPagar;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            validar(contaAReceber);
        } catch (BusinessExpection ex) {
            Logger.getLogger(BusinnessContaAReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public ArrayList<ContaAReceber> buscarContaAReceberPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {
        return daocap.buscarContaAReceberPorPeriodo(dataInicial, dataFinal);
    }

    @Override
    public ArrayList<ContaAReceber> buscarPorBuscaContaAReceber(String busca) throws DAOException {
        return daocap.buscarPorBuscaContaAReceber(busca);
    }

    private void validar(ContaAReceber contaAReceber) throws BusinessExpection {
        String errorMessage = "";

        if (!(contaAReceber.getDataRecebimento() != null)) {
            errorMessage = "Por favor, informe uma data válida.";
        }

        if (contaAReceber.getDescricao().length() == 0) {
            errorMessage = "Por favor, informa a descrição da conta.";
        }
        if (contaAReceber.getValor() < 0) {
            errorMessage = "Por favor, informe o valor da conta.";
        }

        if (errorMessage.length() != 0) {
            throw new BusinessExpection(errorMessage);
        }
    }

}
