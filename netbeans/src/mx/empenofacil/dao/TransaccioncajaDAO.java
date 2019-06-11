/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.beans.Sucursal;
import mx.empenofacil.beans.TransaccionCaja;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Razer
 */
public class TransaccionCajaDAO {
    
    public static TransaccionCaja registrarMovimiento(Sucursal sucursal, BigDecimal monto, String descripcion) {
        TransaccionCaja resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idsucursal", sucursal.getIdsucursal());
            params.put("monto", monto);
            params.put("descripcion", descripcion);
            session.insert("transaccion.registrar", params);
            session.commit();
            Integer id = ((Long) params.get("idtransaccioncaja")).intValue();
            resultado = session.selectOne("transaccion.buscarPorId", id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static TransaccionCaja registrarMovimientoApartado(Sucursal sucursal, Double monto, String descripcion) {
        TransaccionCaja resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idsucursal", sucursal.getIdsucursal());
            params.put("monto", monto);
            params.put("descripcion", descripcion);
            session.insert("transaccion.registrar", params);
            session.commit();
            Integer id = ((Long) params.get("idtransaccioncaja")).intValue();
            resultado = session.selectOne("transaccion.buscarPorId", id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static TransaccionCaja obtenerMovimientoApartado(Integer transaccion){
        TransaccionCaja caja = new TransaccionCaja();
        try(SqlSession session = MyBatisUtils.getSession()){
            caja = session.selectOne("transaccion.obtenerTransaccionApartado", transaccion);
        } catch (IOException ex) {
            Logger.getLogger(TransaccionCajaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }
    
    public static TransaccionCaja cancelarMovimiento(TransaccionCaja transaccionCaja, String descripcion) {
        TransaccionCaja resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("idsucursal", transaccionCaja.getSucursal().getIdsucursal());
            params.put("monto", transaccionCaja.getMonto().negate());
            params.put("descripcion", descripcion);
            session.insert("transaccion.registrar", params);
            session.commit();
            
            Integer id = ((Long) params.get("idtransaccioncaja")).intValue();
            params.put("idtransaccioncancelada", transaccionCaja.getIdTransaccionCaja());
            params.put("idtransacciongenerada", id);
            session.update("transaccion.cancelacion",params);
            session.commit();
            
            resultado = session.selectOne("transaccion.buscarPorId", id);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
