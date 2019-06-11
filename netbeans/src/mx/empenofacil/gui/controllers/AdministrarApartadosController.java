/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mx.empenofacil.beans.Apartado;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.DatosApartado;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.ApartadoDAO;
import mx.empenofacil.dao.ArticuloDAO;
import mx.empenofacil.dao.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class AdministrarApartadosController implements Initializable {

    @FXML
    private Button btn_regresar;
    @FXML
    private TableView<DatosApartado> tb_Apartados;
    @FXML
    private TableColumn<DatosApartado, Integer> col_id;
    @FXML
    private TableColumn<DatosApartado, String> col_nombreCliente;
    @FXML
    private TableColumn<DatosApartado, String> col_articulos;
    @FXML
    private TableColumn<DatosApartado, Double> col_monto;
    @FXML
    private TextField txt_curp;
    @FXML
    private Button btn_filtrar;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_liquidar;
    private ObservableList<DatosApartado> datos;
    Empleado empleado = new Empleado();
    List<Apartado> apartados = new ArrayList<>();
    List<Articulo> articulos = new ArrayList<>();
    List<DatosApartado> datosApartado = new ArrayList<>();
    Apartado apartadoSeleccionado;
    Double monto;

    /**
     * Initializes the controller class.
     *
     * @param empleado
     */
    public void initData(Empleado empleado) {
        this.empleado = empleado;
        obtenerApartados();
        llenarDatos();
    }

    public void llenarDatos() {
        for (int i = 0; i < apartados.size(); i++) {
            Cliente clienteApartado;
            String nombreArticulos = "";
            Double montoPagar;
            Double totalArticulos = 0.0;
            articulos = ArticuloDAO.obtenerArticulosPorApartado(apartados.get(i).getIdapartado());
            if (articulos.size() > 1) {
                for (int j = 0; j < articulos.size(); j++) {
                    nombreArticulos += "\n" + articulos.get(j).getNombre();
                    totalArticulos += articulos.get(j).getPrecio().doubleValue();
                }
            } else {
                nombreArticulos = articulos.get(0).getNombre();
                totalArticulos = articulos.get(0).getPrecio().doubleValue();
            }
            clienteApartado = ClienteDAO.buscarPorId(apartados.get(i).getCliente());
            montoPagar = totalArticulos - ApartadoDAO.obtenerMontoApartado(apartados.get(i).getIdapartado());
            DatosApartado datosApartadoAux = new DatosApartado(apartados.get(i).getIdapartado(), clienteApartado.getNombres() + " " + clienteApartado.getApellidos(),
                    nombreArticulos, montoPagar);
            datosApartado.add(datosApartadoAux);
        }
        iniciarTabla();
    }

    public void iniciarTabla() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        col_articulos.setCellValueFactory(new PropertyValueFactory<>("articulos"));
        col_monto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        colocarDatos();

        tb_Apartados.setItems(datos);
    }

    public void colocarDatos() {
        datos = FXCollections.observableArrayList();
        datosApartado.forEach((de) -> {
            datos.add(de);
        });
    }

    public void obtenerApartados() {
        apartados = ApartadoDAO.obtenerApartadosPorEstatus(38);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void regresar(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    private void seleccion(MouseEvent event) {
        int numero = tb_Apartados.getSelectionModel().getSelectedIndex();
        System.out.println(numero);
        if (numero >= 0) {
            btn_cancelar.setDisable(false);
            btn_liquidar.setDisable(false);
            apartadoSeleccionado = ApartadoDAO.obtenerApartadoPorId(tb_Apartados.getSelectionModel().getSelectedItem().getId());
            monto = tb_Apartados.getSelectionModel().getSelectedItem().getMonto();
        }
    }

    @FXML
    private void filtrar(ActionEvent event) {
        try {
            if (validarCurp()) {
                Cliente cliente = ClienteDAO.buscarPorCURP(txt_curp.getText());
                apartados = ApartadoDAO.obtenerApartadosPorCliente(cliente.getIdcliente());
                datosApartado.clear();
                datos.clear();
                llenarDatos();
                iniciarTabla();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación");
            alert.setHeaderText("Error");
            alert.setContentText("No se encontró información sobre esa CURP");
            alert.show();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Mensaje de confirmación");
        alertConfirmation.setHeaderText("Verificación");
        alertConfirmation.setContentText("¿Desea cancelar este apartado?");
        Optional<ButtonType> result = alertConfirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            ApartadoDAO.cancelarApartado(apartadoSeleccionado.getIdapartado());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    @FXML
    private void liquidar(ActionEvent event) {
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setTitle("Mensaje de confirmación");
        alertConfirmation.setHeaderText("Verificación");
        alertConfirmation.setContentText("¿Desea liquidar este apartado?");
        Optional<ButtonType> result = alertConfirmation.showAndWait();
        if (result.get() == ButtonType.OK) {
            ApartadoDAO.liquidarApartado(empleado, apartadoSeleccionado.getIdapartado(), monto);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    public boolean validarCurp() {
        boolean valido = true;
        if (txt_curp.getText().isEmpty() || txt_curp.getText().length() == 0) {
            valido = false;
        }
        return valido;
    }

}
