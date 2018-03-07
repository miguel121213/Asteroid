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
    final double VELOCIDADBALA = 10;
    Circle formaBala = new Circle(1, 1, 1);
    double posBalaX;
    double posBalaY;
    double direccionBalaX;
    double direccionBalaY;
    

    public Bala(){
        formaBala.setId("balaID");
    }
    public Circle getBala(){
        return formaBala;       
    }
    public void pulsarEspacio(double anguloNave, double posNaveX, double posNaveY){
        direccionBalaX = VELOCIDADBALA * Math.cos(Math.toRadians(anguloNave));
        direccionBalaY = VELOCIDADBALA * Math.sin(Math.toRadians(anguloNave));
        posBalaY = posNaveY+ 10;
        formaBala.setCenterY(posBalaY);
        posBalaX = posNaveX + 50;
        formaBala.setCenterX(posBalaX);            
        formaBala.setVisible(true);
    }
    public void movimientoBala(){
//        posBalaY += velocidadBalaY * direccionBalaY;
//        formaBala.setLayoutY(posBalaY);
//        posBalaX += velocidadBalaX * direccionBalaX;
//        formaBala.setLayoutX(posBalaX);
        formaBala.setCenterX(formaBala.getCenterX() + direccionBalaX);
        formaBala.setCenterY(formaBala.getCenterY() + direccionBalaY);
    }

    public void balaInVisible(){
        formaBala.setVisible(false);
    }
    public void balaVisible(){
        formaBala.setVisible(true);
    }

    
    
}
