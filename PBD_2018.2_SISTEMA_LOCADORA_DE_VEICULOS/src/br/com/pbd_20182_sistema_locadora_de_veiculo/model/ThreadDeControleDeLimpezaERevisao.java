/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
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
public class ThreadDeControleDeLimpezaERevisao extends Task<Integer> {
    
    private Fachada fachada;
    private Veiculo veiculo;
    private Thread thread;
    private long tempoLimpezaERevisao;
    
    public ThreadDeControleDeLimpezaERevisao(Veiculo veiculo) {
        fachada = Fachada.getInstance();
        this.veiculo = veiculo;
        
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fachada.buscarGeral().getTempoDeLimpezaRevisao());
            tempoLimpezaERevisao = (cal.get(Calendar.SECOND) * 1000);
            
            thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
            
        } catch (DAOException ex) {
            Logger.getLogger(ThreadDeControleDeLimpezaERevisao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected Integer call() {
        
        try {
            
            while (true) {
                Thread.sleep(tempoLimpezaERevisao);
                
                
                veiculo.setDisponivel(true);
                fachada.salvarVeiculo(veiculo);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }
    
}
