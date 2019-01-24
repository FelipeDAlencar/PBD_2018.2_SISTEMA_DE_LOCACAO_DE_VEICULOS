/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroFilialDialogController implements Initializable {
    
    @FXML
    private JFXTextField tfNomeFilial;
    
    @FXML
    private JFXTextField tfCidade;
    
    @FXML
    private JFXTextField tfRua;
    
    @FXML
    private JFXTextField tfBairro;
    
    @FXML
    private JFXComboBox<String> comboUF;
    
    @FXML
    private JFXTextField tfNumero;
    
    @FXML
    private JFXButton btnConfirmar;
    
    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    private JFXTextField tfCep;
    
    private Stage stage;
    private boolean sucesso;
    private Filial filial;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<String> ufs = new ArrayList<>();
        ObservableList<String> obsUfs;
        
        for (int i = 0; i < Util.ufs.length; i++) {
            ufs.add(Util.ufs[i]);
        }
        
        obsUfs = FXCollections.observableArrayList(ufs);
        
        comboUF.setItems(obsUfs);
        
        MascarasTF.mascaraNumero(tfNumero);
        MascarasTF.mascaraCEP(tfCep);
    }
    
    @FXML
    void acaoBtns(ActionEvent event) {
        if (event.getSource() == btnConfirmar) {
            try{
            filial.setNome(tfNomeFilial.getText());
            Endereco endereco = new Endereco();
            endereco.setBairro(tfBairro.getText());
            endereco.setCidade(tfCidade.getText());
            endereco.setRua(tfRua.getText());
            endereco.setUf(comboUF.getValue());
            endereco.setNumero(Integer.parseInt(tfNumero.getText()));
            endereco.setCep(Util.removerCaracteres(tfCep.getText()));
            
            filial.setEndereco(endereco);
            filial.setAtivo(true);
            
            sucesso = true;
            stage.close();
            }catch(NumberFormatException e){
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Campos incorretos!", "Preencha corretamente.");
            }
        } else {
            stage.close();
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
    
    public Filial getFilial() {
        return filial;
    }
    
    public void setFilial(Filial filial) {
        this.filial = filial;
        
        if (filial.getId() != null) {
            tfBairro.setText(filial.getEndereco().getBairro());
            tfCidade.setText(filial.getEndereco().getCidade());
            tfNumero.setText(String.valueOf(filial.getEndereco().getNumero()));
            tfRua.setText(filial.getEndereco().getRua());
            comboUF.setValue(filial.getEndereco().getUf());
            tfCep.setText(filial.getEndereco().getCep());
            
            tfNomeFilial.setText(filial.getNome());
            
        }
    }
    
}
