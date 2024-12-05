package piezas;  

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Tablero;  

public abstract class Pieza {  

    public int col, fila;  
    public int xPos, yPos;  
    public boolean esBlanco;  
    public String nombre;  
    public int valor;  
    public String caracter;  
    public String nivelAtaque;   
    public boolean esPrimerMov = true;
    protected int escalaHoja;  
    Image sprite;  
    Tablero tablero;
    BufferedImage hoja;    

    public Pieza(){}
    public Pieza(Tablero tablero) {  
        this.tablero = tablero;  
        try {  
            hoja = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/piezas.png"));  
            if (hoja != null) {  
                escalaHoja = hoja.getWidth() / 6;  
            } else {  
                System.out.println("Error: La imagen no se ha encontrado.");  
            }  
        } catch (IOException e) {  
        }  
    }  

    public String getCaracter() {
        return caracter;
    }
    public String getNivelAtaque() {
        return nivelAtaque;
    }

    
    public boolean esMovimientoValido(int col, int row){return  true;}
    public boolean moverChoqueConPieza(int col, int row){return false;}


    public void pintar(Graphics2D g2d) {  
        if (sprite != null) {  
            g2d.drawImage(sprite, xPos, yPos, null);  
        }  
    }  
} 
    
