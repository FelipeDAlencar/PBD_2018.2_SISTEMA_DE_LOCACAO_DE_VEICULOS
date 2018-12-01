/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.sun.deploy.util.FXLoader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroFilialController implements Initializable {

    @FXML
    private TableView<Filial> tableView;

    @FXML
    private TableColumn<Filial, String> colunaNomeFilial;

    @FXML
    private TableColumn<Filial, String> colunaRua;

    @FXML
    private TableColumn<Filial, String> colunaUF;

    @FXML
    private Label lbNomeFilial;

    @FXML
    private Label lbCidade;

    @FXML
    private Label lbRua;

    @FXML
    private Label lbBairro;

    @FXML
    private Label lbNumero;

    @FXML
    private Label lbUF;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnPesquisar;

    private Fachada fachada;
    private ArrayList<Filial> filiais;
    private ObservableList<Filial> obsFiliais;

    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {

        if (event.getSource() == btnInserir) {
            Filial filial = new Filial();
            boolean sucesso = exibirTelaDeCadastro(filial);

            if (sucesso) {

                fachada.salvarFilial(filial);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir Filial", "Filial "
                        + "Inserida com Sucesso.");

                carregarFiliais();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        carregarFiliais();

        colunaNomeFilial.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colunaUF.setCellValueFactory(new PropertyValueFactory<>("uf"));

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabea(newValue));

    }

    private void carregarFiliais() {
        filiais = fachada.listarTodosFilial();

        obsFiliais = FXCollections.observableArrayList(filiais);

        tableView.setItems(obsFiliais);
    }

    private void selecionouDaTabea(Filial filial) {

        if (filial != null) {
            lbBairro.setText(filial.getEndereco().getBairro());
            lbCidade.setText(filial.getEndereco().getCidade());
            lbNomeFilial.setText(filial.getNome());
            lbNumero.setText(String.valueOf(filial.getEndereco().getNumero()));
            lbUF.setText(filial.getEndereco().getUf());
            lbRua.setText(filial.getEndereco().getRua());
        }
    }

    private boolean exibirTelaDeCadastro(Filial filial) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroFilialController.class.getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroFilialDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FXMLAnchorPaneCadastroFilialDialogController controller = loader.getController();
            controller.setStage(stage);
            controller.setFilial(filial);
            
            
            stage.showAndWait();

            return controller.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

}
