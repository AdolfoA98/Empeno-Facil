package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Cliente;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ClientePane extends GridPane implements Initializable {
    
    private SeleccionarCliente seleccionarCliente;
    private Cliente cliente;
    private boolean permitirEnListaNegra;
    
    @FXML
    private ImageView foto;
    
    @FXML
    private Label id;
    
    @FXML
    private Label nombre;
    
    @FXML
    private Label rfc;
    
    @FXML
    private Label curp;
    
    @FXML
    private Button seleccionar;

    public ClientePane(SeleccionarCliente seleccionarCliente, Cliente cliente, boolean permitirEnListaNegra) {
        this.seleccionarCliente = seleccionarCliente;
        this.cliente = cliente;
        this.permitirEnListaNegra = permitirEnListaNegra;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/ClientePane.fxml"));
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
        // todo cargar foto de cliente
        File placeholder = new File("/mx/empenofacil/imagenes/bandera.png");
        Image image = new Image(placeholder.toURI().toString());
        
        foto.setImage(image);
        
        id.setText(String.format("(%d)", cliente.getIdcliente()));
        nombre.setText(cliente.getNombreCompleto());
        rfc.setText(cliente.getRfc());
        curp.setText(cliente.getCurp());
        
        if (!permitirEnListaNegra && cliente.getEnListaNegra()) {
            seleccionar.setDisable(true);
            seleccionar.setText("En lista negra");
        }
    }
    
    @FXML
    public void seleccionarOnClick(ActionEvent event) {
        seleccionarCliente.setClienteSeleccionado(cliente);
        seleccionarCliente.cerrar(null);
    }
    
}
