/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOCategoria extends DAOGenerico<Categoria> implements IDAOCategoria {
    
    
    @Override
    public ArrayList<Categoria> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Categoria> categorias = null;

        try {
            categorias = (ArrayList) em.createQuery("from Categoria c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return categorias;
    }

}
