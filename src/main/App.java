package main;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import DAO.CConexionDAO;

public class App {

    public static void main(String[] args) throws Exception {


        CConexionDAO objetoDAO = new CConexionDAO(); 

        JFrame frame = new JFrame();
        frame.setTitle("TP8 AjedreSQL DAO");
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(780, 600));
        frame.setLocationRelativeTo(null);

        ImageIcon icono = new ImageIcon(App.class.getResource("/res/PanelIcon.jpg")); 
        frame.setIconImage(icono.getImage());

        Tablero tablero = new Tablero();

        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.Y_AXIS));
        panelEntrada.setPreferredSize(new Dimension(200, 600));

        JTextField movimientoField = new JTextField();
        movimientoField.setEditable(false);
        panelEntrada.add(new JLabel("Moviste:"));
        panelEntrada.add(movimientoField);

        JTextField colorField = new JTextField();
        colorField.setEditable(false);
        panelEntrada.add(new JLabel("Color:"));
        panelEntrada.add(colorField);

        JTextField posicionInicialField = new JTextField();
        posicionInicialField.setEditable(false);
        panelEntrada.add(new JLabel("Pos Inicial:"));
        panelEntrada.add(posicionInicialField);

        JTextField posicionFinalField = new JTextField();
        posicionFinalField.setEditable(false);
        panelEntrada.add(new JLabel("Pos Final:"));
        panelEntrada.add(posicionFinalField);

        JButton enviarBtn = new JButton("Guardar Movimiento en BD");
        panelEntrada.add(enviarBtn);
        confBotonEnviar(enviarBtn, movimientoField, colorField, posicionInicialField, posicionFinalField, objetoDAO);

        JButton mostrarTablaBtn = new JButton("Mostrar las piezas  juego");
        mostrarTablaBtn.addActionListener(e -> ventanaDePiezas(objetoDAO));
        panelEntrada.add(mostrarTablaBtn);

        JButton mostrarMovimientosBtn = new JButton("Mostrar los movimientos");
        mostrarMovimientosBtn.addActionListener(e -> ventanaDeMov(objetoDAO));
        panelEntrada.add(mostrarMovimientosBtn);

        frame.add(tablero, BorderLayout.CENTER);
        frame.add(panelEntrada, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);


    }

    private static void confBotonEnviar(JButton enviarButton, JTextField movimientoField, JTextField colorField, JTextField posicionInicialField, JTextField posicionFinalField, CConexionDAO objetoDAO) {
        enviarButton.addActionListener(e -> {
            try {
                String pieza = movimientoField.getText();
                boolean esBlanco = colorField.getText().equalsIgnoreCase("blanco");

                String posicionInicial = posicionInicialField.getText().replaceAll("[()\\s]", "");
                String posicionFinal = posicionFinalField.getText().replaceAll("[()\\s]", "");

                int colOrigen = Integer.parseInt(posicionInicial.split(",")[0]);
                int filaOrigen = Integer.parseInt(posicionInicial.split(",")[1]);
                int colDestino = Integer.parseInt(posicionFinal.split(",")[0]);
                int filaDestino = Integer.parseInt(posicionFinal.split(",")[1]);

                /*  Llama al método enviarDatos del DAO*/
                objetoDAO.enviarDatos(pieza, colOrigen, filaOrigen, colDestino, filaDestino, esBlanco);

                movimientoField.setText("");
                colorField.setText("");
                posicionInicialField.setText("");
                posicionFinalField.setText("");

            } catch (NumberFormatException ex) {
                System.out.println("Error en formato de posiciones: " + ex.getMessage());
            }
        });
    }

    private static void ventanaDePiezas(CConexionDAO objetoDAO) {  
        JFrame tablaFrame = new JFrame("Piezas en la Base de Datos");  
        tablaFrame.setSize(800, 400);  
        tablaFrame.setLocationRelativeTo(null);  

        ImageIcon icono = new ImageIcon(App.class.getResource("/res/BaseDatoIcon.png")); 
        tablaFrame.setIconImage(icono.getImage());

        ArrayList<String[]> piezasData = objetoDAO.obtenerPiezas();  
        String[] columnNames = {"Tipo", "Columna", "Fila", "Color"};  
        String[][] data = piezasData.toArray(new String[0][0]);  

        JTable table = new JTable(data, columnNames);  
        JScrollPane scrollPane = new JScrollPane(table);  
        tablaFrame.add(scrollPane, BorderLayout.CENTER);  

        JButton borrarBtn = new JButton("Limpiar campos");  
        borrarBtn.addActionListener(e -> {  
            objetoDAO.borrarPiezas();  
            // Actualizar
            tablaFrame.dispose(); 
            ventanaDePiezas(objetoDAO);  
        });  
        tablaFrame.add(borrarBtn, BorderLayout.SOUTH);  

        tablaFrame.setVisible(true);  
    }  

    private static void ventanaDeMov(CConexionDAO objetoDAO) {  
        JFrame tablaFrame = new JFrame("Movimientos Piezas");  
        tablaFrame.setSize(880, 500);  
        tablaFrame.setLocationRelativeTo(null);  

        ImageIcon icono = new ImageIcon(App.class.getResource("/res/BaseDatoIcon.png")); 
        tablaFrame.setIconImage(icono.getImage());

        ArrayList<String[]> movimientosData = objetoDAO.obtenerMovimientos();  
        String[] columnNames = {"Pieza", "Col. Inicial", "Fila Inicial", "Col. Final", "Fila Final", "Color", "Fecha y Hora"};  
        String[][] data = movimientosData.toArray(new String[0][0]);  

        JTable table = new JTable(data, columnNames);  
        JScrollPane scrollPane = new JScrollPane(table);  
        tablaFrame.add(scrollPane, BorderLayout.CENTER);  

        JButton borrarBtn = new JButton("Limpiar campos");  
        borrarBtn.addActionListener(e -> {  
            objetoDAO.borrarMovimientos();  
            // Actualizar
            tablaFrame.dispose(); 
            ventanaDeMov(objetoDAO);  
        });  
        tablaFrame.add(borrarBtn, BorderLayout.SOUTH);  

        tablaFrame.setVisible(true);  
    }

}