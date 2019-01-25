/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ContaAReceber;
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
public class FXMLAnchorPaneRelatorioContasAReceberController implements Initializable {

    @FXML
    private TableView<ContaAReceber> tableView;

    @FXML
    private TableColumn<ContaAReceber, String> colunaDescricao;

    @FXML
    private TableColumn<ContaAReceber, Double> colunaValor;

    @FXML
    private TableColumn<ContaAReceber, Date> colunaDataDeRecebimento;

    @FXML
    private TableColumn<ContaAReceber, Boolean> colunaPago;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private JFXButton btnListarTodos;

    @FXML
    private JFXDatePicker dataInicial;

    @FXML
    private JFXDatePicker dataFinal;

    private Fachada fachada;
    private Boolean verificarListagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        verificarListagem = true;
    }

    @FXML
    void acaoBtns(ActionEvent e) throws DAOException, JRException {
        if (e.getSource() == btnListarTodos) {
            carregarTabela(fachada.listarTodasContasAReceber());
            verificarListagem = true;
        } else {
            InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioContasAReceber.jrxml");
            if (verificarListagem) {
                Util.gerarRelatorio(url, fachada.listarTodasContasAReceber());
            } else {
                Util.gerarRelatorio(url, fachada.buscarContaAReceberPorPeriodo(Util.converterLocalDateEmDate(dataInicial.getValue()),
                        Util.converterLocalDateEmDate(dataFinal.getValue())));
            }
        }
    }

    @FXML
    void acaoDp(ActionEvent event) throws DAOException {
        if (dataInicial.getValue() != null) {
            carregarTabela(fachada.buscarContaAReceberPorPeriodo(Util.converterLocalDateEmDate(dataInicial.getValue()),
                    Util.converterLocalDateEmDate(dataFinal.getValue())));
            verificarListagem = false;
        }
    }

    public void carregarTabela(ArrayList<ContaAReceber> cars) {

        tableView.getItems().clear();

        colunaDataDeRecebimento.setCellValueFactory(new PropertyValueFactory<>("dataRecebimento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaPago.setCellValueFactory(new PropertyValueFactory<>("pago"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableView.setItems(FXCollections.observableArrayList(cars));

    }
}
