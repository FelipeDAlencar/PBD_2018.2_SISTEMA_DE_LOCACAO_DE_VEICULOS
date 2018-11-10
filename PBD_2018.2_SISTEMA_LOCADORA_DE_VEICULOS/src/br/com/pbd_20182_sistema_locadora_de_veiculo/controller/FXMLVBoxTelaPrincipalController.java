/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FXMLVBoxTelaPrincipalController implements  Initializable{

    @FXML
    private MenuItem menuItemClientes;

    @FXML
    private MenuItem menuItemFuncionarios;

    @FXML
    private MenuItem menuItemVeiculos;

    @FXML
    private MenuItem menuItemReservas;

    @FXML
    private MenuItem menuItemLocacoes;

    @FXML
    private MenuItem menuItemClientesFisicos;

    @FXML
    private MenuItem menuItemPessoasJuridicas;

    @FXML
    private MenuItem menuItemReservasPorPeriodo;

    @FXML
    private MenuItem menuItemLocacoesPorPeriodo;

    @FXML
    private MenuItem menuItemLocacoesPorCliente;

    @FXML
    private MenuItem MenuItemLocacoesPorMotorista;

    @FXML
    private MenuItem MenuItemPorCategoriaMaisReservadas;

    @FXML
    public static Menu menuAdministracao;

    @FXML
    private AnchorPane AnchorPaneContent;

    @FXML
    void acaosMenuItensCadastros(ActionEvent event) {
        Pane pane;
         try{
             if(event.getSource() == menuItemClientes){
                pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCliente.fxml"));
                AnchorPaneContent.getChildren().setAll(pane);
             }
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    @FXML
    void acoesMenuItensRelatorios(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
