/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd_20182_sistema_locadora_de_veiculo.controller;

import br.com.pbd_20182_sistema_locadora_de_veiculo.model.FadeTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import App.App;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class FXMLSplashController implements Initializable {

    @FXML
    private AnchorPane parent;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        FadeTransition.applyFadeTransition(parent, Duration.seconds(2), (e) -> {
            try {
                App.stage.close();
                Stage primaryStage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLLogin.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {
                    @Override
                    public Object call(Class<?> param) {
                        return new FXMLLoginController(primaryStage);
                    }
                });
                Pane root = loader.load();

                //Pane root = FXMLLoader.load(getClass().getResource("/br/com/pbd_20182_sistema_locadora_de_veiculo/view/FXMLVboxTelaPrincipal.fxml"));
                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setTitle("LOCADORA DE VEÍCULOS PAJEÚ");
            } catch (IOException ex) {
                Logger.getLogger(FXMLSplashController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                App.stage.setX(event.getScreenX() - xOffset);
                App.stage.setY(event.getScreenY() - yOffset);
                App.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            App.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            App.stage.setOpacity(1.0f);
        });

    }

}
