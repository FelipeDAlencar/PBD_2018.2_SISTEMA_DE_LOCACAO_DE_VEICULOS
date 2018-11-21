/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.model;

import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public abstract class Util {

    public static String gerarCodigoInterno(String nome, String ultimoCodigo) {
        int parteNumerica = Integer.parseInt(ultimoCodigo.substring(3));
        parteNumerica += 1;
        String codigo = nome.substring(0, 3) + String.format("%03d", parteNumerica);

        return codigo;
    }

    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);

    }

    public static Date converterStringEmDate(String dataTexto) {
        DateFormat f = DateFormat.getDateInstance();
        java.util.Date d;
        try {
            d = f.parse(dataTexto);
            return d;

        } catch (ParseException ex) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.ERROR, "Erro ao tentar converter data", "", "");
            
        }
        return null;
    }

}
