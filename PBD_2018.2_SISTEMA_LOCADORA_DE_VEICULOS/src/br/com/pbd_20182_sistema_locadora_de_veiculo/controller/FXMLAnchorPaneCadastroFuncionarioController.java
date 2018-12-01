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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
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
import javafx.scene.control.CheckBox;
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
public class FXMLAnchorPaneCadastroFuncionarioController implements Initializable {

    @FXML
    private TableView<Funcionario> tableView;

    @FXML
    private TableColumn<Funcionario, String> colunaNome;

    @FXML
    private TableColumn<Funcionario, String> colunaLogin;

    @FXML
    private TableColumn<Funcionario, String> colunaMatricula;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbMatricula;

    @FXML
    private Label lbCargo;

    @FXML
    private CheckBox cbSuperUsuario;

    @FXML
    private Label lbCidade;

    @FXML
    private Label lbRua;

    @FXML
    private Label lbBairro;

    @FXML
    private Label lbUF;

    @FXML
    private Label LBNumero;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnExcluir;

    private ArrayList<Funcionario> funcionarios;
    private ObservableList<Funcionario> obsFuncionarios;
    private DAOPessoa dAOPessoa;
    private Fachada fachada;
    private Stage stage;

    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {

        if (event.getSource() == btnInserir) {
            Funcionario funcionario = new Funcionario();
            boolean sucesso = exibirTelaCadastro(funcionario);

            if (sucesso) {
                System.err.println("Aqui");
                funcionario.setSenha(dAOPessoa.criptografarSenha(funcionario.getSenha()));
                fachada.salvarFuncionario(funcionario);
                carregarFuncionarios();
                
                
                
                
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso!", "Inserir Funcionario", "Funcionario"
                        + "Inserido com sucesso!");

            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        cbSuperUsuario.setDisable(true);

        dAOPessoa = new DAOPessoa();

        try {
            carregarFuncionarios();
        } catch (DAOException ex) {
            ex.getMessage();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));

    }

    private void carregarFuncionarios() throws DAOException {

        colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        funcionarios = new ArrayList<>();

        for (Pessoa pessoa : dAOPessoa.listarTodos()) {
            if (pessoa instanceof Funcionario) {
                funcionarios.add((Funcionario) pessoa);
            }
        }

        obsFuncionarios = FXCollections.observableArrayList(funcionarios);

        tableView.setItems(obsFuncionarios);

    }

    private void selecionouDaTabela(Funcionario funcionario) {

        if (funcionario != null) {

            LBNumero.setText(String.valueOf(funcionario.getEndereco().getNumero()));
            lbBairro.setText(funcionario.getEndereco().getBairro());
            lbRua.setText(funcionario.getEndereco().getRua());
            lbUF.setText(funcionario.getEndereco().getUf());
            lbCidade.setText(funcionario.getEndereco().getCidade());

            lbCargo.setText(funcionario.getCargo());
            lbCodigo.setText(funcionario.getCodigo());
            lbMatricula.setText(funcionario.getMatricula());
            lbNome.setText(funcionario.getNome());

            cbSuperUsuario.setSelected(funcionario.isSuperUsuario());

        }
    }

    private boolean exibirTelaCadastro(Funcionario funcionario) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroFuncionarioDialogController.class.getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroFuncionarioDialog.fxml"));
        try {

            Pane pane = loader.load();
            stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FXMLAnchorPaneCadastroFuncionarioDialogController controller = loader.getController();
            controller.setFuncionario(funcionario);
            controller.setStage(stage);
            
            
            stage.showAndWait();
            return controller.isSucesso();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
