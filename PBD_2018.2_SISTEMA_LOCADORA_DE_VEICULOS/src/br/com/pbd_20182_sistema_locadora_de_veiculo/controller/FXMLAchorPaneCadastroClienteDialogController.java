/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAchorPaneCadastroClienteDialogController implements Initializable {

    private Stage stage;
    private Pessoa pessoa;
    private boolean confirmou;

    @FXML
    private Label lbDataNascimento;

    @FXML
    private Label lbDataDeVencimentoCNH;

    @FXML
    private Label lbIndentificacao;

    @FXML
    private Label lbNCNH;

    @FXML
    private Label lbCPFCNPJ;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPFCNPJ;

    @FXML
    private DatePicker cpDataNascimento;

    @FXML
    private DatePicker cpVencimentoCNH;

    @FXML
    private TextField tfIdentificacao;

    @FXML
    private TextField tfNCNH;

    @FXML
    private TextField tfLogin;



    @FXML
    private Button btnCadastrar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private RadioButton rbPessoaJuridica;

    private Label lbInscricaoEstadual;

    private TextField tfInscricaoEstadual;
    @FXML
    private GridPane gridPane2;

    @FXML
    void acoesBtns(ActionEvent event) {
        if (event.getSource() == BtnCancelar) {
            stage.close();
        } else {

            if (rbPessoaJuridica.isSelected()) {
                pessoa = new PessoaJuridica();
                pessoa.setNome(tfNome.getText());
                ((PessoaJuridica)pessoa).setCNPJ(tfCPFCNPJ.getText());
              
                pessoa.setLogin(tfLogin.getText());
                
                pessoa.setSenha(((PessoaJuridica)pessoa).getCNPJ());
                ((PessoaJuridica)pessoa).setInscriçãoEstadual(tfInscricaoEstadual.getText());

                pessoa.setCodigo("5522448");
            } else {
                pessoa = new PessoaFisica();
                pessoa.setNome(tfNome.getText());
                ((PessoaFisica)pessoa).setCPF(tfCPFCNPJ.getText());
                pessoa.setLogin(tfLogin.getText());
                pessoa.setSenha(((PessoaFisica)pessoa).getCPF());
                pessoa.setCodigo("1010");
                Date s = Date.from(cpDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                SimpleDateFormat spdf = new SimpleDateFormat("yyyy/MM/dddd");
                Calendar data_Nascimento = Calendar.getInstance();
                data_Nascimento.setTime(s);

                s = Date.from(cpVencimentoCNH.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Calendar dataVencimentoCNH = Calendar.getInstance();
                dataVencimentoCNH.setTime(s);

               ((PessoaFisica)pessoa).setData_vencimentoCNH(dataVencimentoCNH);

                ((PessoaFisica)pessoa).setIdentificacao(tfIdentificacao.getText());

            }

            confirmou = true;
            stage.close();
        }

    }

    @FXML
    void acaoRbPessoaJuridica(ActionEvent event) {
        if (rbPessoaJuridica.isSelected()) {

            lbDataDeVencimentoCNH.setVisible(false);
            lbDataNascimento.setVisible(false);
            lbIndentificacao.setVisible(false);
            lbNCNH.setVisible(false);

            cpDataNascimento.setVisible(false);
            cpVencimentoCNH.setVisible(false);
            tfNCNH.setVisible(false);
            tfIdentificacao.setVisible(false);

            lbInscricaoEstadual.setVisible(true);
            tfInscricaoEstadual.setVisible(true);

            lbCPFCNPJ.setText("CNPJ");

        } else {
            lbDataDeVencimentoCNH.setVisible(true);
            lbDataNascimento.setVisible(true);
            lbIndentificacao.setVisible(true);
            lbNCNH.setVisible(true);
            tfIdentificacao.setVisible(true);
            tfNCNH.setVisible(true);
            tfNome.setVisible(true);
            cpDataNascimento.setVisible(true);
            cpVencimentoCNH.setVisible(true);
            tfIdentificacao.setVisible(true);
            lbInscricaoEstadual.setVisible(false);
            tfInscricaoEstadual.setVisible(false);

            lbCPFCNPJ.setText("CPF");

        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        lbInscricaoEstadual = new Label("Inscrição Estadual");
        tfInscricaoEstadual = new TextField();

        lbInscricaoEstadual.setVisible(false);
        tfInscricaoEstadual.setVisible(false);

        gridPane2.add(lbInscricaoEstadual, 0, 4);
        gridPane2.add(tfInscricaoEstadual, 1, 4);

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isConfirmou() {
        return confirmou;
    }

    public void setConfirmou(boolean confirmou) {
        this.confirmou = confirmou;
    }

}
