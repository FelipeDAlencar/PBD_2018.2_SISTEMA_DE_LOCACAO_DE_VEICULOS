/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOContaAReceber extends DAOGenerico<ContaAReceber> implements IDAOContaAReceber {

    @Override
    public ArrayList<ContaAReceber> findAll() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAReceber> contaAReceber = null;

        try {
            contaAReceber = (ArrayList) em.createQuery("from ContaAReceber c ").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return contaAReceber;

    }

    public ArrayList<ContaAReceber> buscarContaAReceberPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAReceber> contaAReceber = null;

        try {
            TypedQuery<ContaAReceber> query = em.createQuery(SQLUtil.ContaAReceber.SQL_BUSCAR_CONTA_A_RECEBER_POR_PERIODO, ContaAReceber.class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);

            contaAReceber = (ArrayList<ContaAReceber>) query.getResultList();

            return contaAReceber;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO BUSCAR CONTAS A PAGAR NO BANCO DE DADOS.");
        } finally {
            em.close();
        }

    }
}
