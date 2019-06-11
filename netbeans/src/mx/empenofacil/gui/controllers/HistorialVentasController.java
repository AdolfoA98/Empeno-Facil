/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Venta;
import mx.empenofacil.dao.VentaDAO;

/**
 *
 * @author Razer
 */
public class HistorialVentasController extends VBox implements Initializable {

    private Empleado empleadoSeleccionado;
    private Cliente clienteSeleccionado;
    private ItemCatalogo mostrarTodos;

    @FXML
    private Label lbl_empleado;
    
    @FXML
    private Button btn_empleado;
    
    @FXML
    private Label lbl_cliente;
    
    @FXML
    private Button btn_cliente;

    @FXML
    private DatePicker dpc_desde;

    @FXML
    private DatePicker dpc_hasta;

    @FXML
    private Label lbl_fecha;

    @FXML
    private TextField txt_articulo;

    @FXML
    private ChoiceBox<Integer> cbx_num_resultados;

    @FXML
    private ChoiceBox<ItemCatalogo> cbx_tipo;

    @FXML
    private Label lbl_mensaje;
    
    @FXML
    private VBox container_resultados;

    public HistorialVentasController() {
        mostrarTodos = new ItemCatalogo();
        mostrarTodos.setIditemcatalogo(-1);
        mostrarTodos.setItemcatalogoSuperior(-1);
        mostrarTodos.setNombre("Todos");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/HistorialVentas.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_mensaje.setText(null);
        dpc_desde.setOnAction((event) -> {
            actualizarGUI();
        });
        dpc_hasta.setOnAction((event) -> {
            actualizarGUI();
        });
        dpc_desde.setDayCellFactory((DatePicker param) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (dpc_hasta.getValue() != null && item.isAfter(dpc_hasta.getValue())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        dpc_hasta.setDayCellFactory((DatePicker param) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (dpc_desde.getValue() != null && item.isBefore(dpc_desde.getValue())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        cbx_num_resultados.getItems().addAll(10,20,50,100);
        cbx_tipo.getItems().add(mostrarTodos);
        cbx_tipo.getItems().addAll(VentaDAO.getEstadosVentas());
        reiniciar();
        actualizarGUI();
    }

    @FXML
    private void seleccionarEmpleado() {
        //clienteSeleccionado = Dialogs.
    }

    @FXML
    private void seleccionarCliente() {
        if (clienteSeleccionado == null) {
            clienteSeleccionado = Dialogs.mostrarSeleccionCliente(true);
        } else {
            clienteSeleccionado = null;
        }
        actualizarGUI();
    }

    @FXML
    public void buscar() {
        List<Venta> ventas = VentaDAO.buscar(
                empleadoSeleccionado,
                clienteSeleccionado,
                dpc_desde.getValue(),
                ((dpc_hasta.getValue() != null)? dpc_hasta.getValue().plusDays(1): null),
                txt_articulo.getText(),
                ((cbx_tipo.getValue().equals(mostrarTodos)? null : cbx_tipo.getValue())),
                cbx_num_resultados.getValue()
        );
        lbl_mensaje.setText(ventas.size() + " resultado(s)");
        
        container_resultados.getChildren().clear();
        for (Venta venta : ventas) {
            container_resultados.getChildren().add(new VentaListPaneController(
                    this,
                    venta
            ));
        }
    }

    @FXML
    private void reiniciar() {
        empleadoSeleccionado = null;
        clienteSeleccionado = null;
        dpc_desde.setValue(null);
        dpc_hasta.setValue(null);
        txt_articulo.setText(null);
        cbx_num_resultados.setValue(cbx_num_resultados.getItems().get(0));
        cbx_tipo.setValue(cbx_tipo.getItems().get(0));
        lbl_mensaje.setText(null);
        actualizarGUI();
        container_resultados.getChildren().clear();
    }

    private void actualizarGUI() {
        if (empleadoSeleccionado != null) {
            lbl_empleado.setText(empleadoSeleccionado.getNombreCompleto());
        } else {
            lbl_empleado.setText("Todos los empleados");
        }

        if (clienteSeleccionado != null) {
            lbl_cliente.setText(clienteSeleccionado.getNombreCompleto());
            btn_cliente.setText("Quitar");
        } else {
            lbl_cliente.setText("Todos los clientes");
            btn_cliente.setText("Seleccionar");
        }

        if (dpc_desde.getValue() != null && dpc_hasta.getValue() != null) {
            long dias = ChronoUnit.DAYS.between(
                    dpc_desde.getValue(),
                    dpc_hasta.getValue()
            ) + 1;
            lbl_fecha.setText(dias + " dia(s)");
        } else {
            lbl_fecha.setText(null);
        }
    }
}
