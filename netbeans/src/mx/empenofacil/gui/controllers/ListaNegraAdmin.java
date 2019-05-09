package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.RegistroListaNegra;
import mx.empenofacil.dao.ListaNegraDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ListaNegraAdmin extends VBox implements Initializable {

    private enum Operacion {
        NADA,
        CARGAR_RECIENTES,
        CARGAR_DE_CLIENTE
    }
    
    private Empleado empleado;
    private Cliente cliente;
    private Operacion operacion;
    
    @FXML
    private CheckBox mostrarInactivos;
    
    @FXML
    private Label mensaje;
    
    @FXML
    private VBox resultados;
    
    public ListaNegraAdmin(Empleado empleado) {
        this.empleado = empleado;
        this.operacion = Operacion.NADA;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/ListaNegraAdmin.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mensaje.setText("");
        mostrarInactivos.setOnAction((ActionEvent event) -> {
            recargarResltados();
        });
    }
    
    @FXML
    public void verRecientes(ActionEvent event) {
        operacion = Operacion.CARGAR_RECIENTES;
        recargarResltados();
    }
    
    @FXML
    public void seleccionarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = Dialogs.mostrarSeleccionCliente(true);
        
        cliente = clienteSeleccionado;
        
        if (cliente != null) {
            operacion = Operacion.CARGAR_DE_CLIENTE;
        }
        
        recargarResltados();
    }
    
    @FXML
    public void clicCheckbox() {
        recargarResltados();
    }
    
    @FXML
    public void agregar() {
        Dialogs.mostrarDialog(new AgregarRegistroListaNegra(empleado), "Agregar cliente a lista negraa");
        recargarResltados();
    }
    
    public void recargarResltados() {
        List<RegistroListaNegra> registros = null;
        boolean incluirInactivos = mostrarInactivos.isSelected();
        switch(operacion){
            case NADA:
                break;
            case CARGAR_DE_CLIENTE:
                registros = ListaNegraDAO.getDeCliente(cliente, 10, !incluirInactivos);
                break;
            case CARGAR_RECIENTES:
                registros = ListaNegraDAO.getReciente(10, !incluirInactivos);
                break;
        }
        
        if (registros != null) {
            
            resultados.getChildren().clear();
            
            mensaje.setText(registros.size() + " resultados");
            
            for (RegistroListaNegra registro : registros) {
                resultados.getChildren().add(new RegistroListaNegraPane(
                        this,
                        empleado,
                        registro
                ));
            }
        }
    }
    
}
