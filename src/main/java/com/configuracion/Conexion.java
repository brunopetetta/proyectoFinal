package com.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection con;
    private static final String url="jdbc:mysql://localhost:3306/apunteca";
    private static final String user="root";
    private static final String pass="root";
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);                   
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de Conexión Base de Datos "+e);
        }
        return con;
    }
    
}
