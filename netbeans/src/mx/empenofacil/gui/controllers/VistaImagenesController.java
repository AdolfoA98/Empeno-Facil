/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class VistaImagenesController implements Initializable {

    private static List<Image> listaFotos;

    public static void setFotos(List<Image> fotos) {
        listaFotos = fotos;
    }

    @FXML
    private HBox imageView1Wrapper;

    @FXML
    private HBox imageView2Wrapper;

    @FXML
    private HBox imageView3Wrapper;

    @FXML
    private HBox imageView4Wrapper;

    @FXML
    private ImageView mainImageView;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    private int imagenSeleccionada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imagenSeleccionada = 0;

        imageView1.setOnMouseClicked((MouseEvent e) -> {
            if (imagenSeleccionada != 1) {
                seleccionarImagen(1);
                deseleccionarImagen(imagenSeleccionada);
                imagenSeleccionada = 1;
            }
        });

        imageView2.setOnMouseClicked((MouseEvent e) -> {
            if (imagenSeleccionada != 2) {
                seleccionarImagen(2);
                deseleccionarImagen(imagenSeleccionada);
                imagenSeleccionada = 2;
            }
        });

        imageView3.setOnMouseClicked((MouseEvent e) -> {
            if (imagenSeleccionada != 3) {
                seleccionarImagen(3);
                deseleccionarImagen(imagenSeleccionada);
                imagenSeleccionada = 3;
            }
        });

        imageView4.setOnMouseClicked((MouseEvent e) -> {
            if (imagenSeleccionada != 4) {
                seleccionarImagen(4);
                deseleccionarImagen(imagenSeleccionada);
                imagenSeleccionada = 4;
            }
        });

        if (listaFotos != null) {
            for (int i = 0; i < listaFotos.size(); i++) {
                switch (i) {
                    case 0:
                        imageView1.setImage(listaFotos.get(i));
                        break;
                    case 1:
                        imageView2.setImage(listaFotos.get(i));
                        break;
                    case 2:
                        imageView3.setImage(listaFotos.get(i));
                        break;
                    case 3:
                        imageView4.setImage(listaFotos.get(i));
                        break;
                }
            }
        }

    }

    private void seleccionarImagen(int imagenSeleccionar) {

        switch (imagenSeleccionar) {
            case 1:
                if (listaFotos != null && listaFotos.size() >= 1) {
                    imageView1Wrapper.setStyle("-fx-border-color: yellow;");
                    mainImageView.setImage(listaFotos.get(0));
                }
                break;
            case 2:
                if (listaFotos != null && listaFotos.size() >= 2) {
                    imageView2Wrapper.setStyle("-fx-border-color: yellow;");
                    mainImageView.setImage(listaFotos.get(1));
                }
                break;
            case 3:
                if (listaFotos != null && listaFotos.size() >= 3) {
                    imageView3Wrapper.setStyle("-fx-border-color: yellow;");
                    mainImageView.setImage(listaFotos.get(2));
                }
                break;
            case 4:
                if (listaFotos != null && listaFotos.size() >= 4) {
                    imageView4Wrapper.setStyle("-fx-border-color: yellow;");
                    mainImageView.setImage(listaFotos.get(3));
                }
                break;
        }

    }

    private void deseleccionarImagen(int imagenSeleccionada) {

        switch (imagenSeleccionada) {
            case 1:
                imageView1Wrapper.setStyle("-fx-border-color: white;");
                break;
            case 2:
                imageView2Wrapper.setStyle("-fx-border-color: white;");
                break;
            case 3:
                imageView3Wrapper.setStyle("-fx-border-color: white;");
                break;
            case 4:
                imageView4Wrapper.setStyle("-fx-border-color: white;");
                break;
        }

    }

}
