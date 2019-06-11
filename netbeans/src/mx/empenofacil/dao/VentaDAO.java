/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Consts;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Sucursal;
import mx.empenofacil.beans.TransaccionCaja;
import mx.empenofacil.beans.Venta;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import java.time.LocalDate;

/**
 *
 * @author Razer
 */
public class VentaDAO {
    
    public static void vender(Cliente cliente, Empleado empleado, List<Articulo> articulos) {
        BigDecimal total = BigDecimal.ZERO;
        for (Articulo articulo : articulos) {
            total = total.add(articulo.getPrecio().subtract(articulo.getDescuento()));
        }
        
        Sucursal sucursal = new Sucursal();
        sucursal.setIdsucursal(empleado.getSucursal());
        
        // El sistema genera y guarda la transacción en la caja
        TransaccionCaja transaccionCaja = TransaccionCajaDAO.registrarMovimiento(
                sucursal,
                total,
                "Venta " + articulos.size() + " articulos"
        );
        
        // El sistema registra la venta en la base de datos.
        
        Integer idventa = registrar(empleado.getIdempleado(), cliente.getIdcliente(), transaccionCaja.getIdTransaccionCaja());
        registrarArticulosVenta(idventa, articulos);
        
        // El sistema actualiza los registros de los artículos vendidos.

        ArticuloDAO.cambiarEstado(articulos, Consts.ID_STATUS_ARTICULO_VENDIDO);
        
    }
    
    private static int registrar(Integer idempleado, Integer idcliente, Integer idtransaccioncaja) {
        Integer idGenerado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idempleado", idempleado);
            params.put("idcliente", idcliente);
            params.put("idtransaccioncaja", idtransaccioncaja);
            params.put("estatusVenta", Consts.ID_STATUS_VENTA_COMPLETADA);
            session.insert("venta.registrar", params);
            session.commit();
            idGenerado = ((Long) params.get("idventa")).intValue();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return idGenerado;
    }
    
    private static boolean registrarArticulosVenta(Integer idventa, List<Articulo> articulos) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idventa", idventa);
            params.put("articulos", articulos);
            filasAfectadas = conn.insert("venta.registrarArticulos", params);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static boolean cancelar(Venta venta) {
        TransaccionCajaDAO.cancelarMovimiento(
                venta.getTransaccioncaja(),
                "Venta cancelada"
        );
        
        ArticuloDAO.cambiarEstado(venta.getArticulos(), Consts.ID_STATUS_ARTICULO_DISPONIBLE);
        
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idventa", venta.getIdventa());
            params.put("estado", Consts.ID_STATUS_VENTA_CANCELADA);
            filasAfectadas = conn.insert("venta.cambiarEstado", params);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static List<ItemCatalogo> getEstadosVentas() {
        List<ItemCatalogo> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("itemCatalogo.estadosventas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static List<Venta> buscar(Empleado empleado, Cliente cliente,
            LocalDate desde, LocalDate hasta, String articulo, ItemCatalogo estado, Integer limit) {
        List<Venta> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("empleado", ((empleado != null)? empleado.getNombreCompleto() : ""));
            params.put("cliente", ((cliente != null)? cliente.getNombreCompleto() : ""));
            params.put("desde", ((desde != null)? desde : LocalDate.now().minusYears(1000)));
            params.put("hasta", ((hasta != null)? hasta : LocalDate.now().plusYears(1)));
            params.put("articulo", ((articulo != null)? articulo : ""));
            params.put("estado", ((estado != null)? estado.getNombre() : ""));
            params.put("limit", limit);
            resultado = session.selectList("venta.buscar", params);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
