/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAPagar;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class FXMLAnchorPaneRelatorioContaAPagarController implements Initializable {

    @FXML
    private TableView<ContaAPagar> tableView;

    @FXML
    private TableColumn<ContaAPagar, String> colunaDescricao;

    @FXML
    private TableColumn<ContaAPagar, Double> colunaValor;

    @FXML
    private TableColumn<ContaAPagar, Date> colunaDataDeVencimento;

    @FXML
    private TableColumn<ContaAPagar, Boolean> colunaPago;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private JFXButton btnListarTodos;

    @FXML
    private JFXDatePicker dataInicial;

    @FXML
    private JFXDatePicker dataFinal;

    private Fachada fachada;
    private boolean verificarListagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        verificarListagem = true;
    }

    @FXML
    void acaoBtns(ActionEvent e) throws DAOException, JRException {
        if (e.getSource() == btnListarTodos) {
            carregarTabela(fachada.listarTodasContasAPagar());
            verificarListagem = true;
        } else {
            InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioContasAPagar.jrxml");
            if (verificarListagem) {

                Util.gerarRelatorio(url, fachada.listarTodasContasAPagar());
            } else {
                Util.gerarRelatorio(url, fachada.buscarContaAPagarPorPeriodo(Util.converterLocalDateEmDate(dataInicial.getValue()),
                        Util.converterLocalDateEmDate(dataFinal.getValue())));
            }
        }
    }

    @FXML
    void acaoDp(ActionEvent event) throws DAOException {
        if (dataInicial.getValue() != null) {
            carregarTabela(fachada.buscarContaAPagarPorPeriodo(Util.converterLocalDateEmDate(dataInicial.getValue()),
                    Util.converterLocalDateEmDate(dataFinal.getValue())));
            verificarListagem = false;
        }
    }

    public void carregarTabela(ArrayList<ContaAPagar> caps) {
        tableView.getItems().clear();

        colunaDataDeVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaPago.setCellValueFactory(new PropertyValueFactory<>("pago"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableView.setItems(FXCollections.observableArrayList(caps));

    }
}
