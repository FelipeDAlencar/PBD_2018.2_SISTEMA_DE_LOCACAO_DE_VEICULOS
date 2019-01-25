/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.exception.DAOException;
import br.com.pbd_20182_sistema_locadora_de_veiculo.fachada.Fachada;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDeCarga;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.CaminhonetaDePassageiros;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Categoria;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.MascarasTF;
import br.com.pbd_20182_sistema_locadora_de_veiculo.model.Util;
import br.com.pbd_20182_sistema_locadora_de_veiculo.view.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    private boolean isEditar;
    
        @FXML
    private AnchorPane paneScroll;

    @FXML
    private GridPane grid;

    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfValor;

    @FXML
    private JFXTextField tfDescricao;

    @FXML
    private JFXTextField tfNPortas;

    @FXML
    private JFXTextField tfNPassageiros;

    @FXML
    private JFXCheckBox cbArCondicionado;

    @FXML
    private JFXCheckBox cbMP3;

    @FXML
    private JFXCheckBox cbDVD;

    @FXML
    private JFXCheckBox cbDirecaoHidraulica;

    @FXML
    private JFXCheckBox cbRadio;

    @FXML
    private JFXCheckBox cbTipoCambio;

    @FXML
    private JFXCheckBox cbCameraDeRe;

    @FXML
    private JFXRadioButton rbCategoria;

    @FXML
    private JFXRadioButton rbCategoriaCarga;

    @FXML
    private JFXRadioButton rbCategoriaPassageiros;

    @FXML
    private GridPane gridPassageiros;

    @FXML
    private JFXCheckBox cbCintoSeguranca;

    @FXML
    private JFXCheckBox cbDirecaoAssistida;

    @FXML
    private JFXCheckBox cbAirBag;

    @FXML
    private JFXCheckBox cbRodasDeLigaLeve;

    @FXML
    private JFXCheckBox cbControleDePoluicaoDoAr;

    @FXML
    private GridPane gridCarga;

    @FXML
    private JFXTextField tfPotenciaDoMotor;

    @FXML
    private JFXTextField tfDistanciaEntreEixos;

    @FXML
    private JFXTextField tfCapacidadeDeCarga;

    @FXML
    private JFXTextField tfAcionamentoDaEmbreagem;

    @FXML
    private JFXTextField tfDesempenho;

    @FXML
    private JFXTextField tfVolumeSuportado;

    @FXML
    private JFXButton btnConfirmar;

    @FXML
    private JFXButton btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        rbCategoria.setToggleGroup(tg);
        rbCategoriaCarga.setToggleGroup(tg);
        rbCategoriaPassageiros.setToggleGroup(tg);
        
        fachada = Fachada.getInstance();
        
        try {
            ultimoNome = fachada.buscarUltimoNomeCategoria();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroCategoriaDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 1));
        
        tfNome.setEditable(false);
        
        MascarasTF.mascaraNumero(tfCapacidadeDeCarga);
        MascarasTF.mascaraNumero(tfDistanciaEntreEixos);
        MascarasTF.mascaraNumeroInteiro(tfNPortas);
        MascarasTF.mascaraNumeroInteiro(tfNPassageiros);
        MascarasTF.mascaraNumero(tfPotenciaDoMotor);
        MascarasTF.mascaraNumero(tfVolumeSuportado);
        MascarasTF.mascaraNumero(tfValor);
        
    }
    
    @FXML
    void acoesBtns(ActionEvent event) {
        try {
            if (event.getSource() == btnConfirmar) {
                if (rbCategoria.isSelected()) {
                    
                    pegarValoresCategoria();
                    
                }
                if (rbCategoriaPassageiros.isSelected()) {
                    pegarValorCaminhonetaDePassageiros();
                }
                
                if (rbCategoriaCarga.isSelected()) {
                    pegarValoresCaminhonetaDeCarga();
                    
                }
                categoria.setAtivo(true);
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
            
            gridCarga.setVisible(false);
            gridPassageiros.setVisible(true);
            
            ultimoNome = fachada.buscarUltimoNomeCaminhonetaDePassageiros();
            tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 3));
            
        } else if (event.getSource() == rbCategoria) {
            
            gridCarga.setVisible(false);
            gridPassageiros.setVisible(false);
            
            ultimoNome = fachada.buscarUltimoNomeCategoria();
            tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 2));
            
        } else if (event.getSource() == rbCategoriaCarga) {
            
            gridCarga.setVisible(true);
            gridPassageiros.setVisible(false);
            
            ultimoNome = fachada.buscarUltimoNomeCaminhonetaDeCarga();
            tfNome.setText(Util.gerarNomeCategoria(ultimoNome, 2));
        }
        
    }
    
    public void pegarValoresCategoria() {
        if (!(categoria != null)) {
            categoria = new Categoria();
        }
        
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
    }
    
    public void pegarValoresCaminhonetaDeCarga() {
        if (!(categoria != null)) {
            categoria = new CaminhonetaDeCarga();
        }
        
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
        
        ((CaminhonetaDeCarga) categoria).setPotenciaDoMotor(Double.parseDouble(tfPotenciaDoMotor.getText()));
        ((CaminhonetaDeCarga) categoria).setDistanciaEntreEixos(Double.parseDouble(tfDistanciaEntreEixos.getText()));
        ((CaminhonetaDeCarga) categoria).setCapacidadeDeCarga(Double.parseDouble(tfCapacidadeDeCarga.getText()));
        ((CaminhonetaDeCarga) categoria).setAcionamentoDeEmbragem(tfAcionamentoDaEmbreagem.getText());
        ((CaminhonetaDeCarga) categoria).setDesempenho(tfDesempenho.getText());
        ((CaminhonetaDeCarga) categoria).setVolumeDeAbastecimento(Double.parseDouble(tfVolumeSuportado.getText()));
        
    }
    
    public void pegarValorCaminhonetaDePassageiros() {
        if (!(categoria != null)) {
            categoria = new CaminhonetaDePassageiros();
        }
        
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
        
        if (categoria != null) {
            isEditar = true;
            if (categoria.getId() != null) {
                tfDescricao.setText(categoria.getDescricacao());
                tfNome.setText(categoria.getNome());
                tfValor.setText(String.valueOf(categoria.getValor()));
                tfNPortas.setText(String.valueOf(categoria.getNumeroDePortas()));
                tfNPassageiros.setText(String.valueOf(categoria.getNumeroDePassageiros()));
                
                cbArCondicionado.setSelected(categoria.isArCondicionado());
                cbMP3.setSelected(categoria.isMp3());
                cbDVD.setSelected(categoria.isDvd());
                cbDirecaoHidraulica.setSelected(categoria.isDirecaoHidraulica());
                cbRadio.setSelected(categoria.isRadio());
                cbCameraDeRe.setSelected(categoria.isCameraDeRe());
                
                if (categoria instanceof CaminhonetaDeCarga) {
                    gridCarga.setVisible(true);
                    gridPassageiros.setVisible(false);
                    
                    tfPotenciaDoMotor.setText(String.valueOf(((CaminhonetaDeCarga) categoria).
                            getPotenciaDoMotor()));
                    
                    tfDistanciaEntreEixos.setText(String.valueOf(((CaminhonetaDeCarga) categoria).
                            getDistanciaEntreEixos()));
                    
                    tfCapacidadeDeCarga.setText(String.valueOf(((CaminhonetaDeCarga) categoria).getCapacidadeDeCarga()));
                    tfAcionamentoDaEmbreagem.setText(String.valueOf(((CaminhonetaDeCarga) categoria).getAcionamentoDeEmbragem()));
                    tfDescricao.setText(((CaminhonetaDeCarga) categoria).getDescricacao());
                    tfVolumeSuportado.setText(String.valueOf(((CaminhonetaDeCarga) categoria).getVolumeDeAbastecimento()));
                    tfDesempenho.setText(String.valueOf(((CaminhonetaDeCarga) categoria).getDesempenho()));
                    rbCategoriaCarga.setSelected(true);
                    
                } else if (categoria instanceof CaminhonetaDePassageiros) {
                    
                    gridCarga.setVisible(false);
                    gridPassageiros.setVisible(true);
                    
                    cbCintoSeguranca.setSelected(((CaminhonetaDePassageiros) categoria).
                            isCintoDeSeguranca());
                    cbDirecaoAssistida.setSelected(((CaminhonetaDePassageiros) categoria).isDirecaoAssistida());
                    cbAirBag.setSelected(((CaminhonetaDePassageiros) categoria).isAirBag());
                    cbRodasDeLigaLeve.setSelected(((CaminhonetaDePassageiros) categoria).isRodaDeLigaLeve());
                    cbControleDePoluicaoDoAr.setSelected(((CaminhonetaDePassageiros) categoria).isControleDePoluicaoDoAr());
                    
                    rbCategoriaPassageiros.setSelected(true);
                } else {
                    System.err.println("Entrou");
                    gridCarga.setVisible(false);
                    gridPassageiros.setVisible(false);
                }
                
                rbCategoria.setDisable(true);
                rbCategoriaCarga.setDisable(true);
                rbCategoriaPassageiros.setDisable(true);
            }
            
        }
        
    }
    
}
