/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneContaAPagarDialogController implements Initializable {

    @FXML
    private JFXTextField tfDescricao;

    @FXML
    private JFXTextField tfValor;

    @FXML
    private JFXDatePicker dpDataVencimento;

    @FXML
    private JFXCheckBox cbPago;

    @FXML
    private JFXButton btnConfirmar;

    @FXML
    private JFXButton btnCancelar;

    private ContaAPagar contaAPagar;
    private Stage stage;
    private boolean sucesso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MascarasTF.mascaraNumero(tfValor);
    }

    @FXML
    void acaoBtns(ActionEvent event) {
        if (event.getSource() == btnConfirmar) {
            try {
                
                contaAPagar.setDataVencimento(Date.from(dpDataVencimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                contaAPagar.setDescricao(tfDescricao.getText());
                contaAPagar.setValor(Double.parseDouble(tfValor.getText()));
                contaAPagar.setPago(cbPago.isSelected());
                
                sucesso = true;
                stage.close();
            } catch (NumberFormatException e) {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Campos incorretos!", "Preencha corretamente.");
            }
        } else {
            stage.close();
        }

    }

    public ContaAPagar getContaAPagar() {
        return contaAPagar;
    }

    public void setContaAPagar(ContaAPagar contaAPagar) {
        this.contaAPagar = contaAPagar;
        
        if(contaAPagar.getId() != null){
            
            tfDescricao.setText(contaAPagar.getDescricao());
            tfValor.setText(String.valueOf(contaAPagar.getValor()));
            dpDataVencimento.setValue(Util.converterDateEmLocalDate(contaAPagar.getDataVencimento()));
            cbPago.setSelected(contaAPagar.isPago());
            
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
