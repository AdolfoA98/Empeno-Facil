/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.ClienteDAO;
import mx.empenofacil.gui.tools.Loader;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class RegistrarEmpenoController implements Initializable {

    private static List<Image> fotosPrenda;
    private static List<Image> fotosCliente;

    public static void addPhoto(Image foto, String tipoFoto) {
        switch (tipoFoto) {
            case "prenda":
                if (fotosPrenda == null) {
                    fotosPrenda = new ArrayList<>();
                    fotosPrenda.add(foto);
                } else if (fotosPrenda.size() >= 4) {
                    fotosPrenda.set(3, foto);
                } else {
                    fotosPrenda.add(foto);
                }
                break;
            case "cliente":
                if (fotosCliente == null) {
                    fotosCliente = new ArrayList<>();
                    fotosCliente.add(foto);
                } else if (fotosCliente.size() >= 3) {
                    fotosCliente.set(2, foto);
                } else {
                    fotosCliente.add(foto);
                }
                break;
        }
    }

    @FXML
    private Button tomarFotoPrendaBtn;

    @FXML
    private ImageView fotoClienteView1;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    public void tomarFotoPrenda() {
        CamaraController.setTipoFoto("prenda");
        abrirCamara();
    }

    @FXML
    public void tomarFotoCliente() {
        CamaraController.setTipoFoto("cliente");
        abrirCamara();
    }

    private void abrirCamara() {
        Stage stage = (Stage) this.tomarFotoPrendaBtn.getScene().getWindow();
        Loader.loadAsParent(HomeController.getStage(), "/mx/empenofacil/gui/Camara.fxml", "Cámara");
        stage.close();
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

}
