package mx.empenofacil.dao;

import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.RegistroListaNegra;
import mx.empenofacil.mybatis.MyBatisUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class ListaNegraDAO {
    
    public static boolean enListaNegra(Cliente cliente) {
        boolean resultado = false;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectOne("listaNegra.tieneRegistroActivo", cliente.getIdcliente());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<RegistroListaNegra> getTodos() {
        List<RegistroListaNegra> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("listaNegra.todos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<RegistroListaNegra> getActivos() {
        List<RegistroListaNegra> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("listaNegra.todosActivos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<RegistroListaNegra> getReciente(int cantidad, boolean soloActivos) {
        List<RegistroListaNegra> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            if (soloActivos) {
                resultado = session.selectList("listaNegra.recientesActivos", cantidad);
            } else {
                resultado = session.selectList("listaNegra.recientes", cantidad);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<RegistroListaNegra> getDeCliente(Cliente cliente, int cantidad, boolean soloActivos) {
        List<RegistroListaNegra> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idCliente", cliente.getIdcliente());
            params.put("cantidad", cantidad);
            if (soloActivos) {
                resultado = session.selectList("listaNegra.deClienteActivos", params);
            } else {
                resultado = session.selectList("listaNegra.deCliente", params);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
        
    public static boolean agregarAListaNegra(Cliente cliente, Empleado empleadoAgrega, String motivo) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idCliente", cliente.getIdcliente());
            params.put("idEmpleado", empleadoAgrega.getIdempleado());
            params.put("motivo", motivo);
            filasAfectadas = conn.insert("listaNegra.agregar", params);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static boolean retirarDeListaNegre(RegistroListaNegra registro, Empleado empleadoRetira, String motivo) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", registro.getIdRegistroListaNegra());
            params.put("idEmpleado", empleadoRetira.getIdempleado());
            params.put("motivo", motivo);
            filasAfectadas = conn.update("listaNegra.retirar", params);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
}
