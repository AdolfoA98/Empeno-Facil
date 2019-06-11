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
import mx.empenofacil.beans.Prenda;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author adolf
 */
public class PrendaDAO {

    public static boolean registrarPrenda(Prenda prenda) {
        boolean completado = true;

        try (SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("registrarPrenda", prenda);
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(PrendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            completado = false;
        }

        return completado;
    }

    public static List<Prenda> obtenerPrendasPorEmpeno(Integer empeno) {
        List<Prenda> resultado = null;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            resultado = conn.selectList("prenda.buscarPrendaPorEmpeno", empeno);
        } catch (IOException ex) {
            Logger.getLogger(PrendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public static boolean agregarPrenda(Prenda prenda) {
        boolean valido = false;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            int numero = conn.insert("prenda.agregarPrenda", prenda);
            conn.commit();
            if (numero != 0) {
                valido = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(PrendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }
}
