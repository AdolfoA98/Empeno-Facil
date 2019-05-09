package mx.empenofacil.dao;

import mx.empenofacil.beans.Articulo;
import mx.empenofacil.mybatis.MyBatisUtils;
import java.io.IOException;
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
    
}
