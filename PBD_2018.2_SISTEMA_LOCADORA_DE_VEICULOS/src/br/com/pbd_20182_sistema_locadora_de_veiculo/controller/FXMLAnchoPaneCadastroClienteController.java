/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
    private TableColumn<Pessoa, String> colunaCodigoCliente;

    @FXML
    private Label lbCPFCPNJ;

    @FXML
    private Label lbNumeroCNHD;

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
    private Label lbNumeroCNH;

    @FXML
    private Label lbIdentificacao;

    @FXML
    private Label lbLogin;

    @FXML
    private JFXTextField tfPesquisa;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private JFXButton BtnInserirCliente;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton BtnExcluir;
    private ArrayList<Pessoa> pessoas;
    private ObservableList<Pessoa> obsPessoas;
    private Fachada fachada = Fachada.getInstance();
    private FXMLAchorPaneCadastroClienteDialogController controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
    }

    @FXML
    void acoesDeBotao(ActionEvent event) throws BusinessExpection, DAOException {

        if (event.getSource() == BtnInserirCliente) {
            Pessoa pessoa = null;
            boolean confirmacao = exibirTelaDecadastro(pessoa, event
            );

            if (confirmacao) {
                Pessoa pessoaRetorno = controller.getPessoa();

                if (pessoaRetorno instanceof PessoaFisica) {
                    fachada.salvarPessoaFisica((PessoaFisica) pessoaRetorno);
                } else {

                    fachada.salvarPessoaJuridica((PessoaJuridica) pessoaRetorno);
                }

                DAOPessoa dAOPessoa = new DAOPessoa();
                int id = dAOPessoa.buscarUltimoID();
                pessoaRetorno = dAOPessoa.findById(Pessoa.class, id);

                pessoaRetorno.setSenha(dAOPessoa.criptografarSenha(pessoaRetorno.getSenha()));
                dAOPessoa.salvar(pessoaRetorno);

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserção de Clientes", ""
                        + "Inserção realizada com sucesso");

                carregarClientes(fachada.listarTodosPessoa());

            }

        }

        if (event.getSource() == btnAlterar) {

            Pessoa pessoa = tableViewClientes.getSelectionModel().getSelectedItem();

            if (pessoa != null) {

                boolean sucesso = exibirTelaDecadastro(pessoa, event);

                if (sucesso) {
                    pessoa = controller.getPessoa();

                    if (pessoa instanceof PessoaFisica) {
                        fachada.salvarPessoaFisica((PessoaFisica) pessoa);
                    } else {

                        fachada.salvarPessoaJuridica((PessoaJuridica) pessoa);

                    }

                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Edição de clientes", ""
                            + "Edição realizada com sucesso!");

                }

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atenção", "Edição de clientes", ""
                        + "Selecione o cliente para edição");
            }

        }

        if (event.getSource() == BtnExcluir) {

            Pessoa pessoa = tableViewClientes.getSelectionModel().getSelectedItem();

            if (pessoa != null) {

                pessoa.setAtivo(false);

                if (pessoa instanceof PessoaFisica) {
                    fachada.salvarPessoaFisica((PessoaFisica) pessoa);
                } else {

                    fachada.salvarPessoaJuridica((PessoaJuridica) pessoa);
                }

                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Exclusão de clientes", ""
                        + "Exclusão realizada com sucesso!");

            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Atenção", "Exlusão de clientes", ""
                        + "Selecione o cliente para excluir");
            }

        }

        if (event.getSource() == btnPesquisar) {

            if (tfPesquisa.getText().length() == 0) {
                carregarClientes(fachada.listarTodosPessoa());
            } else {

                carregarClientes(fachada.buscarPorBuscaPessoa(tfPesquisa.getText()));

            }

        }

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
                pessoa = (PessoaFisica) pessoa;

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
                lbDataNascimento.setText(Util.formatarData(((PessoaFisica) pessoa).getData_nascimento()));
                lbDataDeVencimentoCNH.setText(Util.formatarData(((PessoaFisica) pessoa).getData_vencimentoCNH()));
                lbNumeroCNH.setText(((PessoaFisica) pessoa).getNumero_CNH());
                lbIdentificacao.setText(((PessoaFisica) pessoa).getIdentificacao());

            }
        }

    }

    public void carregarClientes(ArrayList<Pessoa> pessoas) {
        ArrayList<Pessoa> clientes = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof PessoaFisica) {
                clientes.add(pessoa);
            }

            if (pessoa instanceof PessoaJuridica) {
                clientes.add(pessoa);
            }

        }

        colunaCodigoCliente.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));

        obsPessoas = FXCollections.observableArrayList(clientes);

        tableViewClientes.setItems(obsPessoas);

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

            controller.setPessoa(pessoa);

            stage.showAndWait();

            return controller.isConfirmou();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Button getBtnPesquisar() {
        return btnPesquisar;
    }

    public Button getBtnInserirCliente() {
        return BtnInserirCliente;
    }

    public Button getBtnAlterar() {
        return btnAlterar;
    }

    public Button getBtnExcluir() {
        return BtnExcluir;
    }

}
