/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOVeiculos;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class BusinessVeiculo implements IBusinessVeiculos {

    private DAOVeiculos dAOVeiculos;

    public BusinessVeiculo() {
        dAOVeiculos = new DAOVeiculos();

    }

    @Override
    public void salvar(Veiculo veiculo) throws DAOException, BusinessExpection {

        validar(veiculo);
        dAOVeiculos.salvar(veiculo);
    }

    @Override
    public ArrayList<Veiculo> listarTodos() throws DAOException {
        return dAOVeiculos.findAll();
    }

    @Override
    public Veiculo buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Veiculo> buscarPorBusca(String busca) throws DAOException {
        return dAOVeiculos.buscarPorBusca(busca);
    }

    private void validar(Veiculo veiculo) throws BusinessExpection {
        String errorMessage = "";
        if (String.valueOf(veiculo.getAnoDeFabricacao()).length() < 4) {
            errorMessage += "Por favor, informe um número de chassi válido.";
        }

        if (String.valueOf(veiculo.getAnoDoModelo()).length() < 4) {
            errorMessage += "Por favor, informe um ano válido.";
        }

        if (!(veiculo.getCategoria() != null)) {
            errorMessage += "Por favor, selecione a categoria.";
        }
        if (veiculo.getCor().length() == 0) {
            errorMessage += "Por favor, informe a cor do carro.";
        }
        if (veiculo.getModelo().length() == 0) {
            errorMessage += "Por favor, informe o modelo";
        }
        if (veiculo.getFabricante().length() == 0) {
            errorMessage += "Por favor, informe o fabricante.";
        }

        if (String.valueOf(veiculo.getNumeroChassi()).length() != 17) {
            errorMessage += "Por favor, informe o número de chassi válido";
        }
        if (veiculo.getPlaca().length() != 8) {
            errorMessage += "Por favor, informe uma placa válida.";
        }
        if (veiculo.getQuilometragemAtual() < 0) {
            errorMessage += "Por favor, informe uma quilometragem válida.";
        }
        if (veiculo.getTipoDeCombustivel().length() == 0) {
            errorMessage += "Por favor, informe o tipo de combustível.";
        }
        if (veiculo.getTorqueDoMotor() <= 0) {
            errorMessage += "Por favor, informe um torque válidor.";
        }

        if (errorMessage.length() != 0) {
            throw new BusinessExpection(errorMessage);
        }
    }

    @Override
    public ArrayList<Veiculo> buscarPorCategoria(Categoria categoria) throws DAOException {
        return dAOVeiculos.buscarPorCategoria(categoria);
    }

    @Override
    public ArrayList<Veiculo> buscarPorCategoriaVeiculosIndisponiveis(Categoria categoria) throws DAOException {
        return dAOVeiculos.buscarPorCategoriaVeiculosIndisponiveis(categoria);
    }

}
