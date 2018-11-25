/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOCategoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.dao.DAOPessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroReservaDialogController implements Initializable {

    private Stage stage;
    private boolean confirmado;
    private ReservaPessoasCategorias reservaPessoasCategorias;
    private DAOPessoa dAOPessoa;
    private DAOCategoria dAOCategoria;

    @FXML
    private ComboBox<Pessoa> comboClientes;

    @FXML
    private ComboBox<Categoria> comboCategorias;

    @FXML
    private TextField tfValor;

    @FXML
    private DatePicker dpData;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    void acaoBtns(ActionEvent event) {
        if (event.getSource() == btnCancelar) {
            stage.close();
        } else {
            reservaPessoasCategorias.setCategoria(comboCategorias.getValue());
            reservaPessoasCategorias.setPessoa(comboClientes.getValue());

            Date s = Date.from(dpData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            SimpleDateFormat spdf = new SimpleDateFormat("dd/yy/dddd HH:mm:ss");
            Calendar dataHora = Calendar.getInstance();

            Calendar c1 = Calendar.getInstance();
            c1.setTime(new Date());
            dataHora.setTime(s);

            dataHora.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
            dataHora.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
            dataHora.set(Calendar.SECOND, c1.get(Calendar.SECOND));

            
            reservaPessoasCategorias.setDataHora(dataHora.getTime());
            reservaPessoasCategorias.setValorPrevisto(Double.parseDouble(tfValor.getText()));
            confirmado = true;
            stage.close();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dAOCategoria = new DAOCategoria();
        dAOPessoa = new DAOPessoa();
        carregarCombos();

    }

    public void carregarCombos() {
        try {
            ArrayList<Pessoa> pessoas = dAOPessoa.buscarTodos();
            ArrayList<Categoria> categorias = dAOCategoria.findAll();

            ObservableList<Categoria> obsCategorias;
            ObservableList<Pessoa> obsPessoas;

            obsCategorias = FXCollections.observableArrayList(categorias);
            obsPessoas = FXCollections.observableArrayList(pessoas);

            comboCategorias.setItems(obsCategorias);
            comboClientes.setItems(obsPessoas);

        } catch (DAOException ex) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.INFORMATION, "Erro", "Erro no banco de dados", "Erro"
                    + "tentar acessar o banco de dados.");
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public ReservaPessoasCategorias getReservaPessoasCategorias() {
        return reservaPessoasCategorias;
    }

    public void setReservaPessoasCategorias(ReservaPessoasCategorias reservaPessoasCategorias) {
        this.reservaPessoasCategorias = reservaPessoasCategorias;
    }

}
