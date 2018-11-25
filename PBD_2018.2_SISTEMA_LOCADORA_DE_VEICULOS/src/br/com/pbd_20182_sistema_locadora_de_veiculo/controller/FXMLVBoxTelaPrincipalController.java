/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLVBoxTelaPrincipalController implements Initializable {

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
    private MenuItem menuItemCategoria;

    @FXML
    private MenuItem menuItemSair;

    @FXML
    public Menu menuAdministracao;

    @FXML
    private Menu menuRelatorios;

    @FXML
    private AnchorPane AnchorPaneContent;

    @FXML
    private MenuItem menuItemConfiguracoes;

    Stage stage;

    @FXML
    void acaosMenuItensCadastros(ActionEvent event) throws IOException {
        Pane pane;

        if (event.getSource() == menuItemClientes) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCliente.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }

        if (event.getSource() == menuItemReservas) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroReserva.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);

        }

        if (event.getSource() == menuItemCategoria) {

            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCategoria.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);

        }

        if (event.getSource() == menuItemVeiculos) {

            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroVeiculos.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
    }

    @FXML
    void acaoMenuItemConfiguracoes(ActionEvent event) {
        Pane pane;
        try {
            if (event.getSource() == menuItemConfiguracoes) {
                pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneGeral.fxml"));
                AnchorPaneContent.getChildren().setAll(pane);
            }

            if (event.getSource() == menuItemSair) {
                stage.close();

                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLLogin.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {
                    @Override
                    public Object call(Class<?> param) {
                        return new FXMLLoginController(primaryStage);
                    }
                });
                Pane root = loader.load();

                //Pane root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVboxTelaPrincipal.fxml"));
                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setTitle("LOCADORA DE VEÍCULOS PAJEÚ");
            }
        } catch (Exception e) {
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

    public MenuItem getMenuItemClientes() {
        return menuItemClientes;
    }

    public MenuItem getMenuItemFuncionarios() {
        return menuItemFuncionarios;
    }

    public MenuItem getMenuItemVeiculos() {
        return menuItemVeiculos;
    }

    public MenuItem getMenuItemReservas() {
        return menuItemReservas;
    }

    public MenuItem getMenuItemLocacoes() {
        return menuItemLocacoes;
    }

    public MenuItem getMenuItemClientesFisicos() {
        return menuItemClientesFisicos;
    }

    public MenuItem getMenuItemPessoasJuridicas() {
        return menuItemPessoasJuridicas;
    }

    public MenuItem getMenuItemReservasPorPeriodo() {
        return menuItemReservasPorPeriodo;
    }

    public MenuItem getMenuItemLocacoesPorPeriodo() {
        return menuItemLocacoesPorPeriodo;
    }

    public MenuItem getMenuItemLocacoesPorCliente() {
        return menuItemLocacoesPorCliente;
    }

    public MenuItem getMenuItemLocacoesPorMotorista() {
        return MenuItemLocacoesPorMotorista;
    }

    public MenuItem getMenuItemPorCategoriaMaisReservadas() {
        return MenuItemPorCategoriaMaisReservadas;
    }

    public Menu getMenuAdministracao() {
        return menuAdministracao;
    }

    public AnchorPane getAnchorPaneContent() {
        return AnchorPaneContent;
    }

    public Menu getMenuRelatorios() {
        return menuRelatorios;
    }

    public MenuItem getMenuItemCategoria() {
        return menuItemCategoria;
    }

    public void setMenuItemCategoria(MenuItem menuItemCategoria) {
        this.menuItemCategoria = menuItemCategoria;
    }

    public MenuItem getMenuItemSair() {
        return menuItemSair;
    }

    public void setMenuItemSair(MenuItem menuItemSair) {
        this.menuItemSair = menuItemSair;
    }

    public MenuItem getMenuItemConfiguracoes() {
        return menuItemConfiguracoes;
    }

    public void setMenuItemConfiguracoes(MenuItem menuItemConfiguracoes) {
        this.menuItemConfiguracoes = menuItemConfiguracoes;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
