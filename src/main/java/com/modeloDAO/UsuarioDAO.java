package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Rol;
import com.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    PreparedStatement ps;
    ResultSet rs;
    
    public Usuario listarId(int id) throws SQLException, Exception {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE idUsuario = " + id;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(7));
                u.setId(rs.getInt(1));
                u.setLegajo(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setRol(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener un cliente", e);
        }
        return u;
    }
    
    public List listar() throws SQLException, Exception {
        List lista = new ArrayList();
        String sql = "SELECT * FROM usuario";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Usuario u = new Usuario();
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(7));
                u.setId(rs.getInt(1));
                u.setLegajo(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setRol(r);
                lista.add(u);
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar obtener los clientes", e);
        }
        return lista;
    }
    
    public Usuario validar(String user, String password) throws SQLException, Exception {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE (email=? OR legajo=?) AND password=?";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, user);
            ps.setString(2, user);
            ps.setString(3, password);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(7));
                u.setId(rs.getInt(1));
                u.setLegajo(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setRol(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar validar las credenciales", e);
        }
        return u;
    }
    
    public int AgregarNuevoUsuario(Usuario u) throws SQLException, Exception {
        String sql = "INSERT INTO usuario(legajo,nombre,apellido,email,password,idRol)values(?,?,?,?,?,?)";
        int r = 0;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, u.getLegajo());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            ps.setInt(6, u.getRol().getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al intentar registrarse", e);
        }
        return r;
    }
    
    public int ActualizarUsuario(Usuario u) throws SQLException, Exception {
        String sql = "UPDATE usuario SET legajo=?, nombre=?, apellido=?, email=?, password=?, idRol=? WHERE idUsuario=?";
        int r = 0;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, u.getLegajo());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            ps.setInt(6, u.getRol().getId());
            ps.setInt(7, u.getId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar actualizar los datos del usuario", e);
        }
        return r;
    }
    
}
