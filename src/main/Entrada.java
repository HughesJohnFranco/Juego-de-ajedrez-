package main;  


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;
import piezas.Pieza;


public class Entrada extends MouseAdapter {  

    Tablero tablero;  
    private List<Movimiento> movimientos;


    public Entrada(Tablero tablero) {  
        this.tablero = tablero; 
        this.movimientos = new ArrayList<>();  
    }  

    @Override  
    public void mousePressed(MouseEvent e) {  
        int col = e.getX() / tablero.tamCasilla;   
        int fila = e.getY() / tablero.tamCasilla;   

        Pieza piezaXY = tablero.obtenerPieza(col, fila);  

        if (piezaXY != null) {  
            tablero.piezaSeleccionada = piezaXY;  
        }  
    }  

    @Override  
    public void mouseDragged(MouseEvent e) {  
        if (tablero.piezaSeleccionada != null) {  
            tablero.piezaSeleccionada.xPos = e.getX() - tablero.tamCasilla / 2;  
            tablero.piezaSeleccionada.yPos = e.getY() - tablero.tamCasilla / 2;  

            tablero.repaint();  
        }  
    }  


    @Override  
    public void mouseReleased(MouseEvent e) {  
        int col = e.getX() / tablero.tamCasilla;  
        int fila = e.getY() / tablero.tamCasilla;  
    
        if (tablero.piezaSeleccionada != null) {  
            Movimiento movimiento = new Movimiento(tablero, tablero.piezaSeleccionada, col, fila);  
    
            if (tablero.esValidoMover(movimiento)) {  
                
                // Usar esBlanco para determinar el color  
                String color = tablero.piezaSeleccionada.esBlanco ? "Blanco" : "Negro";  
                String posInicial = "(" + tablero.piezaSeleccionada.col + ", " + tablero.piezaSeleccionada.fila + ")";  
                String posFinal = "(" + col + ", " + fila + ")";  
    
                tablero.realizarMovimiento(movimiento);  
                
                // Actualiza los campos  
                JPanel panelEntrada = (JPanel) tablero.getParent().getComponent(1); // Obtiene el panel de entrada  
                ((JTextField) panelEntrada.getComponent(1)).setText(tablero.piezaSeleccionada.nombre);  
                ((JTextField) panelEntrada.getComponent(3)).setText(color);  
                ((JTextField) panelEntrada.getComponent(5)).setText(posInicial);  
                ((JTextField) panelEntrada.getComponent(7)).setText(posFinal);  
            } else {  
                tablero.piezaSeleccionada.xPos = tablero.piezaSeleccionada.col * tablero.tamCasilla;  
                tablero.piezaSeleccionada.yPos = tablero.piezaSeleccionada.fila * tablero.tamCasilla;  
            }  
        }  
    
        tablero.piezaSeleccionada = null;  
        tablero.repaint();  
    }


}
