/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Prenda;
import mx.empenofacil.dao.PrendaDAO;
import mx.empenofacil.gui.tools.Loader;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class RegistroPrendaController implements Initializable {
    
    private static Prenda prendaEditar;
    private static int indexPrenda;
    private static List<Image> fotosPrenda;
    private static ObjectProperty<Image> imageProperty;
    
    public static void setPrendaAEditar(Prenda prenda, int index){
        prendaEditar = prenda;
        indexPrenda = index;
    }
    
    public static void agragarFoto(Image foto){
        if (fotosPrenda == null) {
            fotosPrenda = new ArrayList<>();
            fotosPrenda.add(foto);
            imageProperty.set(foto);
        } else if (fotosPrenda.size() >= 4) {
            fotosPrenda.set(3, foto);
        } else {
            fotosPrenda.add(foto);
        }
    }
    
    @FXML
    private ImageView fotoView;

    @FXML
    private TextField nombrePrenda;
    
    @FXML
    private TextArea descripcionPrenda;
    
    @FXML
    private TextField pesoPrenda;
    
    @FXML
    private TextField avaluoPrenda;
    
    @FXML
    private TextField montoPrestado;
    
    @FXML
    private ComboBox<ItemCatalogo> tipoPrenda;

    @FXML
    private ComboBox<ItemCatalogo> categoriaPrenda;

    private List<ItemCatalogo> tiposPrenda;
    //private List<ItemCatalogo> categoriasPrenda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imageProperty = new SimpleObjectProperty<>();

        tiposPrenda = PrendaDAO.obtenerTiposPrenda();

        tipoPrenda.setItems(FXCollections.observableList(tiposPrenda));
        categoriaPrenda.setItems(FXCollections.observableList(tiposPrenda));

        Callback<ListView<ItemCatalogo>, ListCell<ItemCatalogo>> factory = lv -> new ListCell<ItemCatalogo>() {

            @Override
            protected void updateItem(ItemCatalogo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        tipoPrenda.setCellFactory(factory);
        categoriaPrenda.setCellFactory(factory);
        
        Image image = new Image("/mx/empenofacil/imagenes/default.png");
        imageProperty.set(image);
        
        if(prendaEditar != null){
            this.nombrePrenda.setText(prendaEditar.getNombre());
            this.descripcionPrenda.setText(prendaEditar.getDescripcion());
            this.pesoPrenda.setText(prendaEditar.getPeso().toString());
            this.avaluoPrenda.setText(prendaEditar.getAvaluo().toString());
            this.montoPrestado.setText(prendaEditar.getMontoprestado().toString());
            
            for(int i = 0; i < tiposPrenda.size(); i++){
                if(Objects.equals(tiposPrenda.get(i).getIditemcatalogo(), prendaEditar.getTipo())){
                    tipoPrenda.getSelectionModel().select(i);
                }
                
                if(Objects.equals(tiposPrenda.get(i).getIditemcatalogo(), prendaEditar.getCategoria())){
                    this.categoriaPrenda.getSelectionModel().select(i);
                }
            }
            
            if(prendaEditar.getFotos() != null) {
                System.out.println("No nulo");
                imageProperty.set(prendaEditar.getFotos().get(0));
                fotosPrenda = prendaEditar.getFotos();
            }
        }
        
        Bindings.bindBidirectional(fotoView.imageProperty(), imageProperty);

    }
    
    @FXML
    private void agregarPrenda(){
        
        String nombre = this.nombrePrenda.getText();
        String descripcion = this.descripcionPrenda.getText();
        Double peso = Double.parseDouble(this.pesoPrenda.getText());
        Double avaluo = Double.parseDouble(this.avaluoPrenda.getText());
        Double prestado = Double.parseDouble(this.montoPrestado.getText());
        Integer tipo = this.tipoPrenda.getValue().getIditemcatalogo();
        Integer categoria = this.categoriaPrenda.getValue().getIditemcatalogo();
        
        Random rand = new Random();
        
        Prenda prenda = new Prenda();
        prenda.setIdprenda(rand.nextInt());
        prenda.setNombre(nombre);
        prenda.setDescripcion(descripcion);
        prenda.setPeso(peso);
        prenda.setAvaluo(avaluo);
        prenda.setMontoprestado(prestado);
        prenda.setTipo(tipo);
        prenda.setCategoria(categoria);
        prenda.setFotos(fotosPrenda);
        
        if(prendaEditar == null){
            RegistrarEmpenoController.agregarPrenda(prenda);
        } else {
            RegistrarEmpenoController.editarPrendaListada(prenda, indexPrenda);
        }
        
        cerrarVentana();
    }
    
    @FXML
    private void cerrarVentana(){
        Stage stage = (Stage) this.tipoPrenda.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void tomarFoto() {
        CamaraController.setTipoFoto("prenda");
        abrirCamara();
    }

    @FXML
    public void verFotos() {
        if (fotosPrenda != null) {
            VistaImagenesController.setFotos(fotosPrenda);
            Stage stage = (Stage) this.tipoPrenda.getScene().getWindow();
            Loader.loadAsParent(stage, "/mx/empenofacil/gui/VistaImagenes.fxml", "Fotos");
        }
    }
    
    private void abrirCamara() {
        Stage stage = (Stage) this.categoriaPrenda.getScene().getWindow();
        Loader.loadAsParent(stage, "/mx/empenofacil/gui/Camara.fxml", "Cámara");
    }

}
