package piezas;

import java.awt.image.BufferedImage; 
import main.Tablero;

public class Caballo extends Pieza{

    public Caballo(Tablero tablero, int col, int fila, boolean esBlanco){
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Caballo"; 

        this.caracter = "LIgero";  
        this.nivelAtaque = "40%";  

        if (hoja != null) {  
            this.sprite = hoja.getSubimage(3 * escalaHoja, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja)  
                              .getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH);  
        }
    }


    @Override
    public boolean esMovimientoValido(int col, int row){
        return Math.abs(col - this.col) * Math.abs(row- this.fila) == 2; 
    }
}
