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
    public ArrayList<Veiculo> listarTodos() {
        return dAOVeiculos.findAll();
    }

    @Override
    public Veiculo buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Veiculo veiculo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validar(Veiculo veiculo) throws BusinessExpection {

        if (!(veiculo != null)) {
             throw new BusinessExpection("ERRO AO TENTAR INSERIR");
        } 
    }

    @Override
    public ArrayList<Veiculo> buscarPorCategoria(Categoria categoria) throws DAOException {
        return dAOVeiculos.buscarPorCategoria(categoria);
    }

}
