package mx.empenofacil.gui.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.dao.BolsaDAO;
import mx.empenofacil.gui.tools.Loader;

/**
 *
 * @author adolf
 */
public class HomeController implements Initializable {

    private static Stage stage;
    private static Empleado empleado;

    public static void setEmpleado(Empleado emp) {
        empleado = emp;
    }

    public static void setStage(Stage stg) {
        stage = stg;
    }

    public static Stage getStage() {
        return stage;
    }

    @FXML
    private BorderPane mainPane;

    @FXML
    private ToolBar sectionTools;

    @FXML
    private JFXButton adminSButton;

    @FXML
    private JFXButton ventaSButton;

    @FXML
    private JFXButton historialSButton;

    @FXML
    private JFXButton reportesSButton;

    @FXML
    private TextField barcodeInput;

    @FXML
    private Label cajaLabel;

    //Empiezan los botones en los toolbars
    //Seción de administración
    private JFXButton registrarUsuarioBtn;
    private JFXButton usuariosBtn;
    private JFXButton registrarCajaBtn;

    //Sección de ventas y empeños
    private JFXButton clientesButton;
    private JFXButton registrarEmpenoBtn;
    private JFXButton registrarRefrendoBtn;
    private JFXButton registrarReempenoBtn;
    private JFXButton listaNegraBtn;
    private JFXButton articulosBtn;
    private JFXButton apartarBtn;
    private JFXButton venderBtn;

    //Sección de historial de operaciones
    private JFXButton apartadosBtn;
    private JFXButton ventasBtn;
    private JFXButton contratosBtn;

    //Sección de reportes
    private JFXButton operacionesBtn;
    private JFXButton flujoCajaBtn;
    private JFXButton reporteContratosBtn;
    private JFXButton articulosVendidosBtn;
    private JFXButton articulosVentaBtn;
    private JFXButton reporteApartadosBtn;

    //Listas de tools por secciones
    private List<Node> adminList;
    private List<Node> ventaList;
    private List<Node> historialList;
    private List<Node> reportesList;

    private double montoBolsa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.inicializarTools();
        this.inicializarListas();
        cambiarToolBarAVenta();
        actualizarMontoCaja();

