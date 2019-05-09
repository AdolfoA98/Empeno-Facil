package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.RegistroListaNegra;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RegistroListaNegraPane extends GridPane implements Initializable {
    
    private ListaNegraAdmin admin;
    private RegistroListaNegra registroListaNegra;
    private Empleado empleado;
    
    @FXML
    private Label cliente;
    
    @FXML
    private Label motivoAgrega;
    
    @FXML
    private Label fechaAgrega;
    
    @FXML
    private Label empleadoAgrega;
    
    @FXML
    private Label fechaRetira;
    
    @FXML
    private Label empleadoRetira;
    
    @FXML
    private Label motivoRetira;
    
    @FXML
    private Button retirar;

    public RegistroListaNegraPane(ListaNegraAdmin admin, Empleado empleado, RegistroListaNegra registroListaNegra) {
        this.admin = admin;
        this.registroListaNegra = registroListaNegra;
        this.empleado = empleado;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/RegistroListaNegraPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void retirarAction(ActionEvent event) {
        Dialogs.mostrarDialog(new RetirarRegistroListaNegra(registroListaNegra, empleado), "Retirar registro");
        admin.recargarResltados();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("(dd-MM-yyyy)");
        
        cliente.setText(registroListaNegra.getCliente().getNombreCompleto());
        motivoAgrega.setText(registroListaNegra.getMotivoAgrega());
        fechaAgrega.setText(registroListaNegra.getFechaAgrega().format(formatter));
        empleadoAgrega.setText(registroListaNegra.getEmpleadoAgrega().getNombreCompleto());
        
        if (registroListaNegra.estaActivo()) {
            retirar.setDisable(false);
            retirar.setText("Retirar registro");
            fechaRetira.setText("-");
            empleadoRetira.setText("-");
            motivoRetira.setText("-");
        } else {
            retirar.setDisable(true);
            retirar.setText("Registro retirado");
            fechaRetira.setText(registroListaNegra.getFechaRetira().format(formatter));
            empleadoRetira.setText(registroListaNegra.getEmpleadoRetira().getNombreCompleto());
            motivoRetira.setText(registroListaNegra.getMotivoRetira());
        }
    }    
    
}
