/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroVeiculosDialogController implements Initializable {

    @FXML
    private JFXTextField tfModelo;

    @FXML
    private JFXTextField tfFabricante;

    @FXML
    private JFXTextField tfAnoDoModelo;

    @FXML
    private JFXTextField tfAnoDeFabricacao;

    @FXML
    private JFXColorPicker cpCor;

    @FXML
    private JFXTextField tfPlaca;

    @FXML
    private JFXTextField tfNChassi;

    @FXML
    private JFXTextField tfKmAtual;

    @FXML
    private JFXTextField tfTipoCombustivel;

    @FXML
    private JFXTextField tfTorqueDoMotor;

    @FXML
    private JFXComboBox<Categoria> comboCategoria;

    @FXML
    private JFXCheckBox cbDisponivel;

    @FXML
    private JFXButton btnConfirmar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXComboBox<Filial> comboFilial;
    
    
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

        MascarasTF.mascaraNumeroInteiro(tfAnoDeFabricacao);
        MascarasTF.mascaraNumeroInteiro(tfAnoDoModelo);
        MascarasTF.mascaraNumeroInteiro(tfKmAtual);
        MascarasTF.mascaraNumeroInteiro(tfNChassi);
        MascarasTF.mascaraNumeroInteiro(tfTorqueDoMotor);
        MascarasTF.mascaraPlaca(tfPlaca);

    }

    @FXML
    void acoesBtns(ActionEvent event) {

        if (event.getSource() == btnConfirmar) {
            try {
                veiculo.setAnoDeFabricacao(Integer.parseInt(tfAnoDeFabricacao.getText()));
                veiculo.setAnoDoModelo(Integer.parseInt(tfAnoDoModelo.getText()));
                veiculo.setCategoria(comboCategoria.getValue());
                veiculo.setCor(cpCor.getValue().toString());
                veiculo.setDisponivel(cbDisponivel.isSelected());
                veiculo.setFabricante(tfFabricante.getText());
                veiculo.setModelo(tfModelo.getText());
                veiculo.setNumeroChassi(tfNChassi.getText());
                veiculo.setPlaca(tfPlaca.getText());
                veiculo.setQuilometragemAtual(Double.parseDouble(tfKmAtual.getText()));
                veiculo.setTipoDeCombustivel(tfTipoCombustivel.getText());
                veiculo.setTorqueDoMotor(Double.parseDouble(tfTorqueDoMotor.getText()));
                veiculo.setFilial(comboFilial.getValue());

                veiculo.setAtivo(true);

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

    private void carregarCombo() throws DAOException {

        ObservableList<Categoria> obsCategoria = FXCollections.observableArrayList(fachada.listarTodosCategoria());
        comboCategoria.setItems(obsCategoria);
        
        
        ObservableList<Filial> obsFilial = FXCollections.observableArrayList(fachada.listarTodosFilial());
        comboFilial.setItems(obsFilial);
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

        if (veiculo.getId() != null) {
            tfAnoDeFabricacao.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
            tfAnoDoModelo.setText(String.valueOf(veiculo.getAnoDoModelo()));
            cpCor.setValue(Color.web(veiculo.getCor()));
            tfFabricante.setText(veiculo.getFabricante());
            tfKmAtual.setText(String.valueOf(veiculo.getQuilometragemAtual()));
            tfModelo.setText(veiculo.getModelo());
            tfNChassi.setText(veiculo.getNumeroChassi());
            tfPlaca.setText(veiculo.getPlaca());
            tfTipoCombustivel.setText(veiculo.getTipoDeCombustivel());
            tfTorqueDoMotor.setText(String.valueOf(veiculo.getTorqueDoMotor()));

            comboCategoria.setValue(veiculo.getCategoria());

            cbDisponivel.setSelected(veiculo.isDisponivel());

        }
    }

}
