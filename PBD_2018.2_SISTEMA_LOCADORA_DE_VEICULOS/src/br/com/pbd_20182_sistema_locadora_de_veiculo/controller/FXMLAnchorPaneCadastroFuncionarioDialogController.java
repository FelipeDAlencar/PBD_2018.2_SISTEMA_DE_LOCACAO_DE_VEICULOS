/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroFuncionarioDialogController implements Initializable {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfCargo;

    @FXML
    private CheckBox cbSuperUsuario;

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfRua;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfLogin;

    @FXML
    private ComboBox<String> ufCombo;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelas;

    private Funcionario funcionario;
    private Stage stage;
    private boolean sucesso;

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection {
        try {
            if (event.getSource() == btnConfirmar) {

                Endereco endereco = new Endereco();

                endereco.setBairro(tfBairro.getText());
                endereco.setCidade(tfCidade.getText());
                endereco.setNumero(Integer.parseInt(tfNumero.getText()));
                endereco.setRua(tfRua.getText());
                endereco.setUf(ufCombo.getValue());

                funcionario.setEndereco(endereco);
                funcionario.setCargo(tfCargo.getText());
                funcionario.setLogin(tfLogin.getText());
                funcionario.setSenha(tfMatricula.getText());
                funcionario.setMatricula(tfMatricula.getText());
                funcionario.setNome(tfNome.getText());
                funcionario.setSuperUsuario(cbSuperUsuario.isSelected());

                funcionario.setCodigo(Util.gerarCodigoInterno(funcionario.getNome(), new DAOPessoa().buscarUltimoCodigo()));

                sucesso = true;

                stage.close();
            } else if (event.getSource() == btnCancelas) {

                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> obsUFs = FXCollections.observableArrayList(Arrays.asList(Util.ufs));

        ufCombo.setItems(obsUFs);

    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
