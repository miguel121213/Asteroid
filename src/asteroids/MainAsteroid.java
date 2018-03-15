/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;



import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
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
    Bala bala;
    Asteroid asteroid;
    private ArrayList<Asteroid> arrayListasteroid;
    private ArrayList<Bala> arrayListBala;
    
    @Override
    public void start(Stage primaryStage) {

//        Asteroid asteroid = new Asteroid();
        Nave naveFuego = new Nave();
        //Color aleatorio 
        Pane root = new Pane(); //Panel       
        Scene scene = new Scene (root, ANCHOPANEL, ALTOPANEL); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
//        scene.getStylesheets().add("AsteroidCSS.css");
        //Nave
        naveFuego.returnPaneNaveFuego();   
        naveFuego.crearFuego();
        naveFuego.crearNave();
        root.getChildren().add(naveFuego.returnPaneNaveFuego());
        naveFuego.tamañoPaneYañadirNaveFuego(); 
        naveFuego.posPaneNaveFuego();
        naveFuego.fuegoInvisible(); //poner fuego en invisible      
        scene.getStylesheets().add("asteroids/AsteroidCSS.css");
        arrayListasteroid = new ArrayList<Asteroid>();
        for (int i = 0; i < 5; i++){                
            Asteroid asteroid = new Asteroid();
            asteroid.crearFormaAsteroide();
            arrayListasteroid.add(asteroid);
            asteroid.velocidadAsteroide();
            asteroid.asteroidePosY();
            asteroid.asteroidePosX();
            root.getChildren().add(asteroid.getForma());            
        }
        arrayListBala = new ArrayList<Bala>(); //Crear Lista balas
      //keys   
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
                        bala = new Bala(); // crear bala
                        root.getChildren().add(bala.getBala()); // añadirla al root
                        arrayListBala.add(bala);
                        bala.pulsarEspacio(naveFuego.getAnguloGrupo(), naveFuego.getNavePosX(), 
                        naveFuego.getNavePosY());
                        bala.balaVisible();
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
                if(arrayListasteroid.isEmpty()){
                    for (int i = 0; i < 5; i++){                
                    Asteroid asteroid = new Asteroid();
                    asteroid.crearFormaAsteroide();
                    arrayListasteroid.add(asteroid);
                    asteroid.velocidadAsteroide();
                    asteroid.asteroidePosY();
                    asteroid.asteroidePosX();
                    root.getChildren().add(asteroid.getForma());            
        }
                }
                //colision con Asteroide Bala
                for(int i=0; i<arrayListBala.size(); i++){
                    Bala balaGuardada =arrayListBala.get(i);
                    balaGuardada.movimientoBala();
                    if ((balaGuardada.posBalaX > 800) || (balaGuardada.posBalaX < 0)
                    ||(balaGuardada.posBalaY < 0)|| (balaGuardada.posBalaY > 800)){
                            arrayListBala.remove(balaGuardada);
                            balaGuardada.formaBala.setVisible(false);
                            root.getChildren().remove(balaGuardada.formaBala);
                    }
                
                    for (int j = 0; j < arrayListasteroid.size(); j++){
                        Asteroid asteroidGuardado = arrayListasteroid.get(j);
                        Shape colisionBalaAsteroide = Shape.intersect(asteroidGuardado.forma, balaGuardada.formaBala);
                        boolean colisionVaciaBalaAsteroide = colisionBalaAsteroide.getBoundsInLocal().isEmpty();
                            if(colisionVaciaBalaAsteroide == false){
                                    arrayListasteroid.remove(asteroidGuardado);
                                    asteroidGuardado.forma.setVisible(false);
                                    root.getChildren().remove(asteroidGuardado.forma);
                                    
                        }
                    }
                }
                //Colision Nave Asteroide
                for(int i= 0; i<arrayListasteroid.size(); i++){
                    Asteroid asteroideGurdado2 = arrayListasteroid.get(i);
                        Shape colisionNaveAsteroide = Shape.intersect(asteroideGurdado2.forma, naveFuego.formaNave);
                        boolean colisionVaciaBalaAsteroide = colisionNaveAsteroide.getBoundsInLocal().isEmpty();
                        if (colisionVaciaBalaAsteroide == false){
                           this.stop();
                        }
                }
                //Giros
                naveFuego.calcularGiroNave();                             
//                if ((velocidadNaveY>10) || (velocidadNaveX > 10)){
//                    velocidadNaveY = 10;
//                    velocidadNaveX = 10;
//                }
                // al salir del screen 
                naveFuego.naveBordes();                  
            }           
       };
        movimimientoNave.start(); 
//        scene.setOnKeyPressed((KeyEvent event) -> { // switch para teclas 
//            switch(event.getCode()){
//                case F:
//                    movimimientoNave.start(); 
//            }
//        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}