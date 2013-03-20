/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Clases.Establecimiento;
import Clases.Tarea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author fer
 */


public class ConexionJDBC {
//    private static Connection conexion;
//    private static String bd="asistencia";
//    private static String user="root";
//    private static String password="root";
//    private static String host="localhost";
//    private static String server="jdbc:mysql://"+host+"/"+bd;
    private static String URL = "jdbc:mysql://localhost:3306/";
    public ConexionJDBC() throws SQLException {
        
    }
    
    public static Connection getConnection() {
        Connection conn = null;
        String url = URL;
        String dbName = "asistencia";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }
    
    
    public void Desconectar(Connection con){
        //desconectar
        try {
            con.close();
            System.out.println("Cerrar conexion con " + URL + " ... OK");
        } catch (SQLException ex) {
            System.out.println("Imposible cerrar conexion ... FAIL");
        }
    }
    
    
}
