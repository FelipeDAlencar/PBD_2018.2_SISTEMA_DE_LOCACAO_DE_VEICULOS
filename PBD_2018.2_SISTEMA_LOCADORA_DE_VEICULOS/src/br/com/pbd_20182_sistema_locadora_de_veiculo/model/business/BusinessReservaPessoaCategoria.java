/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model.business;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ThreadDeVerificacaoDeReservas;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public class BusinessReservaPessoaCategoria implements IBusinessRerservaPessoaCategoria {

    private DAOReservaPessoaCategoria dAOReservaPessoaCategoria;
    private Fachada fachada;

    public BusinessReservaPessoaCategoria() {
        dAOReservaPessoaCategoria = new DAOReservaPessoaCategoria();

    }

    @Override
    public void salvar(ReservaPessoasCategorias reservaPessoasCategorias) throws DAOException, BusinessExpection {

        validar(reservaPessoasCategorias);

        dAOReservaPessoaCategoria.salvar(reservaPessoasCategorias);

    }

    @Override
    public ArrayList<ReservaPessoasCategorias> listarTodos() throws DAOException {
        return dAOReservaPessoaCategoria.findAll();
    }

    @Override
    public ReservaPessoasCategorias buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReservaPessoasCategorias> buscarPorBusca(String busca) throws DAOException {
        return dAOReservaPessoaCategoria.buscarPorBuscaReserva(busca);
    }

    private void validar(ReservaPessoasCategorias reservaPessoasCategorias) throws BusinessExpection, DAOException {
        fachada = Fachada.getInstance();
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.setTime(new Date());
        double hora = Double.parseDouble(String.valueOf(dataAtual.get(Calendar.HOUR_OF_DAY)) + "."
                + String.valueOf(dataAtual.get(Calendar.MINUTE)));

        String errorMessage = "";

        hora = 15;
        if (hora < 8 || hora > 17) {

            errorMessage += "Horário não permitido \n";
        }
        if (reservaPessoasCategorias.getDataHora() != null) {
            Calendar dataHoraDaReserva = Calendar.getInstance();
            dataHoraDaReserva.setTime(reservaPessoasCategorias.getDataHora());

            dataHoraDaReserva.set(Calendar.HOUR_OF_DAY, 0);
            dataHoraDaReserva.set(Calendar.MINUTE, 0);
            dataHoraDaReserva.set(Calendar.SECOND, 0);
            dataHoraDaReserva.set(Calendar.MILLISECOND, 0);

            dataAtual.set(Calendar.HOUR_OF_DAY, 0);
            dataAtual.set(Calendar.MINUTE, 0);
            dataAtual.set(Calendar.SECOND, 0);
            dataAtual.set(Calendar.MILLISECOND, 0);
            
            
            System.err.println(dataAtual.getTime());
            System.err.println(dataHoraDaReserva.getTime());
            
            
            if (dataHoraDaReserva.compareTo(dataAtual) < 0 && !(reservaPessoasCategorias.getId() != null)) {
                errorMessage += "Data não pode ser anterior do que a atual.\n";
            }
        } else {
            errorMessage += "Por favor, informe a data da reserva.\n";
        }

        if (!(reservaPessoasCategorias.getCategoria() != null)) {
            errorMessage += "Por favor, selecione a categoria.\n";
        }
        if (!(reservaPessoasCategorias.getPessoa() != null)) {
            errorMessage += "Por favor, selecione o cliente.\n";
        }
        if (reservaPessoasCategorias.getValorPrevisto() <= 0) {
            errorMessage += "Valor informado inválido,\ninforme um valor válido.";
        }

       

        if (errorMessage.length() != 0) {

            ThreadDeVerificacaoDeReservas task = new ThreadDeVerificacaoDeReservas();
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            throw new BusinessExpection("Atenção\n\n" + errorMessage);

        }

    }

}
