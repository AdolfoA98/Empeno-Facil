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
public class BolsaDAO {
    
    public static Double getMontoBolsa(){
        Double resultado = null;
        
        try (SqlSession conn = MyBatisUtils.getSession()) {
            resultado = conn.selectOne("getMontoBolsa");
        } catch (Exception ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    public static boolean registrarMonto(HashMap<String, Object> parametros){
        boolean completado = true;
        
        try (SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("setMontoBolsa", parametros);
            conn.commit();
        } catch (Exception ex) {
            Logger.getLogger(BolsaDAO.class.getName()).log(Level.SEVERE, null, ex);
            completado = false;
        }
        
        return completado;
    }
    
}
