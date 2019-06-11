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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.DatosEmpeno;
import mx.empenofacil.beans.Empeno;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.Prenda;
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.dao.EmpenoDAO;
import mx.empenofacil.dao.PrendaDAO;

/**
 * FXML Controller class
 *
 * @author Rainbow Dash
 */
public class RegistrarReempenoRefrendoController implements Initializable {

    @FXML
    private TableView<DatosEmpeno> tblEmpeno;
    @FXML
    private TableColumn<DatosEmpeno, Integer> colId;
    @FXML
    private TableColumn<DatosEmpeno, String> colNombre;
    @FXML
    private TableColumn<DatosEmpeno, String> colPrendas;
    @FXML
    private TableColumn<DatosEmpeno, Double> colInteres;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtCURP;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnRegistrar;
    List<Empeno> empeños = new ArrayList<>();
    List<Prenda> prendas = new ArrayList<>();
    int estatus = 0;
    private ObservableList<DatosEmpeno> datos;
    List<DatosEmpeno> datosEmpeno = new ArrayList<>();
    Empeno empenoSeleccionado = null;
    Empleado empleado;

    //LA eleccion que puede tomar son 0 = Refrendo, 1 = Reempeño y 2 = Finiquito
    public void initData(int eleccion, Empleado empleado) {
        this.empleado = empleado;
        btnRegistrar.setDisable(true);
        switch (eleccion) {
            case 0:
                colInteres.setText("Interes");
                estatus = 22;
                obtenerEmpenos();
                llenarDatos();
                break;
            case 1:
                colInteres.setText("Total");
                estatus = 37;
                obtenerEmpenos();
                llenarDatos();
                break;
            default:
                //Se supone que aquí van los empeños finiquitados
                break;
        }
    }

    public void obtenerEmpenos() {
        empeños = EmpenoDAO.getEmpenosPorEstatus(estatus);
    }

    public void llenarDatos() {
        if (estatus == 22) {
            for (int i = 0; i < empeños.size(); i++) {
                Cliente clienteEmpeno;
                String nombrePrendas = "";
                Double totalPrendas = 0.0;
                prendas = PrendaDAO.obtenerPrendasPorEmpeno(empeños.get(i).getIdempeno());
                if (prendas.size() > 1) {
                    for (int j = 0; j < prendas.size(); j++) {
                        nombrePrendas += "\n" + prendas.get(j).getNombre();
                        totalPrendas += prendas.get(j).getMontoprestado();
                    }
                } else {
                    nombrePrendas = prendas.get(0).getNombre();
                    totalPrendas = prendas.get(0).getMontoprestado();
                }
                clienteEmpeno = ClienteDAO.buscarPorId(empeños.get(i).getCliente());
                DatosEmpeno datosEmpenoSolitario = new DatosEmpeno(empeños.get(i).getIdempeno(), clienteEmpeno.getNombres() + " " + clienteEmpeno.getApellidos(),
                        nombrePrendas, empeños.get(i).getInteres(), totalPrendas);
                datosEmpeno.add(datosEmpenoSolitario);
                prendas.clear();
            }
            iniciarTablaRefrendo();
        } else if (estatus == 37) {
            for (int i = 0; i < empeños.size(); i++) {
                Cliente clienteEmpeno;
                String nombrePrendas = "";
                Double totalPrendas = 0.0;
                prendas = PrendaDAO.obtenerPrendasPorEmpeno(empeños.get(i).getIdempeno());
                if (prendas.size() > 1) {
                    for (int j = 0; j < prendas.size(); j++) {
                        nombrePrendas += "\n" + prendas.get(j).getNombre();
                        totalPrendas += prendas.get(j).getMontoprestado();
                    }
                } else {
                    nombrePrendas = prendas.get(0).getNombre();
                    totalPrendas = prendas.get(0).getMontoprestado();
                }
                clienteEmpeno = ClienteDAO.buscarPorId(empeños.get(i).getCliente());
                DatosEmpeno datosEmpenoSolitario = new DatosEmpeno(empeños.get(i).getIdempeno(), clienteEmpeno.getNombres() + " " + clienteEmpeno.getApellidos(),
                        nombrePrendas, empeños.get(i).getInteres(), totalPrendas);
                datosEmpeno.add(datosEmpenoSolitario);
                prendas.clear();
            }
            iniciarTablaReempeno();
        }
    }

    public void iniciarTablaRefrendo() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idempeno"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colPrendas.setCellValueFactory(new PropertyValueFactory<>("nombrePrenda"));
        colInteres.setCellValueFactory(new PropertyValueFactory<>("interes"));

        colocarDatos();

