/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOReservaPessoaCategoria extends DAOGenerico<ReservaPessoasCategorias> implements IDAOReservaPessoasCategorias{

    @Override
    public ArrayList<ReservaPessoasCategorias> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ReservaPessoasCategorias> reservaPessoasCategoriases = null;

        try {
            reservaPessoasCategoriases = (ArrayList) em.createQuery("from ReservaPessoasCategorias c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return reservaPessoasCategoriases;
    }
    
}
