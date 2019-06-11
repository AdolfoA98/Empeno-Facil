/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Consts;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.ApartadoDAO;
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.dao.VentaDAO;

/**
 *
 * @author Razer
 */
public class VentaPaneController extends VBox implements Initializable {

    private Empleado empleado;
    private List<Articulo> articulos;
    private Cliente cliente;

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_cliente;

    @FXML
    private Label lbl_mensaje;

    @FXML
    private Button btn_cliente;

    @FXML
    private CheckBox btn_publico_general;

    @FXML
    private Label lb_montoMinimo;

    @FXML
    private Label lb_textoMinimo;

    @FXML
    private TextField txt_montoApartado;

    @FXML
    private ListView<String> lst_articulos;

    private int tipo;

    public VentaPaneController(Empleado empleado, int tipo) {
        this.empleado = empleado;
        this.articulos = new ArrayList<>();
        this.tipo = tipo;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/VentaPane.fxml"));
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
        btn_publico_general.setSelected(true);
        if (tipo == 2) {
            txt_montoApartado.setVisible(true);
            lb_textoMinimo.setVisible(true);
            lb_montoMinimo.setVisible(true);
            lb_montoMinimo.setText("#####");
        }
        seleccionPublicoGeneral();
        actualizarClienteSeleccionado();
        actualizarTotal();

    }

    private void cerrar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar() {
        cerrar();
    }

    @FXML
    private void guardar() {
        if (tipo == 1) {
            VentaDAO.vender(cliente, empleado, articulos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación");
            alert.setHeaderText("Exito");
            alert.setContentText("Se registró la venta de manera exitosa");
            alert.show();
            cerrar();
        } else {
            ApartadoDAO.apartar(cliente, empleado, articulos, Double.valueOf(txt_montoApartado.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación");
            alert.setHeaderText("Exito");
            alert.setContentText("Se realizó el apartado con exito");
            alert.show();
            cerrar();
        }
    }

    @FXML
    private void selccionarCliente() {
        Cliente clienteSeleccionado = Dialogs.mostrarSeleccionCliente(false);
        if (clienteSeleccionado != null) {
            cliente = clienteSeleccionado;
        }
        actualizarClienteSeleccionado();
    }

    private void actualizarClienteSeleccionado() {
        if (cliente != null) {
            lbl_cliente.setText(cliente.getNombreCompleto());
        } else {
            lbl_cliente.setText("Cliente no seleccionado");
        }
    }

    private void actualizarTotal() {
        Double minimo;
        BigDecimal total = BigDecimal.ZERO;
        for (Articulo articulo : articulos) {
            total = total.add(articulo.getPrecio());
        }
        this.lbl_total.setText(total.toPlainString());
        if (tipo == 2) {
            minimo = total.doubleValue();
            this.lb_montoMinimo.setText(Double.toString(minimo * 0.10));
        }
    }

    @FXML
    private void seleccionarArticulos() {
        articulos = Dialogs.mostrarSeleccionArticulos(true, articulos);

        ObservableList<String> items = lst_articulos.getItems();
        items.clear();

        for (Articulo articulo : articulos) {
            items.add(articulo.getNombre() + " [" + articulo.getDescripcion() + "] $" + articulo.getPrecio().toPlainString() + " MXN");
        }

        actualizarTotal();
    }

    @FXML
    private void seleccionPublicoGeneral() {
        if (btn_publico_general.isSelected()) {
            cliente = ClienteDAO.buscarPorId(Consts.ID_CLIENTE_PUBLICO_EN_GENERAL);
            btn_cliente.setDisable(true);
        } else {
            cliente = null;
            btn_cliente.setDisable(false);
        }
        actualizarClienteSeleccionado();
    }

    private boolean validarCampo(){
        boolean valido = false;
        if(!txt_montoApartado.getText().isEmpty() || !(txt_montoApartado.getText().length() == 0)){
            try{
                Double.valueOf(txt_montoApartado.getText());
            } catch (NumberFormatException ex){
                
            }
        }
        return valido;
    }
}
