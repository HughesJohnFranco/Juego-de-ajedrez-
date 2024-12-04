package piezas;

import java.awt.image.BufferedImage;
import main.Tablero;

public class Torre extends Pieza{

    public Torre(Tablero tablero, int col, int fila, boolean esBlanco){
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Torre";
        
        this.caracter = "Homerica";  
        this.nivelAtaque = "60%";  

        if (hoja != null) {  
            this.sprite = hoja.getSubimage(4 * escalaHoja, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja)  
                              .getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH);  
        }
    }



    @Override
    public boolean esMovimientoValido(int col, int fila){
        return this.col == col || this.fila == fila;
    }


    // Verifica si hay una pieza en el camino para un movimiento de la torre
    @Override
    public boolean moverChoqueConPieza(int colDestino, int filaDestino){

    // Movimiento a la izquierda
    if (this.col > colDestino) {
        for (int c = this.col - 1; c > colDestino; c--) {
            if (tablero.obtenerPieza(c, this.fila) != null) {
                return true; 
            }
        }
    }
    // Movimiento a la derecha
    else if (this.col < colDestino) {
        for (int c = this.col + 1; c < colDestino; c++) {
            if (tablero.obtenerPieza(c, this.fila) != null) {
                return true;  
            }
        }
    }
    // Movimiento hacia arriba
    else if (this.fila > filaDestino) {
        for (int r = this.fila - 1; r > filaDestino; r--) {
            if (tablero.obtenerPieza(this.col, r) != null) {
                return true; 
            }
        }
    }
    // Movimiento hacia abajo
    else if (this.fila < filaDestino) {
        for (int r = this.fila + 1; r < filaDestino; r++) {
            if (tablero.obtenerPieza(this.col, r) != null) {
                return true;  
            }
        }
    }

    return false;  
    }





}