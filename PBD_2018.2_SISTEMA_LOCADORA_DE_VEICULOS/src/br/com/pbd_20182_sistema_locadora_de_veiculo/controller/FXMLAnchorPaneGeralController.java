/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Geral;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneGeralController implements Initializable {

    @FXML
    private TableView<Pessoa> tableView;

    @FXML
    private TableColumn<Pessoa, String> colunaNome;

    @FXML
    private TableColumn<Pessoa, String> colunaLogin;

    @FXML
    private TableColumn<Pessoa, String> colunaCodigo;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnPesquisa;

    @FXML
    private TextField tfTaxaHigienizacao;

    @FXML
    private TextField tfMetadePrimeiraDiaria;
    
    @FXML
    private TextField tfTaxaCombustivel;

    @FXML
    private Button btnAlterarTaxas;

    @FXML
    private Button btnResetar;

    private ArrayList<Pessoa> pessoas;
    private ObservableList<Pessoa> obsPessoas;
    private DAOPessoa dAOPessoa = new DAOPessoa();
    private Pessoa pessoa;
    private Fachada fachada;

    @FXML
    void acaoBtns(ActionEvent event) throws DAOException {
        if (event.getSource() == btnPesquisa) {
            try {
                carregarTabela((ArrayList<Pessoa>) dAOPessoa.buscarPorBusca(tfPesquisa.getText()));
            } catch (DAOException ex) {
                ex.getMessage();
            }
        }

        if (event.getSource() == btnResetar) {
            try {
                if (pessoa instanceof PessoaFisica) {
                    ((PessoaFisica) pessoa).setSenha(((PessoaFisica) pessoa).getCPF());
                } else if (pessoa instanceof PessoaJuridica) {
                    ((PessoaJuridica) pessoa).setSenha(((PessoaJuridica) pessoa).getCNPJ());
                } else {
                    ((Funcionario) pessoa).setSenha(((Funcionario) pessoa).getMatricula());
                }

                pessoa.setSenha(dAOPessoa.criptografarSenha(pessoa.getSenha()));
                dAOPessoa.salvar(pessoa);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso", "Senha resetada", "");
            } catch (NullPointerException e) {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Problema ao resetar senha.", "Usuario não selecionado.", "Selecione o usuario em que a senha deve ser resetada.");
            } catch (DAOException e2) {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Erro.", "Problema ao tentar atualizar.", "Probela ao atualizar cliente, tente mais tarde.");
            }
        }

        if (event.getSource() == btnAlterarTaxas) {
            Geral geral = fachada.buscarGeral();

            geral.setTaxaCombustivel(Double.parseDouble(tfTaxaCombustivel.getText()));
            geral.setTaxaHigienizacao(Double.parseDouble(tfTaxaHigienizacao.getText()));
            geral.setMetadePrimeiraDiaria(Double.parseDouble(tfMetadePrimeiraDiaria.getText()));

            fachada.salvarGeral(geral);
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso", "Alterção de taxas", "Alteração"
                    + "realizada com sucesso!.");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            carregarTabela(dAOPessoa.listarTodos());
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneGeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouNaTabela(newValue));

        fachada = Fachada.getInstance();

        try {
            Geral geral = fachada.buscarGeral();

            if (geral != null) {

                tfTaxaCombustivel.setText(String.valueOf(geral.getTaxaCombustivel()));
                tfTaxaHigienizacao.setText(String.valueOf(geral.getTaxaHigienizacao()));
                tfMetadePrimeiraDiaria.setText(String.valueOf(geral.getMetadePrimeiraDiaria()));
                

            } else {

                geral = new Geral();

                geral.setTaxaCombustivel(0);
                geral.setTaxaHigienizacao(0);
                geral.setMetadePrimeiraDiaria(0);
                fachada.salvarGeral(geral);

                tfTaxaCombustivel.setText(String.valueOf(geral.getTaxaCombustivel()));
                tfTaxaHigienizacao.setText(String.valueOf(geral.getTaxaHigienizacao()));
                tfTaxaCombustivel.setText(String.valueOf(geral.getMetadePrimeiraDiaria()));
            }

        } catch (DAOException ex) {
            ex.printStackTrace();
        }

    }

    public void carregarTabela(ArrayList<Pessoa> pessoas) {

        this.pessoas = pessoas;

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        obsPessoas = FXCollections.observableArrayList(pessoas);

        tableView.setItems(obsPessoas);

    }

    private void selecionouNaTabela(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TableColumn<Pessoa, String> getColunaCodigo() {
        return colunaCodigo;
    }

    public Button getBtnPesquisa() {
        return btnPesquisa;
    }

    public Button getBtnResetar() {
        return btnResetar;
    }

}
