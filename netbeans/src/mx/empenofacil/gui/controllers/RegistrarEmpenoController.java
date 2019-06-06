/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empeno;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.Prenda;
import mx.empenofacil.dao.BolsaDAO;
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.dao.EmpenoDAO;
import mx.empenofacil.dao.PrendaDAO;
import mx.empenofacil.dao.TransaccioncajaDAO;
import mx.empenofacil.gui.tools.Loader;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class RegistrarEmpenoController implements Initializable {

    private static ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    private static List<Image> fotosCliente;
    private static ObservableList<Prenda> listaPrendas;
    private static Double totalPrestado;

    public static void addPhoto(Image foto) {
        if (fotosCliente == null) {
            fotosCliente = new ArrayList<>();
            fotosCliente.add(foto);
            imageProperty.set(foto);
        } else if (fotosCliente.size() >= 3) {
            fotosCliente.set(2, foto);
        } else {
            fotosCliente.add(foto);
        }
    }

    public static void agregarPrenda(Prenda prenda) {
        totalPrestado += prenda.getMontoprestado();
        listaPrendas.add(prenda);
    }

    public static void editarPrendaListada(Prenda prendaEditada, int index) {
        listaPrendas.set(index, prendaEditada);
    }
    
    public static void limpiar(){
        fotosCliente = null;
        listaPrendas = null;
    }

    @FXML
    private Button tomarFotoClienteBtn;

    @FXML
    private ImageView fotoClienteView;

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

    @FXML
    private TableView prendasView;
    
    @FXML
    private TextField almacenajeTxt;
    
    @FXML
    private TextField interesTxt;
    
    @FXML
    private TextField cotitularTxt;

    private Empleado empleadoSesion;
    private Cliente cliente = null;
    private boolean editarCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        editarCliente = false;
        totalPrestado = 0.0;

        if (listaPrendas == null) {
            List<Prenda> lista = new ArrayList<>();
            listaPrendas = FXCollections.observableList(lista);
        }

        prendasView.setItems(listaPrendas);

        Image image = new Image("/mx/empenofacil/imagenes/default.png");
        imageProperty.set(image);

        Bindings.bindBidirectional(fotoClienteView.imageProperty(), imageProperty);

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
    public void tomarFotoCliente() {
        CamaraController.setTipoFoto("cliente");
        abrirCamara();
    }

    @FXML
    public void verFotos() {
        if (fotosCliente != null) {
            VistaImagenesController.setFotos(fotosCliente);
            Stage stage = (Stage) this.tomarFotoClienteBtn.getScene().getWindow();
            Loader.loadAsParent(stage, "/mx/empenofacil/gui/VistaImagenes.fxml", "Fotos");
        }
    }

    private void abrirCamara() {
        Stage stage = (Stage) this.tomarFotoClienteBtn.getScene().getWindow();
        Loader.loadAsParent(stage, "/mx/empenofacil/gui/Camara.fxml", "Cámara");
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
                editarCliente = true;
            }
        }
    }

    private boolean validarCurp() {
        return !(curpBusqueda.getText().isEmpty() || curpBusqueda.getText().trim().length() == 0);
    }

    @FXML
    private void agregar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mx/empenofacil/gui/FormularioClientes.fxml"));
        Parent ejemplo = loader.load();
        Scene ejemploEscena = new Scene(ejemplo);

        FormularioClientesController controller = loader.getController();
        controller.initData(empleadoSesion);

        Stage stage = new Stage();
        stage.setScene(ejemploEscena);
        stage.show();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
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

            Stage stage = new Stage();
            stage.setScene(ejemploEscena);
            stage.show();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    @FXML
    private void agregarPrenda() {
        RegistroPrendaController.setPrendaAEditar(null, 0);
        Stage window = (Stage) this.prendasView.getScene().getWindow();
        Loader.loadAsParent(window, "/mx/empenofacil/gui/RegistroPrenda.fxml", "RegistrarPrenda");
    }

    @FXML
    private void editarPrenda() {
        Prenda prenda = (Prenda) prendasView.getSelectionModel().getSelectedItem();
        int indexPrenda = prendasView.getSelectionModel().getSelectedIndex();
        RegistroPrendaController.setPrendaAEditar(prenda, indexPrenda);
        Stage window = (Stage) this.prendasView.getScene().getWindow();
        Loader.loadAsParent(window, "/mx/empenofacil/gui/RegistroPrenda.fxml", "RegistrarPrenda");
    }

    @FXML
    private void guardar() {

        if (cliente != null) {
            if (listaPrendas != null) {
                
                Random rand = new Random();
                
                cliente.setFotos(fotosCliente);
                if (!editarCliente) {
                    cliente.setIdcliente(rand.nextInt());
                    ClienteDAO.agregarCliente(cliente);
                } else {
                    ClienteDAO.actualizarCliente(cliente);
                }
                
                Double balanceActual = BolsaDAO.getMontoBolsa();
                
                HashMap<String, Object> transaccion = new HashMap<>();
                transaccion.put("sucursal", 1);
                transaccion.put("monto", totalPrestado);
                transaccion.put("balacecaja", (balanceActual - totalPrestado));
                transaccion.put("descripcion", "Empeño");
                
                TransaccioncajaDAO.registrarTransaccion(transaccion);
                
                int idEmpeno = rand.nextInt();
                
                Empeno empeno = new Empeno();
                empeno.setIdempeno(idEmpeno);
                empeno.setCliente(cliente.getIdcliente());
                empeno.setEmpleado(HomeController.getEmpleado().getIdempleado());
                empeno.setTransaccioncaja(TransaccioncajaDAO.obtenerUltimoId());
                LocalDate fecha = LocalDate.now();
                empeno.setFecha(Date.valueOf(fecha));
                empeno.setFechalimite(Date.valueOf(LocalDate.of(fecha.getDayOfYear(), fecha.getMonthValue() + 1, fecha.getDayOfMonth())));
                empeno.setFechaextendida(empeno.getFechalimite());
                empeno.setAlmacenaje(almacenajeTxt.getText() == null? Double.valueOf(almacenajeTxt.getText()) : 0.0);
                empeno.setInteres(interesTxt.getText() == null? Double.valueOf(interesTxt.getText()) : 0.0);
                empeno.setNombrecotitular(cotitularTxt.getText());
                empeno.setEstatusEmpeno(22);
                empeno.setExtendido(false);
                empeno.setVencido(false);
                empeno.setCancelado(false);
                empeno.setComercializado(false);
                
                EmpenoDAO.registrarEmpeno(empeno);
                
                for (Prenda prenda : listaPrendas) {
                    prenda.setEmpeno(empeno.getIdempeno());
                    PrendaDAO.registrarPrenda(prenda);
                }
            }
        }
    }

}
