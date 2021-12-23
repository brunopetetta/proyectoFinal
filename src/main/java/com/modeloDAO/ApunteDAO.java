/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modeloDAO;

import com.configuracion.ConsultasBD;
import com.modelo.Apunte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ApunteDAO {
    
    public List listar() {
        List lista = new ArrayList();
        String sql = "SELECT * FROM apunte";
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
                lista.add(a);
            }
        } catch (SQLException e) {
            
        }
        return lista;
    }
}
