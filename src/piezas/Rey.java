package piezas;

import java.awt.image.BufferedImage;
import main.Tablero;

public class Rey extends Pieza{

    public Rey(Tablero tablero, int col, int fila, boolean esBlanco){
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Rey"; 

        this.caracter = "Tenue";  
        this.nivelAtaque = "20%";  

        if (hoja != null) {
            this.sprite = hoja.getSubimage(0, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja)
                                .getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH);
        }
    }


    @Override
    public boolean esMovimientoValido(int col, int fila){
        return Math.abs((col - this.col) * (fila - this.fila)) == 1 || Math.abs(col - this.col) + Math.abs(fila - this.fila) == 1;
    }
}
