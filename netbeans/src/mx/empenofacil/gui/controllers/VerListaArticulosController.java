/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mx.empenofacil.beans.Articulo;

/**
 *
 * @author Razer
 */
public class VerListaArticulosController extends VBox implements Initializable {
    
    private List<Articulo> articulos;
    
    @FXML
    private ListView<String> lst_articulos;

    public VerListaArticulosController(List<Articulo> articulos) {
        this.articulos = articulos;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/VerListaArticulos.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = lst_articulos.getItems();
        
        for (Articulo articulo : articulos) {
            items.add(articulo.getNombre() + " [" + articulo.getDescripcion() + "] $" + articulo.getPrecio().toPlainString() + " MXN");
        }
    }
    
    @FXML
    private void cerrar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }
    
}
