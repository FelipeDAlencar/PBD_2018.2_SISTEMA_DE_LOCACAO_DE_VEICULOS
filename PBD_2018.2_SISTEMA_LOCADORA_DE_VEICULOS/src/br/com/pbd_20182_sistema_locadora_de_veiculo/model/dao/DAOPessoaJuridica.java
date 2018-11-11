/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOPessoaJuridica extends DAOGenerico<PessoaJuridica> implements IDAOPessoaJuridica {
    private static DAOPessoaJuridica dAOPessoaJuridica;
    
    
    public DAOPessoaJuridica getInstance(){
        if(dAOPessoaJuridica != null){
            return dAOPessoaJuridica;
        }
        return dAOPessoaJuridica = new DAOPessoaJuridica();
    }
    @Override
    public ArrayList<PessoaJuridica> findAll()throws DAOException{
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<PessoaJuridica> pessoaJuridicas = null;

        try {
            pessoaJuridicas = (ArrayList) em.createQuery("from PessoaJuridica c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return pessoaJuridicas;
    }
    
}
