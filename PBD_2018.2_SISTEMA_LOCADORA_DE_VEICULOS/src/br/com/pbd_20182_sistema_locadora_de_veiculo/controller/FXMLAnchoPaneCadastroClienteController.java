/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Funcionario;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoaJuridica;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchoPaneCadastroClienteController implements Initializable {

    @FXML
    private TableView<Pessoa> tableViewClientes;

    @FXML
    private TableColumn<Pessoa, String> colunaNomeCliente;

    @FXML
    private TableColumn<Pessoa, String> colunaLoginCliente;

    @FXML
    private TableColumn<Pessoa, String> colunaCodigoCliente;

    @FXML
    private Label lbCPFCPNJ;

    @FXML
    private Label lbNumeroCNHD;

    @FXML
    private Label lbNumeroCNH;

    @FXML
    private Label lbDatavencimentoCNHD;

    @FXML
    private Label lbDataNascimentoD;

    @FXML
    private Label lbIdentificacaoD;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbDataNascimento;

    @FXML
    private Label lbCPFCNPJVazio;

    @FXML
    private Label lbDataDeVencimentoCNH;

    @FXML
    private Label lbIdentificacao;

    @FXML
    private Label lbLogin;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button BtnInserirCliente;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button BtnExcluir;

    private ArrayList<Pessoa> pessoas;
    private ObservableList<Pessoa> obsPessoas;
    private DAOPessoaFisica dAOPessoaFisica = new DAOPessoaFisica();
    private DAOPessoaJuridica dAOPessoaJuridica = new DAOPessoaJuridica();
    private Fachada fachada = Fachada.getInstance();
    FXMLAchorPaneCadastroClienteDialogController controller;

    @FXML
    void acoesDeBotao(ActionEvent event) throws BusinessExpection {
        if (event.getSource() == BtnInserirCliente) {

            boolean confirmacao = exibirTelaDecadastro();
           
            if (confirmacao) {
                Pessoa pessoa = controller.getPessoa();
                if (pessoa instanceof PessoaFisica) {
                    fachada.salvarPessoaFisica((PessoaFisica) pessoa);
                } else {

                    fachada.salvarPessoaJuridica((PessoaJuridica) pessoa);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Cadastrado");
                alert.show();
                carregarClientes();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarClientes();

        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
    }

    public void selecionouDaTabela(Pessoa pessoa) {
        if ((pessoa != null)) {
            lbNome.setText(pessoa.getNome());
            lbCodigo.setText(pessoa.getCodigo());
            lbLogin.setText(pessoa.getLogin());

            if (pessoa instanceof PessoaJuridica) {
                lbCPFCPNJ.setText("CNPJ");
                lbCPFCNPJVazio.setText(((PessoaJuridica) pessoa).getCNPJ());

                lbDataNascimentoD.setVisible(false);
                lbDatavencimentoCNHD.setVisible(false);
                lbNumeroCNHD.setVisible(false);
                lbIdentificacaoD.setVisible(false);
                lbDataNascimento.setVisible(false);
                lbDataDeVencimentoCNH.setVisible(false);
                lbIdentificacao.setVisible(false);
                lbNumeroCNH.setVisible(false);

            } else if (pessoa instanceof PessoaFisica) {

                lbDataNascimentoD.setVisible(true);
                lbDatavencimentoCNHD.setVisible(true);
                lbNumeroCNHD.setVisible(true);
                lbIdentificacaoD.setVisible(true);
                lbDataNascimento.setVisible(true);
                lbDataDeVencimentoCNH.setVisible(true);
                lbIdentificacao.setVisible(true);
                lbNumeroCNH.setVisible(true);

                lbCPFCPNJ.setText("CPF");
                lbCPFCNPJVazio.setText(((PessoaFisica) pessoa).getCPF());
                lbDataNascimento.setText(String.valueOf(((PessoaFisica) pessoa).getData_nascimento()));
                lbDataDeVencimentoCNH.setText(String.valueOf(((PessoaFisica) pessoa).getData_vencimentoCNH()));
                lbNumeroCNH.setText(((PessoaFisica) pessoa).getNumero_CNH());
                lbIdentificacao.setText(((PessoaFisica) pessoa).getIdentificacao());

            }
        }

    }

    public void carregarClientes() {
        pessoas = new ArrayList<>();
        try {
            for (PessoaFisica pessoaFisica : dAOPessoaFisica.findAll()) {
                pessoas.add(pessoaFisica);
            }
            for (PessoaJuridica pessoaJ : dAOPessoaJuridica.findAll()) {
                pessoas.add(pessoaJ);
            }

            colunaLoginCliente.setCellValueFactory(new PropertyValueFactory<>("login"));
            colunaCodigoCliente.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));

            obsPessoas = FXCollections.observableArrayList(pessoas);

            tableViewClientes.setItems(obsPessoas);

        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchoPaneCadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean exibirTelaDecadastro() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAchorPaneCadastroClienteDialog.fxml"));
            Pane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Clientes");
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            controller = loader.getController();
            controller.setStage(stage);
            Pessoa pessoa = null;
            controller.setPessoa(pessoa);

            stage.showAndWait();

            return controller.isConfirmou();

        } catch (IOException e) {
        }
        return false;
    }

}
