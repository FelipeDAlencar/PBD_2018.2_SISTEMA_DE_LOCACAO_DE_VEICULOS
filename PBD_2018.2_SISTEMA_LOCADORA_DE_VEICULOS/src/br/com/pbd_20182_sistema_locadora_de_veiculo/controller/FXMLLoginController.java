/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Felipe
 */
public class FXMLLoginController implements Initializable {

    private Stage stage;
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnEntrar;

    @FXML
    private PasswordField tfSenha;

    private DAOPessoa dAOPessoa = new DAOPessoa();

    public FXMLLoginController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void acaoBtnEntrar(ActionEvent event) {
        Pessoa pessoa = new Pessoa();
        pessoa.setLogin(tfLogin.getText());
        pessoa.setSenha(tfSenha.getText());

        try {
            if (dAOPessoa.buscarLogin(pessoa) != null) {

                verificarTipoUsuarioLogin(pessoa);

                FXMLLoader load = new FXMLLoader();
                load.setLocation(FXMLVBoxTelaPrincipalController.class
                        .getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVBoxTelaPrincipal.fxml"));
                Pane root = load.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                this.stage.close();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERRO AO TENTAR LOGAR.");
            alert.show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void verificarTipoUsuarioLogin(Pessoa pessoa) {

        if (pessoa instanceof PessoaFisica || pessoa instanceof PessoaJuridica) {
            System.out.println("É Física");
            FXMLVBoxTelaPrincipalController.menuAdministracao.setDisable(true);
        } else if (pessoa instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) pessoa;
            if (funcionario.isSuperUsuario()) {
                System.out.println("Tem privilegios");
            } else {
                System.out.println("Sem privilegios");
            }

        }

    }
}
