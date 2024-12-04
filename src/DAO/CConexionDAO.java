package DAO;

import java.util.ArrayList;
import ConexionBD.CConexion;
import java.sql.*;
import piezas.Pieza;
import javax.swing.*;

public class CConexionDAO {

    private CConexion conexion;

    public CConexionDAO() {
        conexion = new CConexion();
    }

    public void guardarPiezas(ArrayList<Pieza> listaDePiezas) {  
        String sql = "INSERT INTO piezastablero (tipo, columna, fila, color) VALUES (?, ?, ?, ?)";  
        try (Connection conn = conexion.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            for (Pieza pieza : listaDePiezas) {  
                pstmt.setString(1, pieza.getClass().getSimpleName());  
                pstmt.setInt(2, pieza.col);  
                pstmt.setInt(3, pieza.fila);  
                pstmt.setBoolean(4, pieza.esBlanco);  
                pstmt.addBatch(); 
            }  
            pstmt.executeBatch();   
            JOptionPane.showMessageDialog(null, "Piezas enviadas a la Base de Datos.");  
        } catch (Exception e) {  
            JOptionPane.showMessageDialog(null, "Error al guardar las piezas: " + e.toString());  
        }  
    }

    public void enviarDatos(String pieza, int colOrigen, int filaOrigen, int colDestino, int filaDestino, boolean esBlanco) {
        String sql = "INSERT INTO movimientos (pieza, col_inicial, fila_inicial, col_final, fila_final, es_blanco) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pieza);
            pstmt.setInt(2, colOrigen);
            pstmt.setInt(3, filaOrigen);
            pstmt.setInt(4, colDestino);
            pstmt.setInt(5, filaDestino);
            pstmt.setBoolean(6, esBlanco);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Movimiento enviado a BD.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar el movimiento: NO MOVISTE NADA" + e.toString());
        }
    }

    public ArrayList<String[]> obtenerPiezas() {  
        ArrayList<String[]> piezasList = new ArrayList<>();  
        String sql = "SELECT tipo, columna, fila, color FROM piezastablero";  
        try (Connection conn = conexion.establecerConexion(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {  
            while (rs.next()) {  
                String[] pieza = {  
                    rs.getString("tipo"),  
                    String.valueOf(rs.getInt("columna")),  
                    String.valueOf(rs.getInt("fila")),  
                    rs.getBoolean("color") ? "Blanco" : "Negro"  
                };  
                piezasList.add(pieza);  
            }  
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null, "Error al obtener las piezas: " + e.getMessage());  
        }  
        return piezasList;  
    }

    public ArrayList<String[]> obtenerMovimientos() {
        ArrayList<String[]> movimientosList = new ArrayList<>();
        String sql = "SELECT pieza, col_inicial, fila_inicial, col_final, fila_final, es_blanco, fecha_hora FROM movimientos";
        
        try (Connection conn = conexion.establecerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String[] movimiento = {
                    rs.getString("pieza"),
                    String.valueOf(rs.getInt("col_inicial")),
                    String.valueOf(rs.getInt("fila_inicial")),
                    String.valueOf(rs.getInt("col_final")),
                    String.valueOf(rs.getInt("fila_final")),
                    rs.getBoolean("es_blanco") ? "Blanco" : "Negro",
                    rs.getString("fecha_hora")
                };
                movimientosList.add(movimiento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los movimientos: " + e.getMessage());
        }
        return movimientosList;
    }

    public void borrarPiezas() {  
        String sql = "TRUNCATE TABLE piezastablero";  
        try (Connection conn = conexion.establecerConexion(); 
             Statement stmt = conn.createStatement()) {  
            stmt.executeUpdate(sql);  
            JOptionPane.showMessageDialog(null, "Se eliminaron los registros");  
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null, "Error al borrar las piezas: " + e.getMessage());  
        }  
    }

    public void borrarMovimientos() {  
        String sql = "TRUNCATE TABLE movimientos";  
        try (Connection conn = conexion.establecerConexion(); 
             Statement stmt = conn.createStatement()) {  
            stmt.executeUpdate(sql);  
            JOptionPane.showMessageDialog(null, "Se eliminaron los registros");  
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null, "Error al borrar los movimientos: " + e.getMessage());  
        }  
    } 
}