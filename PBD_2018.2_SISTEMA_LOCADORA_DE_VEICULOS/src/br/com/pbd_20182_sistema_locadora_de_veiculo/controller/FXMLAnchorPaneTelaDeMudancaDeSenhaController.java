/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneTelaDeMudancaDeSenhaController implements Initializable {

    @FXML
    private JFXPasswordField ptfSenha;

    @FXML
    private JFXPasswordField ptfConfirmarSenha;

    @FXML
    private Button btnRedefinir;

    private boolean sucesso;
    private Pessoa pessoa;
    private Stage stage;

    @FXML
    void acaoBtnRedefinir(ActionEvent event) throws BusinessExpection {

        if (pessoa instanceof PessoaFisica) {
            if (((PessoaFisica) pessoa).getCPF().equals(ptfSenha.getText())) {
                throw new BusinessExpection("Senha não permitida.");
            }
        } else if (pessoa instanceof PessoaJuridica) {
            if (((PessoaJuridica) pessoa).getCNPJ().equals(ptfSenha.getText())) {
                throw new BusinessExpection("Senha não permitida.");

            }
        } else if (pessoa instanceof Funcionario) {
            if (((Funcionario) pessoa).getMatricula().equals(ptfSenha.getText())) {
                throw new BusinessExpection("Senha não permitida.");
            }
        }

        if (ptfConfirmarSenha.getText().equals(ptfConfirmarSenha.getText())) {
            pessoa.setSenha(ptfSenha.getText());
            sucesso = true;
            stage.close();
        } else {
            Alerta alerta = Alerta.getInstace(AlertType.NONE);
            alerta.alertar(AlertType.ERROR, "Erro",
                    "Erro ao tentar redefinir senha.", "Senhas não coinicidem");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

   

    public Button getBtnRedefinir() {
        return btnRedefinir;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
