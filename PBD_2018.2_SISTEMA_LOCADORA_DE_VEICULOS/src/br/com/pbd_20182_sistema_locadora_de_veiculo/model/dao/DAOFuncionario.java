/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.connection.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe
 */
public class DAOFuncionario extends DAOGenerico<Funcionario> implements IDAOFuncionario{

    @Override
    public ArrayList<Funcionario> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Funcionario> funcionarios = null;

        try {
            funcionarios = (ArrayList) em.createQuery("from funcionario c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return funcionarios;
    }
    
    
}
