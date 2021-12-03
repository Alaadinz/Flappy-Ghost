package flappyghost;

import static java.awt.PageAttributes.ColorType.COLOR;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.awt.SystemColor.menu;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Ellipse;

public class FlappyGhost extends Application {

    private int score = 0;
    public static final int WIDTH = 640, HEIGHT = 440;
    Image imgBg, imgGhost;
    private Controleur controleur;
    private GraphicsContext context;
    private AnimationTimer timer;
    private Label labelScore;
    private Timer timer2;
    private Timer timer3;
    private boolean gameStopped = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
  
        primaryStage.setTitle("                                 "
                + "                                             "
                + "Flappy Ghost");

        try {
            this.imgGhost = new Image("file:ghost.png");
            this.imgBg = new Image("file:bg.png");
            primaryStage.getIcons().add(imgGhost);

        } catch (Exception e) {
            System.out.println(e);
        }
        ImageView imageView = new ImageView(imgBg);

        imageView.setFitWidth(640);
        imageView.setFitHeight(400);

        //   Pane root = new Pane();
        //  Scene scene = new Scene(root, WIDTH, HEIGHT);
        BorderPane root = new BorderPane();

        //   Canvas canvas = new Canvas(WIDTH, HEIGHT);
        HBox menu = new HBox();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
  
        Canvas canvas = new Canvas(WIDTH, HEIGHT - 40);
        root.setCenter(canvas);

        this.context = canvas.getGraphicsContext2D();
    
        context.drawImage(imgBg,  0, 0);
       
        //  menu.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(1);
        menu.setMaxHeight(40);

        Button btnPause = new Button("Pause");
        System.out.println(scene.getHeight() - canvas.getHeight());
        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(0, 2, 0, 2));
        separator1.setOrientation(Orientation.VERTICAL);
        Separator separator2 = new Separator();
        separator2.setPadding(new Insets(0, 2, 0, 2));
        separator2.setOrientation(Orientation.VERTICAL);
        CheckBox cb = new CheckBox("Mode debug");
        cb.setPadding(new Insets(5, 5, 5, 5));
        
        labelScore = new Label("Score: " + score);
        labelScore.setPadding(new Insets(13, 0, 13, 0));
        labelScore.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        cb.setIndeterminate(false);
     
        menu.getChildren().add(btnPause);

        menu.getChildren().add(separator1);
        menu.getChildren().add(cb);
        menu.getChildren().add(separator2);
        menu.getChildren().add(labelScore);
         
        root.setCenter(canvas);
        
        root.setBottom(menu);

        controleur = new Controleur(this);
        
        timer2 = new Timer();
         timer3 = new Timer();
        
        btnPause.setOnAction(event -> {
    gameStopped = !gameStopped;
      if(gameStopped)
          btnPause.setText("Resume");
      else btnPause.setText("Pause");
        });
        
        cb.setOnAction(event -> {
        controleur.setModeDebug();
        
        });
        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.SPACE) {

                controleur.setVitesseY(-300);

         

            } else if (event.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }

        });
        /* Après l’exécution de la fonction, le
focus va automatiquement au canvas */
        Platform.runLater(() -> {
            canvas.requestFocus();
        });
        /* Lorsqu’on clique ailleurs sur la scène,
le focus retourne sur le canvas */
        scene.setOnMouseClicked((event) -> {
            canvas.requestFocus();
        });

     
     
//---------------------------------------------------------------------------------------
         timer = new AnimationTimer() {
            private long lastTime = 0;
            private double w = 60, h = 60;
            private double firstTime=0;
         
           

            @Override
            public void handle(long now) {
               
            
                   if(lastTime == 0)
                       firstTime = now * 1e-9;

                if (lastTime == 0 || gameStopped) {
                    lastTime = now;
                    return;
                }
                
                if(!gameStopped){
                double deltaTime = (now - lastTime) * 1e-9;
                  
                if((now*1e-9-firstTime)%3==0.0)
                    System.out.println("hello");
                     
              
               
               
                canvas.getGraphicsContext2D().clearRect(WIDTH, HEIGHT, w, h);
                controleur.update(deltaTime);
                controleur.verifieDepacement(deltaTime);
                controleur.verifieCollision();
                 
                
                lastTime = now;
            }}

        };


 timer2.schedule(new TimerTask() {
    @Override
    public void run() {
         if(!gameStopped){
        // Function runs every MINUTES minutes.
      
       controleur.addObstactle();
        controleur.supprimerObstactles();
      // If the function you wanted was static
         }
    }
 }, 3000, 3000);
 
 timer3.schedule(new TimerTask() {
    @Override
    public void run() {
          if(!gameStopped){
      controleur.updateObstacles();
    }}
 }, 0, 200);
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void dessineOvale(double x, double y, double w, double h, char couleur) {
      
        switch (couleur){
            case 'n':
              context.setFill(Color.BLACK);
       context.fillOval(x - w / 2, y - h / 2, w, h);
     
        break;
            case 'r':
          context.setFill(Color.RED);
            context.fillOval(x - w / 2, y - h / 2, w, h);
            break;
            case 'j':
                context.setFill(Color.YELLOW);
            context.fillOval(x - w / 2, y - h / 2, w, h);
         
        }
        
    
     
        
    }

    public void dessineBackGround(double coordXBg) {
        context.drawImage(imgBg, 0, 0);
        context.drawImage(imgBg, coordXBg + WIDTH, 0);
        context.drawImage(imgBg, coordXBg, 0);
    }
    
    public void setScoreLabel(int score){
    labelScore.setText("Score: " + score);

    }
    
    
   
    public static void main(String[] args) {
   
        launch(args);
     
      
      }
    
}
