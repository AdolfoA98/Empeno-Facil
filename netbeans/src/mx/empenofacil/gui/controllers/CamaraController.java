/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mx.empenofacil.gui.tools.Loader;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class CamaraController implements Initializable {

    
    private static String tipoFoto;
    
    public static void setTipoFoto(String tipo){
        tipoFoto = tipo;
    }
    
    @FXML

    Button btnStartCamera;

    @FXML

    Button btnDisposeCamera;

    @FXML

    ComboBox<WebCamInfo> cbCameraOptions;

    @FXML

    BorderPane bpWebCamPaneHolder;

    @FXML

    FlowPane fpBottomPane;

    @FXML

    ImageView imgWebCamCapturedImage;

    private class WebCamInfo {

        private String webCamName;

        private int webCamIndex;

        public String getWebCamName() {

            return webCamName;

        }

        public void setWebCamName(String webCamName) {

            this.webCamName = webCamName;

        }

        public int getWebCamIndex() {

            return webCamIndex;

        }

        public void setWebCamIndex(int webCamIndex) {

            this.webCamIndex = webCamIndex;

        }

        @Override

        public String toString() {

            return webCamName;

        }

    }

    private BufferedImage grabbedImage;

    private Webcam selWebCam = null;

    private boolean stopCamera = false;

    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

    private String cameraListPromptText = "Choose Camera";

    @Override

    public void initialize(URL arg0, ResourceBundle arg1) {

        fpBottomPane.setDisable(true);

        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();

        int webCamCounter = 0;

        for (Webcam webcam : Webcam.getWebcams()) {

            WebCamInfo webCamInfo = new WebCamInfo();

            webCamInfo.setWebCamIndex(webCamCounter);

            webCamInfo.setWebCamName(webcam.getName());

            options.add(webCamInfo);

            webCamCounter++;

        }

        cbCameraOptions.setItems(options);

        cbCameraOptions.setPromptText(cameraListPromptText);

        cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

            @Override

            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {

                if (arg2 != null) {

                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());

                    initializeWebCam(arg2.getWebCamIndex());

                }

            }

        });

        Platform.runLater(new Runnable() {

            @Override

            public void run() {

                setImageViewSize();

            }

        });

    }

    protected void setImageViewSize() {

        double height = bpWebCamPaneHolder.getHeight();

        double width = bpWebCamPaneHolder.getWidth();

        imgWebCamCapturedImage.setFitHeight(height);

        imgWebCamCapturedImage.setFitWidth(width);

        imgWebCamCapturedImage.prefHeight(height);

        imgWebCamCapturedImage.prefWidth(width);

        imgWebCamCapturedImage.setPreserveRatio(true);

    }

    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamIntilizer = new Task<Void>() {

            @Override

            protected Void call() throws Exception {

                if (selWebCam == null) {

                    selWebCam = Webcam.getWebcams().get(webCamIndex);

                    selWebCam.open();

                } else {

                    closeCamera();

                    selWebCam = Webcam.getWebcams().get(webCamIndex);

                    selWebCam.open();

                }

                startWebCamStream();

                return null;

            }

        };

        new Thread(webCamIntilizer).start();

        fpBottomPane.setDisable(false);

    }

    protected void startWebCamStream() {

        stopCamera = false;

        Task<Void> task = new Task<Void>() {

            @Override

            protected Void call() throws Exception {

                while (!stopCamera) {

                    try {

                        if ((grabbedImage = selWebCam.getImage()) != null) {

                            Platform.runLater(new Runnable() {

                                @Override

                                public void run() {

                                    final Image mainiamge = SwingFXUtils
                                            .toFXImage(grabbedImage, null);

                                    imageProperty.set(mainiamge);

                                }

                            });

                            grabbedImage.flush();

                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }

                return null;

            }

        };

        Thread th = new Thread(task);

        th.setDaemon(true);

        th.start();

        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }

    private void closeCamera() {

        if (selWebCam != null) {

            selWebCam.close();

        }

    }

    public void tomarFoto(ActionEvent event) {

        stopCamera = true;
        Image image = imgWebCamCapturedImage.getImage();
        if(tipoFoto.equals("prenda")){
            RegistroPrendaController.agragarFoto(image);
        } else if(tipoFoto.equals("cliente")){
            RegistrarEmpenoController.addPhoto(image);
        }
        closeCamera();
        cerrar();

    }

    public void disposeCamera(ActionEvent event) {

        stopCamera = true;
        closeCamera();
        cerrar();
        
    }

    private void cerrar() {
        Stage stage = (Stage) btnStartCamera.getScene().getWindow();
        stage.close();
    }

}
