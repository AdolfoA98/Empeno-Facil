package mx.empenofacil.gui.controllers;

import java.util.List;
import mx.empenofacil.beans.Cliente;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.empenofacil.beans.Articulo;

public class Dialogs {
    
    public static void mostrarDialog(Pane pane, String titulo) {
        Stage dialog = new Stage();
        dialog.setScene(new Scene(pane));
        dialog.setTitle(titulo);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }
    
    public static Cliente mostrarSeleccionCliente(boolean permitirListaNegra) {
        SeleccionarCliente root = new SeleccionarCliente(permitirListaNegra);
        
        mostrarDialog(root, "Seleccionar cliente");
        
        return root.getClienteSeleccionado();
    }
    
    public static List<Articulo> mostrarSeleccionArticulos(boolean soloDisponibles) {
        SeleccionarArticulosController controller = new SeleccionarArticulosController(soloDisponibles);
        mostrarDialog(controller, "Seleccionar articulos");
        return controller.getArticulosSeleccionados();
    }
    
    public static List<Articulo> mostrarSeleccionArticulos(boolean soloDisponibles, List<Articulo> articulos) {
        SeleccionarArticulosController controller = new SeleccionarArticulosController(soloDisponibles, articulos);
        mostrarDialog(controller, "Seleccionar articulos");
        return controller.getArticulosSeleccionados();
    }
    
}