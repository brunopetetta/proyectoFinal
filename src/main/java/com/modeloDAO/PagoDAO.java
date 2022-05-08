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
}
