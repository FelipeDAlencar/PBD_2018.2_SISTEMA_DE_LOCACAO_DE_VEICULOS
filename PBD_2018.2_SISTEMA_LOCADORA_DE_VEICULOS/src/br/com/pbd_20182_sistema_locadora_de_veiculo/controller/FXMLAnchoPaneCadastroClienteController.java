/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchoPaneCadastroClienteController implements Initializable {
     @FXML
    private TableView<PessoaFisica> TableViewClientes;

    @FXML
    private TableColumn<PessoaFisica, String> ColunaNomeCliente;

    @FXML
    private TableColumn<PessoaFisica, String> ColunaCPFCliente;

    @FXML
    private TableColumn<PessoaFisica, String> ColunaCodigoCliente;

    @FXML
    private Label lbNumeroCNH;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbDataNascimento;

    @FXML
    private Label lbCPF;

    @FXML
    private Label DataDeVencimentoCNH;

    @FXML
    private Label DataNascimento;

    @FXML
    private Label lbIdentificacao;

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

    @FXML
    void acoesDeBotao(ActionEvent event) {
        if(event.getSource() == BtnInserirCliente){
            PessoaFisica pessoaFisica = new PessoaFisica();
            if(event.getSource() == BtnInserirCliente){
            boolean confirmou =  exibirTelaDecadastro(pessoaFisica);
            
          }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    
    
    public void carregarClientes(){
        
    }

    private boolean exibirTelaDecadastro(PessoaFisica pessoaFisica) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAchorPaneCadastroClienteDialog.fxml"));
            Pane pane = loader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Clientes");
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            
            
            FXMLAchorPaneCadastroClienteDialogController controller = loader.getController();
            controller.setStage(stage);
            controller.setPessoaFisica(pessoaFisica);
            
            
            stage.showAndWait();
            
                    
            return controller.isConfirmou();
                    
        }catch(IOException e){
        }
       return false;
    }
    
}
