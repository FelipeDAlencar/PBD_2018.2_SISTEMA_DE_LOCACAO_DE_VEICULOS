/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.BusinessExpection;
import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Pessoa;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.ReservaPessoasCategorias;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private String buscaCliente = "";
    private String buscaCategoria = "";

    @FXML
    private JFXComboBox<Pessoa> comboClientes;

    @FXML
    private JFXTextField tfValor;

    @FXML
    private JFXDatePicker dpData;

    @FXML
    private JFXCheckBox cbEfetivada;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    private JFXComboBox<Categoria> comboCategorias;


    private Fachada fachada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fachada = Fachada.getInstance();
        try {
            carregarCombos();
        } catch (DAOException ex) {
            ex.getMessage();
        }

        adicionarOuvinte();

        MascarasTF.mascaraNumero(tfValor);
    }

    @FXML
    void acaoBtns(ActionEvent event) throws BusinessExpection {
        if (event.getSource() == btnCancelar) {
            stage.close();
        } else {
            try {
                reservaPessoasCategorias.setPessoa(comboClientes.getValue());
                reservaPessoasCategorias.setCategoria(comboCategorias.getValue());

                Date s = Date.from(dpData.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                Calendar dataHora = Calendar.getInstance();

                Calendar c1 = Calendar.getInstance();
                c1.setTime(new Date());
                dataHora.setTime(s);

                dataHora.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
                dataHora.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
                dataHora.set(Calendar.SECOND, c1.get(Calendar.SECOND));

                reservaPessoasCategorias.setDataHora(dataHora.getTime());
                reservaPessoasCategorias.setValorPrevisto(Double.parseDouble(tfValor.getText()));
                reservaPessoasCategorias.setEfetivada(cbEfetivada.isSelected());

                reservaPessoasCategorias.setAtivo(true);

                confirmado = true;

                stage.close();
            } catch (NullPointerException e) {
                //throw new BusinessExpection("Informe o campos corretamente.");
                confirmado = true;
                stage.close();
            }

        }
    }

    @FXML
    void AcaoCombos(ActionEvent event) {

        if (event.getSource() == comboCategorias) {

        }

    }

    public void carregarCombos() throws DAOException {

        ArrayList<Pessoa> pessoas = fachada.listarTodosPessoa();
        ArrayList<Categoria> categorias = fachada.listarTodosCategoria();

        ObservableList<Categoria> obsCategorias;
        ObservableList<Pessoa> obsPessoas;

        obsCategorias = FXCollections.observableArrayList(categorias);
        obsPessoas = FXCollections.observableArrayList(pessoas);

        comboCategorias.setItems(obsCategorias);
        comboClientes.setItems(obsPessoas);

    }

    private void adicionarOuvinte() {
        comboClientes.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {

                KeyCode code = evt.getCode();
                ObservableList<Pessoa> obsPessoa;

                try {

                    if (code.isLetterKey()) {
                        buscaCliente += evt.getText();
                        obsPessoa = FXCollections.
                                observableArrayList(fachada.buscarPorBuscaPessoa(buscaCliente));
                        comboClientes.setItems(obsPessoa);
                    }

                    if (code == KeyCode.BACK_SPACE && buscaCliente.length() > 0) {
                        buscaCliente = buscaCliente.substring(0, buscaCliente.length() - 1);
                        obsPessoa = FXCollections.
                                observableArrayList(fachada.buscarPorBuscaPessoa(buscaCliente));

                        comboClientes.setItems(obsPessoa);

                    }
                    if (code == KeyCode.ESCAPE) {
                        buscaCliente = "";
                    }

                } catch (DAOException ex) {
                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        comboCategorias.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent evt) {

                KeyCode code = evt.getCode();
                ObservableList<Categoria> obsCategoria;

                try {

                    if (code.isLetterKey()) {
                        buscaCategoria += evt.getText();
                        obsCategoria = FXCollections.
                                observableArrayList(fachada.buscarPorBuscaCategoria(buscaCategoria));
                        comboCategorias.setItems(obsCategoria);
                    }

                    if (code == KeyCode.BACK_SPACE && buscaCategoria.length() > 0) {
                        buscaCategoria = buscaCategoria.substring(0, buscaCategoria.length() - 1);
                        obsCategoria = FXCollections.
                                observableArrayList(fachada.buscarCategoriaPorNome(buscaCategoria));

                        comboCategorias.setItems(obsCategoria);
                    }
                    if (code == KeyCode.ESCAPE) {
                        buscaCategoria = "";
                    }

                } catch (DAOException ex) {
                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
//        comboVeiculo.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//            @Override
//            public void handle(KeyEvent evt) {
//                System.err.println(buscaCliente);
//                System.err.println(buscaMotorista);
//                System.err.println(buscaVeiculo);
//
//                KeyCode code = evt.getCode();
//                ObservableList<Veiculo> obsveiculos;
//
//                try {
//
//                    if (code.isLetterKey()) {
//                        buscaVeiculo += evt.getText();
//                    }
//                    obsveiculos = FXCollections.
//                            observableArrayList(fachada.buscarPorBuscaVeiculo(buscaVeiculo));
//                    comboVeiculo.setItems(obsveiculos);
//
//                    if (code == KeyCode.BACK_SPACE && buscaVeiculo.length() > 0) {
//                        buscaVeiculo = buscaVeiculo.substring(0, buscaVeiculo.length() - 1);
//                        comboVeiculo.setItems(obsveiculos);
//                    }
//                    if (code == KeyCode.ESCAPE) {
//                        buscaVeiculo = "";
//                    }
//
//                } catch (DAOException ex) {
//                    Logger.getLogger(FXMLAnchorPaneCadastroLocacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
//        });

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

        if (reservaPessoasCategorias.getId() != null) {
            tfValor.setText(String.valueOf(reservaPessoasCategorias.getValorPrevisto()));
            comboCategorias.setValue(reservaPessoasCategorias.getCategoria());
            comboClientes.setValue(reservaPessoasCategorias.getPessoa());
            dpData.setValue(Util.converterDateEmLocalDate(reservaPessoasCategorias.getDataHora()));

        }
    }

}
