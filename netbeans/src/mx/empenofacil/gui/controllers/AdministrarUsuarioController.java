/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mx.empenofacil.dao.EmpleadoDAO;
import mx.empenofacil.beans.Empleado;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class AdministrarUsuarioController implements Initializable {
    
    Empleado empleadoSesion;
    Empleado empleadoEdicion;
    
    @FXML
    private Button agregar;
    @FXML
    private Button editar;
    @FXML
    private Button eliminar;
    @FXML
    private Button regresar;
    
    private List<Empleado> empleados;
    private ObservableList<Empleado> listaEmpleados;
    @FXML
    private TableView<Empleado> tb_empleados;
    @FXML
    private TableColumn<Empleado, String> col_numeroPersonal;
    @FXML
    private TableColumn<Empleado, String> col_nombreEmpleado;
    @FXML
    private TableColumn<Empleado, String> col_apellidos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void iniciarTabla() {
        col_numeroPersonal.setCellValueFactory(new PropertyValueFactory<Empleado, String>("numpersonal"));
        col_nombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombres"));
        col_apellidos.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellidos"));
        
        colocarEmpleados();
        
        tb_empleados.setItems(listaEmpleados);
    }
    
    public void colocarEmpleados() {
        listaEmpleados = FXCollections.observableArrayList();
        empleados.forEach((e) -> {
            listaEmpleados.add(e);
        });
    }
    
    public void initData(Empleado empleado) {
        empleadoSesion = empleado;
        empleados = EmpleadoDAO.obtenerTodo();
        editar.setDisable(true);
        eliminar.setDisable(true);
        iniciarTabla();
    }
    
    @FXML
    private void agregarUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/FormularioUsuarios.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);
        
        FormularioUsuariosController controller = loader.getController();
        controller.intiDataAgregar(empleadoSesion);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ejemploEscena);
        window.show();
    }
    
    @FXML
    private void editarUsuario(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación");
        alert.setHeaderText("Edición");
        if (empleadoEdicion != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mx/empenofacil/gui/FormularioUsuarios.fxml"));
            Parent ejemplo = loader.load();
            Scene ejemploEscena = new Scene(ejemplo);
            
            FormularioUsuariosController controller = loader.getController();
            controller.initDataActualizacion(empleadoSesion, empleadoEdicion);
            
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ejemploEscena);
            window.show();
        } else {
            alert.setContentText("Seleccione un empleado a editar");
            alert.show();
        }
    }
    
    @FXML
    private void eliminarUsuario(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación");
        alert.setHeaderText("Eliminado");
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Mensaje de confirmación");
        alertConfirmation.setHeaderText("Verificación");
        alertConfirmation.setContentText("Desea eliminar al empleado " + empleadoEdicion.getNombres());
        Optional<ButtonType> result = alertConfirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (empleadoEdicion != null) {
                EmpleadoDAO.eliminarEmpleado(empleadoEdicion.getNumpersonal());
                alert.setContentText("El usuario " + empleadoEdicion.getNombres() + " ha sido eliminado");
                alert.show();
                initData(empleadoSesion);
                empleadoEdicion = null;
            } else {
                alert.setContentText("Seleccione un empleado a eliminar");
                alert.show();
            }
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    @FXML
    private void seleccionEmpleado(MouseEvent event) {
        int numero = tb_empleados.getSelectionModel().getSelectedIndex();
        if (numero >= 0) {
            editar.setDisable(false);
            eliminar.setDisable(false);
            empleadoEdicion = tb_empleados.getSelectionModel().getSelectedItem();
        }
    }
    
}
