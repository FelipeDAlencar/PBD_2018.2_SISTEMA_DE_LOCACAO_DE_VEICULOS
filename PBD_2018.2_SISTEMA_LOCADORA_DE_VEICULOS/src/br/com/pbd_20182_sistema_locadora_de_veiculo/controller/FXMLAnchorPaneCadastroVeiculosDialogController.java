/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FXMLAnchorPaneCadastroVeiculosDialogController implements Initializable {

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfFabricante;

    @FXML
    private TextField tfAnoDoModelo;

    @FXML
    private TextField tfAnoDeFabricacao;

    @FXML
    private TextField tfCor;

    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfNChassi;

    @FXML
    private TextField tfKmAtual;

    @FXML
    private TextField tfTipoCombustivel;

    @FXML
    private TextField tfTorqueDoMotor;

    @FXML
    private ComboBox<Categoria> comboCategoria;

    @FXML
    private CheckBox cbDisponivel;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    private Fachada fachada;
    private Stage stage;
    private boolean sucesso;
    private Veiculo veiculo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        try {
            carregarCombo();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void acoesBtns(ActionEvent event) {

        if (event.getSource() == btnConfirmar) {

            veiculo.setAnoDeFabricacao(Integer.parseInt(tfAnoDeFabricacao.getText()));
            veiculo.setAnoDoModelo(Integer.parseInt(tfAnoDoModelo.getText()));
            veiculo.setCategoria(comboCategoria.getValue());
            veiculo.setCor(tfCor.getText());
            veiculo.setDisponivel(cbDisponivel.isSelected());
            veiculo.setFabricante(tfFabricante.getText());
            veiculo.setModelo(tfModelo.getText());
            veiculo.setNumeroChassi(tfNChassi.getText());
            veiculo.setPlaca(tfPlaca.getText());
            veiculo.setQuilometragemAtual(Integer.parseInt(tfKmAtual.getText()));
            veiculo.setTipoDeCombustivel(tfTipoCombustivel.getText());
            veiculo.setTorqueDoMotor(Integer.parseInt(tfTorqueDoMotor.getText()));

            sucesso = true;
            stage.close();

        } else {
            stage.close();

        }
    }

    private void carregarCombo() throws DAOException {

        ObservableList<Categoria> obs = FXCollections.observableArrayList(fachada.listarTodosCategoria());
        comboCategoria.setItems(obs);

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    
    

}
