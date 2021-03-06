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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Revisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ThreadDeControleDeLimpezaERevisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
    private static double VALOR_KM_LIVRE = 0;
    private static double VALOR_KM_CONTROLE = 0;
    @FXML
    private JFXComboBox<PessoaFisica> comboMotorista;

    @FXML
    private Button btnAddMortorista;

    @FXML
    private JFXComboBox<Filial> comboPontoDeEntrega;

    @FXML
    private JFXComboBox<Veiculo> comboVeiculo;

    @FXML
    private JFXComboBox<Pessoa> comboCliente;

    @FXML
    private JFXComboBox<ReservaPessoasCategorias> comboReservas;

    @FXML
    private JFXTextField tfKmInicial;

    @FXML
    private JFXTextField tfKmFinal;

    @FXML
    private JFXTextField tfMetadeprimeiraDiaria;

    @FXML
    private JFXDatePicker dpDataIda;

    @FXML
    private JFXDatePicker dpDataVolta;

    @FXML
    private JFXCheckBox cbTaxaCombustivel;

    @FXML
    private JFXCheckBox cbKmLivre;

    @FXML
    private JFXCheckBox cbFinalizada;

    @FXML
    private JFXTextField tfValor;

    @FXML
    private JFXButton btnConfirmar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXCheckBox cbTaxaHigienizacao;
    
    

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
        cbKmLivre.setSelected(true);

        try {
            carregarCombos();
        } catch (DAOException ex) {
            ex.printStackTrace();
        }

        try {
            Geral geral = fachada.buscarGeral();
            tfMetadeprimeiraDiaria.setText(String.valueOf(geral.getMetadePrimeiraDiaria()));

            tfValor.setText(String.valueOf(geral.getMetadePrimeiraDiaria() + geral.getValorKmLivre()));

            TAXA_COM = geral.getTaxaCombustivel();
            TAXA_HI = geral.getTaxaHigienizacao();
            VALOR_KM_LIVRE = geral.getValorKmLivre();
            VALOR_KM_CONTROLE = geral.getValorKmControle();

        } catch (DAOException ex) {
            ex.getMessage();
        }

        tfKmInicial.setDisable(true);
        adicionarOuvinte();

        MascarasTF.mascaraNumero(tfKmFinal);
        MascarasTF.mascaraNumero(tfKmInicial);
        MascarasTF.mascaraNumero(tfMetadeprimeiraDiaria);
        MascarasTF.mascaraNumero(tfValor);

    }

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection, DAOException {
        try {
            if (event.getSource() == btnConfirmar) {

                Date localDate = Date.from(dpDataIda.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                Calendar dataHoraIda = Calendar.getInstance();
                Calendar c1 = Calendar.getInstance();
                c1.setTime(new Date());
                dataHoraIda.setTime(localDate);

                if (locacao.getId() != null) {

                    c1.set(Calendar.HOUR, locacao.getDataIda().get(Calendar.HOUR));
                    c1.set(Calendar.MINUTE, locacao.getDataIda().get(Calendar.MINUTE));
                    c1.set(Calendar.SECOND, locacao.getDataIda().get(Calendar.SECOND));

                }
                dataHoraIda.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
                dataHoraIda.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
                dataHoraIda.set(Calendar.SECOND, c1.get(Calendar.SECOND));

                locacao.setDataIda(dataHoraIda);

                localDate = Date.from(dpDataVolta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                Calendar dataHoraVolta = Calendar.getInstance();

                dataHoraVolta.setTime(localDate);
                if (locacao.getId() != null) {

                    c1.set(Calendar.HOUR, locacao.getDataVolta().get(Calendar.HOUR));
                    c1.set(Calendar.MINUTE, locacao.getDataVolta().get(Calendar.MINUTE));
                    c1.set(Calendar.SECOND, locacao.getDataVolta().get(Calendar.SECOND));

                }
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
                locacao.setPontoDeEntregea(comboPontoDeEntrega.getValue());
                locacao.setAtivo(true);
                if (cbTaxaCombustivel.isSelected()) {
                    locacao.setTaxaCombustivel(TAXA_COM);
                }

                if (cbTaxaHigienizacao.isSelected()) {
                    locacao.setTaxaHigienizacao(TAXA_HI);
                }
                if (cbFinalizada.isSelected() && locacao.getId() != null) {

                    double kmRodados = locacao.getKmFinalVeiculo() - locacao.getKmInicialDoVeiculo();

                    if (locacao.getVeiculo().getCategoria() instanceof Categoria && kmRodados >= 5000) {
                        fachada.salvarVeiculo(locacao.getVeiculo());
                        ThreadDeControleDeLimpezaERevisao r
                                = new ThreadDeControleDeLimpezaERevisao(locacao.getVeiculo());
                        fachada.salvarRevisao(new Revisao(locacao.getVeiculo(), new Date()));
                    } else if ((locacao.getVeiculo().getCategoria() instanceof CaminhonetaDeCarga || locacao.getVeiculo().getCategoria() instanceof CaminhonetaDePassageiros) && kmRodados >= 10000) {
                        fachada.salvarVeiculo(locacao.getVeiculo());
                        ThreadDeControleDeLimpezaERevisao r
                                = new ThreadDeControleDeLimpezaERevisao(locacao.getVeiculo());
                        fachada.salvarRevisao(new Revisao(locacao.getVeiculo(), new Date()));

                    }
                    locacao.getVeiculo().setQuilometragemAtual(Double.parseDouble(tfKmFinal.getText()));
                    locacao.getVeiculo().setDisponivel(true);
                    locacao.getVeiculo().setFilial(comboPontoDeEntrega.getValue());
                    fachada.salvarVeiculo(locacao.getVeiculo());

                    ContaAReceber contaAReceber = new ContaAReceber();
                    contaAReceber.setDescricao("Locacao de: " + locacao.getCliente());
                    contaAReceber.setDataRecebimento(new Date());
                    contaAReceber.setPago(true);
                    contaAReceber.setValor(locacao.getValor());

                    fachada.salvarContaAReceber(contaAReceber);

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
        } catch (NullPointerException | NumberFormatException e) {
            alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Campos não preenchidos.", "Confirme se os campos estão todos preechidos");
        }

    }

    @FXML
    void acaoCBs(ActionEvent event) throws DAOException, BusinessExpection {
        double soma = 0;
        if (cbFinalizada.isSelected()) {
            if (locacao.getId() != null) {

                soma += Util.calcularQuantidadeDeHorasAtrasadas(locacao);

            }
        }

        if (cbTaxaCombustivel.isSelected()) {
            soma += TAXA_COM;
        }

        if (cbTaxaHigienizacao.isSelected()) {
            soma += TAXA_HI;
        }

        if (cbKmLivre.isSelected()) {
            soma += VALOR_KM_LIVRE;
        } else {
            soma += calcularKmControle();
        }
        calcularValor(soma);

    }

    @FXML
    void AcaoCombos(ActionEvent e) throws DAOException {

        if (e.getSource() == comboVeiculo) {
            tfKmInicial.setText(String.valueOf(comboVeiculo.getValue().getQuilometragemAtual()));
        }
        if (e.getSource() == comboReservas) {
            comboCliente.setValue(comboReservas.getValue().getPessoa());

            ObservableList<Veiculo> obsVeiculo = FXCollections.observableArrayList(fachada.buscarVeiculoPorCategoria(comboReservas.getValue().getCategoria()));
            comboVeiculo.setItems(obsVeiculo);
        }

    }

    private void carregarCombos() throws DAOException {

        ObservableList<PessoaFisica> obsMotorista = FXCollections.observableArrayList(fachada.listarTodosPessoaFisica());
        ObservableList<Veiculo> obsVeiculos = FXCollections.observableArrayList(fachada.listarTodosVeiculo());
        ObservableList<Pessoa> obsCliente = FXCollections.observableArrayList(fachada.listarTodosPessoa());
        ObservableList<Filial> pbsFilial = FXCollections.observableArrayList(fachada.listarTodosFilial());
        ObservableList<ReservaPessoasCategorias> obsReservas = FXCollections.observableArrayList(fachada.listarTodosReservaPessoasCategorias());
        comboMotorista.setItems(obsMotorista);
        comboCliente.setItems(obsCliente);
        comboVeiculo.setItems(obsVeiculos);
        comboPontoDeEntrega.setItems(pbsFilial);
        comboReservas.setItems(obsReservas);
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

    private void calcularValor(double valor) {
        double soma = 0;
        System.err.println(valor);
        if (locacao != null) {
            soma = valor;
        } else {
            soma = Double.parseDouble(tfMetadeprimeiraDiaria.getText()) + valor;
        }
        tfValor.setText(String.valueOf(soma));
    }

    public double calcularKmControle() {
        if (tfKmFinal.getText().length() != 0) {
            double kmInicial = Double.parseDouble(tfKmInicial.getText());
            double kmFinal = Double.parseDouble(tfKmFinal.getText());

            double diferenca = kmFinal - kmInicial;

            double valor = diferenca * VALOR_KM_CONTROLE;

            return valor;

        }
        return 0;
    }

    private void adicionarOuvinte() {
        comboCliente.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {

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
            comboPontoDeEntrega.setValue(locacao.getPontoDeEntregea());

            tfKmFinal.setText(String.valueOf(locacao.getKmFinalVeiculo()));
            tfKmInicial.setText(String.valueOf(locacao.getKmInicialDoVeiculo()));
            tfMetadeprimeiraDiaria.setText(String.valueOf(locacao.getMetadeDaPrimeiraDiaria()));

            dpDataIda.setValue((Util.converterDateEmLocalDate(locacao.getDataIda().getTime())));
            dpDataVolta.setValue((Util.converterDateEmLocalDate(locacao.getDataVolta().getTime())));

            cbFinalizada.setSelected(locacao.isFinalizada());
            cbKmLivre.setSelected(locacao.isKmLivre());

            tfValor.setText(String.valueOf(locacao.getValor()));
            if (locacao.getTaxaCombustivel() != 0) {
                cbTaxaCombustivel.setSelected(true);
            }

            if (locacao.getTaxaHigienizacao() != 0) {
                cbTaxaHigienizacao.setSelected(true);
            }

        }

    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
