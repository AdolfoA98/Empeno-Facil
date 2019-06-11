/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.beans.Empeno;
import mx.empenofacil.beans.Empeño2;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author adolf
 */
public class EmpenoDAO {

    public static List<Empeno> getTodos() {
        List<Empeno> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("empeno.todos");
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public static boolean registrarEmpeno(Empeno empeno) {
        boolean completado = true;

        try (SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("registrarEmpeno", empeno);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
            completado = false;
        }

        return completado;
    }
    
    public static List<Empeño2> getVencidosNoComercializados() {
        List<Empeño2> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("empeno.vencidosNoComercializados");
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static List<Empeno> getEmpenosPorCliente(Integer idcliente, Integer estatusEmpeno){
        List<Empeno> empeños = null;
        Empeno empeno = new Empeno();
        empeno.setCliente(idcliente);
        empeno.setEstatusEmpeno(estatusEmpeno);
        try(SqlSession conn = MyBatisUtils.getSession()){
            empeños = conn.selectList("empeno.buscarPorIdCliente", empeno);
        } catch (IOException ex){
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empeños;
    }
    
    public static List<Empeno> getEmpenosPorEstatus(Integer estatus){
        List<Empeno> resultado = null;
        try(SqlSession conn = MyBatisUtils.getSession()){
            resultado = conn.selectList("empeno.buscarPorEstatus", estatus);
        } catch (IOException ex) { 
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static Empeno getEmpenoPorId(Integer idempeno){
        Empeno empeno = new Empeno();
        try(SqlSession conn = MyBatisUtils.getSession()){
            empeno = conn.selectOne("empeno.getEmpenoPorId", idempeno);
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empeno;
    }
    
    public static boolean actualizarEstadoEmpeno(Integer idempeno, Integer estado){
        boolean valido = false;
        try(SqlSession conn = MyBatisUtils.getSession()){
            Empeno empeno = new Empeno();
            empeno.setIdempeno(idempeno);
            empeno.setEstatusEmpeno(estado);
            int realizado = conn.update("empeno.actualizarEstado", empeno);
            conn.commit();
            if(realizado > 0){
                valido = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }
    
    public static boolean registrarNuevoEmpeno(Empeno empeno){
        boolean valido = false;
        try(SqlSession conn = MyBatisUtils.getSession()){
            int realizado = conn.insert("empeno.guardarNuevoEmpeno", empeno);
            conn.commit();
            if(realizado > 0){
                valido = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }
    
    public static int obtenerID(){
        int idSeleccion = 0;
        List<Empeno> id;
        try(SqlSession conn = MyBatisUtils.getSession()){
            id = conn.selectList("empeno.obtenerID");
            idSeleccion = id.get(0).getIdempeno();
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idSeleccion;
    }

}
