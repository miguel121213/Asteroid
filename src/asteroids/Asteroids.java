/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;



import javafx.scene.shape.Polygon;
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
public class Asteroids extends Application {
    final int ALTOPANEL = 600;
    final int ANCHOPANEL = 800;
    int velocidadNaveY = 0;
    int velocidadNaveX = 0;
    int velocidadFuegoY = 0;
    int velocidadFuegoX = 0;
    int navePosY = ALTOPANEL/2;
    int navePosX = ANCHOPANEL /2;
    int fuegoPosY = navePosY;
    int fuegoPosX = navePosX;
    double velocidadGiroNave = 0;
    double velocidadGiroFuego = 0;

    double direccionXNave;
    double direccionYNave;
    
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane(); //Panel
        Pane paneNaveFuego = new Pane(); //Pane para que el fuego se mueva con la nave
        root.getChildren().add(paneNaveFuego); //Añadir el pane al root
        paneNaveFuego.setMinHeight(45); //establecer tamaño minimo al pane naveFuego
        
        Scene scene = new Scene (root, ANCHOPANEL, ALTOPANEL, Color.BLACK); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Nave
        Polygon nave = new Polygon(); //crear nave
        Polygon fuego = new Polygon();  //crear fuego
        nave.getPoints().addAll(new Double[]{ //tamaño nave
            0.0, -20.0,
            -10.0, 10.0,
            10.0, 10.0 });
        fuego.getPoints().addAll(new Double[]{ // tamaño fuego
            0.0, 25.0,
            -10.0, 10.0,
            10.0, 10.0 });
        nave.setFill(Color.WHITE); //Color nave
        fuego.setFill(Color.RED); //color fuego
        paneNaveFuego.getChildren().add(fuego); //añadir al pane
        paneNaveFuego.getChildren().add(nave); //añadir al pane
        paneNaveFuego.setLayoutX(navePosX); //Poner el pane naveFuego en el centro
        paneNaveFuego.setLayoutY(navePosY); // poner el panel naveFuego en el centro
        fuego.setVisible(false); //poner fuego en invisible 
        

        
        
        scene.setOnKeyPressed((KeyEvent event) -> { // switch para teclas 
            switch(event.getCode()){
                case UP:
                    velocidadNaveY = 3;
                    velocidadNaveX = 3;
                    fuego.setVisible(true);
                    paneNaveFuego.setLayoutY(navePosY);
                    paneNaveFuego.setLayoutY(navePosX);
                    break;

                    
                case RIGHT:
                    direccionXNave = -1;
                    velocidadGiroNave = 3;
                    break;
                case LEFT:
                    direccionXNave = 1;
                    velocidadGiroNave= -3;
                    break;
            }
        
        
           
        });

        //soltar tecas
        scene.setOnKeyReleased((KeyEvent event) -> {  //Cuando sueltas una tecla pulsada         
                if(event.getCode() == KeyCode.UP ||event.getCode() == KeyCode.LEFT ||event.getCode() == KeyCode.RIGHT){
//                    velocidadFuegoY = 0;
                    velocidadGiroNave = 0;
                    velocidadGiroFuego = 0;
                    fuego.setVisible(false);
                }
                
                     
        });
        
        AnimationTimer movimimientoNave; //crear la animacion
        movimimientoNave = new AnimationTimer() {       
            public void handle(long now) {
                double posNaveFuegoY = paneNaveFuego.getLayoutY();
                double posNaveFuegoX = paneNaveFuego.getLayoutX();
                
                //Calcular angulo para direcciones
                double anguloGrupo = paneNaveFuego.getRotate();
                
                if (anguloGrupo < 0){
                    anguloGrupo += 360;                   
                }
                if (anguloGrupo > 315 || anguloGrupo < 45){
                    direccionYNave = -1;
                    direccionXNave = 0;
                    anguloGrupo %= 360;
                }
                if (anguloGrupo > 45 && anguloGrupo < 135){
                    direccionYNave = 0;
                    direccionXNave = 1;
                }
                if (anguloGrupo > 135 && anguloGrupo < 225){
                    direccionYNave = 1;
                    direccionXNave = 0;
                }
                if (anguloGrupo > 225 && anguloGrupo < 315){
                    direccionYNave= 0;
                    direccionXNave= 0;
                    
                }            
                //movimiento nave y fuego
                navePosY +=  velocidadNaveY * direccionYNave;
                paneNaveFuego.setLayoutY(navePosY);               
                navePosX += velocidadNaveX * direccionXNave;
                paneNaveFuego.setLayoutX(navePosX);
                //Giros
                anguloGrupo += velocidadGiroNave;
                paneNaveFuego.setRotate(anguloGrupo);
                
                
                // calcular resto del angulo
                anguloGrupo %= 360;
                
//                if (posNaveFuegoY > ALTOPANEL){
//                    paneNaveFuego.setLayoutY(1);
//                } else {
//                    paneNaveFuego.setLayoutY(navePosY);
//                }
//                if (posNaveFuegoY < 0){
//                    paneNaveFuego.setLayoutY(599);
//                } else {
//                    paneNaveFuego.setLayoutY(navePosY);
//                }
                
                
                
                
                
                
                
                
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
