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
    Circle formaBala = new Circle(1, 1, 1, Color.WHITE);
    double posBalaX;
    double posBalaY;
    double direccionBalaX;
    double direccionBalaY;

    
    public Circle getBala(){
        return formaBala;       
    }
    public void pulsarEspacio(double anguloNave, double posNaveX, double posNaveY){
        direccionBalaX = Math.cos(Math.toRadians(anguloNave));
        direccionBalaY = Math.sin(Math.toRadians(anguloNave));
        posBalaY = posNaveY + 10;
        formaBala.setLayoutY(posBalaX);
        posBalaX = posNaveX + 50;
        formaBala.setLayoutX(posBalaX);
        velocidadBalaX = 15;
        velocidadBalaY = 15;              
        formaBala.setVisible(true);
    }
    public void movimientoBala(){
        posBalaY += velocidadBalaY * direccionBalaY;
        formaBala.setLayoutY(posBalaY);
        posBalaX += velocidadBalaX * direccionBalaX;
        formaBala.setLayoutX(posBalaX);
    }

    public void balaInVisible(){
        formaBala.setVisible(false);
    }

    
    
}
