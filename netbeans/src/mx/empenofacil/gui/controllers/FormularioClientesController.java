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
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.dao.EmpleadoDAO;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.ItemCatalogo;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class FormularioClientesController implements Initializable {

    @FXML
    private TextField curp;
    @FXML
    private TextField rfc;
    @FXML
    private TextField nombreCliente;
    @FXML
    private TextField apellidoCliente;
    @FXML
    private TextField identificacion;
    @FXML
    private Button cancelar;
    @FXML
    private Button guardar;

    private List<ItemCatalogo> identificaciones;

    private Empleado empleadoSesion;
    private Cliente clienteActualizacion = null;
    @FXML
    private ComboBox<String> box_tipoidentificacion;

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

    public void initData(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
        iniciarTiposIdentificacion();
    }

    public void iniciarTiposIdentificacion() {
        ObservableList<String> tipoIdentificacion = FXCollections.observableArrayList();
        identificaciones = ClienteDAO.obtenerTiposIdentificacion();
        identificaciones.forEach((i) -> {
            tipoIdentificacion.add(i.getNombre());
        });
        box_tipoidentificacion.setItems(tipoIdentificacion);
    }

    public void initDataActualizacion(Empleado empleadoSesion, Cliente clienteActualizacion) {
        this.empleadoSesion = empleadoSesion;
        this.clienteActualizacion = clienteActualizacion;
        iniciarTiposIdentificacion();
        curp.setText(clienteActualizacion.getCurp());
        rfc.setText("" + clienteActualizacion.getRfc());
        nombreCliente.setText(clienteActualizacion.getNombres());
        apellidoCliente.setText(clienteActualizacion.getApellidos());
        identificacion.setText("" + clienteActualizacion.getIdentificacion());
        for (int i = 0; i < identificaciones.size(); i++) {
            if (clienteActualizacion.getTipoidentificacion() == identificaciones.get(i).getIditemcatalogo()) {
                box_tipoidentificacion.getSelectionModel().select(i);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        if (clienteActualizacion == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarEmpeno.fxml"));
            Parent ejemplo = loader.load();
            Scene ejemploEscena = new Scene(ejemplo);

            RegistrarEmpenoController controller = loader.getController();
            controller.initData(empleadoSesion);

            Stage stage = new Stage();
            stage.setScene(ejemploEscena);
            stage.show();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarEmpeno.fxml"));
            Parent ejemplo = loader.load();
            Scene ejemploEscena = new Scene(ejemplo);

            RegistrarEmpenoController controller = loader.getController();
            controller.initDataAgregadoActualizado(empleadoSesion, clienteActualizacion);

            Stage stage = new Stage();
            stage.setScene(ejemploEscena);
            stage.show();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje informativo");
        alert.setHeaderText("Notificación");
        if (validarCampos()) {
            if (clienteActualizacion == null) {
                clienteActualizacion = new Cliente();
                clienteActualizacion.setApellidos(apellidoCliente.getText());
                clienteActualizacion.setCurp(curp.getText());
                clienteActualizacion.setIdentificacion(identificacion.getText());
                clienteActualizacion.setNombres(nombreCliente.getText());
                clienteActualizacion.setRfc(rfc.getText());
                clienteActualizacion.setTipoidentificacion(identificaciones.get(box_tipoidentificacion.getSelectionModel().getSelectedIndex()).getIditemcatalogo());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarEmpeno.fxml"));
                Parent ejemplo = loader.load();
                Scene ejemploEscena = new Scene(ejemplo);

                RegistrarEmpenoController controller = loader.getController();
                controller.initDataAgregadoActualizado(empleadoSesion, clienteActualizacion);

                Stage stage = new Stage();
                stage.setScene(ejemploEscena);
                stage.show();

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            } else {
                clienteActualizacion.setApellidos(apellidoCliente.getText());
                clienteActualizacion.setCurp(curp.getText());
                clienteActualizacion.setIdentificacion(identificacion.getText());
                clienteActualizacion.setNombres(nombreCliente.getText());
                clienteActualizacion.setRfc(rfc.getText());
                clienteActualizacion.setTipoidentificacion(identificaciones.get(box_tipoidentificacion.getSelectionModel().getSelectedIndex()).getIditemcatalogo());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarEmpeno.fxml"));
                Parent ejemplo = loader.load();
                Scene ejemploEscena = new Scene(ejemplo);

                RegistrarEmpenoController controller = loader.getController();
                controller.initDataAgregadoActualizado(empleadoSesion, clienteActualizacion);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(ejemploEscena);
                window.show();

                /**
                 * if (ClienteDAO.actualizarCliente(clienteActualizacion)) {
                 * alert.setContentText("Se ha actualizado con exito el
                 * cliente"); alert.show();
                 *
                 * } else { alert.setContentText("No se pudo actualizar el
                 * cliente, intentelo en otro momento"); alert.show();
                }*
                 */
            }
        } else {
            alert.setContentText("Existen campos invalidos");
            alert.show();
        }
    }

    @FXML
    private void validarCURP(KeyEvent event) {
        if (!curp.getText().matches("^[a-zA-Z,0-9]*$") || curp.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarRFC(KeyEvent event) {
        if (!rfc.getText().matches("^[0-9]*$") || rfc.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarNombre(KeyEvent event) {
        if (!nombreCliente.getText().matches("^[a-zA-Z,\\s]*$") || nombreCliente.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarApellidos(KeyEvent event) {
        if (!apellidoCliente.getText().matches("^[a-zA-Z,\\s]*$") || apellidoCliente.getText().length() > 45) {
            event.consume();
        }
    }

    @FXML
    private void validarIdentificacion(KeyEvent event) {
        if (!identificacion.getText().matches("^[0-9]*$") || identificacion.getText().length() > 45) {
            event.consume();
        }
    }

    private boolean validarCampos() {
        return !(curp.getText() == null || curp.getText().trim().length() == 0
                || rfc.getText() == null || rfc.getText().trim().length() == 0
                || nombreCliente.getText() == null || nombreCliente.getText().trim().length() == 0
                || apellidoCliente.getText() == null || apellidoCliente.getText().trim().length() == 0
                || identificacion.getText() == null || identificacion.getText().trim().length() == 0
                || box_tipoidentificacion.getSelectionModel().getSelectedIndex() < 0);
    }
}
