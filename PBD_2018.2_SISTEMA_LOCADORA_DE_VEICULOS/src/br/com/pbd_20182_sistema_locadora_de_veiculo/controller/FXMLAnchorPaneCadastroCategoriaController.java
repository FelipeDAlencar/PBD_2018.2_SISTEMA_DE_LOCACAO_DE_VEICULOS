/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
    private AnchorPane BtnEditar;

    @FXML
    private JFXTextField tfBusca;

    @FXML
    private JFXButton btnBusca;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbValor;

    @FXML
    private Label lbNumeroDePortas;

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

    @FXML
    private Label lbArCondicionado;

    @FXML
    private JFXButton btInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    private Fachada fachada;
    private ArrayList<Categoria> categorias;
    private ObservableList<Categoria> obsCategorias;
    private FXMLAnchorPaneCadastroCategoriaDialogController controllerDialog;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
    }

    @FXML
    void acaoDeBtns(ActionEvent event) throws BusinessExpection, DAOException {
        if (event.getSource() == btInserir) {

            Categoria categoria = null;

            boolean confirmar = exibirTelaDeCadastroDeCategorias(categoria, event);

            if (confirmar) {
                Categoria categoriaRetorno = controllerDialog.getCategoria();

                if (categoriaRetorno instanceof Categoria) {
                    fachada.salvarCategoria(categoriaRetorno);
                } else if (categoriaRetorno instanceof CaminhonetaDePassageiros) {
                    fachada.salvarCaminhonetaDePassageiros(((CaminhonetaDePassageiros) categoriaRetorno));
                } else if (categoriaRetorno instanceof CaminhonetaDeCarga) {
                    fachada.salvarCaminhonetaDeCarga((CaminhonetaDeCarga) categoriaRetorno);
                }

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.CONFIRMATION, "Cadastro de categoria",
                        "Sucesso!", "Categoria cadastrada com sucesso!");

            }
        }

        if (event.getSource() == btnExcluir) {
            Categoria categoria = tableView.getSelectionModel().getSelectedItem();

            if (categoria != null) {
                categoria.setAtivo(false);

                if (categoria instanceof Categoria) {
                    fachada.salvarCategoria(categoria);
                } else if (categoria instanceof CaminhonetaDePassageiros) {

                    fachada.salvarCaminhonetaDePassageiros((CaminhonetaDePassageiros) categoria);

                } else if (categoria instanceof CaminhonetaDeCarga) {
                    fachada.salvarCaminhonetaDeCarga((CaminhonetaDeCarga) categoria);
                }

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Exclusão de categoria", ""
                        + "Exclusão realizada com sucesso!");

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atenção", "Eclusão de categoria", ""
                        + "Selecione a categoria para exclusão");
            }

        }

        if (event.getSource() == btnEditar) {

            Categoria categoria = tableView.getSelectionModel().getSelectedItem();

            if (categoria != null) {

                boolean sucesso = exibirTelaDeCadastroDeCategorias(categoria, event);

                if (sucesso) {

                    categoria = controllerDialog.getCategoria();

                    if (categoria instanceof Categoria) {
                        fachada.salvarCategoria(categoria);
                    } else if (categoria instanceof CaminhonetaDePassageiros) {

                        fachada.salvarCaminhonetaDePassageiros((CaminhonetaDePassageiros) categoria);

                    } else if (categoria instanceof CaminhonetaDeCarga) {
                        fachada.salvarCaminhonetaDeCarga((CaminhonetaDeCarga) categoria);
                    }

                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Edição de categoria", ""
                            + "Edição realizada com sucesso!");

                }

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atenção", "Edição de categoria", ""
                        + "Selecione a categoria para edição");
            }

        }

        if (event.getSource() == btnBusca) {

            if (tfBusca.getText().length() == 0) {
                carregarTabela(fachada.listarTodosCategoria());
            } else {

                carregarTabela(fachada.buscarPorBuscaCategoria(tfBusca.getText()));

            }

        }
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

    private void carregarTabela(ArrayList<Categoria> categorias) throws DAOException {

        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricacao"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        obsCategorias = FXCollections.observableArrayList(categorias);

        tableView.setItems(obsCategorias);
    }

    public boolean exibirTelaDeCadastroDeCategorias(Categoria categoria, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroCategoriaDialogController.class.
                    getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCategoriaDialog.fxml"));
            Pane pane = loader.load();

            controllerDialog = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            controllerDialog.setStage(stage);
            controllerDialog.setCategoria(categoria);

            stage.showAndWait();

            return controllerDialog.isConfirmar();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

    public TableView<Categoria> getTableView() {
        return tableView;
    }

    public TableColumn<Categoria, String> getColunaNome() {
        return colunaNome;
    }

    public TableColumn<Categoria, String> getColunaDescricao() {
        return colunaDescricao;
    }

    public TableColumn<Categoria, Double> getColunaValor() {
        return colunaValor;
    }

    public TextField getTfBusca() {
        return tfBusca;
    }

    public Button getBtnBusca() {
        return btnBusca;
    }

    public Button getBtInserir() {
        return btInserir;
    }

    public Button getBtnEditar() {
        return btnEditar;
    }

    public Button getBtnExcluir() {
        return btnExcluir;
    }

    public Label getLbNome() {
        return lbNome;
    }

    public Label getLbDescricao() {
        return lbDescricao;
    }

    public Label getLbValor() {
        return lbValor;
    }

    public Label getLbNumeroDePortas() {
        return lbNumeroDePortas;
    }

    public Label getLbArCondicionado() {
        return lbArCondicionado;
    }

    public Label getLbMP3() {
        return lbMP3;
    }

    public Label getLbNumeroDePassageiros() {
        return lbNumeroDePassageiros;
    }

    public Label getLbDVD() {
        return lbDVD;
    }

    public Label getLbDirecaoHidraulica() {
        return lbDirecaoHidraulica;
    }

    public Label getLbRadio() {
        return lbRadio;
    }

    public Label getLbTipoCambio() {
        return lbTipoCambio;
    }

    public Label getLbCameraDeRe() {
        return lbCameraDeRe;
    }

    public Fachada getFachada() {
        return fachada;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public ObservableList<Categoria> getObsCategorias() {
        return obsCategorias;
    }

    public FXMLAnchorPaneCadastroCategoriaDialogController getControllerDialog() {
        return controllerDialog;
    }

}