        PuntoVentaController page = new PuntoVentaController();
        mainPane.setCenter(page.getMainPane());
    }

    private void inicializarTools() {

        //Administración
        this.usuariosBtn = new JFXButton("\nUsuarios");
        this.usuariosBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.usuariosBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/mx/empenofacil/gui/AdministrarUsuario.fxml"));
                    Parent ejemplo = loader.load();
                    Scene ejemploEscena = new Scene(ejemplo);
                    
                    AdministrarUsuarioController controller = loader.getController();
                    controller.initData(empleado);
                    
                    Stage window = new Stage();
                    window.setScene(ejemploEscena);
                    window.setTitle("Usuarios");
                    window.initOwner(stage);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.registrarCajaBtn = new JFXButton("\nRegistrar monto en caja");
        this.registrarCajaBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.registrarCajaBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegistrarBolsaController.setBalanceActual(montoBolsa);
                Loader.loadAsParent(stage, "/mx/empenofacil/gui/RegistrarBolsa.fxml", "Registrar bolsa");
                actualizarMontoCaja();
            }

        });

        //Ventas y empeños
        this.clientesButton = new JFXButton("\nClientes");
        this.clientesButton.setFont(Font.font("Segoe MDL2 Assets"));
        this.registrarEmpenoBtn = new JFXButton("\nRegistrar empeño");
        this.registrarEmpenoBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.registrarEmpenoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Loader.loadAsParent(stage, "/mx/empenofacil/gui/RegistrarEmpeno.fxml", "Registrar empeño");
            }

        });
        this.registrarRefrendoBtn = new JFXButton("\nRegistrar refrendo");
        this.registrarRefrendoBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.registrarRefrendoBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarReempenoRefrendo.fxml"));
                    Parent ejemplo = loader.load();
                    Scene ejemploEscena = new Scene(ejemplo);
                    
                    RegistrarReempenoRefrendoController controller = loader.getController();
                    controller.initData(0, empleado);
                    
                    Stage window = new Stage();
                    window.setScene(ejemploEscena);
                    window.setTitle("Registrar refrendo");
                    window.initOwner(stage);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });

        this.registrarReempenoBtn = new JFXButton("\nRegistrar reempeño");
        this.registrarReempenoBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.registrarReempenoBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/mx/empenofacil/gui/RegistrarReempenoRefrendo.fxml"));
                    Parent ejemplo = loader.load();
                    Scene ejemploEscena = new Scene(ejemplo);
                    
                    RegistrarReempenoRefrendoController controller = loader.getController();
                    controller.initData(1,empleado);
                    
                    Stage window = new Stage();
                    window.setScene(ejemploEscena);
                    window.setTitle("Registrar refrendo");
                    window.initOwner(stage);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });

        this.listaNegraBtn = new JFXButton("\nLista negra");
        this.listaNegraBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.listaNegraBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage nuevoStage = new Stage();
                nuevoStage.setTitle("Lista negra");
                nuevoStage.setScene(new Scene(new ListaNegraAdmin(empleado)));
                nuevoStage.initOwner(stage);
                nuevoStage.initModality(Modality.APPLICATION_MODAL);
                nuevoStage.show();
            }

        });

        this.articulosBtn = new JFXButton("\nArtículos");
        this.articulosBtn.setFont(Font.font("Segoe MDL2 Assets"));

        this.apartarBtn = new JFXButton("\nApartar artículo");
        this.apartarBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.apartarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialogs.mostrarDialog(new VentaPaneController(empleado, 2), "Apartar");
            }
        });
        
        this.venderBtn = new JFXButton("\nVender artículo(s)");
        this.venderBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.venderBtn.setOnAction((event) -> {
            Dialogs.mostrarDialog(new VentaPaneController(empleado, 1), "Vender");
        });

        //Historial de operaciones
        this.apartadosBtn = new JFXButton("\nApartados");
        this.apartadosBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.apartadosBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/mx/empenofacil/gui/AdministrarApartados.fxml"));
                    Parent ejemplo = loader.load();
                    Scene ejemploEscena = new Scene(ejemplo);
                    
                    AdministrarApartadosController controller = loader.getController();
                    controller.initData(empleado);
                    
                    Stage window = new Stage();
                    window.setScene(ejemploEscena);
                    window.setTitle("Apartados Disponibles");
                    window.initOwner(stage);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });

        this.ventasBtn = new JFXButton("\nVentas");
        this.ventasBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.ventasBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialogs.mostrarDialog(new HistorialVentasController(), "Historial de ventas");
            }
        });

        this.contratosBtn = new JFXButton("\nContratos vencidos");
        this.contratosBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.contratosBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage nuevoStage = new Stage();
                nuevoStage.setTitle("Empeños Vencidos");
                nuevoStage.setScene(new Scene(new EmpenosVencidos(empleado)));
                nuevoStage.initOwner(stage);
                nuevoStage.initModality(Modality.APPLICATION_MODAL);
                nuevoStage.show();
            }

        });

        //Reportes
        this.operacionesBtn = new JFXButton("\nReporte diario de operaciones");
        this.operacionesBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.flujoCajaBtn = new JFXButton("\nFlujo de caja");
        this.flujoCajaBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.reporteContratosBtn = new JFXButton("\nReporte de contratos");
        this.reporteContratosBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.articulosVendidosBtn = new JFXButton("\nArtículos vendidos");
        this.articulosVendidosBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.articulosVentaBtn = new JFXButton("\nArtículos en venta");
        this.articulosVentaBtn.setFont(Font.font("Segoe MDL2 Assets"));
        this.reporteApartadosBtn = new JFXButton("\nApartados");
        this.reporteApartadosBtn.setFont(Font.font("Segoe MDL2 Assets"));
    }

    private void inicializarListas() {

        //Sección de administración
        adminList = new ArrayList<>();
        adminList.add(this.usuariosBtn);
        adminList.add(this.registrarCajaBtn);

        //Sección venta
        this.ventaList = new ArrayList<>();
        ventaList.add(this.clientesButton);
        ventaList.add(this.registrarEmpenoBtn);
        ventaList.add(this.registrarRefrendoBtn);
        ventaList.add(this.registrarReempenoBtn);
        ventaList.add(this.listaNegraBtn);
        ventaList.add(this.articulosBtn);
        ventaList.add(this.apartarBtn);
        ventaList.add(this.venderBtn);

        //Historial del operaciones
        this.historialList = new ArrayList<>();
        historialList.add(this.apartadosBtn);
        historialList.add(this.ventasBtn);
        historialList.add(this.contratosBtn);

        //Reportes
        this.reportesList = new ArrayList<>();
        reportesList.add(this.operacionesBtn);
        reportesList.add(this.flujoCajaBtn);
        reportesList.add(this.contratosBtn);
        reportesList.add(this.articulosVendidosBtn);
        reportesList.add(this.articulosVentaBtn);
        reportesList.add(this.reporteApartadosBtn);

    }

    @FXML
    public void actualizarMontoCaja() {

        montoBolsa = BolsaDAO.getMontoBolsa();
        cajaLabel.setText("Caja: " + montoBolsa);

    }

    @FXML
    public void cambiarToolBarAVenta() {
        ventaSButton.setStyle("-fx-background-color: #EEEBEB;");//#F3D46D;
        historialSButton.setStyle("-fx-background-color: #F3D46D;");
        reportesSButton.setStyle("-fx-background-color: #F3D46D;");
        adminSButton.setStyle("-fx-background-color: #F3D46D;");

        sectionTools.getItems().setAll(ventaList);
    }

    @FXML
    public void cambiarToolBarAHistorial() {
        ventaSButton.setStyle("-fx-background-color: #F3D46D;");//#F3D46D;
        historialSButton.setStyle("-fx-background-color: #EEEBEB;");
        reportesSButton.setStyle("-fx-background-color: #F3D46D;");
        adminSButton.setStyle("-fx-background-color: #F3D46D;");

        sectionTools.getItems().setAll(historialList);
    }

    @FXML
    public void cambiarToolBarAReportes() {
        ventaSButton.setStyle("-fx-background-color: #F3D46D;");//#F3D46D;
        historialSButton.setStyle("-fx-background-color: #F3D46D;");
        reportesSButton.setStyle("-fx-background-color: #EEEBEB;");
        adminSButton.setStyle("-fx-background-color: #F3D46D;");

        sectionTools.getItems().setAll(reportesList);
    }

    @FXML
    public void cambiarToolBarAAdmin() {
        ventaSButton.setStyle("-fx-background-color: #F3D46D;");//#F3D46D;
        historialSButton.setStyle("-fx-background-color: #F3D46D;");
        reportesSButton.setStyle("-fx-background-color: #F3D46D;");
        adminSButton.setStyle("-fx-background-color: #EEEBEB;");

        sectionTools.getItems().setAll(adminList);
    }

}
