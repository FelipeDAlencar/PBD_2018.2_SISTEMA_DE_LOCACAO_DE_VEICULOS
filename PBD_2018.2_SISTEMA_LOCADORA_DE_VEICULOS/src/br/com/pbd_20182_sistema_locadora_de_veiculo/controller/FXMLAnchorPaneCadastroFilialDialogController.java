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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField tfNomeFilial;
    
    @FXML
    private TextField tfCidade;
    
    @FXML
    private TextField tfRua;
    
    @FXML
    private TextField tfBairro;
    
    @FXML
    private ComboBox<String> comboUF;
    
    @FXML
    private TextField tfNumero;
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private TextField tfCep;
    
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
            
            tfNomeFilial.setText(filial.getNome());
            
        }
    }
    
}
