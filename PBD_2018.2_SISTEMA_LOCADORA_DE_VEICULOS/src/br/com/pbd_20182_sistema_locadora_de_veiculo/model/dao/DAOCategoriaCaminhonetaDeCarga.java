/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOCategoriaCaminhonetaDeCarga extends DAOGenerico<CaminhonetaDeCarga> implements IDAOCategoriaCaminhonetaDeCarga {

    @Override
    public ArrayList<CaminhonetaDeCarga> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<CaminhonetaDeCarga> caminhonetaDeCarga = null;

        try {
            caminhonetaDeCarga = (ArrayList) em.createQuery("from caminhoneta_de_carga c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return caminhonetaDeCarga;

    }

}
