/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import mx.empenofacil.mybatis.MyBatisUtils;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Foto;
import mx.empenofacil.beans.ItemCatalogo;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Rainbow Dash
 */
public class ClienteDAO {
    
    public static List<Cliente> getTodos() {
        List<Cliente> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("cliente.todos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static boolean agregarCliente(Cliente cliente){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("nombres", cliente.getNombres());
            parametros.put("apellidos", cliente.getApellidos());
            parametros.put("rfc", cliente.getRfc());
            parametros.put("curp", cliente.getCurp());
            parametros.put("identificacion", cliente.getIdentificacion());
            parametros.put("tipoidentificacion", cliente.getTipoidentificacion());
            int numeroFilasAfectadas = 0;
            numeroFilasAfectadas = conn.insert("Cliente.registrarCliente", parametros);
            
            for(Image img: cliente.getFotos()){
                Random rand = new Random();
                Foto foto = new Foto(rand.nextInt(), cliente.getIdcliente(), img, "");
                conn.insert("Foto.agregarFoto", foto);
                conn.insert("Foto.relacionarFotoPrenda", foto);
            }
            
            conn.commit();
            if(numeroFilasAfectadas > 0){
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return false;
    }
    
    public static boolean actualizarCliente(Cliente cliente){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("nombres", cliente.getNombres());
            parametros.put("apellidos", cliente.getApellidos());
            parametros.put("rfc", cliente.getRfc());
            parametros.put("curp", cliente.getCurp());
            parametros.put("identificacion", cliente.getIdentificacion());
            parametros.put("tipoidentificacion", cliente.getTipoidentificacion());
            parametros.put("idcliente", cliente.getIdcliente());
            int numeroFilasAfectadas = 0;
            numeroFilasAfectadas = conn.update("Cliente.actualizarCliente", parametros);
            conn.commit();
            if(numeroFilasAfectadas > 0){
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return false;
    }
    
    public static Cliente buscarPorCURP(String curp){
        Cliente cliente = null;
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            cliente = conn.selectOne("Cliente.buscarClienteCURP", curp);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return cliente;
    }
    
    public static Cliente buscarPorId(int id) {
        Cliente resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectOne("cliente.buscarPorId", id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<Cliente> buscarPorIdNombreRfcCurp(String busqueda) {
        List<Cliente> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("cliente.buscarPorIdNombreRfcCurp", busqueda);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<Cliente> buscarRecientes(int cantidad) {
        List<Cliente> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("cliente.recientes", cantidad);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<ItemCatalogo> obtenerTiposIdentificacion(){
        List<ItemCatalogo> identificaciones = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            identificaciones = conn.selectList("Cliente.obtenerTiposIdentificacion");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn == null){
                conn.close();
            }
        }
        return identificaciones;
    }
}
