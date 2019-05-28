/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.empenofacil.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import mx.empenofacil.beans.Foto;
import mx.empenofacil.beans.ItemCatalogo;
import mx.empenofacil.beans.Prenda;
import mx.empenofacil.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author adolf
 */
public class PrendaDAO {
    
    public static boolean registrarPrenda(Prenda prenda){
        boolean completado = true;
        
        try (SqlSession conn = MyBatisUtils.getSession()) {
            conn.insert("registrarPrenda", prenda);
            for(Image img: prenda.getFotos()){
                Random rand = new Random();
                Foto foto = new Foto(rand.nextInt(), prenda.getIdprenda(), img, "");
                conn.insert("Foto.agregarFoto", foto);
                conn.insert("Foto.relacionarFotoPrenda", foto);
            }
            conn.commit();
        } catch (IOException ex) {
            Logger.getLogger(PrendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            completado = false;
        }
        
        return completado;
    }
    
    public static List<ItemCatalogo> obtenerTiposPrenda(){
        List<ItemCatalogo> tipos = new ArrayList<>();
        try (SqlSession conn = MyBatisUtils.getSession()){
            tipos = conn.selectList("prenda.getTiposPrenda");
        } catch (Exception ex){
            Logger.getLogger(PrendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipos;
    }
    
}
