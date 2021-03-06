/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TableColumn<ReservaPessoasCategorias, Date> colunaData;

    @FXML
    private JFXTextField tfBusca;

    @FXML
    private JFXButton btnBusca;

    @FXML
    private Label lbCliente;

    @FXML
    private Label lbCategoria;

    @FXML
    private Label lbData;

    @FXML
    private Label lbValorPrevisto;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    private ArrayList<ReservaPessoasCategorias> reservaPessoasCategoriases;
    private ObservableList<ReservaPessoasCategorias> obsReservaPessoasCategoriases;

    private Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = new Fachada();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
        
        
        
         if (FXMLLoginController.usuario instanceof PessoaFisica || FXMLLoginController.usuario instanceof PessoaJuridica) {
           
           btnEditar.setDisable(true);
           btnExcluir.setDisable(true);
            
        }

    }

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection, DAOException {
        if (event.getSource() == btnInserir) {
            ReservaPessoasCategorias reservaPessoasCategorias = new ReservaPessoasCategorias();
            boolean confirmacao = exibirTelaDecadastro(reservaPessoasCategorias, event);

            if (confirmacao) {

                fachada.salvarReservaPessoasCategorias(reservaPessoasCategorias);
                carregarReservas(fachada.listarTodosReservaPessoasCategorias());
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Inserção de Reserva", "Sucesso",
                        "Inserção realizada com sucesso!");

            }
        }

        if (event.getSource() == btnEditar) {

            ReservaPessoasCategorias reservaPessoasCategorias = tableView.getSelectionModel().
                    getSelectedItem();
            if (reservaPessoasCategorias != null) {
                boolean confirmacao = exibirTelaDecadastro(reservaPessoasCategorias, event);

                if (confirmacao) {

                    fachada.salvarReservaPessoasCategorias(reservaPessoasCategorias);

                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Edição de Reserva", "Sucesso",
                            "Edição realizado com sucesso.");

                }
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atençãoo", "Atenção", ""
                        + "Selecione a locação na tabela para edição.");
            }
        }

        if (event.getSource() == btnExcluir) {

            ReservaPessoasCategorias reservaPessoasCategorias = tableView.getSelectionModel().
                    getSelectedItem();
            if (reservaPessoasCategorias != null) {

                reservaPessoasCategorias.setAtivo(false);
                fachada.salvarReservaPessoasCategorias(reservaPessoasCategorias);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Exclusão de Reserva", "Sucesso",
                        "Edição realizado com sucesso.");
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atençãoo", "Atenção", ""
                        + "Selecione a locação na tabela para exclusão.");
            }

        }

        if (event.getSource() == btnBusca) {

            if (tfBusca.getText().length() == 0) {
                carregarReservas(fachada.listarTodosReservaPessoasCategorias());
            } else {
                carregarReservas(fachada.buscarPorBuscaReservasPessoaCategoria(tfBusca.getText()));
            }

        }

    }

    public void carregarReservas(ArrayList<ReservaPessoasCategorias> reservas) {

        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("pessoa"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataHora"));

        obsReservaPessoasCategoriases = FXCollections.observableArrayList(reservas);

        tableView.setItems(obsReservaPessoasCategoriases);

    }

    private void selecionouDaTabela(ReservaPessoasCategorias reservaPessoasCategorias) {

        if (reservaPessoasCategorias != null) {
            lbCategoria.setText(reservaPessoasCategorias.getCategoria().toString());
            lbCliente.setText(reservaPessoasCategorias.getPessoa().toString());
            lbData.setText(Util.formatarData(reservaPessoasCategorias.getDataHora()));
            lbValorPrevisto.setText(String.valueOf(reservaPessoasCategorias.getValorPrevisto()));
        }

    }

    private boolean exibirTelaDecadastro(ReservaPessoasCategorias reservaPessoasCategorias, Event event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader
                    .setLocation(FXMLAnchorPaneCadastroReservaDialogController.class
                            .
                            getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroReservaDialog.fxml"));
            Pane pane = loader.load();

            FXMLAnchorPaneCadastroReservaDialogController controller = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            controller.setReservaPessoasCategorias(reservaPessoasCategorias);
            controller.setStage(stage);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();

            return controller.isConfirmado();

        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroReservaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Button getBtnBuscar() {
        return btnBusca;
    }

    public Button getBtnInserir() {
        return btnInserir;
    }

    public Button getBtnEditar() {
        return btnEditar;
    }

    public Button getBtnExcluir() {
        return btnExcluir;
    }

    public TableView<ReservaPessoasCategorias> getTableView() {
        return tableView;
    }

    public TableColumn<ReservaPessoasCategorias, Pessoa> getColunaCliente() {
        return colunaCliente;
    }

    public TableColumn<ReservaPessoasCategorias, Categoria> getColunaCategoria() {
        return colunaCategoria;
    }

    public TableColumn<ReservaPessoasCategorias, Date> getColunaData() {
        return colunaData;
    }

    public TextField getTfBusca() {
        return tfBusca;
    }

    public Label getLbCliente() {
        return lbCliente;
    }

    public Label getLbCategoria() {
        return lbCategoria;
    }

    public Label getLbData() {
        return lbData;
    }

    public Label getLbValorPrevisto() {
        return lbValorPrevisto;
    }

    public ArrayList<ReservaPessoasCategorias> getReservaPessoasCategoriases() {
        return reservaPessoasCategoriases;
    }

    public ObservableList<ReservaPessoasCategorias> getObsReservaPessoasCategoriases() {
        return obsReservaPessoasCategoriases;
    }

    public Fachada getFachada() {
        return fachada;
    }

}
