/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author Felipe
 */
public class ThreadDeVerificacaoDeLocacoes extends Task<Integer> {

    public static double VALOR_DIARIA = 0;

    private Fachada fachada;
    private ArrayList<Locacao> locacaos;
    public static  ArrayList<Locacao> locacaosEmAtraso;
    
    public ThreadDeVerificacaoDeLocacoes() {
        fachada = Fachada.getInstance();
        locacaosEmAtraso = new ArrayList<>();
        try {
            VALOR_DIARIA = fachada.buscarGeral().getMetadePrimeiraDiaria() * 2;
        } catch (DAOException ex) {
            Logger.getLogger(ThreadDeVerificacaoDeLocacoes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected Integer call() {

        try {
            while (true) {
                locacaos = fachada.listarTodosLocacao();
                locacaosEmAtraso = new ArrayList<>();
                System.err.println(locacaos);
                for (Locacao locacao : locacaos) {

                    Calendar dataEntrega = Calendar.getInstance();

                    dataEntrega.setTime(locacao.getDataVolta().getTime());
                    dataEntrega.add(Calendar.MINUTE, 1);
                    Calendar dataAtual = Calendar.getInstance();
                    dataAtual.setTime(new Date());

                    if (dataEntrega.compareTo(dataAtual) < 0) {
                        System.err.println("Em atraso!");
                        locacaosEmAtraso.add(locacao);
                               
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
