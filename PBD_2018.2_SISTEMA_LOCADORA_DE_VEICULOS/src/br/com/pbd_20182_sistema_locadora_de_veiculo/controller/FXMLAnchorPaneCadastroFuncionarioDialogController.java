/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FXMLAnchorPaneCadastroFuncionarioDialogController implements Initializable {

    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfLogin;

    @FXML
    private JFXTextField tfMatricula;

    @FXML
    private JFXCheckBox cbSuperUsuario;

    @FXML
    private JFXTextField tfCidade;

    @FXML
    private JFXTextField tfRua;

    @FXML
    private JFXTextField tfBairro;

    @FXML
    private JFXTextField tfNumero;

    @FXML
    private JFXTextField tfCargo;

    @FXML
    private JFXComboBox<String> ufCombo;

    @FXML
    private JFXButton btnCancelas;

    @FXML
    private JFXButton btnConfirmar;

    private Funcionario funcionario;
    private Stage stage;
    private boolean sucesso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> obsUFs = FXCollections.observableArrayList(Arrays.asList(Util.ufs));

        ufCombo.setItems(obsUFs);

        MascarasTF.mascaraNumero(tfMatricula);
        MascarasTF.mascaraNumero(tfNumero);

    }

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection {

        if (event.getSource() == btnConfirmar) {
            try {
                Endereco endereco = new Endereco();

                endereco.setBairro(tfBairro.getText());
                endereco.setCidade(tfCidade.getText());
                endereco.setNumero(Integer.parseInt(tfNumero.getText()));
                endereco.setRua(tfRua.getText());
                endereco.setUf(ufCombo.getValue());

                cbSuperUsuario.setSelected(funcionario.isSuperUsuario());

                funcionario.setEndereco(endereco);
                funcionario.setCargo(tfCargo.getText());

                funcionario.setLogin(tfLogin.getText());
                funcionario.setSenha(tfMatricula.getText());
                funcionario.setMatricula(tfMatricula.getText());
                funcionario.setNome(tfNome.getText());
                funcionario.setSuperUsuario(cbSuperUsuario.isSelected());

                funcionario.setCodigo(Util.gerarCodigoInterno(funcionario.getNome(), new DAOPessoa().buscarUltimoCodigo()));
                funcionario.setAtivo(true);
                sucesso = true;
                stage.close();
            } catch (Exception e) {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Campos incorretos!", "Preencha corretamente.");
            }

            
        } else if (event.getSource() == btnCancelas) {

            stage.close();
        }

    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;

        if (funcionario.getId() != null) {
            tfBairro.setText(funcionario.getEndereco().getBairro());
            tfCidade.setText(funcionario.getEndereco().getCidade());
            tfNumero.setText(String.valueOf(funcionario.getEndereco().getNumero()));
            tfRua.setText(funcionario.getEndereco().getRua());
            ufCombo.setValue(funcionario.getEndereco().getUf());
            tfNome.setText(funcionario.getNome());
            tfMatricula.setText(funcionario.getMatricula());
            tfLogin.setText(funcionario.getLogin());
            tfCargo.setText(funcionario.getCargo());

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
