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
public class MinhaThread extends Task<Integer> {

    Fachada fachada;

    public MinhaThread() {
        fachada = Fachada.getInstance();
    }

    @Override
    protected Integer call() {
        
        
//        try{
//            while (true) {
//            ArrayList<ReservaPessoasCategorias> reservas = fachada.listarTodosReservaPessoasCategorias();
//            for (int i = 0; i < 10; i++) {
//                System.err.println(i + 1);
//                Thread.sleep(500);
//
//                if (isCancelled()) {
//                    return i;
//                }
//
//            }
//            Thread.sleep(5000);
//        }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return null;
//        
//    }
        try {
            while (true) {
                System.err.println("Aqui");

                ArrayList<ReservaPessoasCategorias> reservas = fachada.listarTodosReservaPessoasCategorias();

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
                        ArrayList<Veiculo> veiculos = fachada.buscarPorVeiculosIndisponiveisPorCategoria(reserva.getCategoria());
                        reserva.setStatus(false);
                        fachada.salvarReservaPessoasCategorias(reserva);

                        veiculos.get(0).setDisponivel(true);
                        fachada.salvarVeiculo(veiculos.get(0));

                    }

                }

                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
