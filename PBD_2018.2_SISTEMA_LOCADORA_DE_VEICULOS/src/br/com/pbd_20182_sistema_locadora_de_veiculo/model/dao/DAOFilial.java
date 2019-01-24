/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewFilialPorEndereco;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOFilial extends DAOGenerico<Filial> implements IDAOFilial{

    @Override
    public ArrayList<Filial> findAll() throws DAOException{
        
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Filial> filiais = null;

        try {
            filiais = (ArrayList) em.createQuery("from Filial c where ativo = true").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return filiais;
       
    }

    @Override
    public ArrayList<Filial> buscarPorBuscaFilial(String busca) throws DAOException {
        
       
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Filial> filial = null;

        try {

            TypedQuery<Filial> query = em.createQuery(SQLUtil.Filial.SQL_BUSCA_POR_BUSCA, Filial.class);
            query.setParameter("nome", "%" + busca.toLowerCase() + "%");
            query.setParameter("rua", "%" + busca.toLowerCase() + "%");

            filial = (ArrayList<Filial>) query.getResultList();

            return filial;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
    }
    
    
    
    public ArrayList<ViewFilialPorEndereco> bsucarFilialPorEndereco() throws DAOException {
        
       
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ViewFilialPorEndereco> filial = null;

        try {

            TypedQuery<ViewFilialPorEndereco> query = em.createQuery("select v  from ViewFilialPorEndereco v", ViewFilialPorEndereco.class);
          
            filial = (ArrayList<ViewFilialPorEndereco>) query.getResultList();

            return filial;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
    }
    
}
