package piezas;

import java.awt.image.BufferedImage;
import main.Tablero;


public class Peon extends Pieza{

    public Peon(Tablero tablero, int col, int fila, boolean esBlanco){
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Peon"; 

        this.caracter = "Agresor";  
        this.nivelAtaque = "20%";  

        if (hoja != null) {  
            this.sprite = hoja.getSubimage(5 * escalaHoja, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja).getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH); 
        }
    }


    @Override
    public boolean esMovimientoValido(int col, int row) {
        int colorIndex = esBlanco ? 1 : -1;
    
        // Verifica movimiento de un espacio hacia adelante
        if (this.col == col && row == this.fila - colorIndex && tablero.obtenerPieza(col, row) == null) {
            return true; // Movimiento válido
        }
    
        // Verifica movimiento de dos espacios hacia adelante
        if (esPrimerMov && this.col == col && row == this.fila - colorIndex * 2 
                && tablero.obtenerPieza(col, row) == null && tablero.obtenerPieza(col, row + colorIndex) == null) {
            return true; // Movimiento válido
        }
    
        // Verifica captura a la izquierda
        if (col == this.col - 1 && row == this.fila - colorIndex && tablero.obtenerPieza(col, row) != null) {
            return true; // Movimiento de captura válido
        }
    
        // Verifica captura a la derecha
        if (col == this.col + 1 && row == this.fila - colorIndex && tablero.obtenerPieza(col, row) != null) {
            return true; // Movimiento de captura válido
        }
    
        return false;
    }
}
