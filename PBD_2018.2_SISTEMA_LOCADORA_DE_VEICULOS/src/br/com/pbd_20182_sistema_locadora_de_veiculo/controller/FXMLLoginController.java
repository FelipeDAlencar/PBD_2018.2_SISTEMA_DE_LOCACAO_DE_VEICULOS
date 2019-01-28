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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ThreadDeVerificacaoDeReservas;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ThreadBackup;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ThreadDeVerificacaoDeLocacoes;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Felipe
 */
public class FXMLLoginController implements Initializable {

    private Stage stage;

    @FXML
    private JFXTextField tfLogin;

    @FXML
    private JFXPasswordField tfSenha;

    @FXML
    private JFXButton btnEntrar;

    private DAOPessoa dAOPessoa = new DAOPessoa();
    private FXMLVBoxTelaPrincipalController controllerPrincipal;
    private FXMLAnchorPaneTelaDeMudancaDeSenhaController controllerMudancaSenha;
    public static Pessoa usuario;

    public FXMLLoginController(Stage stage) {
        this.stage = stage;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ThreadDeVerificacaoDeReservas threadDeVerificacaoDeReservas = new ThreadDeVerificacaoDeReservas();
        ThreadDeVerificacaoDeLocacoes threadDeVerificacaoDeLocacoes = new ThreadDeVerificacaoDeLocacoes();
        ThreadBackup threadBackup = new ThreadBackup();

        Thread thread = new Thread(threadDeVerificacaoDeReservas);
        thread.setDaemon(true);
        thread.start();

        Thread thread2 = new Thread(threadDeVerificacaoDeLocacoes);
        thread2.setDaemon(true);
        thread2.start();

        Thread thread3 = new Thread(threadBackup);
        thread3.setDaemon(true);
        thread3.start();
        stage.setOnCloseRequest((event) -> {
            System.exit(0);
        });

    }

    @FXML
    void acaoBtnEntrar(ActionEvent event) throws BusinessExpection, DAOException {
        Pessoa pessoa = new Pessoa();
        pessoa.setLogin(tfLogin.getText());
        pessoa.setSenha(dAOPessoa.criptografarSenha(tfSenha.getText()));
        System.out.println(dAOPessoa.criptografarSenha("10859046443"));

        pessoa = dAOPessoa.buscarLogin(pessoa);

        this.usuario = pessoa;
        if (pessoa != null) {
            if (!verificarPrimeiroAcesso(pessoa)) {
                exibirTelaPrincipal(pessoa);

            }

        }

    }

    private void verificarTipoUsuarioLogin(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica || pessoa instanceof PessoaJuridica) {

            controllerPrincipal.getMenuItemClientes().setDisable(true);
            controllerPrincipal.getMenuItemFuncionarios().setDisable(true);
            controllerPrincipal.getMenuItemLocacoes().setDisable(true);
            controllerPrincipal.getMenuItemConfiguracoes().setDisable(true);
            controllerPrincipal.getMenuRelatorios().setDisable(true);
            controllerPrincipal.getMenuiItemFilial().setDisable(true);
            controllerPrincipal.getMenuItemCategoria().setDisable(true);
            controllerPrincipal.getMenuFinanceiro().setDisable(true);
            

        } else if (pessoa instanceof Funcionario) {

            Funcionario funcionario = (Funcionario) pessoa;
            if (!(funcionario.isSuperUsuario())) {

                controllerPrincipal.getMenuItemConfiguracoes().setDisable(true);
                controllerPrincipal.getMenuFinanceiro().setDisable(true);
                controllerPrincipal.getMenuItemLocacoesPorPeriodo().setDisable(true);
                controllerPrincipal.getMenuItemReservasPorPeriodo().setDisable(true);
            }

        }

    }

    private boolean verificarPrimeiroAcesso(Pessoa pessoa) throws BusinessExpection, DAOException {

        Fachada fachada = Fachada.getInstance();
        boolean PrimeiroAcesso = false;

        DAOPessoa dAOPessoa = new DAOPessoa();

        //       try {
        if (pessoa instanceof PessoaFisica) {
            if (dAOPessoa.criptografarSenha(((PessoaFisica) pessoa).getCPF()).equals(((PessoaFisica) pessoa).getSenha())) {
                pessoa = ((PessoaFisica) pessoa);
                PrimeiroAcesso = true;
            }
        } else if (pessoa instanceof PessoaJuridica) {
            if (dAOPessoa.criptografarSenha(((PessoaJuridica) pessoa).getCNPJ()).equals(((PessoaJuridica) pessoa).getSenha())) {
                pessoa = ((PessoaJuridica) pessoa);
                PrimeiroAcesso = true;
            }
        } else if (pessoa instanceof Funcionario) {
            if (dAOPessoa.criptografarSenha(((Funcionario) pessoa).getMatricula()).equals(((Funcionario) pessoa).getSenha())) {
                pessoa = ((Funcionario) pessoa);

                PrimeiroAcesso = true;

            }
        }

        if (PrimeiroAcesso) {
            boolean sucesso = exibirTelaRedefinirSenha(pessoa);
            if (sucesso) {
                pessoa.setSenha(dAOPessoa.criptografarSenha(pessoa.getSenha()));
                dAOPessoa.salvar(pessoa);
                exibirTelaPrincipal(pessoa);
            }
        }
//            if (pessoa instanceof PessoaFisica) {
//                if (dAOPessoa.criptografarSenha(((PessoaFisica) pessoa).getCPF()).equals(((PessoaFisica) pessoa).getSenha())) {
//                    sucesso = exibirTelaRedefinirSenha(pessoa);
//                    if (sucesso) {
//                        fachada.salvarPessoaFisica((PessoaFisica) pessoa);
//                       
//                    }
//                }
//            } else if (pessoa instanceof PessoaJuridica) {
//                if (dAOPessoa.criptografarSenha(((PessoaJuridica) pessoa).getCNPJ()).equals(((PessoaJuridica) pessoa).getSenha())) {
//                    sucesso = exibirTelaRedefinirSenha(pessoa);
//                    if (sucesso) {
//                        fachada.salvarPessoaJuridica((PessoaJuridica) pessoa);
//                        
//                    }
//                }
//            } else if (pessoa instanceof Funcionario) {
//                if (dAOPessoa.criptografarSenha(((Funcionario) pessoa).getMatricula()).equals(((Funcionario) pessoa).getSenha())) {
//                    sucesso = exibirTelaRedefinirSenha(pessoa);
//                    System.err.println("Sucesso" + sucesso);
//                    if (sucesso) {
//                        fachada.salvarFuncionario((Funcionario) pessoa);
//                        
//                    }
//                }
//            }
//            if (sucesso) {
//
//                pessoa.setSenha(dAOPessoa.criptografarSenha(pessoa.getSenha()));
//                dAOPessoa.salvar(pessoa);
//                exibirTelaPrincipal(pessoa);
//            }
//        } catch (BusinessExpection e) {
//            throw new BusinessExpection("ERRO AO TENTAR REDEFINIR A SENHA");
//        }
        //catch(DAOException e){
//            e.printStackTrace();
//        }
//        sucesso = controllerMudancaSenha.isSucesso();
//        System.err.println(sucesso);
        return PrimeiroAcesso;

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
            stageRedefinir.setOnCloseRequest(event -> controllerMudancaSenha.setSucesso(false));

            stageRedefinir.showAndWait();
            return controllerMudancaSenha.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;

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
            stage.setTitle("LOCADORA DE VEÍCULOS PAJEÚ");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            controllerPrincipal.setStage(stage);

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.stage.close();
    }
}
