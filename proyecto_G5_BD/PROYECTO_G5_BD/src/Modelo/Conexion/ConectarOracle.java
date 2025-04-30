package Modelo.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarOracle {
    
    // Declaraciones
 private static ConectarOracle instanceConexion;
    private static String DRIVER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@MAUVYC:1521:XE"; 
    private static String USER = "GRUPO5";
    private static String PASSWORD = "GRUPO5";
    private Connection cadena;
    private String value;
    
    // Constructor privado para evitar instancia mediante el operador new
    private ConectarOracle(){
        this.cadena = null;
    }
    
    // Método para obtener la instancia mediante un único método
    // La palabra reservada "synchronized" asegura acceso seguro desde hilos múltiples
    public static synchronized ConectarOracle getInstance(){
        if(instanceConexion == null){
            instanceConexion = new ConectarOracle();
        }
        return instanceConexion;
    }

    // Método conectar
    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion Exitosa"); // Mensaje de conexión exitosa
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Erronea " + e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }

    // Método desconectar
    public void desconectar() {
        try {
            if (this.cadena != null) {
                this.cadena.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Método getConnection estático para obtener la conexión
    public static Connection getConnection(){
        Connection cadena = null;
        try {
            Class.forName(DRIVER);
            cadena = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion Exitosa"); // Mensaje de conexión exitosa
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Erronea " + e.getMessage());
        }
        return cadena;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }    
}
