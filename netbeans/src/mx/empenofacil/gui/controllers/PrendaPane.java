package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Prenda2;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

public class PrendaPane extends GridPane implements Initializable {

    private Prenda2 prenda;

    @FXML
    private CheckBox cbx_comercializar;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_descripcion;

    @FXML
    private Label lbl_avaluo;

    @FXML
    private Label lbl_prestado;

    @FXML
    private Spinner<Double> spn_precio;

    public PrendaPane(Prenda2 prenda) {
        this.prenda = prenda;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/PrendaPane.fxml"));
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
        
        txt_nombre.setDisable(true);
        txt_descripcion.setDisable(true);
        spn_precio.setDisable(true);
        
        txt_nombre.setText(prenda.getNombre());
        txt_descripcion.setText(prenda.getDescripcion());
        
        lbl_avaluo.setText(prenda.getAvaluo().toString());
        lbl_prestado.setText(prenda.getMontoPrestado().toString());

        if (prenda.getComercializado()) {
            cbx_comercializar.setIndeterminate(true);
            cbx_comercializar.setDisable(true);
        }
    }

    @FXML
    private void comercializar() {
        if (cbx_comercializar.isSelected()) {
            txt_nombre.setDisable(false);
            txt_descripcion.setDisable(false);
            spn_precio.setDisable(false);
            Double precioEstimado = prenda.getAvaluo().doubleValue() * 1.1;
            spn_precio.getValueFactory().setValue(precioEstimado);
        } else {
            txt_nombre.setDisable(true);
            txt_descripcion.setDisable(true);
            spn_precio.setDisable(true);
            spn_precio.getValueFactory().setValue(0.0);
        }
    }
    
    public Prenda2 getPrenda() {
        return prenda;
    }
    
    public boolean estaSeleccionada() {
        return cbx_comercializar.isSelected();
    }
    
    public Double getPrecio() {
        return spn_precio.getValue();
    }
    
    public String getNombre() { 
        return txt_nombre.getText();
    }
    
    public String getDescripcion() {
        return txt_descripcion.getText();
    }

}
