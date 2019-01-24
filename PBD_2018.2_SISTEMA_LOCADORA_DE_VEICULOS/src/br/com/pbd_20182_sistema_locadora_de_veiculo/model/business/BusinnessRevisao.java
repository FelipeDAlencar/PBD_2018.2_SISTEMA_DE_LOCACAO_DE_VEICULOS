/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Revisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewHistoricoPorVeiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAORevisao;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class BusinnessRevisao implements IBusinnessRevisao{
    private DAORevisao daor;

    public BusinnessRevisao() {
        daor = new DAORevisao();
    }
    
    
    
    @Override
    public ArrayList<ViewHistoricoPorVeiculo> revisoesPorVeiculo(Veiculo veiculo) throws DAOException {
        return daor.revisoesPorVeiculo(veiculo);
        
    }

    @Override
    public void salvar(Revisao revisao) throws DAOException{
        daor.salvar(revisao);
    }
    
    public ArrayList<ViewHistoricoPorVeiculo> findAllRevisao()throws DAOException{
        return daor.findAll();
    }
    
}
