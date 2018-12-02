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
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOGeral extends DAOGenerico<Geral> implements IDAOGeral {

    @Override
    public Geral buscarGeral() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {

            Geral geral = (Geral) em.createQuery(SQLUtil.Geral.SQL_BUSCAR_GERAL).getSingleResult();
            return geral;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");
        }

    }

}
