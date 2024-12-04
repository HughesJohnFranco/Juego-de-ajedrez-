package piezas;

import java.awt.image.BufferedImage;  
import main.Tablero;


public class Reina extends Pieza {

    public Reina(Tablero tablero, int col, int fila, boolean esBlanco) {
        super(tablero);  

        this.col = col;  
        this.fila = fila;  
        this.xPos = col * tablero.tamCasilla;  
        this.yPos = fila * tablero.tamCasilla;

        this.esBlanco = esBlanco;  
        this.nombre = "Reina"; 

        this.caracter = "Encarnizada";  
        this.nivelAtaque = "100%";  

        if (hoja != null) {
            this.sprite = hoja.getSubimage(escalaHoja, esBlanco ? 0 : escalaHoja, escalaHoja, escalaHoja)
                              .getScaledInstance(tablero.tamCasilla, tablero.tamCasilla, BufferedImage.SCALE_SMOOTH);
        }
    }

    // le implemento el movimiento de la piezas torre y alfil 
    @Override
    public boolean esMovimientoValido(int col, int fila) {
        // Verifica que el movimiento sea válido en línea recta o diagonal
        boolean esMovimientoPosible = this.col == col || this.fila == fila || 
                                      Math.abs(this.col - col) == Math.abs(this.fila - fila);
        // Revisa si el movimiento es posible y si el camino está libre
        return esMovimientoPosible && !moverChoqueConPieza(col, fila);
    }

    @Override
    public boolean moverChoqueConPieza(int col, int fila) {
        // Movimiento en línea recta (horizontal o vertical)
        if (this.col == col || this.fila == fila) {
            // Movimiento horizontal hacia la izquierda
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (tablero.obtenerPieza(c, this.fila) != null) {
                        return true;  // Hay una pieza en el camino
                    }
                }
            }

            // Movimiento horizontal hacia la derecha
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (tablero.obtenerPieza(c, this.fila) != null) {
                        return true;  // Hay una pieza en el camino
                    }
                }
            }

            // Movimiento vertical hacia arriba
            if (this.fila > fila) {
                for (int r = this.fila - 1; r > fila; r--) {
                    if (tablero.obtenerPieza(this.col, r) != null) {
                        return true;  // Hay una pieza en el camino
                    }
                }
            }

            // Movimiento vertical hacia abajo
            if (this.fila < fila) {
                for (int r = this.fila + 1; r < fila; r++) {
                    if (tablero.obtenerPieza(this.col, r) != null) {
                        return true;  // Hay una pieza en el camino
                    }
                }
            }
        } 
        // Movimiento en diagonal
        else {
            int distancia = Math.abs(this.col - col);

            // Diagonal hacia arriba a la izquierda
            if (this.col > col && this.fila > fila) {
                for (int i = 1; i < distancia; i++) {
                    if (tablero.obtenerPieza(this.col - i, this.fila - i) != null) {
                        return true; 
                    }
                }
            }

            // Diagonal hacia arriba a la derecha
            if (this.col < col && this.fila > fila) {
                for (int i = 1; i < distancia; i++) {
                    if (tablero.obtenerPieza(this.col + i, this.fila - i) != null) {
                        return true; 
                    }
                }
            }

            // Diagonal hacia abajo a la izquierda
            if (this.col > col && this.fila < fila) {
                for (int i = 1; i < distancia; i++) {
                    if (tablero.obtenerPieza(this.col - i, this.fila + i) != null) {
                        return true; 
                    }
                }
            }

            // Diagonal hacia abajo a la derecha
            if (this.col < col && this.fila < fila) {
                for (int i = 1; i < distancia; i++) {
                    if (tablero.obtenerPieza(this.col + i, this.fila + i) != null) {
                        return true; 
                    }
                }
            }
        }

        // No hay colisiones en el camino
        return false;
    }
}



