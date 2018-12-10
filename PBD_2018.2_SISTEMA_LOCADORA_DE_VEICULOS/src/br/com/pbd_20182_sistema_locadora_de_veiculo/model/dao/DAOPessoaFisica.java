/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOPessoaFisica extends DAOGenerico<PessoaFisica> implements IDAOPessoaFisica {

    @Override
    public ArrayList<PessoaFisica> findAll() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<PessoaFisica> pessoaFisicas = null;

        try {
            pessoaFisicas = (ArrayList) em.createQuery("from PessoaFisica p where ativo = true").getResultList();
            return pessoaFisicas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }

    }

    @Override
    public ArrayList<PessoaFisica> buscarPorNomeLike(String busca) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<PessoaFisica> pessoaFisicas = null;

        try {
            TypedQuery<PessoaFisica> query = em.createQuery(SQLUtil.PessoaFisica.SQL_BUSCAR_NOME_LIKE, PessoaFisica.class);
            query.setParameter("busca", "%" + busca.toLowerCase() + "%");

            pessoaFisicas = (ArrayList<PessoaFisica>) query.getResultList();
            return pessoaFisicas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
        
    }
    
    @Override
    public PessoaFisica buscarPorCPF(String cpf) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        PessoaFisica pessoaFisicas = null;

        try {
            TypedQuery<PessoaFisica> query = em.createQuery(SQLUtil.PessoaFisica.SQL_BUSCAR_POR_CPF, PessoaFisica.class);
            query.setParameter("cpf", cpf.toLowerCase());

            pessoaFisicas =  query.getSingleResult();
            return pessoaFisicas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
        
    }
}
