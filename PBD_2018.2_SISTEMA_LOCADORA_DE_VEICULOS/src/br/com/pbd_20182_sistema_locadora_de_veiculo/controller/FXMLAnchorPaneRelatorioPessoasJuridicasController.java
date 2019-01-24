/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.JPA.SQLUtil;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.PessoaJuridica;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
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
public class FXMLAnchorPaneRelatorioPessoasJuridicasController implements Initializable {

    @FXML
    private TableView<PessoaJuridica> tableViewPJ;

    @FXML
    private TableColumn<PessoaJuridica, String> colunaNome;

    @FXML
    private TableColumn<PessoaJuridica, String> colunaCNPJ;

    @FXML
    private TableColumn<PessoaJuridica, String> colunaInscricaoEstadual;

    @FXML
    private JFXButton btnImprimir;

    private Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachada = Fachada.getInstance();

        try {
            carregarTabela();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioPessoasJuridicasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void acaoBtnImprimir(ActionEvent event) throws JRException {
        Connection conexao = SQLUtil.getConnectionInstance(SQLUtil.NOME_BD_CONEXAO_POSTGRES);

        URL url = getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/relatorios/RelatorioPessoasJuridicas.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        //null por que n�o estamos usando filtro.
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conexao);

        //false pq n�o deixa feixar a aplica��o principal
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }

    private void carregarTabela() throws DAOException {

        colunaCNPJ.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        colunaInscricaoEstadual.setCellValueFactory(new PropertyValueFactory<>("inscriçãoEstadual"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        ObservableList<PessoaJuridica> obsPessoaJuridicas = FXCollections.observableArrayList(fachada.listarTodosPessoaJuridica());

        tableViewPJ.setItems(obsPessoaJuridicas);
    }
}
