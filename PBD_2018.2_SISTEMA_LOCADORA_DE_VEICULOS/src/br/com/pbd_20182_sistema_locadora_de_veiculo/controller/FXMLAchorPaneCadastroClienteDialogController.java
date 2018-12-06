/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private Endereco endereco;
    private boolean isEdicao;

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

    @FXML
    private RadioButton rbPessoaFisica;

    @FXML
    private Label lbInscricaoEstadual;

    private TextField tfInscricaoEstadual;
    @FXML
    private GridPane gridPane2;

    @FXML
    private TextField tfRua;

    @FXML
    private TextField tfBairro;

    @FXML
    private TextField tfCidade;

    @FXML
    private ComboBox<String> comboUF;

    @FXML
    private TextField tfNumero;

    private DAOPessoa dAOPessoa = new DAOPessoa();

    @FXML
    void acoesBtns(ActionEvent event) throws DAOException {
        try {
            if (event.getSource() == BtnCancelar) {
                stage.close();
            } else {

                if (rbPessoaJuridica.isSelected()) {
                    pegarValoresPessoaJuridica();
                    pegarValoresEndereco();
                } else {

                    pegarValoresPessoaFisica();
                    pegarValoresEndereco();

                }
                pessoa.setAtivo(true);
                confirmou = true;
                stage.close();
            }
        } catch (NumberFormatException e) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Erro ao tentar inserir", "Não se"
                    + "deve ter letras no lugar de números.");
        }

    }

    @FXML
    void acaoRbs(ActionEvent event) {
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

        } else if (rbPessoaFisica.isSelected()) {
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

        gridPane2.add(lbInscricaoEstadual, 0, 3);
        gridPane2.add(tfInscricaoEstadual, 1, 3);

        ArrayList<String> ufs = new ArrayList<>();
        ObservableList<String> obsUfs;

        for (int i = 0; i < Util.ufs.length; i++) {
            ufs.add(Util.ufs[i]);
        }

        obsUfs = FXCollections.observableArrayList(ufs);

        comboUF.setItems(obsUfs);

        ToggleGroup tg = new ToggleGroup();
        rbPessoaFisica.setToggleGroup(tg);
        rbPessoaJuridica.setToggleGroup(tg);

    }

    public void pegarValoresEndereco() {

        pessoa.getEndereco().setBairro(tfBairro.getText());
        pessoa.getEndereco().setRua(tfRua.getText());
        pessoa.getEndereco().setNumero(Integer.parseInt(tfNumero.getText()));
        pessoa.getEndereco().setUf(comboUF.getValue().toUpperCase());
        pessoa.getEndereco().setCidade(tfCidade.getText());

    }

    public void pegarValoresPessoaFisica() throws DAOException {
        System.err.println("pegarValoresPessoaFisica");
        if (!(pessoa != null)) {
            System.err.println("Entrou if");
            pessoa = new PessoaFisica();
            endereco = new Endereco();
            pessoa.setEndereco(endereco);
        }

        if (pessoa instanceof PessoaFisica) {
            System.err.println("É física");
        }

        pessoa.setNome(tfNome.getText());
        ((PessoaFisica) pessoa).setCPF(tfCPFCNPJ.getText());
        pessoa.setLogin(tfLogin.getText());
        if (!isEdicao) {
            pessoa.setSenha(((PessoaFisica) pessoa).getCPF());
            try {
                String UltimoCodigo = dAOPessoa.buscarUltimoCodigo();
                pessoa.setCodigo(Util.gerarCodigoInterno(pessoa.getNome(), UltimoCodigo));

            } catch (DAOException ex) {
                throw new DAOException("ERRO AO BUSCAR ULTIMO CÓDIGO!");
            }
        }
        Date s = Date.from(cpDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar data_Nascimento = Calendar.getInstance();
        data_Nascimento.setTime(s);
        ((PessoaFisica) pessoa).setData_nascimento(data_Nascimento.getTime());

        s = Date.from(cpVencimentoCNH.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar dataVencimentoCNH = Calendar.getInstance();
        dataVencimentoCNH.setTime(s);

        ((PessoaFisica) pessoa).setData_vencimentoCNH(dataVencimentoCNH.getTime());

        ((PessoaFisica) pessoa).setIdentificacao(tfIdentificacao.getText());

        ((PessoaFisica) pessoa).setNumero_CNH(tfNCNH.getText());

    }

    public void pegarValoresPessoaJuridica() throws DAOException {

        if (!(pessoa != null)) {
            pessoa = new PessoaJuridica();
            endereco = new Endereco();
            pessoa.setEndereco(endereco);

        }

        pessoa.setNome(tfNome.getText());
        ((PessoaJuridica) pessoa).setCNPJ(tfCPFCNPJ.getText());

        pessoa.setLogin(tfLogin.getText());

        if (!isEdicao) {

            pessoa.setSenha(((PessoaJuridica) pessoa).getCNPJ());
            ((PessoaJuridica) pessoa).setInscriçãoEstadual(tfInscricaoEstadual.getText());
            String UltimoCodigo;
            try {
                UltimoCodigo = dAOPessoa.buscarUltimoCodigo();
                pessoa.setCodigo(Util.gerarCodigoInterno(pessoa.getNome(), UltimoCodigo));

            } catch (DAOException ex) {
                throw new DAOException("ERRO AO BUSCAR ULTIMO CÓDIGO!");
            }
        }
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
        if (pessoa != null) {
            isEdicao = true;
            if (pessoa.getId() != null) {

                tfBairro.setText(pessoa.getEndereco().getBairro());
                tfCidade.setText(pessoa.getEndereco().getCidade());
                tfRua.setText(pessoa.getEndereco().getRua());
                comboUF.setValue(pessoa.getEndereco().getUf());
                tfNumero.setText(String.valueOf(pessoa.getEndereco().getNumero()));

                tfNome.setText(pessoa.getNome());
                tfLogin.setText(pessoa.getLogin());

                if (pessoa instanceof PessoaFisica) {
                    this.pessoa = ((PessoaFisica) pessoa);

                    cpDataNascimento.setValue(Util.converterDateEmLocalDate(((PessoaFisica) pessoa).getData_nascimento()));
                    cpVencimentoCNH.setValue(Util.converterDateEmLocalDate(((PessoaFisica) pessoa).getData_vencimentoCNH()));
                    tfCPFCNPJ.setText(((PessoaFisica) pessoa).getCPF());
                    tfNCNH.setText(((PessoaFisica) pessoa).getNumero_CNH());
                    tfIdentificacao.setText(((PessoaFisica) pessoa).getIdentificacao());
                    rbPessoaJuridica.setDisable(true);

                } else {

                    this.pessoa = ((PessoaJuridica) pessoa);

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

                    tfCPFCNPJ.setText(((PessoaJuridica) this.pessoa).getCNPJ());
                    tfInscricaoEstadual.setText(((PessoaJuridica) this.pessoa).getInscriçãoEstadual());
                    tfCPFCNPJ.setText(((PessoaJuridica) pessoa).getCNPJ());

                    rbPessoaJuridica.setSelected(true);
                    rbPessoaJuridica.setDisable(true);

                }

            }
        }

    }

    public boolean isConfirmou() {
        return confirmou;
    }

    public void setConfirmou(boolean confirmou) {
        this.confirmou = confirmou;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
