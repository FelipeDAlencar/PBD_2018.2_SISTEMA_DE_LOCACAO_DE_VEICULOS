/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOLocacao;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public class BusinessLocacao implements IBusinessLocacao {

    private DAOLocacao dAOLocacao;

    public BusinessLocacao() {
        dAOLocacao = new DAOLocacao();
    }

    @Override
    public void salvar(Locacao locacao) throws DAOException, BusinessExpection {
        
        validar(locacao);
        dAOLocacao.salvar(locacao);
    }

    @Override
    public ArrayList<Locacao> listarTodos() throws DAOException {
        return dAOLocacao.findAll();
    }

    @Override
    public Locacao buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Locacao locacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validar(Locacao locacao) throws BusinessExpection {
        String errorMessage = "";

        if (!(locacao.getCliente() != null)) {
            errorMessage += "Por favor, selecione o cliente.";
        }

        if (!(locacao.getVeiculo() != null)) {
            errorMessage += "Por favor, selecione o veículo.";

        }
        if (!(locacao.getMotorista() != null)) {
            errorMessage += "Por favor, selecione o Motorista.";
        }

        if (!(locacao.getDataIda() != null)) {
            errorMessage += "Por favor, selecione a uma data de ida válida.";
        }

        if (locacao.getDataIda().before(new Date())) {
            errorMessage += "Data de ida não pode ser anterior a de hoje.";
        }
        if (!(locacao.getDataVolta() != null)) {
            errorMessage += "Por favor, selecione a uma data de volta válida.";
        }

        if (locacao.getDataVolta().before(new Date())) {
            errorMessage += "Data de volta não pode ser anterior a de hoje.";
        }

        if (locacao.getKmInicialDoVeiculo() <= 0) {
            errorMessage = "Informe o km inicial do veículo.";
        }
        if (locacao.getMetadeDaPrimeiraDiaria() <= 0) {
            errorMessage = "Informe o valor da primeira diária.";
        }

        if (errorMessage.length() != 0) {
            throw new BusinessExpection(errorMessage);
        }

    }

}
