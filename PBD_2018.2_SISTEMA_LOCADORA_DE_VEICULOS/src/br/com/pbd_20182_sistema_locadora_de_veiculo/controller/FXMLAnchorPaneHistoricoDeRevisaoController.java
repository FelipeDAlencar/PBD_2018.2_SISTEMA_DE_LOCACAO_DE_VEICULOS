/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Revisao;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Veiculo;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewHistoricoPorVeiculo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.InputStream;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneHistoricoDeRevisaoController implements Initializable {

    @FXML
    private TableView<ViewHistoricoPorVeiculo> tableView;

    @FXML
    private TableColumn<ViewHistoricoPorVeiculo, Integer> colunaRevisao;

    @FXML
    private TableColumn<ViewHistoricoPorVeiculo, String> colunaVeiculo;

    @FXML
    private TableColumn<ViewHistoricoPorVeiculo, String> colunaPlacaDoVeiculo;

    @FXML
    private TableColumn<ViewHistoricoPorVeiculo, Date> colunaDataDeRevisao;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private JFXComboBox<Veiculo> comboVeiculo;

    @FXML
    private JFXButton btnListarTodos;

    private Fachada fachada;
    private ObservableList<ViewHistoricoPorVeiculo> obs;
    private boolean verificarListagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();
        verificarListagem = true;
        try {
            carregarCombo();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneHistoricoDeRevisaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void acaoBtns(ActionEvent e) throws DAOException, JRException {
        if (e.getSource() == btnListarTodos) {
            carregarTabela(fachada.findAll());
            verificarListagem = true;
        } else {
            InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/HistoricoDeRevisoes.jrxml");
            ArrayList<ViewHistoricoPorVeiculo> vhpvs = null;
            if (verificarListagem) {
                vhpvs = fachada.findAll();
            } else {
                vhpvs = fachada.revisoesPorVeiculo(comboVeiculo.getValue());
            }

            Util.gerarRelatorio(url, vhpvs);
        }
    }

    @FXML
    void acaoCombo(ActionEvent event) throws DAOException {
        carregarTabela(fachada.revisoesPorVeiculo(comboVeiculo.getValue()));
        verificarListagem = false;
    }

    private void carregarCombo() throws DAOException {

        ObservableList<Veiculo> obsVeiculo = FXCollections.observableArrayList(fachada.listarTodosVeiculo());
        comboVeiculo.setItems(obsVeiculo);

    }

    private void carregarTabela(ArrayList<ViewHistoricoPorVeiculo> revisoesPorVeiculo) {
        colunaDataDeRevisao.setCellValueFactory(new PropertyValueFactory<>("dataDeRevisao"));
        colunaPlacaDoVeiculo.setCellValueFactory(new PropertyValueFactory<>("placaVeiculo"));
        colunaRevisao.setCellValueFactory(new PropertyValueFactory<>("revisaoId"));
        System.err.println(revisoesPorVeiculo);
        colunaVeiculo.setCellValueFactory(new PropertyValueFactory<>("modeloVeiculo"));
        obs = FXCollections.observableArrayList(revisoesPorVeiculo);
        tableView.setItems(obs);
    }

}
