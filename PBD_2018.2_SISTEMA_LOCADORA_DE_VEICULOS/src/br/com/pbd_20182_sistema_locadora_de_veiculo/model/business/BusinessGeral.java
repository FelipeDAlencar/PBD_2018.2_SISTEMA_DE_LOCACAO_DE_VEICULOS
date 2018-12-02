/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOGeral;

/**
 *
 * @author Felipe
 */
public class BusinessGeral implements IBusinessGeral{
   
    private DAOGeral dAOGeral;

    public BusinessGeral() {
        dAOGeral = new DAOGeral();
    }
            
    
    @Override
    public void salvar(Geral geral) throws DAOException {
        dAOGeral.salvar(geral);
    }

    @Override
    public Geral buscarGeral() throws DAOException {
        
        
        return dAOGeral.buscarGeral();
    }

    
}
