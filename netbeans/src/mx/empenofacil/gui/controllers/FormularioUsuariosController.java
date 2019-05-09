/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mx.empenofacil.dao.EmpleadoDAO;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.Sucursal;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class FormularioUsuariosController implements Initializable {

    @FXML
    private TextField nombreEmpleado;
    @FXML
    private TextField nombreUsuario;
    @FXML
    private TextField contrasenia;
    @FXML
    private TextField numeroPersonal;
    @FXML
    private Button cancelar;
    @FXML
    private Button guardar;

    Empleado empleadoSesion;
    Empleado empleadoModificacion;
    @FXML
    private ComboBox<String> box_tipoEmpleado;

    List<String> numerosPersonal;
    List<ItemCatalogo> tiposEmpleados;
    List<Sucursal> sucursales;
    @FXML
    private ComboBox<String> box_sucursal;
    @FXML
    private TextField txt_apellidos;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initDataActualizacion(Empleado empleadoSesion, Empleado empleadoModificacion) {
        llenarTiposDeEmpleados();
        llenarSucursales();
        this.empleadoSesion = empleadoSesion;
        this.empleadoModificacion = empleadoModificacion;
        nombreEmpleado.setText(this.empleadoModificacion.getNombres());
        txt_apellidos.setText(this.empleadoModificacion.getApellidos());
        nombreUsuario.setText(this.empleadoModificacion.getNombreusuario());
        contrasenia.setText(this.empleadoModificacion.getContrasenahash());
        numeroPersonal.setText(this.empleadoModificacion.getNumpersonal());
        for (int i = 0; i < tiposEmpleados.size(); i++) {
            if (tiposEmpleados.get(i).getIditemcatalogo() == empleadoModificacion.getTipoempleado()) {
                box_tipoEmpleado.getSelectionModel().select(i);
            }
        }
        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i).getIdsucursal() == empleadoModificacion.getSucursal()) {
                box_sucursal.getSelectionModel().select(i);
            }
        }
    }

    public void llenarSucursales() {
        ObservableList<String> todasSucursales = FXCollections.observableArrayList();
        sucursales = EmpleadoDAO.obtenerSucursales();
        sucursales.forEach((s) -> {
            todasSucursales.add(s.getNombre());
        });
        box_sucursal.setItems(todasSucursales);
    }

    public void llenarTiposDeEmpleados() {
        ObservableList<String> empleadosTipo = FXCollections.observableArrayList();
        tiposEmpleados = EmpleadoDAO.obtenerTipoEmpleado();
        tiposEmpleados.forEach((c) -> {
            empleadosTipo.add(c.getNombre());
        });
        box_tipoEmpleado.setItems(empleadosTipo);
    }

    public void intiDataAgregar(Empleado empleadoSesion) {
        llenarTiposDeEmpleados();
        llenarSucursales();
        this.empleadoSesion = empleadoSesion;
        this.empleadoModificacion = null;
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
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
    private void guardar(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje informativo");
        alert.setHeaderText("Notificación");
        if (validarCampos()) {
            Empleado empleado = new Empleado();
            List<String> nombresUsuarios = EmpleadoDAO.obtenerNombresUsuario();
            boolean chido = false;
            for (int i = 0; i < nombresUsuarios.size(); i++) {
                if (nombresUsuarios.get(i).equals(nombreUsuario.getText()) && !nombresUsuarios.get(i).equals(empleadoModificacion.getNombreusuario())) {
                    chido = true;
                    break;
                }
                System.out.println(nombresUsuarios.get(i));
            }
            if (!chido) {
                if (empleadoModificacion == null) {
                    empleado.setContrasenahash(contrasenia.getText());
                    empleado.setNombres(nombreEmpleado.getText());
                    empleado.setApellidos(txt_apellidos.getText());
                    empleado.setNombreusuario(nombreUsuario.getText());
                    empleado.setNumpersonal(numeroPersonal.getText());
                    empleado.setSucursal(1);
                    empleado.setTipoempleado(tiposEmpleados.get(box_tipoEmpleado.getSelectionModel().getSelectedIndex()).getIditemcatalogo());
                    empleado.setSucursal(sucursales.get(box_sucursal.getSelectionModel().getSelectedIndex()).getIdsucursal());
                    if (EmpleadoDAO.agregarEmpleado(empleado)) {
                        alert.setContentText("El usuario se agregó con exito");
                        alert.showAndWait();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/AdministrarUsuario.fxml"));
                        Parent ejemplo = loader.load();
                        Scene ejemploEscena = new Scene(ejemplo);

                        AdministrarUsuarioController controller = loader.getController();
                        controller.initData(empleadoSesion);

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(ejemploEscena);
                        window.show();
                    } else {
                        alert.setContentText("En este momento no es posible agregar al usuario");
                    }
                } else {
                    empleado.setContrasenahash(contrasenia.getText());
                    empleado.setNombres(nombreEmpleado.getText());
                    empleado.setApellidos(txt_apellidos.getText());
                    empleado.setNombreusuario(nombreUsuario.getText());
                    empleado.setNumpersonal(numeroPersonal.getText());
                    empleado.setSucursal(1);
                    empleado.setTipoempleado(tiposEmpleados.get(box_tipoEmpleado.getSelectionModel().getSelectedIndex()).getIditemcatalogo());
                    empleado.setSucursal(sucursales.get(box_sucursal.getSelectionModel().getSelectedIndex()).getIdsucursal());
                    if (EmpleadoDAO.actualizarEmpleado(empleado)) {
                        alert.setContentText("El usuario se actualizó con exito");
                        alert.showAndWait();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/AdministrarUsuario.fxml"));
                        Parent ejemplo = loader.load();
                        Scene ejemploEscena = new Scene(ejemplo);

                        AdministrarUsuarioController controller = loader.getController();
                        controller.initData(empleadoSesion);

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(ejemploEscena);
                        window.show();
                    } else {
                        alert.setContentText("En este momento no es posible actualizar al usuario");
                        alert.showAndWait();
                    }
                }
            } else {
                alert.setContentText("El nombre de usuario está en uso, intente con otro");
                alert.showAndWait();
            }
        } else {
            alert.setContentText("Los datos ingresados son invalidos");
            alert.showAndWait();
        }
    }

    @FXML
    private void validarNombre(KeyEvent event) {
        if (!nombreEmpleado.getText().matches("^[a-zA-Z,\\s]*$") || nombreEmpleado.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarNombreUsuario(KeyEvent event) {
        if (!nombreUsuario.getText().matches("^[a-zA-Z,0-9]*$") || nombreUsuario.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarContrasenia(KeyEvent event) {
        if (!contrasenia.getText().matches("^[a-zA-Z,0-9]*$") || contrasenia.getText().length() > 45) {
            event.consume();
        }
    }

    private boolean validarCampos() {
        return !(contrasenia.getText() == null || contrasenia.getText().trim().length() == 0
                || nombreUsuario.getText() == null || nombreUsuario.getText().trim().length() == 0
                || nombreEmpleado.getText() == null || nombreEmpleado.getText().trim().length() == 0
                || box_tipoEmpleado.getSelectionModel().getSelectedIndex() < 0
                || box_sucursal.getSelectionModel().getSelectedIndex() < 0);
    }
}
