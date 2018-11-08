/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOFilial extends DAOGenerico<Filial> implements IDAOFilial{

    @Override
    public ArrayList<Filial> findAll() {
        
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Filial> filiais = null;

        try {
            filiais = (ArrayList) em.createQuery("from filial c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return filiais;
       
    }
    
}
