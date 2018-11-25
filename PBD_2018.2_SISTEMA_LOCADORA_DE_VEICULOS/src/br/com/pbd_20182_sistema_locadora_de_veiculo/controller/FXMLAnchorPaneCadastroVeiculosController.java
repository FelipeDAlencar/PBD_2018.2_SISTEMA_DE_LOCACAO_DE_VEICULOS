/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroVeiculosController implements Initializable {

    @FXML
    private TableView<Veiculo> tableView;

    @FXML
    private TableColumn<Veiculo, String> colunaModelo;

    @FXML
    private TableColumn<Veiculo, String> colunaCor;

    @FXML
    private TableColumn<Veiculo, Integer> colunaAnoModelo;

    @FXML
    private AnchorPane btnPesquisar;

    @FXML
    private Label lbModelo;

    @FXML
    private Label lbFabricante;

    @FXML
    private Label lbAnoModelo;

    @FXML
    private Label lbAnoDeFabricacao;

    @FXML
    private Label lbCor;

    @FXML
    private Label lbPlaca;

    @FXML
    private Label lbNChassi;

    @FXML
    private Label lbKmAtual;

    @FXML
    private Label lbTipoCombustivel;

    @FXML
    private Label LalbTorquel;

    @FXML
    private Label lbCategoria;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;
    
    
    private Fachada fachada;
    private ArrayList<Veiculo> veiculos;
    private ObservableList<Veiculo> obsVeiculos;
    
    @FXML
    void acaoBtns(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        
        carregaVeiculos();
    }

    private void carregaVeiculos() {
        
        veiculos = fachada.listarTodosVeiculo();
        
        colunaAnoModelo.setCellValueFactory(new PropertyValueFactory<>("anoDoModelo"));
        colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        
        obsVeiculos = FXCollections.observableArrayList(veiculos);
        
        tableView.setItems(obsVeiculos);
    }

}
