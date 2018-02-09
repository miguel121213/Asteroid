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
public class Asteroids extends Application {
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
    int fuegoPosY = navePosY;
    int fuegoPosX = navePosX;
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
        Pane paneNaveFuego = new Pane(); //Pane para que el fuego se mueva con la nave
        root.getChildren().add(paneNaveFuego); //Añadir el pane al root
        paneNaveFuego.setMinWidth(anchuraPane);//establecer tamaño minimo al pane naveFuego
        paneNaveFuego.setMinHeight(5);
        //paneNaveFuego.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Scene scene = new Scene (root, ANCHOPANEL, ALTOPANEL, Color.BLACK); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Nave
        Polygon nave = new Polygon(); //crear nave
        Polygon fuego = new Polygon();  //crear fuego
        Polygon asteroid = new Polygon(); // Crear asteroid
        Circle bala = new Circle(1, 1, 1, Color.WHITE);
        nave.getPoints().addAll(new Double[]{ //tamaño nave
            59.0,alturaPane/2,
            30.0, 0.0,
            30.0, 20.0 });
        fuego.getPoints().addAll(new Double[]{ // tamaño fuego
            15.0, 10.0,
            30.0, 0.0,
            30.0, 20.0 });
        asteroid.getPoints().addAll(new Double[]{
             0.0, 0.0,
            -50.0, 40.0,
            -35.0, 100.0,
            0.0, 70.0,
            35.0, 100.0,
            50.0, 40.0,
            });
        
        
        fuego.setFill(Color.RED); //color fuego
        paneNaveFuego.getChildren().add(fuego); //añadir al pane
        paneNaveFuego.getChildren().add(nave); //añadir al pane
        root.getChildren().add(bala); //añadir la bala al root
        asteroid.setFill(Color.WHITE);
        asteroid.setLayoutX(asteroidPosX);
        asteroid.setLayoutY(asteroidPosY);
        root.getChildren().add(asteroid);
        paneNaveFuego.setLayoutX(navePosX); //Poner el pane naveFuego en el centro
        paneNaveFuego.setLayoutY(navePosY); // poner el panel naveFuego en el centro
        fuego.setVisible(false); //poner fuego en invisible 
        bala.setVisible(false);
        
        


        
        
        scene.setOnKeyPressed((KeyEvent event) -> { // switch para teclas 
            switch(event.getCode()){
                case UP:                    
                    velocidadNaveY += 3;
                    velocidadNaveX += 3;
                    fuego.setVisible(true);
//                    paneNaveFuego.setLayoutY(navePosY);
//                    paneNaveFuego.setLayoutX(navePosX);
                    break;               
                case RIGHT:
                    velocidadGiroNave = 3;                   
                    break;
                case LEFT:
                    velocidadGiroNave= -3;
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
                    velocidadGiroNave = 0;
                    fuego.setVisible(false);
                }
                
                     
        });
        
        AnimationTimer movimimientoNave; //crear la animacion
        movimimientoNave = new AnimationTimer() {       
            public void handle(long now) {               
                //Calcular angulo para direcciones
                double anguloAsteroid = asteroid.getRotate();
                anguloAsteroid += velocidadGiroAsteroid;
                asteroid.setRotate(anguloAsteroid);
                double anguloGrupo = paneNaveFuego.getRotate();              
                 direccionXNave = Math.cos(Math.toRadians(anguloGrupo));
                 System.out.println("Coseno:  " + direccionXNave);
                 direccionYNave = Math.sin(Math.toRadians(anguloGrupo));
                 System.out.println("seno:  " + direccionYNave);
                 //color aleatorio nave
                 Random colorRandom = new Random(System.currentTimeMillis());
                for (int i = 0; i<100; i++){
                    int red = colorRandom.nextInt(255);
                    int green = colorRandom.nextInt(255);
                    int blue = colorRandom.nextInt(255);
                    nave.setFill(Color.rgb(red, green, blue, .99)); //Color nave

                 }
                //movimiento nave y fuego
                navePosY +=  velocidadNaveY  * direccionYNave;
                System.out.println("navePosY: " + navePosY);
                paneNaveFuego.setLayoutY(navePosY);               
                navePosX += velocidadNaveX  * direccionXNave;
                System.out.println("navePosY: " + navePosX);
                paneNaveFuego.setLayoutX(navePosX);
                //Movimiento Asteroid
                asteroidPosY += velocidadAsteroidY;
                asteroid.setLayoutY(asteroidPosY);
                asteroidPosX += velocidadAsteroidX;
                asteroid.setLayoutX(asteroidPosX);
                //MovimientoBala
                posBalaY += velocidadBalaY * direccionBalaY;
                bala.setLayoutY(posBalaY);
                posBalaX += velocidadBalaX * direccionBalaX;
                bala.setLayoutX(posBalaX);
                //Giros
                anguloGrupo += velocidadGiroNave;
                paneNaveFuego.setRotate(anguloGrupo);                
                
//                if ((velocidadNaveY>10) || (velocidadNaveX > 10)){
//                    velocidadNaveY = 10;
//                    velocidadNaveX = 10;
//                }
                anguloGrupo %= 360;
                System.out.println("angulo: "+ anguloGrupo);
                // calcular resto del angulo
                
                // al salir del screen 
                if (paneNaveFuego.getLayoutY() > 600){ //nave
                    navePosY = 0;                                     
                } 
                if (asteroid.getLayoutY()>600){ //asteroide
                    asteroidPosY = 0;  
                }
                
                if (paneNaveFuego.getLayoutY() < 0){ //nave
                    navePosY = 600;
                } 
                if (asteroid.getLayoutY()<0){ //asteroide
                    asteroidPosY = 600;  
                } 
                
                if (paneNaveFuego.getLayoutX() > 800){ //nave
                    navePosX = 0;
                } 
                if (asteroid.getLayoutX()>800){ //asteroide
                    asteroidPosX = 0;  
                }
                
                if (paneNaveFuego.getLayoutX() < 0){ //nave
                    navePosX = 800;
                } 
                if (asteroid.getLayoutX()<0){ //asteroide
                    asteroidPosX = 800;  
                }
                
                
                
                
                
                
                
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
