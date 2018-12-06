/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Endereco;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Filial;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.sun.deploy.util.FXLoader;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroFilialController implements Initializable {
    
    @FXML
    private TableView<Filial> tableView;
    
    @FXML
    private TableColumn<Filial, String> colunaNomeFilial;
    
    @FXML
    private TableColumn<Filial, String> colunaRua;
    
    @FXML
    private TableColumn<Filial, String> colunaUF;
    
    @FXML
    private Label lbNomeFilial;
    
    @FXML
    private Label lbCidade;
    
    @FXML
    private Label lbRua;
    
    @FXML
    private Label lbBairro;
    
    @FXML
    private Label lbNumero;
    
    @FXML
    private Label lbUF;
    
    @FXML
    private Button btnInserir;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private Button btnPesquisar;
    
    private Fachada fachada;
    private ArrayList<Filial> filiais;
    private ObservableList<Filial> obsFiliais;
    
    @FXML
    void acaoBtns(ActionEvent event) throws DAOException, BusinessExpection {
        
        if (event.getSource() == btnInserir) {
            Filial filial = new Filial();
            boolean sucesso = exibirTelaDeCadastro(filial);
            
            if (sucesso) {
                
                fachada.salvarFilial(filial);
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Inserir Filial", "Filial "
                        + "Inserida com Sucesso.");
                
                carregarFiliais(fachada.listarTodosFilial());
            }
        }
        
        if (event.getSource() == btnEditar) {
            
            Filial filial = tableView.getSelectionModel().getSelectedItem();
            
            if (filial != null) {
                
                boolean sucesso = exibirTelaDeCadastro(filial);
                
                if (sucesso) {
                    fachada.salvarFilial(filial);
                    
                    Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                    alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Edição realizada "
                            + "com sucesso");
                    
                    carregarFiliais(fachada.listarTodosFilial());
                }
                
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", "Por favor, selecione "
                        + "a filial que deseja editar");
            }
            
        }
        
        if (event.getSource() == btnExcluir) {
            Filial filial = tableView.getSelectionModel().getSelectedItem();
            
            if (filial != null) {
                filial.setAtivo(false);
                fachada.salvarFilial(filial);
                carregarFiliais(fachada.listarTodosFilial());
                
            } else {
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Atenção", "Por favor, selecione "
                        + "a filial que deseja excluir");
            }
            
        }
        
        if (event.getSource() == btnPesquisar) {
            System.err.println("Entrou");
            if (tfPesquisa.getText().length() != 0) {
                carregarFiliais(fachada.buscarPorBuscaFilial(tfPesquisa.getText()));
            } else {
                carregarFiliais(fachada.listarTodosFilial());
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        try {
            carregarFiliais(fachada.listarTodosFilial());
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        
        colunaNomeFilial.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colunaUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionouDaTabea(newValue));
        
    }
    
    private void carregarFiliais(ArrayList<Filial> filials) throws DAOException {
        
        obsFiliais = FXCollections.observableArrayList(filials);
        
        tableView.setItems(obsFiliais);
    }
    
    private void selecionouDaTabea(Filial filial) {
        
        if (filial != null) {
            lbBairro.setText(filial.getEndereco().getBairro());
            lbCidade.setText(filial.getEndereco().getCidade());
            lbNomeFilial.setText(filial.getNome());
            lbNumero.setText(String.valueOf(filial.getEndereco().getNumero()));
            lbUF.setText(filial.getEndereco().getUf());
            lbRua.setText(filial.getEndereco().getRua());
        }
    }
    
    private boolean exibirTelaDeCadastro(Filial filial) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLAnchorPaneCadastroFilialController.class.getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroFilialDialog.fxml"));
            Pane pane = loader.load();
            
            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            
            FXMLAnchorPaneCadastroFilialDialogController controller = loader.getController();
            controller.setStage(stage);
            controller.setFilial(filial);
            
            stage.showAndWait();
            
            return controller.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return false;
        
    }

    public TableView<Filial> getTableView() {
        return tableView;
    }

    public TableColumn<Filial, String> getColunaNomeFilial() {
        return colunaNomeFilial;
    }

    public TableColumn<Filial, String> getColunaRua() {
        return colunaRua;
    }

    public TableColumn<Filial, String> getColunaUF() {
        return colunaUF;
    }

    public Label getLbNomeFilial() {
        return lbNomeFilial;
    }

    public Label getLbCidade() {
        return lbCidade;
    }

    public Label getLbRua() {
        return lbRua;
    }

    public Label getLbBairro() {
        return lbBairro;
    }

    public Label getLbNumero() {
        return lbNumero;
    }

    public Label getLbUF() {
        return lbUF;
    }

    public Button getBtnInserir() {
        return btnInserir;
    }

    public Button getBtnEditar() {
        return btnEditar;
    }

    public Button getBtnExcluir() {
        return btnExcluir;
    }

    public TextField getTfPesquisa() {
        return tfPesquisa;
    }

    public Button getBtnPesquisar() {
        return btnPesquisar;
    }

    public Fachada getFachada() {
        return fachada;
    }

    public ArrayList<Filial> getFiliais() {
        return filiais;
    }

    public ObservableList<Filial> getObsFiliais() {
        return obsFiliais;
    }
    
    
    
    
}
