/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.empenofacil.gui.controllers.HomeController;

/**
 *
 * @author adolf
 */
public class Starter extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Starter.class.getResource("/mx/empenofacil/gui/InicioSesion.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        HomeController.setStage(primaryStage);
        primaryStage.show();
    }
    
    public static void main(String args[]){
        launch(args);
    }
}
