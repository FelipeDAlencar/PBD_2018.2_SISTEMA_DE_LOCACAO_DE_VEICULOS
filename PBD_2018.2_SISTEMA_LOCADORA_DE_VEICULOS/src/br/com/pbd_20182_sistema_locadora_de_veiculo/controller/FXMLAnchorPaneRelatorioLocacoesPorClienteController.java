/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Locacao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneRelatorioLocacoesPorClienteController implements Initializable {

    @FXML
    private TableView<Locacao> tableViewLocacao;
    @FXML
    private TableColumn<Locacao, Pessoa> colunaCliente;

    @FXML
    private TableColumn<Locacao, PessoaFisica> colunaMotorista;

    @FXML
    private TableColumn<Locacao, Veiculo> colunaVeiculo;

    @FXML
    private TableColumn<Locacao, Date> colunaDataDeLocacao;

    @FXML
    private TableColumn<Locacao, Date> colunaDataFinal;

    @FXML
    private TableColumn<Locacao, Double> colunaValor;

    @FXML
    private TableColumn<Locacao, Boolean> colunaFinalizada;

    @FXML
    private TableColumn<Locacao, Boolean> colunaKmLivre;

    @FXML
    private JFXComboBox<Pessoa> comboCliente;

    @FXML
    private JFXButton btnImprimir;

    private Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fachada = Fachada.getInstance();

        try {
            carregarCombo();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioLocacoesPorClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void acaoBtnImprimir(ActionEvent event) throws JRException, DAOException {

        InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioLocacaoPorCliente.jrxml");
        Util.gerarRelatorio(url, fachada.buscarLocacaoPorCliente(comboCliente.getValue()));

    }

    @FXML
    void acaoCombo(ActionEvent event) throws DAOException {

        carregarTabela(fachada.buscarLocacaoPorCliente(comboCliente.getValue()));

    }

    public void carregarTabela(ArrayList<Locacao> locacaos) {

        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaMotorista.setCellValueFactory(new PropertyValueFactory<>("motorista"));
        colunaVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));

        colunaDataDeLocacao.setCellValueFactory(new PropertyValueFactory<>("dataIda"));
        colunaDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataVolta"));

        colunaFinalizada.setCellValueFactory(new PropertyValueFactory<>("finalizada"));
        colunaKmLivre.setCellValueFactory(new PropertyValueFactory<>("kmLivre"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        ObservableList obsLocacoes = FXCollections.observableArrayList(locacaos);

        tableViewLocacao.setItems(obsLocacoes);

    }

    public void carregarCombo() throws DAOException {

        ObservableList<Pessoa> obsPessoas = FXCollections.observableArrayList(fachada.listarTodosPessoa());
        comboCliente.setItems(obsPessoas);

    }

}
