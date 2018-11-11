/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOPessoa implements IDAOPessoa {

    private static DAOPessoa dAOPessoa;

    public static DAOPessoa getInstace() {
        if (dAOPessoa != null) {
            return dAOPessoa;
        }

        return dAOPessoa = new DAOPessoa();
    }

    @Override
    public Pessoa buscarLogin(Pessoa pessoa)throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {

            TypedQuery<Pessoa> query = em.createQuery(SQLUtil.SQL_BUSCA_LOGIN, Pessoa.class);
            query.setParameter("login", pessoa.getLogin());
            query.setParameter("senha", pessoa.getSenha());
            Pessoa resuPessoa;
            return resuPessoa = query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public ArrayList<Pessoa> buscarTodos()throws DAOException{

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Pessoa> pessoas = null;
        try{
            pessoas = (ArrayList<Pessoa>) em.createQuery("from Pessoa p").getResultList();
            return pessoas;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
        
        return null;
    }

}
