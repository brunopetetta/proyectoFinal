package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RolDAO {
    PreparedStatement ps;
    ResultSet rs;
    
    public Rol listarId(int id) throws SQLException, Exception {
        Rol r = new Rol();
        String sql = "SELECT * FROM rol WHERE idRol = " + id;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                r.setId(rs.getInt(1));
                r.setDescripcion(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener un cliente", e);
        }
        return r;
    }
}
