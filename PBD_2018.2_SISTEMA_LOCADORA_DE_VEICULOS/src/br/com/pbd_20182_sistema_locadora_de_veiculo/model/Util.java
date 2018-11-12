/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Felipe
 */
public abstract class Util {
    
    
    public static  String gerarCodigoInterno(String nome, String ultimoCodigo){
        int parteNumerica = Integer.parseInt(ultimoCodigo.substring(3));
        parteNumerica += 1;
        String codigo = nome.substring(0, 3) + String.format("%03d", parteNumerica) ;
        
        return codigo;
    }
    
    
    public static String formatarData(Calendar data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = data.getTime();
        return sdf.format(d);
        
    }
            
}
