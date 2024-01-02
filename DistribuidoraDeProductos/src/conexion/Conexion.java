package conexion;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    //Acontinuacion vamos a hacer una conexion con la base de datos creada anteriormente de manera manual
    //El primer paso para esto es descargar el conector que MySQl que es un dirver que contien unas herramientas
    //Que nos permiten conectarnos con la base de datos esto lo hacemos con la sentencia clas.forName que nos 
    //Permite instanciar cualquier libreria externa 
 public static void main(String[] args) {
    String usuario = "root";
    String pasword = "admin";
    String url = "jdbc:mysql://localhost:3306/Distribuidora";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }

    try (Connection conexion = DriverManager.getConnection(url, usuario, pasword);
         Statement statement = conexion.createStatement()) {

        // Operación de actualización
        statement.executeUpdate("INSERT INTO cliente(telefono, `Nombre del Cliente`) VALUES (2321133, 'Sara Lozano')");

    } catch (SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }

    try (Connection conexion = DriverManager.getConnection(url, usuario, pasword);
         Statement selectStatement = conexion.createStatement()) {

        // Consulta
        try (ResultSet rs = selectStatement.executeQuery("SELECT * FROM cliente")) {
            while (rs.next()) {
                System.out.println("telefono: " + rs.getInt("telefono") + ", Nombre del Cliente: " + rs.getString("Nombre del Cliente"));
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}