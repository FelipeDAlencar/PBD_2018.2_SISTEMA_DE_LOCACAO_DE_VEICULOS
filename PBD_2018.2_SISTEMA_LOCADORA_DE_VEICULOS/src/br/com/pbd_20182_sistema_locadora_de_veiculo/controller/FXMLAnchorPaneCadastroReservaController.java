/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOReservaPessoaCategoria;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroReservaController implements Initializable {

    @FXML
    private TableView<ReservaPessoasCategorias> tableView;

    @FXML
    private TableColumn<ReservaPessoasCategorias, Pessoa> colunaCliente;

    @FXML
    private TableColumn<ReservaPessoasCategorias, Categoria> colunaCategoria;

    @FXML
    private TableColumn<ReservaPessoasCategorias, Calendar> colunaData;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lbCliente;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbData;

    @FXML
    private Label lbValorPrevisto;
    
    
    private ArrayList<ReservaPessoasCategorias> reservaPessoasCategoriases;
    private ObservableList<ReservaPessoasCategorias> obsReservaPessoasCategoriases;
    private DAOReservaPessoaCategoria dAOReservaPessoaCategoria;
    
    @FXML
    void acaoBtns(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dAOReservaPessoaCategoria = new DAOReservaPessoaCategoria();
        
        carregarReservas();
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
        
        
    }
    
    
    public void carregarReservas(){
        
        reservaPessoasCategoriases = dAOReservaPessoaCategoria.findAll();
        
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data_hora"));
        
        
        obsReservaPessoasCategoriases = FXCollections.observableArrayList(reservaPessoasCategoriases);
        
        tableView.setItems(obsReservaPessoasCategoriases);
        
        
    }

    private void selecionouDaTabela(ReservaPessoasCategorias reservaPessoasCategorias) {
        
        if(reservaPessoasCategorias != null){
            lbCategoria.setText(reservaPessoasCategorias.getCategoria().toString());
            lbCliente.setText(reservaPessoasCategorias.getPessoa().toString());
            lbData.setText(Util.formatarData(reservaPessoasCategorias.getDataHora()));
            lbValorPrevisto.setText(String.valueOf(reservaPessoasCategorias.getValorPrevisto()));          
        }

    }

}
