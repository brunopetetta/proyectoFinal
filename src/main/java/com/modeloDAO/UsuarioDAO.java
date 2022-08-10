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
                Rol r = rdao.listarId(rs.getInt(8));
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setLegajo(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellido(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRol(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener un usuario", e);
        }
        return u;
    }
    public Usuario listarEmail(String email) throws SQLException, Exception {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, email);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(8));
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setLegajo(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellido(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRol(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar obtener un usuario", e);
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
                Rol r = rdao.listarId(rs.getInt(8));
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setLegajo(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellido(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRol(r);
                lista.add(u);
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar obtener los usuarios", e);
        }
        return lista;
    }
    
    public List listarAlumnos() throws SQLException, Exception {
        List lista = new ArrayList();
        String sql = "SELECT * FROM usuario WHERE idRol=2";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Usuario u = new Usuario();
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(8));
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setLegajo(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellido(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRol(r);
                lista.add(u);
            }
        } catch (Exception e) {
            throw new Exception("Error al intentar obtener los alumnos", e);
        }
        return lista;
    }
    
    public Usuario validar(String user, String password) throws SQLException, Exception {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE (email=? OR legajo=? OR dni=?) AND password=?";
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, user);
            ps.setString(2, user);
            ps.setString(3, user);
            ps.setString(4, password);
            rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                RolDAO rdao = new RolDAO();
                Rol r = rdao.listarId(rs.getInt(8));
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setLegajo(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setApellido(rs.getString(5));
                u.setEmail(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRol(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al intentar validar las credenciales", e);
        }
        return u;
    }
    
    public int AgregarNuevoUsuario(Usuario u) throws SQLException, Exception {
        String sql = "INSERT INTO usuario(dni,legajo,nombre,apellido,email,password,idRol)values(?,?,?,?,?,?,?)";
        int r = 0;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getLegajo());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getApellido());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getRol().getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al intentar registrarse", e);
        }
        return r;
    }
    
    public int ActualizarUsuario(Usuario u) throws SQLException, Exception {
        String sql = "UPDATE usuario SET dni=?, legajo=?, nombre=?, apellido=?, email=?, password=?, idRol=? WHERE idUsuario=?";
        int r = 0;
        try {
            ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getLegajo());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getApellido());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getRol().getId());
            ps.setInt(8, u.getId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar actualizar los datos del usuario", e);
        }
        return r;
    }
    
}
