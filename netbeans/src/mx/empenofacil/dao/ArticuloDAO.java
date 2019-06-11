package mx.empenofacil.dao;

import mx.empenofacil.beans.Articulo;
import mx.empenofacil.mybatis.MyBatisUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.empenofacil.beans.ArticuloEnApartado;
import org.apache.ibatis.session.SqlSession;

public class ArticuloDAO {
    
    public static boolean comercializar(Articulo articulo) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            filasAfectadas = conn.insert("articulo.comercializar", articulo);
            conn.commit();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
     public static List<Articulo> buscar(String busqueda) {
        List<Articulo> resultado = null;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            resultado = conn.selectList("articulo.buscar", busqueda);
        }  catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static boolean guardarCambios(Articulo articulo) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            filasAfectadas = conn.update("articulo.modificar", articulo);
            conn.commit();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static boolean cambiarEstado(List<Articulo> articulos, Integer estado) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            for (Articulo articulo : articulos) {
                 Map<String, Object> params = new HashMap<>();
                params.put("idarticulo", articulo.getIdarticulo());
                params.put("estado", estado);
                filasAfectadas += conn.update("articulo.cambiarEstado", params);
            }
            conn.commit();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static boolean marcarApartados(List<Articulo> articulos) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            for (Articulo articulo : articulos) {
                filasAfectadas += conn.update("articulo.marcarApartado", articulo);
            }
            conn.commit();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
    public static List<Articulo> obtenerArticulosPorApartado(Integer idApartado){
        List<ArticuloEnApartado> articuloEnApartado = new ArrayList<>();
        List<Articulo> articulos = new ArrayList<>();
        try(SqlSession session = MyBatisUtils.getSession()){
            articuloEnApartado = session.selectList("articulo.obtenerArticulosApartado", idApartado);
            for (int i = 0; i < articuloEnApartado.size(); i++) {
                articulos.add(session.selectOne("articulo.obtenerArticuloPorId", articuloEnApartado.get(i).getArticulo()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articulos;
    }

    static boolean marcarDisponibles(List<Articulo> articulos) {
        int filasAfectadas = 0;
        try (SqlSession conn = MyBatisUtils.getSession()) {
            for (Articulo articulo : articulos) {
                filasAfectadas += conn.update("articulo.marcarDisponible", articulo);
            }
            conn.commit();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return filasAfectadas > 0;
    }
    
}
