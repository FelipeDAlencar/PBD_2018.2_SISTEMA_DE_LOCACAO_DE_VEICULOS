/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOVeiculos extends DAOGenerico<Veiculo> implements IDAOVeiculos{

    @Override
    public ArrayList<Veiculo> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Veiculo> veiculos = null;

        try {
            veiculos = (ArrayList) em.createQuery("from veiculo c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return veiculos;
    }
    
}
