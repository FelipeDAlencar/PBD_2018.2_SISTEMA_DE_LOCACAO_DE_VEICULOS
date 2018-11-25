/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.EntidadeBase;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import javax.persistence.EntityManager;


public class DAOGenerico <T extends EntidadeBase>{
    
    private static DAOGenerico dAOGenerico;

    public static DAOGenerico getInstace() {

        if (dAOGenerico != null) {
            return dAOGenerico;
        }
        return dAOGenerico = new DAOGenerico();

    }

    public void salvar(T t) throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        try {
            em.getTransaction().begin();
            if (t.getId() != null) {
                em.merge(t);
            } else {
                em.persist(t);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new DAOException("ERRO AO TENTAR INSERIR NO BANCO DE DADOS");
        } finally {
            em.close();
        }

    }

    public T findById(Class<T> classe, int id)throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        T t = null;

        try {
            t = em.find(classe, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");
        } finally {
            em.close();
        }
        return t;
    }
    
    
//    public ArrayList<T> findAll(){
//        EntityManager em = ConnectionFactory.getInstance().getConnection();
//        ArrayList<T> entidades = null;
//        
//        try{
//            entidades =(ArrayList) em.createQuery("from Gerente s").getResultList();
//        }catch(Exception e){
//            e.printStackTrace();
//        
//        }finally{
//            em.close();
//        }
//        
//        return entidades;
//    }
    
    public void remove(Class<T> classe, int id)throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        T entidade = null;
        
        
        try{
            em.getTransaction().begin();
            entidade = em.find(classe, id);
            em.remove(entidade);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new DAOException("ERRO AO TENTAR REMOVER DO BANCO DE DADOS");
        }finally{
            em.close();
        }
    }
    
    
    
    
    
}
