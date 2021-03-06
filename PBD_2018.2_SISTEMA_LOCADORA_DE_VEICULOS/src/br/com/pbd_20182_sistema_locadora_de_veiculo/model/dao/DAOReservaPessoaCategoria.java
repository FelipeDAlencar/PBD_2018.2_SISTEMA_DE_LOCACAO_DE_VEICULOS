/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewLocacaoPorPeriodo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewReservasPorPeriodo;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOReservaPessoaCategoria extends DAOGenerico<ReservaPessoasCategorias> implements IDAOReservaPessoasCategorias {

    @Override
    public ArrayList<ReservaPessoasCategorias> findAll() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ReservaPessoasCategorias> reservaPessoasCategoriases = null;

        try {
            reservaPessoasCategoriases = (ArrayList) em.createQuery("from ReservaPessoasCategorias c where ativo = true").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return reservaPessoasCategoriases;
    }

    @Override
    public ArrayList<ReservaPessoasCategorias> buscarPorBuscaReserva(String busca) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ReservaPessoasCategorias> reservas = null;

        try {
            TypedQuery<ReservaPessoasCategorias> query = em.createQuery(SQLUtil.Reservas.SQL_BUSCAR_POR_BUSCA, ReservaPessoasCategorias.class);
            query.setParameter("NomeCliente", "%" + busca.toLowerCase() + "%");
            query.setParameter("nomeCategoria", "%" + busca.toLowerCase() + "%");
            query.setParameter("dataHora", "%" + busca.toLowerCase() + "%");

            reservas = (ArrayList<ReservaPessoasCategorias>) query.getResultList();

            return reservas;

        } catch (Exception e) {
            throw new DAOException("ERRO AO BUSCAR NO BANCO DE DADOS");
        }

    }

    @Override
    public ArrayList<ViewReservasPorPeriodo> buscarReservaPorPeriodo(Date dataIncial, Date dataFinal) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ViewReservasPorPeriodo> filial = null;

        try {

            TypedQuery<ViewReservasPorPeriodo> query = em.createQuery(SQLUtil.Reservas.SQL_BUSCAR_RESERVA_POR_PERIODO, ViewReservasPorPeriodo.class);
            query.setParameter("dataInicial", dataIncial);
            query.setParameter("dataFinal", dataFinal);
            filial = (ArrayList<ViewReservasPorPeriodo>) query.getResultList();

            return filial;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
    }

}
