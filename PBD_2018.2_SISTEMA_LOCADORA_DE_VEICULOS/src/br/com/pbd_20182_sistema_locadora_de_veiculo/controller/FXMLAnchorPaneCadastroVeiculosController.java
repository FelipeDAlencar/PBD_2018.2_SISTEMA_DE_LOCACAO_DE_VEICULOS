/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.sun.deploy.util.FXLoader;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroVeiculosController implements Initializable {
    
    @FXML
    private TableView<Veiculo> tableView;
    
    @FXML
    private TableColumn<Veiculo, String> colunaModelo;
    
    @FXML
    private TableColumn<Veiculo, String> colunaCor;
    
    @FXML
    private TableColumn<Veiculo, Integer> colunaAnoModelo;
    
    @FXML
    private Button btnPesquisar;
    
    @FXML
    private Label lbModelo;
    
    @FXML
    private Label lbFabricante;
    
    @FXML
    private Label lbAnoModelo;
    
    @FXML
    private Label lbAnoDeFabricacao;
    
    @FXML
    private Label lbCor;
    
    @FXML
    private Label lbPlaca;
    
    @FXML
    private Label lbNChassi;
    
    @FXML
    private Label lbKmAtual;
    
    @FXML
    private Label lbTipoCombustivel;
    
    @FXML
    private Label lbTorqueDoMotor;
    
    @FXML
    private Label lbCategoria;
    
    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private CheckBox cbDisponível;
    @FXML
    private Button btnInserir;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnExcluir;
    
    private Fachada fachada;
    private ArrayList<Veiculo> veiculos;
    private ObservableList<Veiculo> obsVeiculos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        try {
            carregaVeiculos(fachada.listarTodosVeiculo());
        } catch (DAOException ex) {
            ex.getMessage();
        }
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabela(newValue));
        
        cbDisponível.setDisable(true);
    }
    
    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {
        if (event.getSource() == btnInserir) {
            Veiculo veiculo = new Veiculo();
            
            boolean sucesso = exibirTelaDeCadastro(veiculo);
            
            if (sucesso) {
                
                fachada.salvarVeiculo(veiculo);
                
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso", "Inserção de Veículos", ""
                        + "Inserção realidade com sucesso!");
                
                carregaVeiculos(fachada.listarTodosVeiculo());
                
            }
            
        }
        
        if (event.getSource() == btnEditar) {
            Veiculo veiculo = tableView.getSelectionModel().getSelectedItem();
            
            if (veiculo != null) {
                boolean sucesso = exibirTelaDeCadastro(veiculo);
                
                if (sucesso) {
                    
                    fachada.salvarVeiculo(veiculo);
                    
                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso", "Inserção de Veículos", ""
                            + "Inserção realizada com sucesso!");
                    
                    carregaVeiculos(fachada.listarTodosVeiculo());
                    
                }
                
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", ""
                        + "Selecione o veículo na tabela para edição!");
            }
        }
        
        if (event.getSource() == btnExcluir) {
            Veiculo veiculo = tableView.getSelectionModel().getSelectedItem();
            
            if (veiculo != null) {
                
                veiculo.setAtivo(false);
                fachada.salvarVeiculo(veiculo);
                
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.CONFIRMATION, "Sucesso", "Exclusão de Veículos", ""
                        + "Exclusão realizada com sucesso!");
                
                carregaVeiculos(fachada.listarTodosVeiculo());
                
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", ""
                        + "Selecione o veículo na tabela para exclusão!");
            }
        }
        
        if(event.getSource() == btnPesquisar){
            System.err.println("Entrou");
            if(tfPesquisa.getText().length() != 0){
                carregaVeiculos(fachada.buscarPorBuscaVeiculo(tfPesquisa.getText()));
                System.err.println(fachada.buscarPorBuscaVeiculo(tfPesquisa.getText()));
            }else{
                carregaVeiculos(fachada.listarTodosVeiculo());
            }
        }
    }
    
    private void carregaVeiculos(ArrayList<Veiculo> veiculos) throws DAOException {
        
        
        
        colunaAnoModelo.setCellValueFactory(new PropertyValueFactory<>("anoDoModelo"));
        colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        
        obsVeiculos = FXCollections.observableArrayList(veiculos);
        
        tableView.setItems(obsVeiculos);
    }
    
    private void selecionouDaTabela(Veiculo veiculo) {
        
        if (veiculo != null) {
            
            lbAnoDeFabricacao.setText(String.valueOf(veiculo.getAnoDeFabricacao()));
            lbAnoModelo.setText(String.valueOf(veiculo.getAnoDoModelo()));
            lbCategoria.setText(veiculo.getCategoria().toString());
            lbCor.setText(veiculo.getCor());
            lbFabricante.setText(veiculo.getFabricante());
            lbKmAtual.setText(String.valueOf(veiculo.getQuilometragemAtual()));
            lbModelo.setText(veiculo.getModelo());
            lbNChassi.setText(String.valueOf(veiculo.getNumeroChassi()));
            lbPlaca.setText(veiculo.getPlaca());
            lbTipoCombustivel.setText(veiculo.getTipoDeCombustivel());
            lbTorqueDoMotor.setText(String.valueOf(veiculo.getTorqueDoMotor()));
            cbDisponível.setSelected(veiculo.isDisponivel());
        }
        
    }
    
    private boolean exibirTelaDeCadastro(Veiculo veiculo) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroVeiculosDialogController.class.getResource("/br/com"
                    + "/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroVeiculosDialog.fxml"));
            Pane pane = loader.load();
            
            FXMLAnchorPaneCadastroVeiculosDialogController controller = loader.getController();
            
            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            
            controller.setStage(stage);
            controller.setVeiculo(veiculo);
            
            stage.showAndWait();
            
            return controller.isSucesso();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
        
    }
    
}
