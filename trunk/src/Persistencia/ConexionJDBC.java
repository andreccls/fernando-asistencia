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
    private static Connection conexion;
    private static String bd="asistencia";
    private static String user="root";
    private static String password="root";
    private static String host="localhost";
    private static String server="jdbc:mysql://"+host+"/"+bd;
    
    public ConexionJDBC() throws SQLException {
        
    }
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server, user, password);
            System.out.println("Conexión a base de datos " + server + " ... OK");
//    }catch(Exception e){}
        } catch (ClassNotFoundException ex) {
            System.out.println("Error cargando el Driver MySQL JDBC ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Imposible realizar conexion con " + server + " ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    
    public List getTareas() {
        //realizar consulta
        List items = new ArrayList();
        try {
            // Preparamos la consulta
            Connection con = getConexion();
            Statement s = con.createStatement();
            String sql = "select tar.nombre as nombre, tar.lugar as lugar, tarcla.aula as aula, tarcla.numero as numero from Tarea tar, Tareaclase tarcla where tar.id_tarea=tarcla.id_tarea and tar.estado=1";
            ResultSet rs = s.executeQuery(sql);
//            while(rs.next()){
//                Establecimiento est= new Establecimiento();
//                Tarea tar = new Tarea(est,rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),null,null,null,null,null);
//                //public Tarea(Establecimiento establecimiento, String nombre, String lugar, String comentario, Boolean estado, Set<Tareareunion> tareareunions, Set<Agenda> agendas, Set<Tareaotro> tareaotros, Set<Tareaextracurricular> tareaextracurriculars, Set<Tareaclase> tareaclases) {
//                items.add(tar);
//            }
            ResultSetMetaData md = rs.getMetaData();
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                HashMap row = new HashMap();
                
                for (int i = 1; i <= columns; i++) {
//                    row.values().add(rs.getObject(i));
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                items.add(row);
            }
//            items=extract(rs);
        } catch (SQLException ex) {
            System.out.println("Imposible realizar consulta ... FAIL");
        }
        return items;
    }
    
//    public static ArrayList<ArrayList<String>> extract(ResultSet resultSet) throws SQLException {  
//        ArrayList<ArrayList<String>> table;  
//        int columnCount = resultSet.getMetaData().getColumnCount();  
//          
//        if(resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
//            table = new ArrayList<ArrayList<String>>();  
//        }  
//        else {    
//            resultSet.last();  
//            table = new ArrayList<ArrayList<String>>(resultSet.getRow());  
//            resultSet.beforeFirst();  
//        }  
//    
//        for(ArrayList<String> row; resultSet.next(); table.add(row)) {  
//            row = new ArrayList<String>(columnCount);  
//      
//            for(int c = 1; c <= columnCount; ++ c) {
//                row.add(resultSet.getObject(c).toString());
//            }  
//        }  
//          
//        return table;  
//    }  
//    
    public void Desconectar(Connection con){
        //desconectar
        try {
            con.close();
            System.out.println("Cerrar conexion con " + server + " ... OK");
        } catch (SQLException ex) {
            System.out.println("Imposible cerrar conexion ... FAIL");
        }
    }
    
    
}
