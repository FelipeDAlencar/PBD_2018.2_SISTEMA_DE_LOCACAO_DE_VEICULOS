/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOLocacao extends DAOGenerico<Locacao> implements IDAOLocacao{

    @Override
    public ArrayList<Locacao> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Locacao> locacoes = null;

        try {
            locacoes = (ArrayList) em.createQuery("from locacao c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return locacoes;
    }
    
}
