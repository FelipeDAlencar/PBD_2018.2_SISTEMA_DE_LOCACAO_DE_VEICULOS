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
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOCategoria extends DAOGenerico<Categoria> implements IDAOCategoria {

    @Override
    public ArrayList<Categoria> findAll()throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Categoria> categorias = null;

        try {
            categorias = (ArrayList) em.createQuery("from Categoria c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }

        return categorias;
    }

    public String buscarUltimoNome() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        String nome = null;
        try {
            nome = em.createQuery(SQLUtil.Categoria.SQL_BUSCA_ULTIMONOME, String.class).getSingleResult();
            return nome;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");
        } finally {
            em.close();
        }

        
    }
    
    @Override
    public Categoria buscarPorNome(String nome)throws DAOException{
        
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        try{
            TypedQuery<Categoria> query =  em.createQuery(SQLUtil.Categoria.SQL_BUSCAR_POR_NOME, Categoria.class);
            query.setParameter("nome", nome);
            Categoria categoria = query.getSingleResult();
            
            
            return categoria;
        
        }catch(Exception e){
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS.\nNÃO HÁ CATEGORIA.");
        }
       
    }
    
    

}
