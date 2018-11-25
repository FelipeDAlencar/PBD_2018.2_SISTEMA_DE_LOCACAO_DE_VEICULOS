/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLAnchorPaneCadastroCategoriaDialogController implements Initializable {

    private Stage stage;
    private boolean confirmar;
    private Categoria categoria;
    private Fachada fachada;
    private String ultimoNome = "";

    @FXML
    private Label lbAirbag;

    @FXML
    private Label lbCintoSeguranca;

    @FXML
    private Label lbDirecaoAssistida;

    @FXML
    private Label lbRodasDeLigaLeve;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNPortas;

    @FXML
    private TextField tfNPassageiros;

    @FXML
    private CheckBox cbArCondicionado;

    @FXML
    private CheckBox cbMP3;

    @FXML
    private CheckBox cbDVD;

    @FXML
    private CheckBox cbDirecaoHidraulica;

    @FXML
    private CheckBox cbRadio;

    @FXML
    private CheckBox cbTipoCambio;

    @FXML
    private CheckBox cbCameraDeRe;

    @FXML
    private CheckBox cbCintoSeguranca;

    @FXML
    private CheckBox cbDirecaoAssistida;

    @FXML
    private Label lbControleDePoluicaoDoAr;

    @FXML
    private CheckBox cbAirBag;

    @FXML
    private CheckBox cbRodasDeLigaLeve;

    @FXML
    private CheckBox cbControleDePoluicaoDoAr;

    @FXML
    private RadioButton rbCategoria;

    @FXML
    private RadioButton rbCategoriaCarga;

    @FXML
    private RadioButton rbCategoriaPassageiros;

    @FXML
    private Button btnConfirmar;

    @FXML
    void acoesBtns(ActionEvent event) {
        try {
            if (event.getSource() == btnConfirmar) {
                if (rbCategoria.isSelected()) {

                    categoria = new Categoria();

                    categoria.setArCondicionado(cbArCondicionado.isSelected());
                    categoria.setCameraDeRe(cbCameraDeRe.isSelected());
                    categoria.setDescricacao(tfDescricao.getText());
                    categoria.setDirecaoHidraulica(cbDirecaoHidraulica.isSelected());
                    categoria.setRadio(cbRadio.isSelected());
                    categoria.setTipoDeCambio(cbTipoCambio.isSelected());
                    categoria.setDvd(cbDVD.isSelected());
                    categoria.setMp3(cbMP3.isSelected());
                    categoria.setNome(tfNome.getText());
                    categoria.setNumeroDePassageiros(Integer.parseInt(tfNPassageiros.getText()));
                    categoria.setNumeroDePortas(Integer.parseInt(tfNPortas.getText()));
                    categoria.setValor(Double.parseDouble(tfValor.getText()));

                } else if (rbCategoriaPassageiros.isSelected()) {
                    categoria = new CaminhonetaDePassageiros();

                    categoria.setArCondicionado(cbArCondicionado.isSelected());
                    categoria.setCameraDeRe(cbCameraDeRe.isSelected());
                    categoria.setDescricacao(tfDescricao.getText());
                    categoria.setDirecaoHidraulica(cbDirecaoHidraulica.isSelected());
                    categoria.setRadio(cbRadio.isSelected());
                    categoria.setTipoDeCambio(cbTipoCambio.isSelected());
                    categoria.setDvd(cbDVD.isSelected());
                    categoria.setMp3(cbMP3.isSelected());

                    ((CaminhonetaDePassageiros) categoria).setCintoDeSeguranca(cbCintoSeguranca.isSelected());
                    ((CaminhonetaDePassageiros) categoria).setDirecaoAssistida(cbDirecaoAssistida.isSelected());
                    ((CaminhonetaDePassageiros) categoria).setAirBag(cbAirBag.isSelected());
                    ((CaminhonetaDePassageiros) categoria).setRodaDeLigaLeve(cbRodasDeLigaLeve.isSelected());
                    ((CaminhonetaDePassageiros) categoria).setControleDePoluicaoDoAr(cbControleDePoluicaoDoAr.isSelected());
                    
                    
                    
                    categoria.setNome(tfNome.getText());
                    categoria.setNumeroDePassageiros(Integer.parseInt(tfNPassageiros.getText()));
                    categoria.setNumeroDePortas(Integer.parseInt(tfNPortas.getText()));
                    categoria.setValor(Double.parseDouble(tfValor.getText()));
                }

                confirmar = true;
                stage.close();

            } else {
                stage.close();
            }
        } catch (NumberFormatException e) {
            Alerta alerta = Alerta.getInstace(Alert.AlertType.NONE);
            alerta.alertar(Alert.AlertType.WARNING, "Atenção", "Muito cuidado!", "Campos de numéricos "
                    + "não devem conter letras");
        }
    }

    @FXML
    void acoesRadios(ActionEvent event) throws DAOException {
        if (event.getSource() == rbCategoriaPassageiros) {

            lbAirbag.setVisible(true);
            lbCintoSeguranca.setVisible(true);
            lbControleDePoluicaoDoAr.setVisible(true);
            lbDirecaoAssistida.setVisible(true);
            lbRodasDeLigaLeve.setVisible(true);

            cbAirBag.setVisible(true);
            cbCintoSeguranca.setVisible(true);
            cbControleDePoluicaoDoAr.setVisible(true);
            cbDirecaoAssistida.setVisible(true);
            cbRodasDeLigaLeve.setVisible(true);

            ultimoNome = fachada.buscarUltimoNomeCaminhonetaDePassageiros();
            tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 3));

        } else if (event.getSource() == rbCategoria) {

            lbAirbag.setVisible(false);
            lbCintoSeguranca.setVisible(false);
            lbControleDePoluicaoDoAr.setVisible(false);
            lbDirecaoAssistida.setVisible(false);
            lbRodasDeLigaLeve.setVisible(false);

            cbAirBag.setVisible(false);
            cbCintoSeguranca.setVisible(false);
            cbControleDePoluicaoDoAr.setVisible(false);
            cbDirecaoAssistida.setVisible(false);
            cbRodasDeLigaLeve.setVisible(false);

            ultimoNome = fachada.buscarUltimoNomeCategoria();
            tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 1));

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        rbCategoria.setToggleGroup(tg);
        rbCategoriaCarga.setToggleGroup(tg);
        rbCategoriaPassageiros.setToggleGroup(tg);

        lbAirbag.setVisible(false);
        lbCintoSeguranca.setVisible(false);
        lbControleDePoluicaoDoAr.setVisible(false);
        lbDirecaoAssistida.setVisible(false);
        lbRodasDeLigaLeve.setVisible(false);

        cbAirBag.setVisible(false);
        cbCintoSeguranca.setVisible(false);
        cbControleDePoluicaoDoAr.setVisible(false);
        cbDirecaoAssistida.setVisible(false);
        cbRodasDeLigaLeve.setVisible(false);

        fachada = Fachada.getInstance();

        try {
            ultimoNome = fachada.buscarUltimoNomeCategoria();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroCategoriaDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 1));

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isConfirmar() {
        return confirmar;
    }

    public void setConfirmar(boolean confirmar) {
        this.confirmar = confirmar;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
