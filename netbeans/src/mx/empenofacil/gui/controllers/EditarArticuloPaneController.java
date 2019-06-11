/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.dao.ArticuloDAO;

/**
 *
 * @author Razer
 */
public class EditarArticuloPaneController extends VBox implements Initializable {
    
    private Articulo articulo;
    
    @FXML
    private TextField txt_nombre;
    
    @FXML
    private TextField txt_descripcion;
    
    @FXML
    private Spinner<Double> spn_precio;
    
    @FXML
    private Label lbl_mensaje;

    public EditarArticuloPaneController(Articulo articulo) {
        this.articulo = articulo;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/EditarArticuloPane.fxml"));
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
        
        SpinnerValueFactory.DoubleSpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 999999.99, 0.0, 0.1);
        factory.setConverter(new StringConverter<Double>() {
            private final DecimalFormat df = new DecimalFormat("######0.00");

            @Override
            public String toString(Double object) {
                if (object == null) {
                    return "";
                } else {
                    return df.format(object);
                }
            }

            @Override
            public Double fromString(String string) {
                try {
                    if (string == null) {
                        return null;
                    }
                    string = string.trim();
                    if (string.length() < 1) {
                        return null;
                    }
                    return df.parse(string).doubleValue();
                } catch (ParseException ex) {
                    return 0.0;
                }
            }

        }
        );
        spn_precio.setValueFactory(factory);
        
        txt_nombre.setText(articulo.getNombre());
        txt_descripcion.setText(articulo.getDescripcion());
        spn_precio.getValueFactory().setValue(articulo.getPrecio().doubleValue());
        lbl_mensaje.setText(null);
        
    }
    
    @FXML
    private void cancelar(ActionEvent e) {
        cerrar();
    }
    
    @FXML
    private void guardar(ActionEvent e) {
        String nombre = txt_nombre.getText();
        String descripcion = txt_descripcion.getText();
        Double precio = spn_precio.getValueFactory().getValue();
        
        if (nombre == null || nombre.isEmpty()) {
            lbl_mensaje.setText("Nombre no valido");
            return;
        }
        
        if (descripcion == null || descripcion.isEmpty()) {
            lbl_mensaje.setText("Descripcion no valida");
            return;
        }
        
        if (precio == null || precio.equals(0d)) {
            lbl_mensaje.setText("Precio no valida");
            return;
        }
        
        Articulo articuloModificado = new Articulo();
        
        articuloModificado.setIdarticulo(articulo.getIdarticulo());
        articuloModificado.setNombre(nombre);
        articuloModificado.setDescripcion(descripcion);
        articuloModificado.setPrecio(BigDecimal.valueOf(precio));
        
        boolean exito = ArticuloDAO.guardarCambios(articuloModificado);
        
        if (exito) {
            cerrar();
        } else {
            lbl_mensaje.setText("ERROR");
        }
    }
    
    private void cerrar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }
    
}
