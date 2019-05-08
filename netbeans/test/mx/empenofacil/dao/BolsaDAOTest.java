/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.util.HashMap;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adolf
 */
public class BolsaDAOTest {

    public BolsaDAOTest() {
    }

    @Test
    public void prubaGetMonto() {

        Double monto = BolsaDAO.getMontoBolsa();

        assertNotEquals("Prueba para obtener monto", null, monto);

    }

    @Test
    public void pruebaRegistrarMonto() {

        Random rand = new Random();
        Double montoActualizar = Integer.valueOf(rand.nextInt(10000)).doubleValue();
        Double montoActual = BolsaDAO.getMontoBolsa();
        Double balance = montoActual + montoActualizar;
        
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("sucursal", 1);
        parametros.put("monto", montoActualizar);
        parametros.put("balacecaja", balance);
        
        if(montoActualizar < 0){
            parametros.put("descripcion", "Retiro");
        }else {
            parametros.put("descripcion", "Depósito");
        }

        boolean hecho = BolsaDAO.registrarMonto(parametros);

        if (hecho) {
            montoActual = BolsaDAO.getMontoBolsa();
            assertEquals("Prueba de registro de monto", balance, montoActual);
        }
    }

}
