/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    private Fachada fachada;
    private Stage stage;
    private boolean sucesso;
    private Locacao locacao;
    private Alerta alerta;
    private Thread thread;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        alerta = Alerta.getInstace(Alert.AlertType.NONE);
        try {
            carregarCombos();
        } catch (DAOException ex) {
            ex.getMessage();
        }

        tfValor.setDisable(true);
        tfMetadeprimeiraDiaria.setDisable(true);

        try {
            Geral geral = fachada.buscarGeral();
            tfMetadeprimeiraDiaria.setText(String.valueOf(geral.getMetadePrimeiraDiaria()));
            TAXA_COM = geral.getTaxaCombustivel();
            TAXA_HI = geral.getTaxaHigienizacao();

        } catch (DAOException ex) {
            ex.getMessage();
        }
        tfValor.setText(tfMetadeprimeiraDiaria.getText());

    }

    @FXML
    void acaoBtns(ActionEvent event) {
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

        } else {
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
        
        if(locacao.getId() != null){
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
            
            if(locacao.getTaxaCombustivel() != 0){
                cbTaxaCombustivel.setSelected(true);
            }
            
            if(locacao.getTaxaHigienizacao() != 0){
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

    private void calcularValor(double valor) {

        double soma = Double.parseDouble(tfMetadeprimeiraDiaria.getText()) + valor;
        tfValor.setText(String.valueOf(soma));
    }

}
