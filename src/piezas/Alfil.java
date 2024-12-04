package piezas;

import java.awt.image.BufferedImage; 
import main.Tablero;

public class Alfil extends Pieza{

    public Alfil(Tablero tablero, int col, int fila, boolean esBlanco){
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Alfil"; 

        this.caracter = "Oblicuo";  
        this.nivelAtaque = "70%";  

        this.sprite = hoja.getSubimage(2 * escalaHoja, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja).getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH);
    }


    
    @Override
    public boolean esMovimientoValido(int col, int fila){
        return Math.abs(this.col - col) == Math.abs(this.fila - fila);
    }


    @Override
    public boolean moverChoqueConPieza(int col, int fila){

        //abajo izq 
        if (this.col > col && this.fila > fila) {
            for(int i=1; i< Math.abs(this.col - col); i++){
                if(tablero.obtenerPieza(this.col - i, this.fila - i) != null){
                    return true;
                }
            }
        }

        //arriba der 
        if (this.col < col && this.fila > fila) {
            for(int i=1; i< Math.abs(this.col - col); i++){
                if(tablero.obtenerPieza(this.col + i, this.fila - i) != null){
                    return true;
                }
            }
        }

        //abjo izq
        if (this.col > col && this.fila < fila) {
            for(int i=1; i< Math.abs(this.col - col); i++){
                if(tablero.obtenerPieza(this.col - i, this.fila + i) != null){
                    return true;
                }
            }
        }

        //abajo der 
        if (this.col < col && this.fila < fila) {
            for(int i=1; i< Math.abs(this.col - col); i++){
                if(tablero.obtenerPieza(this.col + i, this.fila + i) != null){
                    return true;
                }
            }
        }
        return false;
    }
}
