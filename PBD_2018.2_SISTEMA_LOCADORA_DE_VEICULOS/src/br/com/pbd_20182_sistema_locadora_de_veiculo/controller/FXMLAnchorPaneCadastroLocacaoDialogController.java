/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroLocacaoDialogController implements Initializable {

    private static double TAXA_HI = 0;
    private static double TAXA_COM = 0;

    @FXML
    private ComboBox<PessoaFisica> comboMotorista;

    @FXML
    private ComboBox<Pessoa> comboCliente;

    @FXML
    private ComboBox<Veiculo> comboVeiculo;

    @FXML
    private TextField tfKmInicial;

    @FXML
    private TextField tfKmFinal;

    @FXML
    private TextField tfMetadeprimeiraDiaria;

    @FXML
    private CheckBox cbTaxaHigienizacao;

    @FXML
    private CheckBox cbTaxaCombustivel;

    @FXML
    private DatePicker dpDataIda;

    @FXML
    private DatePicker dpDataVolta;

    @FXML
    private CheckBox cbFinalizada;

    @FXML
    private CheckBox cbKmLivre;

    @FXML
    private TextField tfValor;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAddMortorista;

    private Fachada fachada;
    private Stage stage;
    private boolean sucesso;
    private Locacao locacao;
    private Alerta alerta;
    private Thread thread;
    private FXMLAchorPaneCadastroClienteDialogController controller;
    private String buscaCliente = "";
    private String buscaMotorista = "";
    private String buscaVeiculo = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        alerta = Alerta.getInstace(Alert.AlertType.NONE);

        tfValor.setDisable(true);
        tfMetadeprimeiraDiaria.setDisable(true);

        try {
            carregarCombos();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }

        try {
            Geral geral = fachada.buscarGeral();
            tfMetadeprimeiraDiaria.setText(String.valueOf(geral.getMetadePrimeiraDiaria()));
            TAXA_COM = geral.getTaxaCombustivel();
            TAXA_HI = geral.getTaxaHigienizacao();

        } catch (DAOException ex) {
            ex.getMessage();
        }
        tfValor.setText(tfMetadeprimeiraDiaria.getText());

        adicionarOuvinte();

    }

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection, DAOException {
        if (event.getSource() == btnConfirmar) {

            Date LocalDate = Date.from(dpDataIda.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            SimpleDateFormat spdf = new SimpleDateFormat("dd/yy/dddd HH:mm:ss");

            Calendar dataHoraIda = Calendar.getInstance();
            Calendar c1 = Calendar.getInstance();
            c1.setTime(new Date());
            dataHoraIda.setTime(LocalDate);

            dataHoraIda.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
            dataHoraIda.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
            dataHoraIda.set(Calendar.SECOND, c1.get(Calendar.SECOND));

            locacao.setDataIda(dataHoraIda);

            LocalDate = Date.from(dpDataVolta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());;

            Calendar dataHoraVolta = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(new Date());
            dataHoraIda.setTime(LocalDate);

            dataHoraVolta.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
            dataHoraVolta.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
            dataHoraVolta.set(Calendar.SECOND, c1.get(Calendar.SECOND));

            locacao.setDataVolta(dataHoraVolta);

            locacao.setFinalizada(cbFinalizada.isSelected());
            locacao.setKmFinalVeiculo(Double.parseDouble(tfKmFinal.getText()));
            locacao.setKmInicialDoVeiculo(Double.parseDouble(tfKmInicial.getText()));
            locacao.setKmLivre(cbKmLivre.isSelected());
            locacao.setMetadeDaPrimeiraDiaria(Double.parseDouble(tfMetadeprimeiraDiaria.getText()));
            locacao.setValor(Double.parseDouble(tfValor.getText()));
            locacao.setMotorista(comboMotorista.getValue());
            locacao.setVeiculo(comboVeiculo.getValue());
            locacao.setCliente(comboCliente.getValue());

            locacao.setAtivo(true);
            if (cbTaxaCombustivel.isSelected()) {
                locacao.setTaxaCombustivel(TAXA_COM);
            }

            if (cbTaxaHigienizacao.isSelected()) {
                locacao.setTaxaHigienizacao(TAXA_HI);
            }

            sucesso = true;
            stage.close();

        } else if (event.getSource() == btnAddMortorista) {

            PessoaFisica pessoaFisica = null;

            boolean sucesso = exibirTelaDecadastro(pessoaFisica, event);

            if (sucesso) {

                pessoaFisica = (PessoaFisica) controller.getPessoa();

                fachada.salvarPessoaFisica(pessoaFisica);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserção de motorista",
                        "Inserção realizada com sucesso. ");

            }

        } else if (event.getSource() == btnCancelar) {
            stage.close();
        }
    }

    @FXML
    void acaoCBs(ActionEvent event) {
        double soma = 0;

        if (cbTaxaCombustivel.isSelected()) {
            soma += TAXA_COM;
        }

        if (cbTaxaHigienizacao.isSelected()) {
            soma += TAXA_HI;
        }

        calcularValor(soma);

    }

    @FXML
    void AcaoCombos(ActionEvent event) {

    }

    private void carregarCombos() throws DAOException {

        ObservableList<PessoaFisica> obsMotorista = FXCollections.observableArrayList(fachada.listarTodosPessoaFisica());
        ObservableList<Veiculo> obsVeiculos = FXCollections.observableArrayList(fachada.listarTodosVeiculo());
        ObservableList<Pessoa> obsCliente = FXCollections.observableArrayList(fachada.listarTodosPessoa());
        comboMotorista.setItems(obsMotorista);
        comboCliente.setItems(obsCliente);
        comboVeiculo.setItems(obsVeiculos);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;

        if (locacao.getId() != null) {
            comboCliente.setValue(locacao.getCliente());
            comboMotorista.setValue(locacao.getMotorista());
            comboVeiculo.setValue(locacao.getVeiculo());

            tfKmFinal.setText(String.valueOf(locacao.getKmFinalVeiculo()));
            tfKmInicial.setText(String.valueOf(locacao.getKmInicialDoVeiculo()));
            tfMetadeprimeiraDiaria.setText(String.valueOf(locacao.getMetadeDaPrimeiraDiaria()));
            tfValor.setText(String.valueOf(locacao.getValor()));

            dpDataIda.setValue((Util.converterDateEmLocalDate(locacao.getDataIda().getTime())));
            dpDataVolta.setValue((Util.converterDateEmLocalDate(locacao.getDataVolta().getTime())));

            cbFinalizada.setSelected(locacao.isFinalizada());
            cbKmLivre.setSelected(locacao.isKmLivre());

            if (locacao.getTaxaCombustivel() != 0) {
                cbTaxaCombustivel.setSelected(true);
            }

            if (locacao.getTaxaHigienizacao() != 0) {
                cbTaxaHigienizacao.setSelected(true);
            }

        }

    }

    private boolean exibirTelaDecadastro(Pessoa pessoa, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAchorPaneCadastroClienteDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();

            stage.setTitle("Cadastro de Clientes");
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            controller = loader.getController();
            controller.setStage(stage);

            controller.getRbPessoaFisica().setSelected(true);
            controller.getRbPessoaJuridica().setDisable(true);

            controller.setPessoa(pessoa);

            stage.showAndWait();

            return controller.isConfirmou();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    private void calcularValor(double valor) {

        double soma = Double.parseDouble(tfMetadeprimeiraDiaria.getText()) + valor;
        tfValor.setText(String.valueOf(soma));
    }

    private void adicionarOuvinte() {
        comboCliente.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {

                System.err.println(buscaCliente);
                System.err.println(buscaMotorista);
                System.err.println(buscaVeiculo);

                KeyCode code = evt.getCode();
                ObservableList<Pessoa> obsPessoa;

                try {

                    if (code.isLetterKey()) {
                        buscaCliente += evt.getText();
                        obsPessoa = FXCollections.
                                observableArrayList(fachada.buscarPorBuscaPessoa(buscaCliente));
                        comboCliente.setItems(obsPessoa);
                    }

                    if (code == KeyCode.BACK_SPACE && buscaCliente.length() > 0) {
                        buscaCliente = buscaCliente.substring(0, buscaCliente.length() - 1);
                        obsPessoa = FXCollections.
                                observableArrayList(fachada.buscarPorBuscaPessoa(buscaCliente));

                        comboCliente.setItems(obsPessoa);

                    }
                    if (code == KeyCode.ESCAPE) {
                        buscaCliente = "";
                    }

                } catch (DAOException ex) {
                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        comboMotorista.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {

                System.err.println(buscaCliente);
                System.err.println(buscaMotorista);
                System.err.println(buscaVeiculo);

                KeyCode code = evt.getCode();
                ObservableList<PessoaFisica> obsMotorista;

                try {

                    if (code.isLetterKey()) {
                        buscaMotorista += evt.getText();
                        obsMotorista = FXCollections.
                                observableArrayList(fachada.buscarPorNomeLikePessoaFisica(buscaMotorista));
                        comboMotorista.setItems(obsMotorista);
                    }

                    if (code == KeyCode.BACK_SPACE && buscaMotorista.length() > 0) {
                        buscaMotorista = buscaMotorista.substring(0, buscaMotorista.length() - 1);
                        obsMotorista = FXCollections.
                                observableArrayList(fachada.buscarPorNomeLikePessoaFisica(buscaMotorista));

                        comboMotorista.setItems(obsMotorista);
                    }
                    if (code == KeyCode.ESCAPE) {
                        buscaMotorista = "";
                    }

                } catch (DAOException ex) {
                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        comboVeiculo.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {
                System.err.println(buscaCliente);
                System.err.println(buscaMotorista);
                System.err.println(buscaVeiculo);

                KeyCode code = evt.getCode();
                ObservableList<Veiculo> obsveiculos;

                try {

                    if (code.isLetterKey()) {
                        buscaVeiculo += evt.getText();
                    }
                    obsveiculos = FXCollections.
                            observableArrayList(fachada.buscarPorBuscaVeiculo(buscaVeiculo));
                    comboVeiculo.setItems(obsveiculos);

                    if (code == KeyCode.BACK_SPACE && buscaVeiculo.length() > 0) {
                        buscaVeiculo = buscaVeiculo.substring(0, buscaVeiculo.length() - 1);
                        comboVeiculo.setItems(obsveiculos);
                    }
                    if (code == KeyCode.ESCAPE) {
                        buscaVeiculo = "";
                    }

                } catch (DAOException ex) {
                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }

}
