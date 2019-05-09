package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.ListaNegraDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AgregarRegistroListaNegra extends VBox implements Initializable {
    
    private Empleado empleado;
    private Cliente cliente;
    
    @FXML
    private Button seleccionarBtn;
    
    @FXML
    private Label nombreCliente;
    
    @FXML
    private Label idCliente;
    
    @FXML
    private Label fecha;
    
    @FXML
    private Label nombreEmpleado;
    
    @FXML
    private TextField motivo;
    
    @FXML
    private Label mensaje;

    public AgregarRegistroListaNegra(Empleado empleado) {
        this.empleado = empleado;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/empenofacil/resources/fxml/AgregarRegistroListaNegra.fxml"));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        mensaje.setText("");
        nombreCliente.setText("-");
        idCliente.setText("");
        fecha.setText(LocalDateTime.now().format(formatter));
        nombreEmpleado.setText(empleado.getNombreCompleto());
        
        Platform.runLater(() -> {
            requestFocus();
        });
        
    }   
    
    @FXML
    private void seleccionar() {
        Cliente clienteSeleccionado = Dialogs.mostrarSeleccionCliente(false);
        if (clienteSeleccionado != null) {
            cliente = clienteSeleccionado;
            nombreCliente.setText(cliente.getNombreCompleto());
            idCliente.setText(String.format("(%d)", cliente.getIdcliente()));
            seleccionarBtn.setText("Cambiar cliente");
        }
    }
    
    @FXML
    private void guardar() {
        if (cliente == null) {
            mensaje.setText("Seleccione un cliente");
            return;
        }
        
        String motivoString = motivo.getText();
        
        if (motivoString.isEmpty()) {
            mensaje.setText("Escriba el motivo");
            return;
        }
        
        if (ListaNegraDAO.agregarAListaNegra(cliente, empleado, motivoString)) {
            cerrar();
        } else {
            mensaje.setText("Error");
        }
    }
    
    @FXML
    private void cerrar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }
    
}
