/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.util.Random;
import mx.empenofacil.beans.Prenda;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adolf
 */
public class PrendaDAOTest {
    
    public PrendaDAOTest() {
    }

    @Test
    public void registrarPrenda() {
        
        Prenda prenda = new Prenda();
        Random rand = new Random();
        prenda.setIdprenda(rand.nextInt(20));
        prenda.setEmpeno(7);
        prenda.setTipo(1);
        prenda.setCategoria(1);
        prenda.setAvaluo(200.0);
        prenda.setMontoprestado(250.0);
        prenda.setNombre("Prenda");
        prenda.setDescripcion("Descripción de prenda");
        prenda.setPeso(200.0);
        
        boolean completado = PrendaDAO.registrarPrenda(prenda);
        
        assertEquals("Prueba de registro de prenda", true, completado);
        
    }
    
}
