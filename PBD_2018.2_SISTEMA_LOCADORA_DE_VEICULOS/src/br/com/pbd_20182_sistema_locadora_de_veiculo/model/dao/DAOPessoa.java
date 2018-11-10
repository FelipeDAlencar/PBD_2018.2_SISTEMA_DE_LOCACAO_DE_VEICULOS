/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOPessoa implements IDAOPessoa {

    @Override
    public Pessoa buscarLogin(Pessoa pessoa) {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        
        TypedQuery<Pessoa> query = em.createQuery(SQLUtil.SQL_BUSCA_LOGIN, Pessoa.class);
        query.setParameter("login", pessoa.getLogin());
        query.setParameter("senha", pessoa.getSenha());
        
        Pessoa resuPessoa;
        return resuPessoa = query.getSingleResult();
    }

}
