/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.concurrent.Task;

/**
 *
 * @author Felipe
 */
public class ThreadDeVerificacaoDeReservas extends Task<Integer> {

    Fachada fachada;

    public ThreadDeVerificacaoDeReservas() {
        fachada = Fachada.getInstance();
    }

    @Override
    protected Integer call() {

        try {
            while (true) {

                ArrayList<ReservaPessoasCategorias> reservas = fachada.listarTodosReservaPessoasCategorias();
                //System.err.println(reservas);
                for (ReservaPessoasCategorias reserva : reservas) {
                    Calendar dataHoraDaReserva = Calendar.getInstance();
                    dataHoraDaReserva.setTime(reserva.getDataHora());
                    Calendar dataHoraAtual = Calendar.getInstance();
                    dataHoraAtual.setTime(new Date());

                    if (isCancelled()) {
                        System.err.println("Cancelou");
                    }

                    dataHoraDaReserva.add(Calendar.MINUTE, 60);

                    
                    if (dataHoraDaReserva.compareTo(dataHoraAtual) < 0) {

                        reserva.setAtivo(false);
                        fachada.salvarReservaPessoasCategorias(reserva);

                    }

                }

                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }

}
