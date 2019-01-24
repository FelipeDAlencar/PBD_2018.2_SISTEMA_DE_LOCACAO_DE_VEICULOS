/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Revisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewHistoricoPorVeiculo;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAORevisao extends DAOGenerico<Revisao> implements IDAORevisao {

    @Override
    public ArrayList<ViewHistoricoPorVeiculo> findAll() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {
            TypedQuery<ViewHistoricoPorVeiculo> query = em.createQuery(SQLUtil.Revisao.SQL_BUSCAR_REVISOES, ViewHistoricoPorVeiculo.class);
            ArrayList<ViewHistoricoPorVeiculo> vhpvs = (ArrayList<ViewHistoricoPorVeiculo>) query.getResultList();
            return vhpvs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");
        }
    }

    @Override
    public ArrayList<ViewHistoricoPorVeiculo> revisoesPorVeiculo(Veiculo veiculo) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {
            TypedQuery<ViewHistoricoPorVeiculo> query = em.createQuery(SQLUtil.Revisao.SQL_BUSCAR_REVISOES_POR_VEICULO, ViewHistoricoPorVeiculo.class);
            query.setParameter("veiculo_id", veiculo.getId());
            ArrayList<ViewHistoricoPorVeiculo> vhpvs = (ArrayList<ViewHistoricoPorVeiculo>) query.getResultList();

            return vhpvs;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");
        }

    }

}
