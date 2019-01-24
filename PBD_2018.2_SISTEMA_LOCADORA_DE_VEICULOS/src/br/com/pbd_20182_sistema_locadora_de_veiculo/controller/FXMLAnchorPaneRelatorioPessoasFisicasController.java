/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaFisica;
import java.net.URL;
import java.sql.Connection;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneRelatorioPessoasFisicasController implements Initializable {

    @FXML
    private TableView<PessoaFisica> tableViewRelatorio;

    @FXML
    private TableColumn<PessoaFisica, String> colunaNome;

    @FXML
    private TableColumn<PessoaFisica, String> colunaCPF;

    @FXML
    private TableColumn<PessoaFisica, String> colunaCNH;

    @FXML
    private TableColumn<PessoaFisica, Date> colunaDataCNH;

    private Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        try {
            carregarTabela();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioPessoasFisicasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void acaoBtnImprimir(ActionEvent event) throws JRException {
        Connection conexao = SQLUtil.getConnectionInstance(SQLUtil.NOME_BD_CONEXAO_POSTGRES);

        URL url = getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioPessosFisicas.jrfxml");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        //null por que n�o estamos usando filtro.
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conexao);

        //false pq n�o deixa feixar a aplica��o principal
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }

    private void carregarTabela() throws DAOException {
        colunaCNH.setCellValueFactory(new PropertyValueFactory<>("numero_CNH"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        colunaDataCNH.setCellValueFactory(new PropertyValueFactory<>("data_vencimentoCNH"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        ObservableList<PessoaFisica> obsPessoaFisica = FXCollections.observableArrayList(fachada.listarTodosPessoaFisica());

        tableViewRelatorio.setItems(obsPessoaFisica);
    }

}
