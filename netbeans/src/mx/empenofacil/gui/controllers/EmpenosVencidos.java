package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Empeno;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.EmpenoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmpenosVencidos extends VBox implements Initializable {
    
    private Empleado empleado;
    
    @FXML
    private Label mensaje;
    
    @FXML
    private VBox empenos;

    public EmpenosVencidos(Empleado empleado) {
        this.empleado = empleado;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/EmpenosVencidos.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void actualizar() {
        empenos.getChildren().clear();
        
        List<Empeno> empeñosList = EmpenoDAO.getVencidosNoComercializados();
        for (Empeno empeno : empeñosList) {
            empenos.getChildren().add(new EmpenoPane(empeno));
        }
        
        mensaje.setText(String.format(
                "%d resultados",
                empeñosList.size()
        ));
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        mensaje.setText("");
        
        actualizar();
    }
    
    @FXML
    public void comercializar() {
        List<Articulo> articulos = new ArrayList<>();
        
        for (Node node : empenos.getChildren()) {
            EmpenoPane empenoPane = (EmpenoPane) node;
            for (Articulo articulo : empenoPane.getPrendasSeleccionadasComoArticulos()) {
                articulo.setEmpleado(empleado);
                // todo codigo de barras
                articulo.setCodigoDeBarras("");
                articulos.add(articulo);
            }
        }
        
        if (articulos.isEmpty()) {
            mensaje.setText("Seleccione 1 o mas prendas");
            return;
        }
        
        
        Dialogs.mostrarDialog(new ComercializarPane(articulos), "Comercializar Prendas");
        
        actualizar();
        
    }

}
