/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author 1DAW08
 */
public class Bala {
    double velocidadBalaY;
    double velocidadBalaX;
    double posBalaY;
    double posBalaX;
    Circle formaBala = new Circle(1, 1, 1, Color.WHITE);
    Nave getNavePos = new Nave();
    double direccionBalaY = getNavePos.getDireccionNaveY();
    double direccionBalaX = getNavePos.getDireccionNaveX();
    
    public Circle getBala(){
        return formaBala;       
    }
    public void pulsarEspacio(){
        posBalaY = getNavePos.getNavePosY() +10;
        posBalaX = getNavePos.getNavePosX() + 50; 
        velocidadBalaX = 15;
        velocidadBalaY = 15;               ;
        formaBala.setVisible(true);
    }
    public void movimientoBala(){ 
        posBalaY += velocidadBalaY * direccionBalaY;
        formaBala.setLayoutY(posBalaY);
        System.out.println("direccionBalaY: " + direccionBalaY);
        posBalaX += velocidadBalaX * direccionBalaX;
        formaBala.setLayoutX(posBalaX);
    }

    public void balaInVisible(){
        formaBala.setVisible(false);
    }
    
}
