/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Felipe
 */
public class Alerta {

    public static javafx.scene.control.Alert alert;
    
    public static void getInstace(AlertType tipo, String title, String cabecalho, String conteudo) {
        if (alert == null) {
            alert = new Alert(tipo);
        }
        alert.setAlertType(tipo);
        alert.setTitle(title);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.show();
    }

}
