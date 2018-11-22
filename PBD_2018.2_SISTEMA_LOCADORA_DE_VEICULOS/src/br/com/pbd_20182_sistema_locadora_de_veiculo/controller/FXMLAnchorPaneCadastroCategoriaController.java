/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLAnchorPaneCadastroCategoriaController implements Initializable {

    @FXML
    private TableView<Categoria> tableView;

    @FXML
    private TableColumn<Categoria, String> colunaNome;

    @FXML
    private TableColumn<Categoria, String> colunaDescricao;

    @FXML
    private TableColumn<Categoria, Double> colunaValor;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btnBusca;

    @FXML
    private Button btInserir;

    @FXML
    private Button BtnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbValor;

    @FXML
    private Label lbNumeroDePortas;

    @FXML
    private Label lbArCondicionado;

    @FXML
    private Label lbMP3;

    @FXML
    private Label lbNumeroDePassageiros;

    @FXML
    private Label lbDVD;

    @FXML
    private Label lbDirecaoHidraulica;

    @FXML
    private Label lbRadio;

    @FXML
    private Label lbTipoCambio;

    @FXML
    private Label lbCameraDeRe;

    private DAOCategoria dAOCategoria;
    private ArrayList<Categoria> categorias;
    private ObservableList<Categoria> obsCategorias;

    @FXML
    void acaoDeBtns(ActionEvent event) {
        if (event.getSource() == btInserir) {
            exibirTelaDeSelecaoDeCategorias();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dAOCategoria = new DAOCategoria();

        carregarTabela();
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
    }

    private void selecionouDaTabela(Categoria categoria) {

        if (categoria != null) {
            lbArCondicionado.setText(String.valueOf(categoria.isArCondicionado()));
            lbCameraDeRe.setText(String.valueOf(categoria.isCameraDeRe()));
            lbDVD.setText(String.valueOf(categoria.isDvd()));
            lbDescricao.setText(categoria.getDescricacao());
            lbDirecaoHidraulica.setText(String.valueOf(categoria.isDirecaoHidraulica()));
            lbMP3.setText(String.valueOf(categoria.isMp3()));
            lbNome.setText(categoria.getNome());
            lbRadio.setText(String.valueOf(categoria.isRadio()));
            lbTipoCambio.setText(String.valueOf(categoria.isTipoDeCambio()));
            lbValor.setText(String.valueOf(categoria.getValor()));
            lbNumeroDePassageiros.setText(String.valueOf(categoria.getNumeroDePassageiros()));
            lbNumeroDePortas.setText(String.valueOf(categoria.getNumeroDePortas()));

        }
    }

    private void carregarTabela() {

        categorias = dAOCategoria.findAll();

        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricacao"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        obsCategorias = FXCollections.observableArrayList(categorias);

        tableView.setItems(obsCategorias);
    }

    public void exibirTelaDeSelecaoDeCategorias() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroReservaDialogController.class.
                    getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneSelecaoCategoria.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
