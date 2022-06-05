package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Apunte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ApunteDAO {
    
    public List listar() {
        List lista = new ArrayList();
        String sql = "SELECT * FROM apunte WHERE upload = 0";
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Apunte a = new Apunte();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setDescripcion(rs.getString(3));
                a.setCarrera(rs.getString(4));
                a.setMateria(rs.getString(5));
                a.setCantPaginas(rs.getInt(6));
                a.setUpload(rs.getBoolean(7));
                a.setIdAlumno(rs.getInt(8));
                lista.add(a);
            }
        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    public List listarAlumno(int id) {
        List lista = new ArrayList();
        String sql = "SELECT * FROM apunte WHERE idAlumno="+id;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Apunte a = new Apunte();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setDescripcion(rs.getString(3));
                a.setCarrera(rs.getString(4));
                a.setMateria(rs.getString(5));
                a.setCantPaginas(rs.getInt(6));
                a.setUpload(rs.getBoolean(7));
                a.setIdAlumno(id);
                lista.add(a);
            }
        } catch (SQLException e) {
            
        }
        return lista;
    }
    
    public Apunte listarId(int id) throws SQLException, Exception {
        Apunte a = new Apunte();
        String sql = "SELECT * FROM apunte WHERE idApunte="+id;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setDescripcion(rs.getString(3));
                a.setCarrera(rs.getString(4));
                a.setMateria(rs.getString(5));
                a.setCantPaginas(rs.getInt(6));
                a.setUpload(rs.getBoolean(7));
                a.setIdAlumno(rs.getInt(8));
            }
        } catch (SQLException e) {
            
        }
        return a;
    }
    
    public int AgregarNuevoApunte(Apunte a) throws SQLException, Exception {
        String sql = "INSERT INTO apunte(nombre,descripcion,carrera,materia,cantPaginas,upload,idAlumno)values(?,?,?,?,?,?,?)";
        int rs = 0;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getDescripcion());
            ps.setString(3, a.getCarrera());
            ps.setString(4, a.getMateria());
            ps.setInt(5, a.getCantPaginas());
            ps.setBoolean(6, a.getUpload());
            ps.setInt(7, a.getIdAlumno());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar agregar un nuevo producto", e);
        }
        return rs;
    }
    
    public int ActualizarApunte(Apunte a) throws SQLException, Exception {
        String sql = "UPDATE apunte SET nombre=?, descripcion=?, carrera=?, materia=?, cantPaginas=?, upload=?, idAlumno=? WHERE idApunte=?";
        int rs = 0;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getDescripcion());
            ps.setString(3, a.getCarrera());
            ps.setString(4, a.getMateria());
            ps.setInt(5, a.getCantPaginas());
            ps.setBoolean(6, a.getUpload());
            ps.setInt(7, a.getIdAlumno());
            ps.setInt(8, a.getId());
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar actualizar un apunte", e);
        }
        return rs;
    }

    public int BorrarApunte(int id) throws Exception {
        String sql = "DELETE FROM apunte WHERE idApunte=" + id;
        int rs = 0;
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            rs = ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al intentar borrar un apunte", e);
        }
        return rs;
    }
    
}
