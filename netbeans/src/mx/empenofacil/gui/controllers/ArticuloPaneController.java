/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Consts;

/**
 *
 * @author Razer
 */
public class ArticuloPaneController extends VBox implements Initializable {
    
    private static final String SELECCIONAR = "Seleccionar";
    private static final String DESELECCIONAR = "Deseleccionar";
    
    private SeleccionarArticulosController mainController;
    private Articulo articulo;
    private boolean esSeleccionado;
    
    @FXML
    private Button btn_accion;
    
    @FXML
    private Label lbl_nombre;
    
    @FXML
    private Label lbl_descripcion;
    
    @FXML
    private Label lbl_precio;
    
    @FXML
    private Button btn_editar;

    public ArticuloPaneController(SeleccionarArticulosController mainController, Articulo articulo, boolean esSeleccionado) {
        this.mainController = mainController;
        this.articulo = articulo;
        this.esSeleccionado = esSeleccionado;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/ArticuloPane.fxml"));
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
        lbl_nombre.setText(articulo.getNombre());
        lbl_descripcion.setText(articulo.getDescripcion());
        lbl_precio.setText(articulo.getPrecio().toPlainString());
        if (esSeleccionado) {
            btn_accion.setText(DESELECCIONAR);
        } else {
            btn_accion.setText(SELECCIONAR);
        }
        actualizarBotonEditar();
    }
    
    private void actualizarBotonEditar () {
        if (articulo.getStatus().getIditemcatalogo() != Consts.ID_STATUS_ARTICULO_DISPONIBLE) {
            btn_editar.setDisable(true);
            return;
        }
        
        if (esSeleccionado) {
            btn_editar.setDisable(true);
        } else {
            btn_editar.setDisable(false);
        }
    }
    
    @FXML
    private void cambiarEstado(ActionEvent e) {
        if (esSeleccionado) {
            mainController.deseleccionar(articulo);
            esSeleccionado = false;
            btn_accion.setText(SELECCIONAR);
        } else {
            mainController.seleccionar(articulo);
            esSeleccionado = true;
            btn_accion.setText(DESELECCIONAR);
        }
        actualizarBotonEditar();
    }
    
    @FXML
    private void editar(ActionEvent e) {
        Dialogs.mostrarDialog(new EditarArticuloPaneController(articulo), "Modificar");
        mainController.actualizarResultados();
    }
    
}
