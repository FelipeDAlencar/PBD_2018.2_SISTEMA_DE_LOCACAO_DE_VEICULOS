/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
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
public class FXMLAnchorPaneCadastroLocacaoController implements Initializable {

    @FXML
    private TableView<Locacao> tableView;

    @FXML
    private TableColumn<Locacao, PessoaFisica> colunaMotorista;

    @FXML
    private TableColumn<Locacao, Veiculo> colunaVeiculo;

    @FXML
    private TableColumn<Locacao, Double> colunaValor;

    @FXML
    private Label lbCliente;

    @FXML
    private Label lbMotorista;

    @FXML
    private Label lbVeiculo;

    @FXML
    private Label lbPontoDeEntrega;
    
    @FXML
    private Label lbKmInicial;

    @FXML
    private Label lbKmFinal;

    @FXML
    private Label lbMetadePrimeiraDiaria;

    @FXML
    private Label lbTaxaCombustivel;

    @FXML
    private Label lbTaxaIgienizacao;

    @FXML
    private Label lbDataIda;

    @FXML
    private Label lbDataVolta;

    @FXML
    private Label lbValor;

     @FXML
    private JFXCheckBox cbKmLivre;

    @FXML
    private JFXCheckBox cbFinalizada;

    @FXML
    private JFXTextField tfPesquisar;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnExcluir;


    private Fachada fachada;
    private ArrayList<Locacao> locacaos;
    private ObservableList<Locacao> obsLocacoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        cbKmLivre.setDisable(true);
        cbFinalizada.setDisable(true);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
    }

    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {

        if (event.getSource() == btnInserir) {

            Locacao locacao = new Locacao();
            boolean sucesso = exibirTelaDeCadastro(locacao, event);

            if (sucesso) {
                fachada.salvarLocacao(locacao);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir locaçao", "Inserção"
                        + " da locação foi efetuada com sucesso.");

            }
        }

        if (event.getSource() == btnEditar) {

            Locacao locacao = tableView.getSelectionModel().getSelectedItem();

            if (locacao != null) {
                boolean sucesso = exibirTelaDeCadastro(locacao, event);

                if (sucesso) {

                    fachada.salvarLocacao(locacao);
                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir locaçao", "Alteração"
                            + " da locação foi efetuada com sucesso!");
                    carregarLocacoes(fachada.listarTodosLocacao());
                }
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atençãoo", "Atenção", ""
                        + "Selecione a locação na tabela para edição.");

            }

        }

        if (event.getSource() == btnExcluir) {
            Locacao locacao = tableView.getSelectionModel().getSelectedItem();

            if (locacao != null) {

                locacao.setAtivo(false);
                fachada.salvarLocacao(locacao);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Excluir locaçao", "Exclusão "
                        + "da locação foi efetuada com sucesso!.");
                carregarLocacoes(fachada.listarTodosLocacao());

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atençãoo", "Atenção", ""
                        + "Selecione a locação na tabela para exclusão.");

            }

        }

        if (event.getSource() == btnPesquisar) {
            if (tfPesquisar.getLength() == 0) {
                carregarLocacoes(fachada.listarTodosLocacao());
            }
        }

    }

    private void carregarLocacoes(ArrayList<Locacao> locacaos) throws DAOException {

        colunaMotorista.setCellValueFactory(new PropertyValueFactory<>("motorista"));
        colunaVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        obsLocacoes = FXCollections.observableArrayList(locacaos);

        tableView.setItems(obsLocacoes);
    }

    private void selecionouDaTabela(Locacao locacao) {

        if (locacao != null) {

            lbDataIda.setText(Util.formatarData(locacao.getDataIda().getTime()));
            lbDataVolta.setText(Util.formatarData(locacao.getDataVolta().getTime()));
            lbKmFinal.setText(String.valueOf(locacao.getKmFinalVeiculo()));
            lbKmInicial.setText(String.valueOf(locacao.getKmInicialDoVeiculo()));
            lbMetadePrimeiraDiaria.setText(String.valueOf(locacao.getMetadeDaPrimeiraDiaria()));
            lbTaxaCombustivel.setText(String.valueOf(locacao.getTaxaCombustivel()));
            lbTaxaIgienizacao.setText(String.valueOf(locacao.getTaxaHigienizacao()));
            lbValor.setText(String.valueOf(locacao.getValor()));
            lbVeiculo.setText(locacao.getVeiculo().toString());
            lbMotorista.setText(locacao.getMotorista().toString());
            lbCliente.setText(locacao.getCliente().toString());
            System.err.println(locacao.getPontoDeEntregea());
            lbPontoDeEntrega.setText(locacao.getPontoDeEntregea().toString());
            cbKmLivre.setSelected(locacao.isKmLivre());
            cbFinalizada.setSelected(locacao.isFinalizada());

        }
    }

    private boolean exibirTelaDeCadastro(Locacao locacao, Event event) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroLocacaoDialogController.class.getResource(""
                    + "/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroLocacaoDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FXMLAnchorPaneCadastroLocacaoDialogController controller = loader.getController();
            controller.setStage(stage);
            controller.setLocacao(locacao);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();

            return controller.isSucesso();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public TableView<Locacao> getTableView() {
        return tableView;
    }

    public TableColumn<Locacao, PessoaFisica> getColunaMotorista() {
        return colunaMotorista;
    }

    public TableColumn<Locacao, Veiculo> getColunaVeiculo() {
        return colunaVeiculo;
    }

    public TableColumn<Locacao, Double> getColunaValor() {
        return colunaValor;
    }

    public Label getLbCliente() {
        return lbCliente;
    }

    public Label getLbMotorista() {
        return lbMotorista;
    }

    public Label getLbVeiculo() {
        return lbVeiculo;
    }

    public Label getLbKmInicial() {
        return lbKmInicial;
    }

    public Label getLbKmFinal() {
        return lbKmFinal;
    }

    public Label getLbMetadePrimeiraDiaria() {
        return lbMetadePrimeiraDiaria;
    }

    public Label getLbTaxaCombustivel() {
        return lbTaxaCombustivel;
    }

    public Label getLbTaxaIgienizacao() {
        return lbTaxaIgienizacao;
    }

    public Label getLbDataIda() {
        return lbDataIda;
    }

    public Label getLbDataVolta() {
        return lbDataVolta;
    }

    public Label getLbValor() {
        return lbValor;
    }

    public CheckBox getCbKmLivre() {
        return cbKmLivre;
    }

    public CheckBox getCbFinalizada() {
        return cbFinalizada;
    }

    public TextField getTfPesquisar() {
        return tfPesquisar;
    }

    public Button getBtnPesquisar() {
        return btnPesquisar;
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

    public ArrayList<Locacao> getLocacaos() {
        return locacaos;
    }

    public ObservableList<Locacao> getObsLocacoes() {
        return obsLocacoes;
    }

}
