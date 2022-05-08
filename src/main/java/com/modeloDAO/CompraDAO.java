
package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Carrito;
import com.modelo.Compra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompraDAO {
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    public int GenerarCompra(Compra compra) throws Exception {
        int idcompra;
        String sql = "INSERT INTO compra(idUsuario,idPago,fechaCompra,monto,estado)values(?,?,?,?,?)";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setInt(1, compra.getUsuario().getId());
            ps.setInt(2, compra.getPago().getId());
            ps.setString(3, compra.getFecha());
            ps.setDouble(4, compra.getMonto());
            ps.setString(5, compra.getEstado());
            r = ps.executeUpdate();

            sql = "SELECT @@IDENTITY AS idCompra";
            rs = ps.executeQuery(sql);
            rs.next();
            idcompra = rs.getInt("idCompra");
            rs.close();

            for (Carrito detalle : compra.getDetallecompras()) {
                sql = "INSERT INTO detalle_compra(idApunte,idCompra,cantidadCopias,precioCompra,anillado,tipoImpresion,paginaDesde,paginaHasta,observaciones)values(?,?,?,?,?,?,?,?,?)";
                ps = ConsultasBD.preparedStatement(sql);
                ps.setInt(1, detalle.getIdApunte());
                ps.setInt(2, idcompra);
                ps.setInt(3, detalle.getCantidadCopias());
                ps.setDouble(4, detalle.getPrecioCompra());
                ps.setString(5, detalle.getAnillado());
                ps.setString(6, detalle.getTipoImpresion());
                ps.setInt(7, detalle.getPaginaDesde());
                ps.setInt(8, detalle.getPaginaHasta());
                ps.setString(9, detalle.getObservaciones());
                r = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar procesar la compra", e);
        }

        return r;
    }
}
