/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.exception;

import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import javafx.scene.control.Alert;

/**
 *
 * @author Felipe
 */
public class BusinessExpection extends Exception{
    
    public BusinessExpection(String message){
        Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
        alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção",message);
    }
    
}
