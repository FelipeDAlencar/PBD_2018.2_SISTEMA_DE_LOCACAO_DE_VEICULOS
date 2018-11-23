/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOCategoriaCaminhonetaDePassageiros extends DAOGenerico<CaminhonetaDePassageiros> implements IDAOCategoriaCaminhonetaDePassageiros{

    @Override
    public ArrayList<CaminhonetaDePassageiros> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<CaminhonetaDePassageiros> caminhonetasDePassageiros = null;

        try {
            caminhonetasDePassageiros = (ArrayList) em.createQuery("from CaminhonetaDePassageiros c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }
        return caminhonetasDePassageiros;
    }
    
    public String buscarUltimoNome() throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        String nome = null;
        try {
            nome = em.createQuery(SQLUtil.CaminhonetaDePassageiros.SQL_BUSCA_ULTIMONOME, String.class).getSingleResult();
            return nome;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    
}
