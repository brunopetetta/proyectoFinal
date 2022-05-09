
package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Carrito;
import com.modelo.Compra;
import com.modelo.Pago;
import com.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List listarPorUsuario(int idu) throws Exception {
        List lista = new ArrayList();
        String sql = "SELECT * FROM compra WHERE idUsuario = " + idu;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Compra com = new Compra();
                UsuarioDAO udao = new UsuarioDAO();
                PagoDAO pdao = new PagoDAO();
                Pago pago = pdao.listarId(rs.getInt(3));
                Usuario usu = udao.listarId(idu);
                com.setId(rs.getInt(1));
                com.setUsuario(usu);
                com.setPago(pago);
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                lista.add(com);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener los pedidos del usuario", e);
        }
        return lista;
    }
    
    public List listar() throws Exception {
        List lista = new ArrayList();
        String sql = "SELECT * FROM compra";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Compra com = new Compra();
                UsuarioDAO udao = new UsuarioDAO();
                PagoDAO pdao = new PagoDAO();
                Pago pago = pdao.listarId(rs.getInt(3));
                Usuario usu = udao.listarId(rs.getInt(2));
                com.setId(rs.getInt(1));
                com.setUsuario(usu);
                com.setPago(pago);
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                lista.add(com);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener los pedidos", e);
        }
        return lista;
    }
}
