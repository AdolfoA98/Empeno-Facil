/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Consts;
import mx.empenofacil.beans.Venta;
import mx.empenofacil.dao.VentaDAO;

/**
 *
 * @author Razer
 */
public class VentaListPaneController extends GridPane implements Initializable {

    private HistorialVentasController mainController;
    private Venta venta;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Label lbl_cliente;

    @FXML
    private Label lbl_empleado;

    @FXML
    private Label lbl_num_articulos;

    @FXML
    private Label lbl_estado;

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_fecha;

    public VentaListPaneController(HistorialVentasController mainController, Venta venta) {
        this.mainController = mainController;
        this.venta = venta;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/VentaListPane.fxml"));
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
        lbl_cliente.setText(venta.getCliente().getNombreCompleto());
        lbl_empleado.setText(venta.getEmpleado().getNombreCompleto());
        lbl_num_articulos.setText(String.valueOf(venta.getArticulos().size()));
        lbl_estado.setText(venta.getEstatusVenta().getNombre());
        BigDecimal total = BigDecimal.ZERO;
        for (Articulo articulo : venta.getArticulos()) {
            total = total.add(articulo.getPrecio());
        }
        lbl_total.setText(total.toPlainString());
        lbl_id.setText(venta.getIdventa().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        lbl_fecha.setText(venta.getTransaccioncaja().getFechaHora().format(formatter));

        if (venta.getEstatusVenta().getIditemcatalogo() != Consts.ID_STATUS_VENTA_COMPLETADA
                || !venta.getTransaccioncaja().getFechaHora().toLocalDate().equals(LocalDate.now())) {
            btn_cancelar.setDisable(true);
        }
    }

    @FXML
    private void verArticulos() {
        Dialogs.mostrarDialog(new VerListaArticulosController(venta.getArticulos()), "Artículos");
    }

    @FXML
    private void cancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Cancelar venta");
        alert.setContentText("Se cancelara la venta");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Cancelar");
            alert2.setHeaderText(null);

            if (VentaDAO.cancelar(venta)) {
                alert2.setContentText("Venta cancelada con exito");
            } else {
                alert2.setContentText("Error");
            }

            alert2.show();
            
            mainController.buscar();
        }

    }

}
