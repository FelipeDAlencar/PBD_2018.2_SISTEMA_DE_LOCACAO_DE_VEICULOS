/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneContasAReceberController implements Initializable {

    @FXML
    private TableView<ContaAReceber> tableView;

    @FXML
    private TableColumn<ContaAReceber, String> colunaDescricao;

    @FXML
    private TableColumn<ContaAReceber, Double> colunaValor;

    @FXML
    private TableColumn<ContaAReceber, Date> colunaDataDeRecebimento;

    @FXML
    private TableColumn<ContaAReceber, Boolean> colunaPago;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbDataDeRecimento;

    @FXML
    private Label lbValor;

    @FXML
    private JFXCheckBox cbPago;

    @FXML
    private JFXButton btnBuscar;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXTextField tfBuscar;

    private Fachada fachada;
    private ObservableList<ContaAReceber> obsContasAReceber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabea(newValue));
    }
    
    @FXML
    void acaoBtns(ActionEvent e) throws DAOException {

        if (e.getSource() == btnBuscar) {

            if (tfBuscar.getText().length() == 0) {
                carregarTabela(fachada.listarTodasContasAReceber());
            } else {

            }

        }

        if (e.getSource() == btnInserir) {
            ContaAReceber contaAReceber = new ContaAReceber();
            boolean sucesso = exibirTelaDeCadastro(contaAReceber, e);

            if (sucesso) {
                fachada.salvarContaAReceber(contaAReceber);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir conta a pagar", "Conta a pagarw "
                        + "Inserida com Sucesso.");
            }
        }

        if (e.getSource() == btnEditar) {

            ContaAReceber contaAReceber = tableView.getSelectionModel().getSelectedItem();

            if (contaAReceber != null) {

                boolean sucesso = exibirTelaDeCadastro(contaAReceber, e);

                if (sucesso) {

                    fachada.salvarContaAReceber(contaAReceber);

                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Edição realizada "
                            + "com sucesso");

                }

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", "Por favor, selecione "
                        + "a conta a pagar que deseja editar");
            }

        }

        if (e.getSource() == btnExcluir) {
            ContaAReceber contaAReceber = tableView.getSelectionModel().getSelectedItem();

            if (contaAReceber != null) {

                fachada.excluirContaAReceber(contaAReceber);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Exclusão realizada "
                        + "com sucesso");

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", "Por favor, selecione "
                        + "a conta a pagar que deseja excluir");
            }

        }
        carregarTabela(fachada.listarTodasContasAReceber());

    }

    public void carregarTabela(ArrayList<ContaAReceber> contaAReceber) {

        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaDataDeRecebimento.setCellValueFactory(new PropertyValueFactory<>("dataRecebimento"));
        colunaPago.setCellValueFactory(new PropertyValueFactory<>("pago"));

        obsContasAReceber = FXCollections.observableArrayList(contaAReceber);

        tableView.setItems(obsContasAReceber);

    }

    private void selecionouDaTabea(ContaAReceber contaAReceber) {

        if (contaAReceber != null) {
            lbDataDeRecimento.setText(Util.formatarData(contaAReceber.getDataRecebimento()));
            lbDescricao.setText(contaAReceber.getDescricao());
            cbPago.setSelected(contaAReceber.isPago());
            lbValor.setText(String.valueOf(contaAReceber.getValor()));
        }
    }

    private boolean exibirTelaDeCadastro(ContaAReceber contaAReceber, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneContasAReceberController.class.getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneContaAReceberDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FXMLAnchorPaneContaAReceberDialogController controller = loader.getController();

            stage.initOwner(((Node) e.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            controller.setStage(stage);
            controller.setContaAReceber(contaAReceber);
            stage.showAndWait();

            return controller.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

}
