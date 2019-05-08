/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    private ImageView fotoClienteView2;

    @FXML
    private ImageView fotoClienteView3;

    @FXML
    private ImageView fotoPrendaView1;

    @FXML
    private ImageView fotoPrendaView2;

    @FXML
    private ImageView fotoPrendaView3;

    @FXML
    private ImageView fotoPrendaView4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (fotosPrenda != null) {
            inicializarFotosViewsPrenda();
        }

        if (fotosCliente != null) {
            inicializarFotosViewsCliente();
        }

    }

    private void inicializarFotosViewsPrenda() {
        for (int i = 0; i < fotosPrenda.size(); i++) {
            switch (i) {
                case 0:
                    fotoPrendaView1.setImage(fotosPrenda.get(i));
                    break;
                case 1:
                    fotoPrendaView2.setImage(fotosPrenda.get(i));
                    break;
                case 2:
                    fotoPrendaView3.setImage(fotosPrenda.get(i));
                    break;
                case 3:
                    fotoPrendaView4.setImage(fotosPrenda.get(i));
                    break;
            }
        }
    }
    
    private void inicializarFotosViewsCliente() {
        for (int i = 0; i < fotosCliente.size(); i++) {
            switch (i) {
                case 0:
                    fotoClienteView1.setImage(fotosCliente.get(i));
                    break;
                case 1:
                    fotoClienteView2.setImage(fotosCliente.get(i));
                    break;
                case 2:
                    fotoClienteView3.setImage(fotosCliente.get(i));
                    break;
            }
        }
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
    
    private void abrirCamara(){
        Stage stage = (Stage) this.tomarFotoPrendaBtn.getScene().getWindow();
        Loader.loadAsParent(HomeController.getStage(), "/mx/empenofacil/gui/Camara.fxml", "Cámara");
        stage.close();
    }

}
