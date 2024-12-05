package main;  

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import DAO.CConexionDAO;
import piezas.*;  



public final class Tablero extends JPanel {  

    public int tamCasilla = 70;  
    int columnas = 8;  
    int filas = 8;  
    public Pieza piezaSeleccionada; 
    ArrayList<Pieza> listaDePiezas = new ArrayList<>();  
    Entrada entrada = new Entrada(this);  


    public Tablero() {  
        this.setPreferredSize(new Dimension(columnas * tamCasilla, filas * tamCasilla));  
        this.addMouseListener(entrada);  
        this.addMouseMotionListener(entrada);  
        agregarPiezas();  
    }  


    public Pieza obtenerPieza(int col, int fila) {  
        for (Pieza pieza : listaDePiezas) {  
            if (pieza.col == col && pieza.fila == fila) {  
                return pieza;  
            }  
        }  
        return null;  
    }  
    
    
    public void realizarMovimiento(Movimiento movimiento) {  
        String color = movimiento.pieza.esBlanco ? "Blanco" : "Negro";  
        String comportamiento = movimiento.pieza.getCaracter();
        String nivelAtaque = movimiento.pieza.getNivelAtaque();
        System.out.println("\nMOVISTE: " + movimiento.pieza.getClass().getSimpleName() + " (Color: " + color + ") \nPOSICION: (" + movimiento.pieza.col + ", " + movimiento.pieza.fila + ") -> (" + movimiento.colNueva + ", " + movimiento.filaNueva + ")" +"\nCARACTER: " + comportamiento + "\nNIVEL DE ATAQUE: " + nivelAtaque + "\n");
        movimiento.pieza.col = movimiento.colNueva;  
        movimiento.pieza.fila = movimiento.filaNueva;  
        movimiento.pieza.xPos = movimiento.colNueva * tamCasilla;  
        movimiento.pieza.yPos = movimiento.filaNueva * tamCasilla;   
        movimiento.pieza.esPrimerMov = false;
        capturar(movimiento);  
    }  
    

    public void capturar(Movimiento movimiento) {  
        if (movimiento.captura != null) {  
            String nombrePiezaCapturada = movimiento.captura.getClass().getSimpleName();
            listaDePiezas.remove(movimiento.captura);
            JOptionPane.showMessageDialog(this, "Te comiste :  " + nombrePiezaCapturada, "NUEVA CAPTURA", JOptionPane.INFORMATION_MESSAGE);
        }
    }  


    public boolean esValidoMover(Movimiento movimiento) {  
        if (mismoEquipo(movimiento.pieza, movimiento.captura)) {  
            return false;  
        }  
        if(!movimiento.pieza.esMovimientoValido(movimiento.colNueva, movimiento.filaNueva)){
            return false;
        }
        if(movimiento.pieza.moverChoqueConPieza(movimiento.colNueva, movimiento.filaNueva)) {
            return false;
        }
        return true;  
    }  


    public boolean mismoEquipo(Pieza p1, Pieza p2) {  
        if (p1 == null || p2 == null) {  
            return false;  
        }  
        return p1.esBlanco == p2.esBlanco;  
    }  



    public void agregarPiezas() {  
        listaDePiezas.add(new Torre(this, 0, 0, false));  
        listaDePiezas.add(new Caballo(this, 1, 0, false));  
        listaDePiezas.add(new Alfil(this, 2, 0, false));  
        listaDePiezas.add(new Reina(this, 3, 0, false));  
        listaDePiezas.add(new Rey(this, 4, 0, false));  
        listaDePiezas.add(new Alfil(this, 5, 0, false));  
        listaDePiezas.add(new Caballo(this, 6, 0, false));  
        listaDePiezas.add(new Torre(this, 7, 0, false));  

        listaDePiezas.add(new Peon(this, 0, 1, false));  
        listaDePiezas.add(new Peon(this, 1, 1, false));  
        listaDePiezas.add(new Peon(this, 2, 1, false));   
        listaDePiezas.add(new Peon(this, 3, 1, false));  
        listaDePiezas.add(new Peon(this, 4, 1, false));  
        listaDePiezas.add(new Peon(this, 5, 1, false));  
        listaDePiezas.add(new Peon(this, 6, 1, false));  
        listaDePiezas.add(new Peon(this, 7, 1, false));  

        listaDePiezas.add(new Torre(this, 0, 7, true));  
        listaDePiezas.add(new Caballo(this, 1, 7, true));  
        listaDePiezas.add(new Alfil(this, 2, 7, true));  
        listaDePiezas.add(new Reina(this, 3, 7, true));  
        listaDePiezas.add(new Rey(this, 4, 7, true));  
        listaDePiezas.add(new Alfil(this, 5, 7, true));  
        listaDePiezas.add(new Caballo(this, 6, 7, true));  
        listaDePiezas.add(new Torre(this, 7, 7, true));  

        listaDePiezas.add(new Peon(this, 0, 6, true));  
        listaDePiezas.add(new Peon(this, 1, 6, true));  
        listaDePiezas.add(new Peon(this, 2, 6, true));  
        listaDePiezas.add(new Peon(this, 3, 6, true));  
        listaDePiezas.add(new Peon(this, 4, 6, true));  
        listaDePiezas.add(new Peon(this, 5, 6, true));  
        listaDePiezas.add(new Peon(this, 6, 6, true));  
        listaDePiezas.add(new Peon(this, 7, 6, true));
       
        CConexionDAO piezaDAO = new CConexionDAO(); 
        piezaDAO.guardarPiezas(listaDePiezas);
    }  


    @Override  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D) g;
        Color marronClaro = new Color(248, 196, 113);
        Color marronOscuro = new Color(147, 87, 22); 
        for (int f = 0; f < filas; f ++) {  
            for (int c = 0; c < columnas; c++) {  
                g2d.setColor((c + f) % 2 == 0 ? marronClaro : marronOscuro);  
                g2d.fillRect(c * tamCasilla, f * tamCasilla, tamCasilla, tamCasilla);  
            }  
        }

        Color captura = new Color(0, 174, 83); 
        if(piezaSeleccionada != null){
            for(int f = 0; f < filas; f ++){
                for(int c = 0; c < columnas; c++){
                    if(esValidoMover(new Movimiento(this, piezaSeleccionada, c, f))){
                        g2d.setColor(captura);
                        g2d.fillRect(c * tamCasilla, f * tamCasilla, tamCasilla, tamCasilla);
                    }
                }
            }
        }

        for (Pieza pieza : listaDePiezas) {  
            pieza.pintar(g2d);  
        }  
    }  
}

