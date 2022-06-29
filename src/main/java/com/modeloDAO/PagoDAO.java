package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Pago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoDAO {
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    int idp = 0;

    public int GenerarPago(Pago pago) throws Exception {
        try {
            String sql = "INSERT INTO pago(monto)values(?)";
            ps = ConsultasBD.preparedStatement(sql);
            ps.setDouble(1, pago.getMonto());
            r = ps.executeUpdate();
            sql = "SELECT @@IDENTITY idPago";
            rs = ps.executeQuery(sql);
            rs.next();
            idp = rs.getInt("idPago");
            rs.close();
        } catch (SQLException ex) {
            throw new Exception("Error al intentar generar el pago", ex);
        }
        return idp;
    }
    
    public Pago listarId(int id) throws SQLException, Exception {
        Pago p = new Pago();
        String sql = "SELECT * FROM pago WHERE idPago = " + id;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setMonto(rs.getDouble(2));
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener un cliente", e);
        }
        return p;
    }
    public int ActualizarPago(Pago p) throws SQLException, Exception {
        String sql = "UPDATE pago SET monto=? WHERE idPago=?";
        int rs = 0;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ps.setDouble(1, p.getMonto());
            ps.setInt(2, p.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar actualizar un pago", e);
        }
        return rs;
    }
}