        tblEmpeno.setItems(datos);
    }

    public void iniciarTablaReempeno() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idempeno"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colPrendas.setCellValueFactory(new PropertyValueFactory<>("nombrePrenda"));
        colInteres.setCellValueFactory(new PropertyValueFactory<>("total"));

        colocarDatos();

        tblEmpeno.setItems(datos);
    }

    public void colocarDatos() {
        datos = FXCollections.observableArrayList();
        datosEmpeno.forEach((de) -> {
            datos.add(de);
        });
    }

    /**
     * Initializes the controller class.
     */
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
    private void filtrar(ActionEvent event) {
        try {
            if (validarCurp()) {
                Cliente cliente = ClienteDAO.buscarPorCURP(txtCURP.getText());
                empeños = EmpenoDAO.getEmpenosPorCliente(cliente.getIdcliente(), estatus);
                datosEmpeno.clear();
                datos.clear();
                llenarDatos();
                if (estatus == 22) {
                    iniciarTablaRefrendo();
                } else if (estatus == 37) {
                    iniciarTablaReempeno();
                }
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
    private void registrar(ActionEvent event) {
        Empeno nuevoEmpeno = new Empeno();
        if (estatus == 22) {
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Mensaje de confirmación");
            alertConfirmation.setHeaderText("Verificación");
            alertConfirmation.setContentText("Desea refrendar este empeño con el monto a pagar de: " + tblEmpeno.getSelectionModel().getSelectedItem().getInteres()
                    + "\n bajo el cliente: " + tblEmpeno.getSelectionModel().getSelectedItem().getNombreCliente() + "?");
            Optional<ButtonType> result = alertConfirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                nuevoEmpeno.setAlmacenaje(empenoSeleccionado.getAlmacenaje());
                nuevoEmpeno.setCliente(empenoSeleccionado.getCliente());
                nuevoEmpeno.setEmpleado(empleado.getIdempleado());
                nuevoEmpeno.setEstatusEmpeno(22);
                nuevoEmpeno.setFechalimite(empenoSeleccionado.getFechalimite());
                nuevoEmpeno.setInteres(empenoSeleccionado.getInteres());
                nuevoEmpeno.setNombrecotitular(empenoSeleccionado.getNombrecotitular());
                if (EmpenoDAO.registrarNuevoEmpeno(nuevoEmpeno)) {
                    int id = EmpenoDAO.obtenerID();
                    prendas = PrendaDAO.obtenerPrendasPorEmpeno(empenoSeleccionado.getIdempeno());
                    for (int i = 0; i < prendas.size(); i++) {
                        Prenda nuevaPrenda;
                        nuevaPrenda = prendas.get(i);
                        nuevaPrenda.setEmpeno(id);
                        PrendaDAO.agregarPrenda(nuevaPrenda);
                    }
                    EmpenoDAO.actualizarEstadoEmpeno(empenoSeleccionado.getIdempeno(), 24);
                    initData(estatus, empleado);
                }
            }
        } else if (estatus == 37) {
            Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Mensaje de confirmación");
            alertConfirmation.setHeaderText("Verificación");
            alertConfirmation.setContentText("Desea reempeñar este empeño con el monto a pagar de (con intereses): " + tblEmpeno.getSelectionModel().getSelectedItem().getTotal()
                    + "\n del cliente: " + tblEmpeno.getSelectionModel().getSelectedItem().getNombreCliente() + "?");
            Optional<ButtonType> result = alertConfirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                nuevoEmpeno.setAlmacenaje(empenoSeleccionado.getAlmacenaje());
                nuevoEmpeno.setCliente(empenoSeleccionado.getCliente());
                nuevoEmpeno.setEmpleado(empleado.getIdempleado());
                nuevoEmpeno.setEstatusEmpeno(22);
                nuevoEmpeno.setFechalimite(empenoSeleccionado.getFechalimite());
                nuevoEmpeno.setInteres(empenoSeleccionado.getInteres());
                nuevoEmpeno.setNombrecotitular(empenoSeleccionado.getNombrecotitular());
                if (EmpenoDAO.registrarNuevoEmpeno(nuevoEmpeno)) {
                    int id = EmpenoDAO.obtenerID();
                    prendas = PrendaDAO.obtenerPrendasPorEmpeno(empenoSeleccionado.getIdempeno());
                    for (int i = 0; i < prendas.size(); i++) {
                        Prenda nuevaPrenda;
                        nuevaPrenda = prendas.get(i);
                        nuevaPrenda.setEmpeno(id);
                        PrendaDAO.agregarPrenda(nuevaPrenda);
                    }
                    initData(estatus, empleado);
                }
            }
        }
    }

    @FXML
    private void seleccionEmpeno(MouseEvent event) {
        int numero = tblEmpeno.getSelectionModel().getSelectedIndex();
        if (numero >= 0) {
            btnRegistrar.setDisable(false);
            empenoSeleccionado = EmpenoDAO.getEmpenoPorId(tblEmpeno.getSelectionModel().getSelectedItem().getIdempeno());
        }
    }

    public boolean validarCurp() {
        boolean valido = true;
        if (txtCURP.getText().isEmpty() || txtCURP.getText().length() == 0) {
            valido = false;
        }
        return valido;
    }

}
