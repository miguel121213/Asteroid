/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;



import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
/**
 *
 * @author 1DAW08
 */
public class MainAsteroid extends Application {
    final int ALTOPANEL = 600;
    final int ANCHOPANEL = 800;
    double velocidadNaveY = 0;
    double velocidadNaveX = 0;
    int velocidadFuegoY = 0;
    int velocidadFuegoX = 0;
    int navePosY = ALTOPANEL/2;
    int navePosX = ANCHOPANEL /2;
    double  asteroidPosY = Math.random()* (ALTOPANEL);
    double asteroidPosX = Math.random()* (ANCHOPANEL);
    double velocidadGiroNave = 0;
    double velocidadGiroFuego = 0;
    double direccionXpaneFuegoNave = 0;
    double direccionYpaneFuegoNave = 0;
    double anchuraPane = 60;
    double alturaPane = 20;

    double direccionXNave;
    double direccionYNave;
    double velocidadAsteroidX = Math.random()* (5 - (-5)) + (-5);
    double velocidadAsteroidY = Math.random() * (5 - (-5)) + (-5);
    double velocidadGiroAsteroid = Math.random() *(5 - (-5)) + (-5);
    double velocidadBalaY;
    double velocidadBalaX;
    double velocidadGiroBala;
    double posBalaY = navePosY +10;
    double posBalaX = navePosX + 50;
    double direccionBalaX; 
    double direccionBalaY;
    
    @Override
    public void start(Stage primaryStage) {
        //Color aleatorio 
        Pane root = new Pane(); //Panel       
        Scene scene = new Scene (root, ANCHOPANEL, ALTOPANEL, Color.BLACK); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Nave
        Circle bala = new Circle(1, 1, 1, Color.WHITE);
        Nave naveFuego = new Nave();
        naveFuego.returnPaneNaveFuego();
        
        naveFuego.crearFuego();
        naveFuego.crearNave();
//        naveFuego.returnPaneNaveFuego();
        root.getChildren().add(naveFuego.returnPaneNaveFuego());
        naveFuego.tamañoPaneYañadirNaveFuego();
        Asteroid asteroid = new Asteroid();
        asteroid.crearAsteroide();
        asteroid.velocidadAsteroide();
        asteroid.asteroidePos();       
        root.getChildren().add(bala); //añadir la bala al root
        root.getChildren().add(asteroid.getForma());
        naveFuego.posPaneNaveFuego();
        naveFuego.fuegoInvisible(); //poner fuego en invisible 
        bala.setVisible(false);
        scene.setOnKeyPressed((KeyEvent event) -> { // switch para teclas 
            switch(event.getCode()){
                case UP:                    
                    naveFuego.velocidadNave();
                    naveFuego.fuegoVisible();
                    break;               
                case RIGHT:
                    naveFuego.giroDerecha();
                    break;
                case LEFT:
                    naveFuego.giroIzquierda();
                    break;
                case SPACE:
                    posBalaY = navePosY +10;
                    posBalaX = navePosX + 50;
                    velocidadBalaX = 15;
                    velocidadBalaY = 15;               
                    direccionBalaY = direccionYNave;
                    direccionBalaX = direccionXNave;
                    bala.setVisible(true);
                    break;
            }
 
        });

        //soltar tecas
        scene.setOnKeyReleased((KeyEvent event) -> {  //Cuando sueltas una tecla pulsada         
                if(event.getCode() == KeyCode.UP ||event.getCode() 
                == KeyCode.LEFT ||event.getCode() == KeyCode.RIGHT){
                    naveFuego.pararGirorNave();

                    naveFuego.fuegoInvisible();
                }         
        });
        
        AnimationTimer movimimientoNave; //crear la animacion
        movimimientoNave = new AnimationTimer() {       
            public void handle(long now) {               
                //Calcular angulo para direcciones
                naveFuego.calcularAngulo();
                asteroid.giroAsteroide();
                naveFuego.calcularAngulo();
                 //color aleatorio nave
                naveFuego.colorNave();
                //movimiento nave y fuego
                naveFuego.movimientoPaneNaveFuego();
                naveFuego.rotarNave();
                //Movimiento Asteroid
                asteroid.movimientoAsteroide();
                //MovimientoBala
                posBalaY += velocidadBalaY * direccionBalaY;
                bala.setLayoutY(posBalaY);
                posBalaX += velocidadBalaX * direccionBalaX;
                bala.setLayoutX(posBalaX);
                //Giros
                naveFuego.calcularGiroNave();                             
//                if ((velocidadNaveY>10) || (velocidadNaveX > 10)){
//                    velocidadNaveY = 10;
//                    velocidadNaveX = 10;
//                }
                naveFuego.calcularRestoAngulo();
                // al salir del screen 
                asteroid.bordes();
                naveFuego.naveBordes();                  
            }           
       };
        movimimientoNave.start();     
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
