/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Felipe
 */
public class DAOLocacao extends DAOGenerico<Locacao> implements IDAOLocacao {

    @Override
    public ArrayList<Locacao> findAll() {
        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<Locacao> locacoes = null;

        try {
            locacoes = (ArrayList) em.createQuery("from Locacao l where ativo = true and finalizada = false").getResultList();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            em.close();
        }

        return locacoes;
    }

    @Override
    public Time procedureCalcularIntervaloDeAtraso(Calendar dataAtual, Integer id) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery(SQLUtil.Locacao.SQL_PROCEDURE_CALCULAR_INTERVALO_DE_ATRASO);
        query.registerStoredProcedureParameter("dataAtual", Calendar.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idPar", Integer.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("intervalo", Time.class, ParameterMode.OUT);
        query.setParameter("dataAtual", dataAtual);
        query.setParameter("idPar", id);
        query.execute();

        Time intervalo = (Time) query
                .getOutputParameterValue("intervalo");
        
        

        em.close();
        return intervalo;

    }

}
