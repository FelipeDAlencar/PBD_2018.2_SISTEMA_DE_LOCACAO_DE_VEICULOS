/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
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
public class FXMLAnchorPaneContaAReceberDialogController implements Initializable {

       @FXML
    private JFXTextField tfDescricao;

    @FXML
    private JFXTextField tfValor;

    @FXML
    private JFXDatePicker dpDataRecebimento;

    @FXML
    private JFXCheckBox cbPago;

    @FXML
    private JFXButton btnConfirmar;

    @FXML
    private JFXButton btnCancelar;

    private ContaAReceber contaAreceber;
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
                
                contaAreceber.setDataRecebimento(Date.from(dpDataRecebimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                contaAreceber.setDescricao(tfDescricao.getText());
                contaAreceber.setValor(Double.parseDouble(tfValor.getText()));
                contaAreceber.setPago(cbPago.isSelected());
                
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

    public ContaAReceber getContaAReceber() {
        return contaAreceber;
    }

    public void setContaAReceber(ContaAReceber contaAReceber) {
        this.contaAreceber = contaAReceber;
        
        if(contaAReceber.getId() != null){
            
            tfDescricao.setText(contaAReceber.getDescricao());
            tfValor.setText(String.valueOf(contaAReceber.getValor()));
            dpDataRecebimento.setValue(Util.converterDateEmLocalDate(contaAReceber.getDataRecebimento()));
            cbPago.setSelected(contaAReceber.isPago());
            
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
