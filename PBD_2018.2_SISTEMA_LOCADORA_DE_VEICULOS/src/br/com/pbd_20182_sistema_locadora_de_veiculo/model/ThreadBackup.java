/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import java.sql.Time;
import java.util.Calendar;

import java.util.Date;

import javafx.concurrent.Task;

/**
 *
 * @author Felipe
 */
public class ThreadBackup extends Task<Integer> {

    public ThreadBackup() {

    }

    @Override
    protected Integer call() throws DAOException {
        Fachada fachada = Fachada.getInstance();
        Geral geral = fachada.buscarGeral();

        Time horaBackup = geral.getHoraDeBackup();
        Calendar calHoraAtual = Calendar.getInstance();

        Calendar calHoraBack = Calendar.getInstance();
        calHoraBack.setTime(horaBackup);

        int horaBack = calHoraBack.get(Calendar.HOUR_OF_DAY);
        int minutoBack = calHoraBack.get(Calendar.MINUTE);

        try {
            while (true) {

                Date dataAtual = new Date();
                calHoraAtual.setTime(dataAtual);

                int horaAtual = calHoraAtual.get(Calendar.HOUR_OF_DAY);
                int minutoAtual = calHoraAtual.get(Calendar.MINUTE);
                    
                
                System.err.println(horaBack + " - " + minutoBack);
                System.err.println(horaAtual + " - " + minutoAtual);
                if (horaBack >= horaAtual && minutoBack == minutoAtual) {

                    Backup.realizaBackup();
                    break;
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
