/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.ConnectionFactory;
import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewLocacaoPorPeriodo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewMotoristaPorLocacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;

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

    @Override
    public boolean verificarVencimentoCNH(Calendar dataIda, Calendar dataVolta, Integer id) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery(SQLUtil.Locacao.SQL_PROCEDURE_Verificar_Vencimento_CNH);
        query.registerStoredProcedureParameter("dataIda", Calendar.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dataVolta", Calendar.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("idMotorista", Integer.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("retorno", boolean.class, ParameterMode.OUT);
        query.setParameter("dataIda", dataIda);
        query.setParameter("dataVolta", dataVolta);
        query.setParameter("idMotorista", id);
        query.execute();

        boolean retorno = (Boolean) query
                .getOutputParameterValue("retorno");

        em.close();
        return retorno;

    }

    @Override
    public int calcularIdade(Integer id) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        StoredProcedureQuery query = em
                .createStoredProcedureQuery(SQLUtil.Locacao.SQL_PROCEDURE_CALCULAR_IDADE);

        query.registerStoredProcedureParameter("idPar", Integer.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("retorno", Integer.class, ParameterMode.OUT);

        query.setParameter("idPar", id);

        query.execute();

        int retorno = (Integer) query
                .getOutputParameterValue("retorno");

        em.close();
        return retorno;

    }

    @Override
    public ArrayList<Locacao> buscarLocacaoPorCliente(Pessoa pessoa) throws DAOException {
        EntityManager em = ConnectionFactory.getInstance().getConnection();

        try {

            TypedQuery<Locacao> query = em.createQuery(SQLUtil.Locacao.SQL_BUSCAR_LOCACAO_POR_CLIENTE, Locacao.class);
            query.setParameter("idcliente", pessoa);

            ArrayList<Locacao> resultLocacao;
            return resultLocacao = (ArrayList<Locacao>) query.getResultList();

        } catch (NoResultException e) {
            e.printStackTrace();
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.INFORMATION, "Erro.", "Problema ao logar.", "Login ou senha inválidos.");
        } finally {
            em.close();
        }

        return null;
    }

    public ArrayList<ViewMotoristaPorLocacao> buscarMotoristaPorLocacao(PessoaFisica pessoaFisica) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ViewMotoristaPorLocacao> filial = null;

        try {

            TypedQuery<ViewMotoristaPorLocacao> query = em.createQuery(SQLUtil.Locacao.SQL_BUSCAR_LOCACAO_POR_MOTORISTA, ViewMotoristaPorLocacao.class);
            query.setParameter("id", pessoaFisica.getId());
            filial = (ArrayList<ViewMotoristaPorLocacao>) query.getResultList();

            return filial;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<ViewLocacaoPorPeriodo> buscarLocacaoPorPeriodo(Date dataIncial, Date dataFinal) throws DAOException {

        EntityManager em = ConnectionFactory.getInstance().getConnection();
        ArrayList<ViewLocacaoPorPeriodo> filial = null;

        try {

            TypedQuery<ViewLocacaoPorPeriodo> query = em.createQuery(SQLUtil.Locacao.SQL_BUSCAR_LOCACAO_POR_PERIODO, ViewLocacaoPorPeriodo.class);
            query.setParameter("dataInicial", dataIncial);
            query.setParameter("dataFinal", dataFinal);
            filial = (ArrayList<ViewLocacaoPorPeriodo>) query.getResultList();

            return filial;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("ERRO AO TENTAR BUSCAR NO BANCO DE DADOS");

        } finally {
            em.close();
        }
    }

}
