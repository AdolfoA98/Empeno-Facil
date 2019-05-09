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
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mx.empenofacil.beans.Empleado;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class MenuPrincipalController implements Initializable {

    private Empleado empleadoSesion;

    @FXML
    Circle cir2;
    @FXML
    private AnchorPane fondo;
    @FXML
    private Button admin;
    @FXML
    private Button empenio;
    @FXML
    private Button cerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Empleado empleado) {
        fondo.setCenterShape(true);
        Image imagen = new Image("/mx/empenofacil/imagenes/bandera.jpg");
        cir2.setFill(new ImagePattern(imagen));
        cir2.setStroke(Color.SEAGREEN);
        cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        empleadoSesion = empleado;
        System.out.printf("Empleado %s %s\n", empleadoSesion.getNombres(), empleadoSesion.getApellidos());
    }

    @FXML
    private void administrarUsuarios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/AdministrarUsuario.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        AdministrarUsuarioController controller = loader.getController();
        controller.initData(empleadoSesion);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }

    @FXML
    private void empenio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/Empenio.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        EmpenioController controller = loader.getController();
        controller.initData(empleadoSesion);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/InicioSesion.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        InicioSesionController controller = loader.getController();
        controller.initData();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }

}
