/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import mx.empenofacil.beans.Empeno;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adolf
 */
public class EmpenoDAOTest {
    
    public EmpenoDAOTest() {
    }

    @Test
    public void prubaRegistrarEmpeno() {
        
        Empeno empeno = new Empeno();
        Random rand = new Random();
        empeno.setIdempeno(rand.nextInt(20));
        empeno.setEmpleado(1);
        empeno.setCliente(1);
        empeno.setTransaccioncaja(5);
        empeno.setFecha(Date.valueOf(LocalDate.now()));
        empeno.setFechalimite(Date.valueOf(LocalDate.now()));
        empeno.setFechaextendida(Date.valueOf(LocalDate.now()));
        empeno.setInteres(100.0);
        empeno.setAlmacenaje(100.0);
        empeno.setNombrecotitular("Carlos Hernández Casas");
        
        boolean correcto = EmpenoDAO.registrarEmpeno(empeno);
        
        assertEquals("Prueba de registro de empeño", true, correcto);
        
    }
    
}
