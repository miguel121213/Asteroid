/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;



import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
/**
/**
 *
 * @author 1DAW08
 */
public class MainAsteroid extends Application {
    final int ALTOPANEL = 600;
    final int ANCHOPANEL = 800;
    int navePosY = ALTOPANEL/2;
    int navePosX = ANCHOPANEL /2;
    double velocidadGiroBala;
    double posBalaY = navePosY +10;
    double posBalaX = navePosX + 50;

    private ArrayList<Asteroid> arrayListasteroid;
    private ArrayList<Bala> arrayListBala;
    
    @Override
    public void start(Stage primaryStage) {

//        Asteroid asteroid = new Asteroid();
        Nave naveFuego = new Nave();
        Bala bala = new Bala();
        //Color aleatorio 
        Pane root = new Pane(); //Panel       
        Scene scene = new Scene (root, ANCHOPANEL, ALTOPANEL, Color.BLACK); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Nave
        naveFuego.returnPaneNaveFuego();   
        naveFuego.crearFuego();
        naveFuego.crearNave();
        root.getChildren().add(naveFuego.returnPaneNaveFuego());
//        root.getChildren().add(asteroid.getForma());
        naveFuego.tamañoPaneYañadirNaveFuego();
//        asteroid.velocidadAsteroide();
//        asteroid.asteroidePos();       
        root.getChildren().add(bala.getBala()); //añadir la bala al root
        naveFuego.posPaneNaveFuego();
        naveFuego.fuegoInvisible(); //poner fuego en invisible 
        bala.balaInVisible();
        scene.getStylesheets().add("newCascadeStyleSheet.css");
//        asteroid.crearAsteroide();
        arrayListasteroid = new ArrayList<Asteroid>();
        for (int i = 0; i < 5; i++){
            Asteroid asteroid = new Asteroid();
            asteroid.crearFormaAsteroide();
            arrayListasteroid.add(asteroid);
            asteroid.velocidadAsteroide();
            asteroid.asteroidePos(); 
            root.getChildren().add(asteroid.getForma());            
        }
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
                    bala.pulsarEspacio(naveFuego.getAnguloGrupo(), naveFuego.getNavePosX(), 
                    naveFuego.getNavePosY());
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
                for(int i=0; i<arrayListasteroid.size(); i++){
                    Asteroid getAsteroid =  arrayListasteroid.get(i);
                    getAsteroid.giroAsteroide();
                    getAsteroid.movimientoAsteroide();
                    getAsteroid.bordes();
                }                      
                naveFuego.calcularAngulo();
                naveFuego.calcularAngulo();
                 //color aleatorio nave
                naveFuego.colorNave();
                //movimiento nave y fuego
                naveFuego.movimientoPaneNaveFuego();
                //Movimiento Asteroid

                //MovimientoBala
                bala.movimientoBala();
                //Giros
                naveFuego.calcularGiroNave();                             
//                if ((velocidadNaveY>10) || (velocidadNaveX > 10)){
//                    velocidadNaveY = 10;
//                    velocidadNaveX = 10;
//                }
//                naveFuego.calcularRestoAngulo();
                // al salir del screen 
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
