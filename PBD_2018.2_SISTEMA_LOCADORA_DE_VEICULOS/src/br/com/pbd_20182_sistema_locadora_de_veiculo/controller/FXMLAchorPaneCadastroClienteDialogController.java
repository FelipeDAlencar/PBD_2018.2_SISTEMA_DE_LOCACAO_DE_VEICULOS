/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAchorPaneCadastroClienteDialogController implements Initializable {

    private Stage stage;
    private PessoaFisica pessoaFisica;
    private boolean confirmou;
    
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

    @FXML
    private DatePicker cpDataNascimento;

    @FXML
    private DatePicker cpVencimentoCNH;

    @FXML
    private TextField tfIdentificacao;

    @FXML
    private TextField tfNCNH;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button BtnCancelar;

    @FXML
    void acoesBtns(ActionEvent event) {
        if(event.getSource() == BtnCancelar){
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cadastrado");
            confirmou = true;
            stage.close();
        }

    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public boolean isConfirmou() {
        return confirmou;
    }

    public void setConfirmou(boolean confirmou) {
        this.confirmou = confirmou;
    }
    
    
    
}
