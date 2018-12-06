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
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;


/**
 *
 * @author Felipe
 */
public class DAOPessoa extends DAOGenerico<Pessoa> implements IDAOPessoa {

    private static DAOPessoa dAOPessoa;

//    public DAOPessoa(int a) {
//    }
    
    

    public static DAOPessoa getInstace() {
        if (dAOPessoa != null) {
            return dAOPessoa;
        }

        return dAOPessoa = new DAOPessoa();
    }

    @Override
    public Pessoa buscarLogin(Pessoa pessoa) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {

            TypedQuery<Pessoa> query = em.createQuery(SQLUtil.Pessoa.SQL_BUSCA_LOGIN, Pessoa.class);
            query.setParameter("login", pessoa.getLogin());
            query.setParameter("senha", pessoa.getSenha());
            Pessoa resuPessoa;
            return resuPessoa = query.getSingleResult();

        } catch (NoResultException e) {
            e.printStackTrace();
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.INFORMATION, "Erro.", "Problema ao logar.", "Login ou senha inválidos.");
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public ArrayList<Pessoa> listarTodos() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Pessoa> pessoas = null;
        try {
            pessoas = (ArrayList<Pessoa>) em.createQuery("from Pessoa p where ativo = true").getResultList();
            return pessoas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public String buscarUltimoCodigo() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        String codigo = null;
        try {
            codigo = em.createQuery(SQLUtil.Pessoa.SQL_BUSCA_ULTIMO_CODIGO_PESSOA, String.class).getSingleResult();
            return codigo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public ArrayList<Pessoa> buscarPorBusca(String texto) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Pessoa> pessoas = null;
        try {
            TypedQuery<Pessoa> query = em.createQuery(SQLUtil.Pessoa.SQL_BUSCA_POR_BUSCA, Pessoa.class);
            query.setParameter("nome", "%" + texto.toLowerCase() + "%");
            query.setParameter("login", "%" + texto.toLowerCase() + "%");
            query.setParameter("codigo", "%" + texto.toLowerCase() + "%");

            return pessoas = (ArrayList<Pessoa>) query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public String criptografarSenha(String senha) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery(SQLUtil.Pessoa.SQL_PROCEDURE_CRIPTOGRAFAR_SENHA)
                .registerStoredProcedureParameter("senha", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("cript", String.class, ParameterMode.OUT)
                .setParameter("senha", senha);

        query.execute();

        String cripttografia = (String) query
                .getOutputParameterValue("cript");
        
        
        em.close();
        return cripttografia;

    }

    @Override
    public int buscarUltimoID() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        int id = 0;
        try {
            id = em.createQuery(SQLUtil.Pessoa.SQL_BUSCA_ULTIMO_ID_PESSOA, Integer.class).getSingleResult();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return 0;
    }

}
