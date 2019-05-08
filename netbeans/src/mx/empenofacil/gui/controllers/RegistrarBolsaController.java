/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.gui.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.empenofacil.dao.BolsaDAO;

/**
 * FXML Controller class
 *
 * @author adolf
 */
public class RegistrarBolsaController implements Initializable {

    private static Double balanceActual;
    
    public static void setBalanceActual(Double balance){
        balanceActual = balance;
    }
    
    @FXML
    private TextField montoField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void registrarMonto(){
        Double montoA = Double.parseDouble(montoField.getText());
        
        Double monto = montoA - balanceActual;
        Double balance = balanceActual + monto;
        
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("sucursal", 1);
        parametros.put("monto", monto);
        parametros.put("balacecaja", balance);
        
        if(monto < 0){
            parametros.put("descripcion", "Retiro");
        }else {
            parametros.put("descripcion", "Depósito");
        }
        
        BolsaDAO.registrarMonto(parametros);
        closeStage();
    }
    
    @FXML
    private void closeStage() {
        Stage stage  = (Stage) montoField.getScene().getWindow();
        stage.close();
    }
    
}
