/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneSelecaoCategoriaController implements Initializable {

    @FXML
    private Group grupoRadios;

    @FXML
    private RadioButton rbCategoria;

    @FXML
    private RadioButton rbDeCarga;

    @FXML
    private RadioButton rbDePassageiros;

    @FXML
    void AcaoRadios(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup toggleGroup = new ToggleGroup();
        
        rbCategoria.setToggleGroup(toggleGroup);
        rbDeCarga.setToggleGroup(toggleGroup);
        rbDePassageiros.setToggleGroup(toggleGroup);
        
    }

}
