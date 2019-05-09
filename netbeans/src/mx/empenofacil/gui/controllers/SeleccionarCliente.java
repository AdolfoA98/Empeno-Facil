package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Cliente;
import mx.empenofacil.dao.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeleccionarCliente extends VBox implements Initializable {
    
    private boolean permitirEnListaNegra;
    private Cliente clienteSeleccionado;
    
    @FXML
    private Label mensaje;
    
    @FXML
    private TextField busqueda;
    
    @FXML
    private VBox resultados;

    public SeleccionarCliente(boolean permitirEnListaNegra) {
        this.permitirEnListaNegra = permitirEnListaNegra;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/SeleccionarCliente.fxml"));
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
        Platform.runLater(() -> {
            requestFocus();
        });
    }
    
    private void mostrarResultados(List<Cliente> clientes) {
        resultados.getChildren().clear();
        
        mensaje.setText(clientes.size() + " resultados");
        
        for (Cliente cliente : clientes) {
            ClientePane clientePane = new ClientePane(
                    this,
                    cliente,
                    permitirEnListaNegra
            );
            resultados.getChildren().add(clientePane);
        }
    }
    
    
    @FXML
    public void buscar(ActionEvent event) {
        String txt = this.busqueda.getText();
        if (txt.isEmpty()) {
            mensaje.setText("Escriba una busqueda");
            return;
        }
        
        List<Cliente> clientes = ClienteDAO.buscarPorIdNombreRfcCurp(txt);
        
        mostrarResultados(clientes);
    }
    
    @FXML
    public void mostrarRecientes(ActionEvent event) {
        List<Cliente> clientes = ClienteDAO.buscarRecientes(10);
        mostrarResultados(clientes);
    }
    
    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
    
}