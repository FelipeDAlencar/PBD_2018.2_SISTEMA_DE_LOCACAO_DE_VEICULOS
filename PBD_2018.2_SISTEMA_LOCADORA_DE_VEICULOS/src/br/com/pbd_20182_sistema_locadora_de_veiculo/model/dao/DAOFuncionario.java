/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOFuncionario extends DAOGenerico<Funcionario> implements IDAOFuncionario {

    @Override
    public ArrayList<Funcionario> findAll() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Funcionario> funcionarios = null;

        try {
            funcionarios = (ArrayList) em.createQuery("from Funcionario c where ativo = true").getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }

        return funcionarios;
    }

    @Override
    public ArrayList<Funcionario> buscarPorBuscaFuncionario(String busca) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Funcionario> funcionarios = null;

        try {

            TypedQuery<Funcionario> query = em.createQuery(SQLUtil.Funcionario.SQL_BUSCA_POR_BUSCA, Funcionario.class);
            query.setParameter("nome", "%" + busca + "%");
            query.setParameter("login", "%" + busca + "%");
            query.setParameter("matricula", "%" + busca + "%");

            funcionarios = (ArrayList<Funcionario>) query.getResultList();

            return funcionarios;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }

    }

}
