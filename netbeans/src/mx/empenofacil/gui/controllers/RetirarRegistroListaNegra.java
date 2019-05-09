package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.RegistroListaNegra;
import mx.empenofacil.dao.ListaNegraDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RetirarRegistroListaNegra extends GridPane implements Initializable {
    
    private RegistroListaNegra registro;
    private Empleado empleado;
    
    @FXML
    private Label mensaje;
    
    @FXML
    private Label cliente;
    
    @FXML
    private Label fechaAgrega;
    
    @FXML
    private Label tiempoPasado;
    
    @FXML
    private Label empleadoAgrega;
    
    @FXML
    private Label motivoAgrega;
    
    @FXML
    private Label empleadoRetira;
    
    @FXML
    private Label fechaRetira;
    
    @FXML
    private TextField motivo;

    public RetirarRegistroListaNegra(RegistroListaNegra registro, Empleado empleadoRetira) {
        this.registro = registro;
        this.empleado = empleadoRetira;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/RetirarRegistroListaNegra.fxml"));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("(dd-MM-yyyy)");
        LocalDateTime fechaHoy = LocalDateTime.now();
        long diasPasados = ChronoUnit.DAYS.between(registro.getFechaAgrega(), fechaHoy);
        
        mensaje.setText("");
        cliente.setText(registro.getCliente().getNombreCompleto());
        fechaAgrega.setText(registro.getFechaAgrega().format(formatter));
        tiempoPasado.setText(String.format("(hace %d días)", diasPasados));
        empleadoAgrega.setText(registro.getEmpleadoAgrega().getNombreCompleto());
        motivoAgrega.setText(registro.getMotivoAgrega());
        empleadoRetira.setText(empleado.getNombreCompleto());
        fechaRetira.setText(fechaHoy.format(formatter));
        
        Platform.runLater(() -> {
            requestFocus();
        });
    }
    
    @FXML
    public void guardar(ActionEvent event) {
        if (motivo.getText().isEmpty()) {
            mensaje.setText("Escriba el motivo");
            return;
        }
        
        ListaNegraDAO.retirarDeListaNegre(
                registro,
                empleado,
                motivo.getText()
        );
        
        cerrar(null);
    }
    
    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }
    
}
