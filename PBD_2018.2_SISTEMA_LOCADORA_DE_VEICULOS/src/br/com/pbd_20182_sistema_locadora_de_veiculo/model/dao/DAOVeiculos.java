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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Felipe
 */
public class DAOVeiculos extends DAOGenerico<Veiculo> implements IDAOVeiculos {

    @Override
    public ArrayList<Veiculo> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Veiculo> veiculos = null;

        try {
            veiculos = (ArrayList) em.createQuery("from Veiculo c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return veiculos;
    }

    @Override
    public ArrayList<Veiculo> buscarPorCategoria(Categoria categoria) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {
            TypedQuery<Veiculo> query = em.createQuery(SQLUtil.Veiculo.SQL_BUSCAR_PORCATEGORIA, Veiculo.class);
            query.setParameter("categoria", categoria);

            ArrayList<Veiculo> veiculos = (ArrayList<Veiculo>) query.getResultList();
            
           
            return veiculos;
                    
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR VEICULO NO BANCO DE DADOS");
        }finally{
            em.close();
        }

    }
    
    
    @Override
    public ArrayList<Veiculo> buscarPorCategoriaVeiculosIndisponiveis(Categoria categoria)throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {
            TypedQuery<Veiculo> query = em.createQuery(SQLUtil.Veiculo.SQL_BUSCAR_PORCATEGORIA_VEICULOS_NÃODISPONIVEIS, Veiculo.class);
            query.setParameter("categoria", categoria);

            ArrayList<Veiculo> veiculos = (ArrayList<Veiculo>) query.getResultList();
            
           
            return veiculos;
                    
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR VEICULO NO BANCO DE DADOS");
        }finally{
            em.close();
        }
    }

}
