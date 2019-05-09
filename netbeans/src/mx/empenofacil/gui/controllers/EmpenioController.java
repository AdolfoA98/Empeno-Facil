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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class EmpenioController implements Initializable {

    @FXML
    private Pane infoCliente;
    @FXML
    private TextField nombreCliente;
    @FXML
    private TextField apellidoCliente;
    @FXML
    private TextField curp;
    @FXML
    private TextField rfc;
    @FXML
    private TextField identificacion;
    @FXML
    private Button agregar;
    @FXML
    private Button editar;
    @FXML
    private TextField curpBusqueda;
    @FXML
    private Button buscar;

    private Empleado empleadoSesion;
    private Cliente cliente = null;
    @FXML
    private Button regresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }

    public void initDataAgregadoActualizado(Empleado empleadoSesion, Cliente clienteActual) {
        this.empleadoSesion = empleadoSesion;
        this.cliente = clienteActual;
        nombreCliente.setText(clienteActual.getNombres());
        apellidoCliente.setText(clienteActual.getApellidos());
        curp.setText(clienteActual.getCurp());
        rfc.setText(clienteActual.getRfc());
        identificacion.setText(clienteActual.getIdentificacion());
        editar.setDisable(false);
    }

    @FXML
    private void agregar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/FormularioClientes.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        FormularioClientesController controller = loader.getController();
        controller.initData(empleadoSesion);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        if (cliente != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mx/empenofacil/gui/FormularioClientes.fxml"));
            Parent ejemplo = loader.load();
            Scene ejemploEscena = new Scene(ejemplo);

            FormularioClientesController controller = loader.getController();
            controller.initDataActualizacion(empleadoSesion, cliente);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ejemploEscena);
            window.show();
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        if (validarCurp()) {
            cliente = ClienteDAO.buscarPorCURP(curpBusqueda.getText());
            if (cliente != null) {
                nombreCliente.setText(cliente.getNombres());
                apellidoCliente.setText(cliente.getApellidos());
                curp.setText(cliente.getCurp());
                rfc.setText(cliente.getRfc());
                identificacion.setText(cliente.getIdentificacion());
                editar.setDisable(false);
            }
        }
    }

    private boolean validarCurp() {
        return !(curpBusqueda.getText().isEmpty() || curpBusqueda.getText().trim().length() == 0);
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/MenuPrincipal.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        MenuPrincipalController controller = loader.getController();
        controller.initData(empleadoSesion);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }

}
