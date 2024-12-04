package main;  

import piezas.Pieza;  

public class Movimiento {  

    
    int colAnterior;  
    int filaAnterior;  
    int colNueva;  
    int filaNueva;  
  
    
    Pieza pieza; /* pieza que se esta movinedo */
     
    Pieza captura; /*Pieza que sera capturada en el movimiento (si es quee existe)*/

    public Movimiento(){}
    public Movimiento(Tablero tablero, Pieza pieza, int colNueva, int filaNueva) {  
       
        this.colAnterior = pieza.col;  
        this.filaAnterior = pieza.fila;  
        this.colNueva = colNueva;  
        this.filaNueva = filaNueva;  
        this.pieza = pieza;  

        this.captura = tablero.obtenerPieza(colNueva, filaNueva);  
    }   
    
}