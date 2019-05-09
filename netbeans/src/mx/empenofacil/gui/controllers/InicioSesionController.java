/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mx.empenofacil.dao.EmpleadoDAO;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.gui.tools.Loader;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class InicioSesionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button ingresar;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView imagen;
    @FXML
    private Circle cir2;
    @FXML
    private AnchorPane fondo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }
    
    public void initData(){
        fondo.setCenterShape(true);
        Image im = new Image("mx/empenofacil/imagenes/default.png");
        cir2.setFill(new ImagePattern(im));
        cir2.setStroke(Color.TRANSPARENT);
        //cir2.setEffect(new DropShadow(+10d, 0d, +2d, Color.DEEPSKYBLUE));
    }

    @FXML
    private void ingresar(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje informativo");
        alert.setHeaderText("No se pudo acceder al sistema" + label.getText());
        alert.setContentText("Usuario o contraseña incorrectos, intentelo otra vez");
        if (validarCampos()) {
            Empleado empleado;
            empleado = EmpleadoDAO.inicarSesion(userName.getText(), password.getText());
            if (empleado == null) {
                alert.showAndWait();
            } else {
                HomeController.setEmpleado(empleado);
                Loader.loadPage("/mx/empenofacil/gui/Home.fxml", "Inicio");
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.close();
            }
        } else {

            alert.showAndWait();
        }
    }

    @FXML
    private void validarCaracteres(KeyEvent event) {
        if (!userName.getText().matches("^[a-zA-Z,0-9]*$") || userName.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarContrasenia(KeyEvent event) {
        if (!password.getText().matches("^[a-zA-Z,0-9]*$") || password.getText().length() > 45) {
            event.consume();
        }
    }

    private boolean validarCampos() {
        return !(password.getText() == null || password.getText().trim().length() == 0
                || userName.getText() == null || userName.getText().trim().length() == 0);
    }
}
