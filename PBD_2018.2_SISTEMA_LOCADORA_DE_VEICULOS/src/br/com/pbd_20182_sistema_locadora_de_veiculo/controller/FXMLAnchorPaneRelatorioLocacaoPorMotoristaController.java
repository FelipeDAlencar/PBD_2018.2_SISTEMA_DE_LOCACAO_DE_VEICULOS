/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ViewMotoristaPorLocacao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
public class FXMLAnchorPaneRelatorioLocacaoPorMotoristaController implements Initializable {

    @FXML
    private TableView<ViewMotoristaPorLocacao> tableView;

    @FXML
    private TableColumn<ViewMotoristaPorLocacao, Integer> colunaIdLocacao;

    @FXML
    private TableColumn<ViewMotoristaPorLocacao, String> colunaNumeroCnh;

    @FXML
    private TableColumn<ViewMotoristaPorLocacao, String> colunaNomeVeiculo;

    @FXML
    private TableColumn<ViewMotoristaPorLocacao, String> colunaNomeCliente;

    @FXML
    private JFXComboBox<PessoaFisica> comboMotorista;

    @FXML
    private JFXButton btnImprimir;

    Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        try {
            carregarcoCombo();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioLocacaoPorMotoristaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void acaBtn(ActionEvent event) throws JRException, DAOException {

        InputStream url = getClass().getResourceAsStream("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioLocacaoPorMotorista.jrxml");
        Util.gerarRelatorio(url, fachada.buscarMotoristaPorLocacao(comboMotorista.getValue()));
    }

    @FXML
    void acaoCombo(ActionEvent event) throws DAOException {

        ArrayList<ViewMotoristaPorLocacao> vmpls = new ArrayList<>();
        ViewMotoristaPorLocacao viewMotoristaPorLocacao = new ViewMotoristaPorLocacao();
        viewMotoristaPorLocacao.setLocacaoId(0);
        viewMotoristaPorLocacao.setModeloVeiculo("Fusca");
        viewMotoristaPorLocacao.setNomeCliente("Felipe");
        viewMotoristaPorLocacao.setNumeroCnh("111");
        vmpls.add(viewMotoristaPorLocacao);

        carregarTabela(fachada.buscarMotoristaPorLocacao(comboMotorista.getValue()));
    }

    private void carregarcoCombo() throws DAOException {

        ObservableList<PessoaFisica> obs
                = FXCollections.observableArrayList(fachada.listarTodosPessoaFisica());

        comboMotorista.setItems(obs);

    }

    private void carregarTabela(ArrayList<ViewMotoristaPorLocacao> vmpls) {
        colunaIdLocacao.setCellValueFactory(new PropertyValueFactory<>("locacaoId"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaNomeVeiculo.setCellValueFactory(new PropertyValueFactory<>("modeloVeiculo"));
        colunaNumeroCnh.setCellValueFactory(new PropertyValueFactory<>("numeroCnh"));

        ObservableList<ViewMotoristaPorLocacao> obs = FXCollections.observableArrayList(vmpls);

        tableView.setItems(obs);
    }
}
