/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
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
public class FXMLAnchorPaneContaAPagarController implements Initializable {

    @FXML
    private TableView<ContaAPagar> tableView;

    @FXML
    private TableColumn<ContaAPagar, String> colunaDescricao;

    @FXML
    private TableColumn<ContaAPagar, Double> colunaValor;

    @FXML
    private TableColumn<ContaAPagar, Boolean> colunaSituacao;

    @FXML
    private Label lbDrescricao;

    @FXML
    private Label lbDataVencimento;

    @FXML
    private Label lbValor;

    @FXML
    private Label lbSituacao;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXTextField tfBuscar;

    @FXML
    private JFXButton btnBuscar;

    private Fachada fachada;

    private ObservableList<ContaAPagar> obsContasAPagar;

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
                carregarTabela(fachada.listarTodasContasAPagar());
            } else {

            }

        }

        if (e.getSource() == btnInserir) {
            ContaAPagar contaAPagar = new ContaAPagar();
            boolean sucesso = exibirTelaDeCadastro(contaAPagar, e);

            if (sucesso) {
                fachada.salvarContaAPagar(contaAPagar);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir conta a pagar", "Conta a pagarw "
                        + "Inserida com Sucesso.");
            }
        }

        if (e.getSource() == btnEditar) {

            ContaAPagar contaAPagar = tableView.getSelectionModel().getSelectedItem();

            if (contaAPagar != null) {

                boolean sucesso = exibirTelaDeCadastro(contaAPagar, e);

                if (sucesso) {

                    fachada.salvarContaAPagar(contaAPagar);

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
            ContaAPagar contaAPagar = tableView.getSelectionModel().getSelectedItem();

            if (contaAPagar != null) {

                fachada.excluirContaAPagar(contaAPagar);
               
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Exclusão realizada "
                        + "com sucesso");

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", "Por favor, selecione "
                        + "a conta a pagar que deseja excluir");
            }

        }
         carregarTabela(fachada.listarTodasContasAPagar());

    }

    public void carregarTabela(ArrayList<ContaAPagar> contaAPagars) {
        
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("pago"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        obsContasAPagar = FXCollections.observableArrayList(contaAPagars);

        tableView.setItems(obsContasAPagar);

    }

    private void selecionouDaTabea(ContaAPagar contaAPagar) {

        if (contaAPagar != null) {
            lbDataVencimento.setText(Util.formatarData(contaAPagar.getDataVencimento()));
            lbDrescricao.setText(contaAPagar.getDescricao());
            lbSituacao.setText(String.valueOf(contaAPagar.isPago()));
            lbValor.setText(String.valueOf(contaAPagar.getValor()));
        }
    }

    private boolean exibirTelaDeCadastro(ContaAPagar contaAPagar, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneContaAPagarDialogController.class.getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneContaAPagarDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FXMLAnchorPaneContaAPagarDialogController controller = loader.getController();

            stage.initOwner(((Node) e.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            controller.setStage(stage);
            controller.setContaAPagar(contaAPagar);
            stage.showAndWait();

            return controller.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

}
