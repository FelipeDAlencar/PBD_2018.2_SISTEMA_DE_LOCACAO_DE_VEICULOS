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
public class Alerta extends Alert {

    private static Alerta alert;

    public static Alerta getInstace(AlertType tipo) {
        if (alert == null) {
            return alert = new Alerta(tipo);
        }

        return alert;
    }

    private Alerta(AlertType alertType) {
        super(alertType);
    }

    public void alertar(AlertType tipo, String titulo, String cabecalho, String conteudo ) {
        alert.setAlertType(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

}
