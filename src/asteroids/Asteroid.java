/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author 1DAW08
 */
public class Asteroid {
    final int ALTOPANEL = 600;
    final int ANCHOPANEL = 800;
    private double velocidadAsteroidX;
    private double velocidadAsteroidY;
    private double velocidadGiroAsteroid;
    private double  asteroidPosY;
    private double asteroidPosX;
    Polygon forma = new Polygon();
    
    
    public Polygon getForma(){
        return forma;
    }

    
    
    public void asteroidePosY(){
             asteroidPosY = Math.random()* (ALTOPANEL);          
    }
    public void asteroidePosX( ){
        asteroidPosX = Math.random()* (ANCHOPANEL);
    }
    public void velocidadAsteroide(){
        velocidadAsteroidX = Math.random()* (3 - (-1)) + (-1);
        velocidadAsteroidY = Math.random() * (3 - (-1)) + (-1);
    }
    public void movimientoAsteroide(){       
        asteroidPosY += velocidadAsteroidY;
        forma.setLayoutY(asteroidPosY);
        asteroidPosX += velocidadAsteroidX;
        forma.setLayoutX(asteroidPosX);
        
    }
    public void crearFormaAsteroide(){       
        
        forma.getPoints().addAll(new Double[]{
             0.0, 0.0,
            -50.0, 40.0,
            -35.0, 100.0,
            0.0, 70.0,
            35.0, 100.0,
            50.0, 40.0,
            }); 
        forma.setFill(Color.WHITE);
        forma.setLayoutX(asteroidPosX);
        forma.setLayoutY(asteroidPosY);
    }
    public void bordes(){
        if (forma.getLayoutY()>600){ //asteroide
            asteroidPosY = 0;                
        }
        if (forma.getLayoutY()<-25){ //asteroide
            asteroidPosY = 600;  
        } 
        if (forma.getLayoutX()>800){ //asteroide
            asteroidPosX = 0;  
        }
        if (forma.getLayoutX()<0){ //asteroide
            asteroidPosX = 800;  
               }
    }
    public void giroAsteroide(){
         velocidadGiroAsteroid = 3;
         double anguloAsteroid = forma.getRotate();
         anguloAsteroid += velocidadGiroAsteroid;
         forma.setRotate(anguloAsteroid);
    }    
}
