/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Label lbTorqueDoMotor;

    @FXML
    private Label lbCategoria;

    @FXML
    private JFXCheckBox cbDisponivel;

    @FXML
    private JFXTextField tfPesquisa;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;

    private Fachada fachada;
    private ArrayList<Veiculo> veiculos;
    private ObservableList<Veiculo> obsVeiculos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));

        cbDisponivel.setDisable(true);

        if (FXMLLoginController.pessoa instanceof PessoaFisica || FXMLLoginController.pessoa instanceof PessoaJuridica) {
            btnEditar.setDisable(true);
            btnExcluir.setDisable(true);
            btnInserir.setDisable(true);
        }
    }

    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {
        if (event.getSource() == btnInserir) {
            Veiculo veiculo = new Veiculo();

            boolean sucesso = exibirTelaDeCadastro(veiculo, event);

            if (sucesso) {

                fachada.salvarVeiculo(veiculo);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserção de Veículos", ""
                        + "Inserção realidade com sucesso!");

                carregaVeiculos(fachada.listarTodosVeiculo());

            }

        }

        if (event.getSource() == btnEditar) {
            Veiculo veiculo = tableView.getSelectionModel().getSelectedItem();

            if (veiculo != null) {
                boolean sucesso = exibirTelaDeCadastro(veiculo, event);

                if (sucesso) {

                    fachada.salvarVeiculo(veiculo);

                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserção de Veículos", ""
                            + "Inserção realizada com sucesso!");

                    carregaVeiculos(fachada.listarTodosVeiculo());

                }

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", ""
                        + "Selecione o veículo na tabela para edição!");
            }
        }

        if (event.getSource() == btnExcluir) {
            Veiculo veiculo = tableView.getSelectionModel().getSelectedItem();

            if (veiculo != null) {

                veiculo.setAtivo(false);
                fachada.salvarVeiculo(veiculo);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Exclusão de Veículos", ""
                        + "Exclusão realizada com sucesso!");

                carregaVeiculos(fachada.listarTodosVeiculo());

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", ""
                        + "Selecione o veículo na tabela para exclusão!");
            }
        }

        if (event.getSource() == btnPesquisar) {

            if (tfPesquisa.getText().length() != 0) {
                carregaVeiculos(fachada.buscarPorBuscaVeiculo(tfPesquisa.getText()));
                System.err.println(fachada.buscarPorBuscaVeiculo(tfPesquisa.getText()));
            } else {
                carregaVeiculos(fachada.listarTodosVeiculo());
            }
        }
    }

    private void carregaVeiculos(ArrayList<Veiculo> veiculos) throws DAOException {

        colunaAnoModelo.setCellValueFactory(new PropertyValueFactory<>("anoDoModelo"));
        colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        obsVeiculos = FXCollections.observableArrayList(veiculos);

        tableView.setItems(obsVeiculos);
    }

    private void selecionouDaTabela(Veiculo veiculo) {

        if (veiculo != null) {

            lbAnoDeFabricacao.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
            lbAnoModelo.setText(String.valueOf(veiculo.getAnoDoModelo()));
            lbCategoria.setText(veiculo.getCategoria().toString());
            lbCor.setText(veiculo.getCor());
            lbFabricante.setText(veiculo.getFabricante());
            lbKmAtual.setText(String.valueOf(veiculo.getQuilometragemAtual()));
            lbModelo.setText(veiculo.getModelo());
            lbNChassi.setText(String.valueOf(veiculo.getNumeroChassi()));
            lbPlaca.setText(veiculo.getPlaca());
            lbTipoCombustivel.setText(veiculo.getTipoDeCombustivel());
            lbTorqueDoMotor.setText(String.valueOf(veiculo.getTorqueDoMotor()));
            cbDisponivel.setSelected(veiculo.isDisponivel());
        }

    }

    private boolean exibirTelaDeCadastro(Veiculo veiculo, Event event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroVeiculosDialogController.class.getResource("/br/com"
                    + "/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroVeiculosDialog.fxml"));
            Pane pane = loader.load();

            FXMLAnchorPaneCadastroVeiculosDialogController controller = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            controller.setStage(stage);
            controller.setVeiculo(veiculo);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();

            return controller.isSucesso();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public TableView<Veiculo> getTableView() {
        return tableView;
    }

    public TableColumn<Veiculo, String> getColunaModelo() {
        return colunaModelo;
    }

    public TableColumn<Veiculo, String> getColunaCor() {
        return colunaCor;
    }

    public TableColumn<Veiculo, Integer> getColunaAnoModelo() {
        return colunaAnoModelo;
    }

    public Button getBtnPesquisar() {
        return btnPesquisar;
    }

    public Label getLbModelo() {
        return lbModelo;
    }

    public Label getLbFabricante() {
        return lbFabricante;
    }

    public Label getLbAnoModelo() {
        return lbAnoModelo;
    }

    public Label getLbAnoDeFabricacao() {
        return lbAnoDeFabricacao;
    }

    public Label getLbCor() {
        return lbCor;
    }

    public Label getLbPlaca() {
        return lbPlaca;
    }

    public Label getLbNChassi() {
        return lbNChassi;
    }

    public Label getLbKmAtual() {
        return lbKmAtual;
    }

    public Label getLbTipoCombustivel() {
        return lbTipoCombustivel;
    }

    public Label getLbTorqueDoMotor() {
        return lbTorqueDoMotor;
    }

    public Label getLbCategoria() {
        return lbCategoria;
    }

    public TextField getTfPesquisa() {
        return tfPesquisa;
    }

    public CheckBox getCbDisponivel() {
        return cbDisponivel;
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

    public Fachada getFachada() {
        return fachada;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public ObservableList<Veiculo> getObsVeiculos() {
        return obsVeiculos;
    }

}
