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
    
    public static List<Empeno> getVencidosNoComercializados() {
        List<Empeno> resultado = null;
        try (SqlSession session = MyBatisUtils.getSession()) {
            resultado = session.selectList("empeno.vencidosNoComercializados");
        } catch (IOException ex) {
            Logger.getLogger(EmpenoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
