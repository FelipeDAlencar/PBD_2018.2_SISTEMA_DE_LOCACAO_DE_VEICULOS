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
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLVBoxTelaPrincipalController implements Initializable {
    
    @FXML
    private MenuItem menuItemClientes;
    
    @FXML
    private MenuItem menuItemFuncionarios;
    
    @FXML
    private MenuItem menuItemVeiculos;
    
    @FXML
    private MenuItem menuItemReservas;
    
    @FXML
    private MenuItem menuItemLocacoes;
    
    @FXML
    private MenuItem menuItemCategoria;
    
    @FXML
    private MenuItem menuiItemFilial;
    
    @FXML
    private MenuItem menuItemHistoricoDeRevisoes;
    @FXML
    private Menu menuRelatorios;
    
    @FXML
    private MenuItem menuItemClientesFisicos;
    
    @FXML
    private MenuItem menuItemPessoasJuridicas;
    
    @FXML
    private MenuItem menuItemReservasPorPeriodo;
    
    @FXML
    private MenuItem menuItemLocacoesPorPeriodo;
    
    @FXML
    private MenuItem menuItemLocacoesPorCliente;
    
    @FXML
    private MenuItem MenuItemLocacoesPorMotorista;
    
    @FXML
    private Menu menuAdministracao;
    
    @FXML
    private MenuItem menuItemConfiguracoes;
    
    @FXML
    private MenuItem menuItemSair;
    
    @FXML
    private Menu menuFinanceiro;
    
    @FXML
    private MenuItem menuItemContasAReceber;
    
    @FXML
    private MenuItem menuItemContasAPagar;
    
    @FXML
    private MenuItem meniItemRelatorioContaAPagar;
    
    @FXML
    private MenuItem meniItemRelatorioContaAReceber;
    
    @FXML
    private MenuItem menuItemAlterarSenha;
    
    @FXML
    private AnchorPane AnchorPaneContent;
    
    private Fachada fachada;
    private DAOPessoa daop;
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        daop = new DAOPessoa();
        
    }
    
    @FXML
    void acaoMenuFinanceiro(ActionEvent event) throws IOException {
        Pane pane;
        
        if (event.getSource() == menuItemContasAPagar) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneContaAPagar.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        if (event.getSource() == menuItemContasAReceber) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneContasAReceber.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
    }
    
    @FXML
    void acaosMenuItensCadastros(ActionEvent event) throws IOException {
        Pane pane;
        
        if (event.getSource() == menuItemClientes) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCliente.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuItemReservas) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroReserva.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
            
        }
        
        if (event.getSource() == menuItemCategoria) {
            
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroCategoria.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
            
        }
        
        if (event.getSource() == menuItemVeiculos) {
            
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroVeiculos.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuiItemFilial) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroFilial.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuItemFuncionarios) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroFuncionario.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuItemLocacoes) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneCadastroLocacao.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
    }
    
    @FXML
    void acaoMenuItemConfiguracoes(ActionEvent e) throws IOException, DAOException, BusinessExpection {
        Pane pane;
        
        if (e.getSource() == menuItemConfiguracoes) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneGeral.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (e.getSource() == menuItemSair) {
            stage.close();
            
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLLogin.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    return new FXMLLoginController(primaryStage);
                }
            });
            Pane root = loader.load();

            //Pane root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVboxTelaPrincipal.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("LOCADORA DE VEÍCULOS PAJEÚ");
        }
        
        if (e.getSource() == menuItemAlterarSenha) {
            boolean sucesso = exibirTelaRedefinirSenha(FXMLLoginController.usuario);
            
            if (sucesso) {
                FXMLLoginController.usuario.setSenha(daop.criptografarSenha(FXMLLoginController.usuario.getSenha()));
                fachada.salvarPessoa(FXMLLoginController.usuario);
                
                Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
                alerta.alertar(Alert.AlertType.INFORMATION, "Sucesso", "Alteração de senha", "Senha alterada com sucesso.");
            }
        }
        
    }
    
    @FXML
    void acoesMenuItensRelatorios(ActionEvent event) throws IOException {
        Pane pane;
        
        if (event.getSource() == menuItemClientesFisicos) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioPessoasFisicas.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuItemPessoasJuridicas) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioPessoasJuridicas.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
            
        }
        if (event.getSource() == menuItemLocacoesPorCliente) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioLocacoesPorCliente.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        if (event.getSource() == MenuItemLocacoesPorMotorista) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioLocacaoPorMotorista.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        if (event.getSource() == menuItemLocacoesPorPeriodo) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAchorPaneRelatorioLocacaoPorPeriodo.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        if (event.getSource() == menuItemReservasPorPeriodo) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioReservasPorPeriodo.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == menuItemHistoricoDeRevisoes) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneHistoricoDeRevisao.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        
        if (event.getSource() == meniItemRelatorioContaAPagar) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioContaAPagar.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
        if (event.getSource() == meniItemRelatorioContaAReceber) {
            pane = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLAnchorPaneRelatorioContasAReceber.fxml"));
            AnchorPaneContent.getChildren().setAll(pane);
        }
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
            FXMLAnchorPaneTelaDeMudancaDeSenhaController controllerMudancaSenha = load.getController();
            
            controllerMudancaSenha.setPessoa(pessoa);
            controllerMudancaSenha.setStage(stageRedefinir);
            
            stageRedefinir.showAndWait();
            return controllerMudancaSenha.isSucesso();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return false;
        
    }
    
    public MenuItem getMenuItemClientes() {
        return menuItemClientes;
    }
    
    public MenuItem getMenuItemFuncionarios() {
        return menuItemFuncionarios;
    }
    
    public MenuItem getMenuItemVeiculos() {
        return menuItemVeiculos;
    }
    
    public MenuItem getMenuItemReservas() {
        return menuItemReservas;
    }
    
    public MenuItem getMenuItemLocacoes() {
        return menuItemLocacoes;
    }
    
    public MenuItem getMenuItemClientesFisicos() {
        return menuItemClientesFisicos;
    }
    
    public MenuItem getMenuItemPessoasJuridicas() {
        return menuItemPessoasJuridicas;
    }
    
    public MenuItem getMenuItemReservasPorPeriodo() {
        return menuItemReservasPorPeriodo;
    }
    
    public MenuItem getMenuItemLocacoesPorPeriodo() {
        return menuItemLocacoesPorPeriodo;
    }
    
    public MenuItem getMenuItemLocacoesPorCliente() {
        return menuItemLocacoesPorCliente;
    }
    
    public MenuItem getMenuItemLocacoesPorMotorista() {
        return MenuItemLocacoesPorMotorista;
    }
    
    public Menu getMenuAdministracao() {
        return menuAdministracao;
    }
    
    public AnchorPane getAnchorPaneContent() {
        return AnchorPaneContent;
    }
    
    public Menu getMenuRelatorios() {
        return menuRelatorios;
    }
    
    public MenuItem getMenuItemCategoria() {
        return menuItemCategoria;
    }
    
    public void setMenuItemCategoria(MenuItem menuItemCategoria) {
        this.menuItemCategoria = menuItemCategoria;
    }
    
    public MenuItem getMenuItemSair() {
        return menuItemSair;
    }
    
    public void setMenuItemSair(MenuItem menuItemSair) {
        this.menuItemSair = menuItemSair;
    }
    
    public MenuItem getMenuItemConfiguracoes() {
        return menuItemConfiguracoes;
    }
    
    public void setMenuItemConfiguracoes(MenuItem menuItemConfiguracoes) {
        this.menuItemConfiguracoes = menuItemConfiguracoes;
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
        
        if (stage != null) {
            stage.setOnCloseRequest((event) -> {
                System.exit(0);
            });
            
        }
    }
    
    public MenuItem getMenuiItemFilial() {
        return menuiItemFilial;
    }
    
    public MenuItem getMenuItemHistoricoDeRevisoes() {
        return menuItemHistoricoDeRevisoes;
    }
    
    public void setMenuItemHistoricoDeRevisoes(MenuItem menuItemHistoricoDeRevisoes) {
        this.menuItemHistoricoDeRevisoes = menuItemHistoricoDeRevisoes;
    }
    
    public Menu getMenuFinanceiro() {
        return menuFinanceiro;
    }
    
    public void setMenuFinanceiro(Menu menuFinanceiro) {
        this.menuFinanceiro = menuFinanceiro;
    }
    
    public MenuItem getMenuItemContasAReceber() {
        return menuItemContasAReceber;
    }
    
    public void setMenuItemContasAReceber(MenuItem menuItemContasAReceber) {
        this.menuItemContasAReceber = menuItemContasAReceber;
    }
    
    public MenuItem getMenuItemContasAPagar() {
        return menuItemContasAPagar;
    }
    
    public void setMenuItemContasAPagar(MenuItem menuItemContasAPagar) {
        this.menuItemContasAPagar = menuItemContasAPagar;
    }
    
}
