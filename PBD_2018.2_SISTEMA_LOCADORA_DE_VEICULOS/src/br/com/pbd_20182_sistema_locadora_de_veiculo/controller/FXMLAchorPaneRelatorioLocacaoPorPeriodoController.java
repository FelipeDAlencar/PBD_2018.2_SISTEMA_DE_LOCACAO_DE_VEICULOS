/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewLocacaoPorPeriodo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
public class FXMLAchorPaneRelatorioLocacaoPorPeriodoController implements Initializable {

    @FXML
    private TableView<ViewLocacaoPorPeriodo> tableView;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, Integer> colunaIdLocacao;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, String> colunaNomeCliente;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, Date> colunaDataIda;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, Date> colunaDataVolta;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, Double> colunaValor;

    @FXML
    private TableColumn<ViewLocacaoPorPeriodo, Double> colunaFinalizada;
    @FXML
    private JFXDatePicker dpInicial;

    @FXML
    private JFXDatePicker dpFinal;

    @FXML
    private JFXButton btnImprimir;

    private Fachada fachada;
    private ObservableList<ViewLocacaoPorPeriodo> obsView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
    }

    @FXML
    void acaoBtn(ActionEvent event) throws JRException, DAOException {
        InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioLocacoesPorPeriodo.jrxml");
        
        ArrayList<ViewLocacaoPorPeriodo> vlpps = fachada.buscarLocacaoPorPeriodo(Util.converterLocalDateEmDate(dpInicial.getValue()),
                    Util.converterLocalDateEmDate(dpFinal.getValue()));
        
        Util.gerarRelatorio(url,vlpps);
    }

    @FXML
    void acaoDate(ActionEvent event) throws DAOException {

        if (dpInicial.getValue() != null) {

            carregarTabela(fachada.buscarLocacaoPorPeriodo(Util.converterLocalDateEmDate(dpInicial.getValue()),
                    Util.converterLocalDateEmDate(dpFinal.getValue())));
        }
    }

    public void carregarTabela(ArrayList<ViewLocacaoPorPeriodo> vlpps) {

        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaDataIda.setCellValueFactory(new PropertyValueFactory<>("dataIda"));
        colunaDataVolta.setCellValueFactory(new PropertyValueFactory<>("dataVolta"));
        colunaIdLocacao.setCellValueFactory(new PropertyValueFactory<>("locacaoID"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaFinalizada.setCellValueFactory(new PropertyValueFactory<>("finalizada"));
        obsView = FXCollections.observableArrayList(vlpps);

        tableView.setItems(obsView);

    }

}
