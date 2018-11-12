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
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.NoResultException;

/**
 *
 * @author Felipe
 */
public class FXMLLoginController implements Initializable {

    private Stage stage;
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnEntrar;

    @FXML
    private PasswordField tfSenha;

    private DAOPessoa dAOPessoa = new DAOPessoa();
    private FXMLVBoxTelaPrincipalController controllerPrincipal;
    private FXMLAnchorPaneTelaDeMudancaDeSenhaController controllerMudancaSenha;

    public FXMLLoginController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void acaoBtnEntrar(ActionEvent event) throws BusinessExpection, DAOException {
        Pessoa pessoa = new Pessoa();
        pessoa.setLogin(tfLogin.getText());
        pessoa.setSenha(tfSenha.getText());
        pessoa = dAOPessoa.buscarLogin(pessoa);
        if (pessoa != null) {
            if(!verificarPrimeiroAcesso(pessoa)){
                 exibirTelaPrincipal(pessoa);
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void verificarTipoUsuarioLogin(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica || pessoa instanceof PessoaJuridica) {

            controllerPrincipal.getMenuItemClientes().setDisable(true);
            controllerPrincipal.getMenuItemFuncionarios().setDisable(true);
            controllerPrincipal.getMenuItemLocacoes().setDisable(true);
            controllerPrincipal.getMenuAdministracao().setDisable(true);
            controllerPrincipal.getMenuRelatorios().setDisable(true);

        } else if (pessoa instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) pessoa;
            if (funcionario.isSuperUsuario()) {
                System.out.println("Tem privilegios");
            } else {
                controllerPrincipal.getMenuAdministracao().setDisable(true);
            }

        }

    }

    private boolean verificarPrimeiroAcesso(Pessoa pessoa) throws BusinessExpection {

        Fachada fachada = Fachada.getInstance();
        boolean sucesso = false;
        try {
            if (pessoa instanceof PessoaFisica) {
                if (((PessoaFisica) pessoa).getCPF().equals(((PessoaFisica) pessoa).getSenha())) {
                    sucesso = exibirTelaRedefinirSenha(pessoa);
                    if (sucesso) {
                        fachada.salvarPessoaFisica((PessoaFisica) pessoa);
                    }
                }
            } else if (pessoa instanceof PessoaJuridica) {
                if (((PessoaJuridica) pessoa).getCNPJ().equals(((PessoaJuridica) pessoa).getSenha())) {
                    sucesso = exibirTelaRedefinirSenha(pessoa);
                    if (sucesso) {
                        fachada.salvarPessoaJuridica((PessoaJuridica) pessoa);
                    }
                }
            } else if (pessoa instanceof Funcionario) {
                if (((Funcionario) pessoa).getMatricula().equals(((Funcionario) pessoa).getSenha())) {
                    sucesso = exibirTelaRedefinirSenha(pessoa);
                    if (sucesso) {
                        fachada.salvarFuncionario((Funcionario) pessoa);
                    }
                }
            }   
            if(sucesso){
                exibirTelaPrincipal(pessoa);
            }
            
        } catch (BusinessExpection e) {
            throw new BusinessExpection("ERRO AO TENTAR REDEFINIR A SENHA");
        }
        return sucesso;

    }

    public boolean exibirTelaRedefinirSenha(Pessoa pessoa) {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLAnchorPaneTelaDeMudancaDeSenhaController.class
                .getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneTelaDeMudancaDeSenha.fxml"));

        Pane root;
        try {
            root = load.load();
            Stage stageRedefinir = new Stage();
            Scene scene = new Scene(root);
            stageRedefinir.setScene(scene);
            controllerMudancaSenha = load.getController();

            controllerMudancaSenha.setPessoa(pessoa);
            controllerMudancaSenha.setStage(stageRedefinir);

            stageRedefinir.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return controllerMudancaSenha.isSucesso();

    }

    public void exibirTelaPrincipal(Pessoa pessoa) {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLVBoxTelaPrincipalController.class
                .getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVBoxTelaPrincipal.fxml"));

        Pane root;
        try {
            root = load.load();
            controllerPrincipal = load.getController();

            verificarTipoUsuarioLogin(pessoa);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.stage.close();
    }
}
