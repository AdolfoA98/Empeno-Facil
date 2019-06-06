/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author adolf
 */
public class TransaccioncajaDAO {
    
    public static Integer obtenerUltimoId() {
        Integer id = 0;
        
        try (SqlSession conn = MyBatisUtils.getSession()) {
            id = conn.selectOne("getUltimoId");
        } catch (Exception ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public static boolean registrarTransaccion(HashMap<String, Object> parametros){
        boolean completado = true;
        
        try (SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("registrarTransaccion", parametros);
            conn.commit();
        } catch (Exception ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
            completado = false;
        }
        
        return completado;
    }
    
}
