/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewReservasPorPeriodo;
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

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneRelatorioReservasPorPeriodoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<ViewReservasPorPeriodo> tableView;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, Integer> colunaIdReserva;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, String> colunaNomeCliente;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, Date> colunaDataDaReserva;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, Boolean> colunaEfetivada;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, Double> colunaValor;
    
    @FXML
    private TableColumn<ViewReservasPorPeriodo, String> colunaNomeCategoria;
    
    @FXML
    private JFXDatePicker dpInicial;
    
    @FXML
    private JFXDatePicker dpFinal;
    
    @FXML
    private JFXButton btnImprimir;
    
    private Fachada fachada;
    private ObservableList<ViewReservasPorPeriodo> obs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
    }
    
    @FXML
    void acaoBtn(ActionEvent event) throws DAOException, JRException {
        InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioReservasPorPeriodo.jrxml");
        ArrayList<ViewReservasPorPeriodo> vrpps = fachada.buscarReservaPorPeriodo(Util.converterLocalDateEmDate(dpInicial.getValue()),
                    Util.converterLocalDateEmDate(dpFinal.getValue()));
        
        Util.gerarRelatorio(url, vrpps);
    }
    
    @FXML
    void acaoDate(ActionEvent event) throws DAOException {
        
        if (dpInicial != null) {
            carregarTabela(fachada.buscarReservaPorPeriodo(Util.converterLocalDateEmDate(dpInicial.getValue()),
                    Util.converterLocalDateEmDate(dpFinal.getValue())));
        }
    }
    
    public void carregarTabela(ArrayList<ViewReservasPorPeriodo> vrpps) {
        colunaDataDaReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
        colunaEfetivada.setCellValueFactory(new PropertyValueFactory<>("efetivada"));
        colunaIdReserva.setCellValueFactory(new PropertyValueFactory<>("reservaId"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomecliente"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorPrevisto"));
        colunaNomeCategoria.setCellValueFactory(new PropertyValueFactory<>("nomeCategoria"));
        
        obs = FXCollections.observableArrayList(vrpps);
        
        tableView.setItems(obs);
    }
}
