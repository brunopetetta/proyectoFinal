
package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Apunte;
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
                sql = "INSERT INTO detalle_compra(idApunte,idCompra,cantidadCopias,precioCompra,anillado,tipoImpresion,paginaDesde,paginaHasta,observaciones,subtotal)values(?,?,?,?,?,?,?,?,?,?)";
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
                ps.setDouble(10, detalle.getSubtotal());
                r = ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar procesar el pedido", e);
        }

        return r;
    }
    
    public int ActualizarDetalleCompra(Compra compra) throws Exception {
        String sql = "UPDATE compra SET idUsuario=?, idPago=?, fechaCompra=?, monto=?, estado=? WHERE idCompra=?";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setInt(1, compra.getUsuario().getId());
            ps.setInt(2, compra.getPago().getId());
            ps.setString(3, compra.getFecha());
            ps.setDouble(4, compra.getMonto());
            ps.setString(5, compra.getEstado());
            ps.setInt(6, compra.getId());
            r = ps.executeUpdate();
            
            sql = "DELETE FROM detalle_compra WHERE idCompra = ?";
            ps = ConsultasBD.preparedStatement(sql);
            ps.setInt(1, compra.getId());
            r = ps.executeUpdate();
            
            for (Carrito detalle : compra.getDetallecompras()) {
                sql = "INSERT INTO detalle_compra(idApunte,idCompra,cantidadCopias,precioCompra,anillado,tipoImpresion,paginaDesde,paginaHasta,observaciones,subtotal)values(?,?,?,?,?,?,?,?,?,?)";
                ps = ConsultasBD.preparedStatement(sql);
                ps.setInt(1, detalle.getIdApunte());
                ps.setInt(2, compra.getId());
                ps.setInt(3, detalle.getCantidadCopias());
                ps.setDouble(4, detalle.getPrecioCompra());
                ps.setString(5, detalle.getAnillado());
                ps.setString(6, detalle.getTipoImpresion());
                ps.setInt(7, detalle.getPaginaDesde());
                ps.setInt(8, detalle.getPaginaHasta());
                ps.setString(9, detalle.getObservaciones());
                ps.setDouble(10, detalle.getSubtotal());
                r = ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar actualizar el pedido", e);
        }

        return r;
    }
    
    public List listarDetallePedido(int idp) throws Exception {
        List lista = new ArrayList();
        String sql = "SELECT * FROM detalle_compra WHERE idCompra = " + idp;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Carrito car = new Carrito();
                Apunte apu = new Apunte();
                ApunteDAO apudao = new ApunteDAO();
                apu = apudao.listarId(rs.getInt(2));
                car.setNombre(apu.getNombre());
                car.setIdApunte(apu.getId());
                car.setCantPaginas(apu.getCantPaginas());
                car.setPrecioCompra(rs.getDouble(5));
                car.setDescripcion(apu.getDescripcion());
                car.setCantidadCopias(rs.getInt(4));
                car.setAnillado(rs.getString(6));
                car.setTipoImpresion(rs.getString(7));
                car.setPaginaDesde(rs.getInt(8));
                car.setPaginaHasta(rs.getInt(9));
                car.setObservaciones(rs.getString(10));
                car.setSubtotal(rs.getDouble(11));
                lista.add(car);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar detallar el pedido", e);
        }
        return lista;
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
    
    public Compra listarId(int id) throws Exception {
        Compra c = new Compra();
        String sql = "SELECT * FROM compra WHERE idCompra="+id;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                c.setId(rs.getInt(1));
                UsuarioDAO udao = new UsuarioDAO();
                PagoDAO pdao = new PagoDAO();
                Pago pago = pdao.listarId(rs.getInt(3));
                Usuario usu = udao.listarId(rs.getInt(2));
                c.setPago(pago);
                c.setUsuario(usu);
                c.setFecha(rs.getString(4));
                c.setMonto(rs.getDouble(5));
                c.setEstado(rs.getString(6));                
            }            
        }
        catch (SQLException e) {
            throw new Exception("Error al intentar obtener el pedido del usuario", e);
        }
        return c;
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
    
    public int ActualizarCompra(Compra c) throws Exception {
        String sql = "UPDATE compra SET idUsuario=?, idPago=?, fechaCompra=?, monto=?, estado=? WHERE idCompra=?";
        r = 0;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setInt(1, c.getUsuario().getId());
            ps.setInt(2, c.getPago().getId());
            ps.setString(3, c.getFecha());
            ps.setDouble(4, c.getMonto());
            ps.setString(5, c.getEstado());
            ps.setInt(6, c.getId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar actualizar el estado del pedido", e);
        }
        return r;
    }
}
