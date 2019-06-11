/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Consts;
import mx.empenofacil.dao.ArticuloDAO;

/**
 *
 * @author Razer
 */
public class SeleccionarArticulosController extends VBox implements Initializable {
    
    private boolean soloDisponibles;
    private List<Articulo> articulosSeleccionados;
    
    @FXML
    private TextField txt_busqueda;
    
    @FXML
    private Label lbl_mensaje;
    
    @FXML
    private VBox container_resultados;
    
    @FXML
    private Label lbl_numarticulos;
    
    @FXML
    private Label lbl_total;

    public SeleccionarArticulosController(boolean soloDisponibles) {
        this.soloDisponibles = soloDisponibles;
        articulosSeleccionados = new ArrayList<>();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/SeleccionarArticulos.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SeleccionarArticulosController(boolean soloDisponibles, List<Articulo> articulosSeleccionados) {
        this.soloDisponibles = soloDisponibles;
        this.articulosSeleccionados = articulosSeleccionados;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/SeleccionarArticulos.fxml"));
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
        lbl_mensaje.setText(null);
        actualizarLabels();
        
    }
    
    @FXML
    private void buscar(ActionEvent e) {
        String busqueda = txt_busqueda.getText();
        
        if (busqueda == null || busqueda.isEmpty()) {
            lbl_mensaje.setText("Escriba consulta");
            return;
        }
        
        lbl_mensaje.setText(null);
        
        List<Articulo> articulos = ArticuloDAO.buscar(busqueda);
        
        container_resultados.getChildren().clear();
        
        int numResultados = 0;
        
        for (Articulo articulo : articulos) {
            if (!soloDisponibles || (soloDisponibles && articulo.getStatus().getIditemcatalogo() == Consts.ID_STATUS_ARTICULO_DISPONIBLE)) {
                container_resultados.getChildren().add(new ArticuloPaneController(
                    this,
                    articulo,
                    articulosSeleccionados.contains(articulo)
                ));
                numResultados++;
            }
        }
        
        lbl_mensaje.setText(numResultados + " resultados");
    }
    
    @FXML
    private void cancelar(ActionEvent e) {
        articulosSeleccionados.clear();
        cerrar();
    }
    
    @FXML
    private void aceptar(ActionEvent e) {
        if (articulosSeleccionados.isEmpty()) {
            lbl_mensaje.setText("No hay articulos seleccionados");
        } else {
            cerrar();
        }
    }
    
    private void cerrar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    public List<Articulo> getArticulosSeleccionados() {
        return articulosSeleccionados;
    }
    
    public void actualizarResultados() {
        buscar(null);
    }
    
    public void seleccionar(Articulo articulo) {
        this.articulosSeleccionados.add(articulo);
        actualizarLabels();
    }
    
    public void deseleccionar(Articulo articulo) {
        this.articulosSeleccionados.remove(articulo);
        actualizarLabels();
    }
    
    private void actualizarLabels() {
        this.lbl_numarticulos.setText(Integer.toString(articulosSeleccionados.size()));
        BigDecimal total = BigDecimal.ZERO;
        for (Articulo articulo : articulosSeleccionados) {
            total = total.add(articulo.getPrecio());
        }
        this.lbl_total.setText(total.toPlainString());
    }
    
}
