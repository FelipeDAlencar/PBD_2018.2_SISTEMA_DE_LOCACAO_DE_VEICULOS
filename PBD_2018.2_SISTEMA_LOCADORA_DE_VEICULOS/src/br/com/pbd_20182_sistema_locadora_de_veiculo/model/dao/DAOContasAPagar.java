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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.business.IDAOContasAPagar;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOContasAPagar extends DAOGenerico<ContaAPagar> implements IDAOContasAPagar {

    @Override
    public ArrayList<ContaAPagar> findAll() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAPagar> contasAPagar = null;

        try {
            contasAPagar = (ArrayList) em.createQuery("from ContaAPagar c ").getResultList();

            return contasAPagar;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return contasAPagar;

    }

    @Override
    public ArrayList<ContaAPagar> buscarContaAPagarPorPeriodo(Date dataInicial, Date dataFinal)
            throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAPagar> contasAPagar = null;

        try {
            TypedQuery<ContaAPagar> query = em.createQuery(SQLUtil.ContaAPagar.SQL_BUSCAR_CONTA_A_PAGAR_POR_PERIODO, ContaAPagar.class);
            query.setParameter("dataInicial", dataInicial);
            query.setParameter("dataFinal", dataFinal);

            contasAPagar = (ArrayList<ContaAPagar>) query.getResultList();

            return contasAPagar;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO BUSCAR CONTAS A PAGAR NO BANCO DE DADOS.");
        } finally {
            em.close();
        }

    }

    @Override
    public ArrayList<ContaAPagar> buscarPorBuscaContaAPagar(String busca) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAPagar> contasAPagar = null;
        busca = "%" + busca.toLowerCase() + "%";
        try {
            TypedQuery<ContaAPagar> query = em.createQuery(SQLUtil.ContaAPagar.SQL_BUSCAR_POR_BUSCA, ContaAPagar.class);
            query.setParameter("descricao", busca);
            query.setParameter("valor", busca);
            query.setParameter("dataVencimento", busca);

            contasAPagar = (ArrayList<ContaAPagar>) query.getResultList();

            return contasAPagar;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO BUSCAR CONTAS A PAGAR NO BANCO DE DADOS.");
        } finally {
            em.close();
        }

    }

}
