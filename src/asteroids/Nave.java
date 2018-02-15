/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author 1DAW08
 */
public class Nave {
    private final int ALTOPANEL = 600;
    private final int ANCHOPANEL = 800;
    private double velocidadNaveY = 0;
    private double velocidadNaveX = 0;
    private int velocidadFuegoY = 0;
    private int velocidadFuegoX = 0;
    private int navePosY = ALTOPANEL/2;
    private int navePosX = ANCHOPANEL /2;
    private double velocidadGiroNave;
    private double direccionXpaneFuegoNave = 0;
    private double direccionYpaneFuegoNave = 0;
    private double direccionXNave;
    private double direccionYNave;
    private double anchuraPane = 60;
    private double alturaPane = 20;
    private double anguloGrupo;
    
    Polygon formaNave = new Polygon(); //crear nave
    Polygon formaFuego = new Polygon();  //crear fuego
    Pane paneNaveFuego = new Pane(); //Pane para que el fuego se mueva con la nave
    public void calcularAngulo(){
        anguloGrupo = paneNaveFuego.getRotate();        
    }  
    public void tamañoPaneYañadirNaveFuego(){
        paneNaveFuego.setMinWidth(anchuraPane);//establecer tamaño minimo al pane naveFuego
        paneNaveFuego.setMinHeight(5);
        paneNaveFuego.getChildren().add(formaFuego); //añadir al pane
        paneNaveFuego.getChildren().add(formaNave); //añadir al pane
        
    }
    
    public void crearNave(){
        formaNave.getPoints().addAll(new Double[]{ //tamaño nave
            59.0,alturaPane/2,
            30.0, 0.0,
            30.0, 20.0 });
    }
    public void crearFuego(){
        formaFuego.getPoints().addAll(new Double[]{ // tamaño fuego
            15.0, 10.0,
            30.0, 0.0,
            30.0, 20.0 });
        formaFuego.setFill(Color.RED);
    }
    public Pane returnPaneNaveFuego(){
        return paneNaveFuego;
    }
    public void colorNave(){
        Random colorRandom = new Random(System.currentTimeMillis());
        for (int i = 0; i<100; i++){
            int red = colorRandom.nextInt(255);
            int green = colorRandom.nextInt(255);
            int blue = colorRandom.nextInt(255);
            formaNave.setFill(Color.rgb(red, green, blue, .99)); //Color nave
         }
    }
    public void fuegoInvisible(){
         formaFuego.setVisible(false);
    }
    public void fuegoVisible(){
         formaFuego.setVisible(true);
    }
    public void velocidadNave(){
         velocidadNaveY += 3;
         velocidadNaveX += 3;
        
    }
    public void movimientoPaneNaveFuego(){              
            anguloGrupo += velocidadGiroNave;
            paneNaveFuego.setRotate(anguloGrupo);
            direccionXNave = Math.cos(Math.toRadians(anguloGrupo));
            direccionYNave = Math.sin(Math.toRadians(anguloGrupo));
            navePosY +=  velocidadNaveY  * direccionYNave;
            paneNaveFuego.setLayoutY(navePosY);               
            navePosX += velocidadNaveX  * direccionXNave;
            paneNaveFuego.setLayoutX(navePosX);
    }
    public void naveBordes(){
        if (paneNaveFuego.getLayoutY() > 600){ //nave
            navePosY = 0;                                     
        }                 
        if (paneNaveFuego.getLayoutY() < 0){ //nave
            navePosY = 600;
        }                 
        if (paneNaveFuego.getLayoutX() > 800){ //nave
            navePosX = 0;
        }                 
        if (paneNaveFuego.getLayoutX() < 0){ //nave
            navePosX = 800;
        }                    

    }
    public void calcularGiroNave(){
        anguloGrupo += velocidadGiroNave;
        paneNaveFuego.setRotate(anguloGrupo);  
    }
    public void posPaneNaveFuego(){
        paneNaveFuego.setLayoutX(navePosX); //Poner el pane naveFuego en el centro
        paneNaveFuego.setLayoutY(navePosY); // poner el panel naveFuego en el centro
    }
    public void calcularRestoAngulo(){
        anguloGrupo %= 360;
    }
    public void giroDerecha(){
        velocidadGiroNave = 3;
    }
    public void giroIzquierda(){
        velocidadGiroNave = -3;
    }
    public void pararGirorNave(){
        velocidadGiroNave = 0;
    }
    public int getNavePosY(){
        return navePosY;
    }
    public int getNavePosX(){
        return navePosX;
    }
    public double getDireccionNaveX(){
        return direccionXNave;
    }
    public double getDireccionNaveY(){
        return direccionYNave;
    }
    
    
    
}

