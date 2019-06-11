/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.beans.Apartado;
import mx.empenofacil.beans.Articulo;
import mx.empenofacil.beans.Cliente;
import mx.empenofacil.beans.Consts;
import mx.empenofacil.beans.Empleado;
import mx.empenofacil.beans.PagoApartado;
import mx.empenofacil.beans.Sucursal;
import mx.empenofacil.beans.TransaccionCaja;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Rainbow Dash
 */
public class ApartadoDAO {
    public static void apartar(Cliente cliente, Empleado empleado, List<Articulo> articulos, Double apartado) {
        double total = apartado;
        
        Sucursal sucursal = new Sucursal();
        sucursal.setIdsucursal(empleado.getSucursal());
        
        // El sistema genera y guarda la transacción en la caja
        TransaccionCaja transaccionCaja = TransaccionCajaDAO.registrarMovimientoApartado(
                sucursal,
                total,
                "Apartar " + articulos.size() + " articulos"
        );
        
        Integer idtransaccion = transaccionCaja.getIdTransaccionCaja();
        
        // El sistema registra la venta en la base de datos.
        
        Integer idapartado = registrar(empleado.getIdempleado(), cliente.getIdcliente());
        registrarArticulosApartado(idapartado, articulos);
        registrarPago(idapartado, idtransaccion);
        
        // El sistema actualiza los registros de los artículos vendidos.

        ArticuloDAO.marcarApartados(articulos);
        
    }
    
    private static boolean registrarPago(Integer idapartado, Integer idtransaccion){
        int filasAfectadas = 0;
        try(SqlSession conn = MyBatisUtils.getSession()){
            Map<String, Object> params = new HashMap<>();
            params.put("apartado", idapartado);
            params.put("transaccioncaja", idtransaccion);
            filasAfectadas = conn.insert("apartado.registrarPagoApartado", params);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas > 0;
    }
    
    private static int registrar(Integer idempleado, Integer idcliente) {
        Integer idGenerado = null;
        Apartado apartado = new Apartado();
        try (SqlSession session = MyBatisUtils.getSession()) {
            apartado.setEstatusApartado(Consts.ID_STATUS_APARTADO_VIGENTE);
            apartado.setCliente(idcliente);
            apartado.setEmpleado(idempleado);
            session.insert("apartado.registrarApartado", apartado);
            session.commit();
            apartado = session.selectOne("apartado.obtenerID");
            idGenerado = apartado.getIdapartado();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return idGenerado;
    }
    
    private static boolean registrarArticulosApartado(Integer idapartado, List<Articulo> articulos) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idapartado", idapartado);
            params.put("articulos", articulos);
            filasAfectadas = conn.insert("apartado.registrarArticulos", params);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static List<Apartado> obtenerApartadosPorEstatus(Integer estatusApartado){
        List<Apartado> apartados = new ArrayList<>();
        try(SqlSession session = MyBatisUtils.getSession()){
            apartados = session.selectList("apartado.obtenerApartadosPorEstatus", estatusApartado);
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apartados;
    }
    
    public static Double obtenerMontoApartado(Integer apartado){
        Double montoApartado = 0.0;
        TransaccionCaja caja;
        PagoApartado pago;
        try(SqlSession session = MyBatisUtils.getSession()){
            pago = session.selectOne("apartado.obtenerPagoApartado", apartado);
            caja = session.selectOne("transaccion.obtenerTransaccionApartado", pago.getTransaccioncaja());
            montoApartado = caja.getMonto().doubleValue();
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return montoApartado;
    }

    public static List<Apartado> obtenerApartadosPorCliente(Integer idcliente) {
        List<Apartado> apartados = null;
        try(SqlSession session = MyBatisUtils.getSession()){
            apartados = session.selectList("apartado.obtenerApartadosPorCliente", idcliente);
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apartados;
    }

    public static Apartado obtenerApartadoPorId(Integer id) {
        Apartado apartado = null;
        try(SqlSession session = MyBatisUtils.getSession()){
            apartado = session.selectOne("apartado.obtenerApartadoPorId", id);
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apartado;
    }
    
    public static void cancelarApartado(Integer idapartado){
        cambiarEstatusApartadoCancelar(idapartado);
        List<Articulo> articulos = ArticuloDAO.obtenerArticulosPorApartado(idapartado);
        ArticuloDAO.marcarDisponibles(articulos);
    }
    
    public static void liquidarApartado(Empleado empleado, Integer idapartado, Double apartado){
        Sucursal sucursal = new Sucursal();
        sucursal.setIdsucursal(empleado.getSucursal());
        
        TransaccionCaja transaccionCaja = TransaccionCajaDAO.registrarMovimientoApartado(
                sucursal,
                apartado,
                "Se liquida el apartado " + idapartado
        );
        
        Integer idtransaccion = transaccionCaja.getIdTransaccionCaja();
        
        registrarPago(idapartado, idtransaccion);
        cambiarEstatusApartadoLiquidar(idapartado);
        List<Articulo> articulos = ArticuloDAO.obtenerArticulosPorApartado(idapartado);
        ArticuloDAO.cambiarEstado(articulos, Consts.ID_STATUS_ARTICULO_VENDIDO);
    }
    
    private static void cambiarEstatusApartadoLiquidar(Integer idapartado){
        try(SqlSession session = MyBatisUtils.getSession()){
            session.update("apartado.liquidarEstatusApartado", idapartado);
            session.commit();
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void cambiarEstatusApartadoCancelar(Integer idapartado){
        try(SqlSession session = MyBatisUtils.getSession()){
            session.update("apartado.cancelarEstatusApartado", idapartado);
            session.commit();
        } catch (IOException ex) {
            Logger.getLogger(ApartadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
