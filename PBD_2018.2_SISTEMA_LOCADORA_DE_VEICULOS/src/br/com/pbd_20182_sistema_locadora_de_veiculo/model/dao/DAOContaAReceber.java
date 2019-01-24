/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOContaAReceber extends DAOGenerico<ContaAReceber> implements IDAOContaAReceber{
    
    @Override
    public ArrayList<ContaAReceber> findAll() throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ContaAReceber> contaAReceber = null;

        try {
            contaAReceber = (ArrayList) em.createQuery("from ContaAReceber c ").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return contaAReceber;

    }
    
}