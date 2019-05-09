package mx.empenofacil.gui.controllers;

import mx.empenofacil.beans.Articulo;
import mx.empenofacil.dao.ArticuloDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComercializarPane extends VBox implements Initializable {

    private List<Articulo> articulos;

    @FXML
    private Label descripcion;

    @FXML
    private ProgressBar progreso;

    @FXML
    private TextArea log;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_aceptar;

    public ComercializarPane(List<Articulo> articulos) {
        this.articulos = articulos;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/mx/empenofacil/gui/ComercializarPane.fxml"));
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
        descripcion.setText(String.format(
                "Está a punto de comercializar %d prenda(s)",
                articulos.size()
        ));
    }

    @FXML
    public void cancelar() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    @FXML
    public void aceptar() {
        progreso.setProgress(0);
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("Iniciando...").append("\n");
        log.setText(logBuilder.toString());
        
        btn_cancelar.setText("Terminar");
        btn_cancelar.setDisable(true);
        btn_aceptar.setVisible(false);

        new Thread(() -> {

            int total = articulos.size();
            int completados = 0;

            for (Articulo articulo : articulos) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // ignore
                }
                
                logBuilder.append("Comercializando: ").append(articulo.getNombre()).append(" . . . ");
                Platform.runLater(() -> {
                    log.setText(logBuilder.toString());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // ignore
                }
                if (ArticuloDAO.comercializar(articulo)) {
                    logBuilder.append("OK");
                } else {
                    logBuilder.append("ERROR");
                }
                logBuilder.append("\n");

                double porcentaje = (double) ++completados / total;

                Platform.runLater(() -> {
                    log.setText(logBuilder.toString());
                    progreso.setProgress(porcentaje);
                });
            }
            
            logBuilder.append("Finalizado");
            Platform.runLater(() -> {
                log.setText(logBuilder.toString());
            });
            
            btn_cancelar.setDisable(false);
        }).start();


    }
}
