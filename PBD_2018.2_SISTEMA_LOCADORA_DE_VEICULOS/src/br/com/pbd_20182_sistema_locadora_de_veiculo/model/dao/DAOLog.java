/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Log;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOLog extends DAOGenerico<Log> implements IDAOLog{

    @Override
    public ArrayList<Log> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Log> logs = null;

        try {
            logs = (ArrayList) em.createQuery("from log c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return logs;
    }
    
    
}
