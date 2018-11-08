/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOEndereco extends DAOGenerico<Endereco> implements IDAOEndereco{

    @Override
    public ArrayList<Endereco> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Endereco> enderecos = null;

        try {
            enderecos = (ArrayList) em.createQuery("from endereco c").getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return enderecos;
    }
    
}
