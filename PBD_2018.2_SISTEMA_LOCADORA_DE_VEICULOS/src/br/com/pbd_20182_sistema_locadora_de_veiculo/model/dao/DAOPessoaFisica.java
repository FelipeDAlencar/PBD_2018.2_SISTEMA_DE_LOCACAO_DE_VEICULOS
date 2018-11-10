/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOPessoaFisica extends DAOGenerico<PessoaFisica> implements IDAOPessoaFisica {

    @Override
    public ArrayList<PessoaFisica> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<PessoaFisica> pessoaFisicas = null;

        try {
            pessoaFisicas = (ArrayList) em.createQuery("from PessoaFisica p").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return pessoaFisicas;
    }
}
