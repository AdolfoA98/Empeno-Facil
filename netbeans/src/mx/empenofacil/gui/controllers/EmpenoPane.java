package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Empeño2;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.Prenda2;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class EmpenoPane extends Accordion implements Initializable {
    
    private Empeño2 empeno;
    
    @FXML
    private Accordion accordion;
    
    @FXML
    private TitledPane container;
    
    @FXML
    private VBox prendas;

    public EmpenoPane(Empeño2 empeno) {
        this.empeno = empeno;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/EmpenoPane.fxml"));
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
        LocalDateTime fechaHoy = LocalDateTime.now();
        long diasPasados = 0;//ChronoUnit.DAYS.between(empeno.getFechaExtendida().toLocalDate(), fechaHoy);
        
        container.setText(String.format("Contrato ID (%d) %d días vencido",
                empeno.getIdEmpeño(),
                diasPasados
        ));
        
        for (Prenda2 prenda : empeno.getPrendas()) {
            prendas.getChildren().add(new PrendaPane(prenda));
        }
        accordion.setExpandedPane(container);
    }
    
    public List<Articulo> getPrendasSeleccionadasComoArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        
        for (Node node : prendas.getChildren()) {
            PrendaPane prendaPane = (PrendaPane) node;
            if (prendaPane.estaSeleccionada()) {
                Articulo articulo = new Articulo();
                articulo.setNombre(prendaPane.getNombre());
                articulo.setDescripcion(prendaPane.getDescripcion());
                articulo.setPrenda(prendaPane.getPrenda());
                articulo.setPrecio(BigDecimal.valueOf(prendaPane.getPrecio()));
                
                articulos.add(articulo);
            }
        }
        
        return articulos;
    }
}
