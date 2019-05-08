/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class PuntoVentaController{

    @FXML
    public BorderPane mainPane;
    
    public PuntoVentaController(){
        FXMLLoader loader = new FXMLLoader(PuntoVentaController.class.getClass().getResource("/mx/empenofacil/gui/PuntoVenta.fxml"));
        loader.setController(this);
        
        try {
            mainPane = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PuntoVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BorderPane getMainPane(){
        return mainPane;
    }
    
}
