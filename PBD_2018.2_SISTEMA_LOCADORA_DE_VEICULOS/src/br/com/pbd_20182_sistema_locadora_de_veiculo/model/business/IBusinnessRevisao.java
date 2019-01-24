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
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public interface IBusinnessRevisao {
    public ArrayList<ViewHistoricoPorVeiculo> revisoesPorVeiculo(Veiculo veiculo) throws DAOException;
    public void salvar(Revisao revisao)throws DAOException;
    public ArrayList<ViewHistoricoPorVeiculo> findAllRevisao()throws DAOException;
    
}
