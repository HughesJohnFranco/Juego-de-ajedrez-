package ConexionBD;

import java.sql.*;

public class CConexion {

    private static final String usuario = "root";
    private static final String contrasenia = "E3tttx869&";
    private static final String bd = "ajedrez";
    private static final String ip = "localhost";
    private static final String puerto = "3306";
    private static final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        Connection conectar = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            System.out.println("Conectado con la Base de Datos");
        } catch (Exception e) {
            System.err.println("Error al conectar con la Base de Datos: " + e.getMessage());
        }
        return conectar;
    }

    public void cerrarConexion(Connection conectar) {
        if (conectar != null) {
            try {
                conectar.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}