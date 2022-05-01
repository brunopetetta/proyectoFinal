package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.PrecioFotocopia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrecioFotocopiaDAO {
    PreparedStatement ps;
    ResultSet rs;
    
    public PrecioFotocopia listarMax() throws SQLException, Exception {
        PrecioFotocopia p = new PrecioFotocopia();
        String sql = "SELECT * FROM precio_fotocopia WHERE idPrecio = (SELECT MAX(idPrecio) FROM precio_fotocopia)";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                p.setIdPrecio(rs.getInt(1));
                p.setFechaDesdePrecio(rs.getString(2));
                p.setValorFotocopia(rs.getDouble(3));
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener el precio de fotocopia", e);
        }
        return p;
    }
}
