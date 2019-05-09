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
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.mybatis.MyBatisUtils;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.Sucursal;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Rainbow Dash
 */
public class EmpleadoDAO {
    public static Empleado inicarSesion(String nombreUsuario, String contrasenia){
        Empleado empleado = new Empleado();
        SqlSession conn = null;
        
        try{
            HashMap<String,Object> parametros = new HashMap<>();
            parametros.put("nombreusuario", nombreUsuario);
            parametros.put("contrasenahash", contrasenia);
            
            conn = MyBatisUtils.getSession();
            empleado = conn.selectOne("Sesion.usuarioRegistrado", parametros);
            
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(conn!=null) conn.close();
        }
        return empleado;
    }
    
    public static List<Empleado> getTodos() {
        List<Empleado> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("empleado.todos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static Empleado getEmpleado(int id) {
        Empleado resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectOne("empleado.buscarPorId", id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static boolean agregarEmpleado(Empleado empleado){
        SqlSession conn = null;
        try{
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("sucursal", empleado.getSucursal());
            parametros.put("numpersonal", empleado.getNumpersonal());
            parametros.put("nombres", empleado.getNombres());
            parametros.put("apellidos", empleado.getApellidos());
            parametros.put("contrasenahash", empleado.getContrasenahash());
            parametros.put("tipoempleado", empleado.getTipoempleado());
            parametros.put("nombreusuario", empleado.getNombreusuario());
            
            conn = MyBatisUtils.getSession();
            
            int numeroFilasAfectadas = 0;
            numeroFilasAfectadas = conn.insert("Empleado.registrarEmpleado", parametros);
            conn.commit();
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public static boolean actualizarEmpleado(Empleado empleado){
        SqlSession conn = null;
        try{
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("sucursal", empleado.getSucursal());
            parametros.put("numpersonal", empleado.getNumpersonal());
            parametros.put("nombres", empleado.getNombres());
            parametros.put("apellidos", empleado.getApellidos());
            parametros.put("contrasenahash", empleado.getContrasenahash());
            parametros.put("tipoempleado", empleado.getTipoempleado());
            parametros.put("nombreusuario", empleado.getNombreusuario());
            
            conn = MyBatisUtils.getSession();
            
            int numeroFilasAfectadas = 0;
            numeroFilasAfectadas = conn.update("Empleado.actualizarEmpleado", parametros);
            conn.commit();
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public static boolean eliminarEmpleado (String numeroPersonal){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            int numeroFilasAfectadas = 0;
            numeroFilasAfectadas = conn.delete("Empleado.eliminarEmpleado", numeroPersonal);
            conn.commit();
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
    
    public static List<String> obtenerNumerosPersonal(){
        List<String> empleados = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            empleados = conn.selectList("Empleado.numerosPersonal");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return empleados;
    }
    
    public static List<String> obtenerNombresUsuario(){
        List<String> empleados = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            empleados = conn.selectList("Empleado.nombresUsuario");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return empleados;
    }
    
    public static List<Empleado> obtenerTodo(){
        List<Empleado> empleados = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            empleados = conn.selectList("Empleado.obtenerTodo");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return empleados;
    }
    
    public static List<ItemCatalogo> obtenerTipoEmpleado(){
        List<ItemCatalogo> tiposEmpleado = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            tiposEmpleado = conn.selectList("Empleado.obtenerTipoEmpleado");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return tiposEmpleado;
    }
    
    public static List<Sucursal> obtenerSucursales(){
        List<Sucursal> sucursales = new ArrayList<>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            
            sucursales = conn.selectList("Empleado.obtenerSucursales");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(conn!=null){
                conn.close();
            }
        }
        return sucursales;
    }
}
