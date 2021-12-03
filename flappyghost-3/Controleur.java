/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyghost;

import static flappyghost.FlappyGhost.HEIGHT;
import static flappyghost.FlappyGhost.WIDTH;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author jmc
 */
public class Controleur {

    private ArrayList<Entity> listObstactles = new ArrayList<>();
    private Ghost ghost;
    private FlappyGhost vue;
    private long lastTime = 0;
    private double x = WIDTH / 2, y = HEIGHT / 1.5;
    private double vx = 0, vy = 50;
    private double w = 60, h = 60;
    private double ax = 0, ay = 500;
 
    private double coorXBg = 0;
    private double vitesseHori = 120;
    private int score = 0;
    double newY;
    private boolean modeDebug =false;

    public Controleur(FlappyGhost vue){
        this.vue = vue;
        this.ghost = new Ghost(WIDTH / 2, HEIGHT / 1.5, 30);

    }

   private void restart() {
        x = WIDTH / 2;
        y = HEIGHT / 1.5;
        vx = 0;
        vy = 50;
        w = 60;
        h = 60;
        ax = 0;
        ay = 500;
        
        coorXBg = 0;
        vitesseHori = 120;
        listObstactles.clear();
        this.ghost = new Ghost(WIDTH / 2, HEIGHT / 1.5, 30);
        score= 0;
        vue.setScoreLabel(score);
    }
    
    public void verifieDepacement(double dt){
    for(Entity obsta : listObstactles){
        if(obsta.getX() + obsta.getW() / 2 < WIDTH / 2 && !obsta.getDepacer()){
            obsta.setDepacer(true);
            score += 5;
            vue.setScoreLabel(score);
            if(score%10==0){
               ay += 15;
               System.out.print(ay);
             
               vitesseHori +=15;
            }
        }
    
    }
    
    }
    
    public void setModeDebug(){
        
        this.modeDebug = !modeDebug; 
        System.out.print(modeDebug);
    }
    public void verifieCollision(){
        if(!listObstactles.isEmpty())
    for(Entity obsta : listObstactles){
        
        if(Entity.intersects(ghost,obsta)){
            if(!modeDebug){
            restart();
              break;}
            else{ this.vue.dessineOvale(obsta.getX(), obsta.getY(), obsta.getW(), obsta.getH(),'r');
            this.vue.dessineOvale(ghost.getX(), ghost.getY(), w, h, 'n');
            }
        }
       
        
    }}
// jai oublier d'integerer la logique du fantome dans la classe Ghost. toute les autre object sont dans leurs classe appropier
    public void update(double dt) {

        updateObstacles(dt);
        
        vx += dt * ax;

        vy += dt * ay;

        vy = (vy > 300) ? 300 : vy;

        newY = y + dt * vy;

        if (newY + h / 2 > HEIGHT - 40 || newY - h / 2 < 0) {

            vy = (vy < 300) ? -300 : vy;
            vy = (vy > 300) ? 300 : vy;
        
            vy *= -1;

        } else {

            y = newY;
            ghost.setPosition(x, y);
        }

        coorXBg += -dt * vitesseHori;
      
        if (coorXBg < -WIDTH) {

            coorXBg = 0;
        }

        this.vue.dessineBackGround(coorXBg);
        updateObstacles(dt);
        if(modeDebug)
        this.vue.dessineOvale(ghost.getX(), ghost.getY(), w, h, 'n');
        else this.vue.dessineOvale(ghost.getX(), ghost.getY(), w, h, 'n');
    }
// update tout les obstacles 
    private void updateObstacles(double dt) {
        if (!listObstactles.isEmpty()) {
            for (Entity obsta : listObstactles) {

                obsta.update(dt);
                if(modeDebug)
                this.vue.dessineOvale(obsta.getX(), obsta.getY(), obsta.getW(), obsta.getH(),'j');
                else this.vue.dessineOvale(obsta.getX(), obsta.getY(), obsta.getW(), obsta.getH(),'j');

            }
        }
    }
// update(sans paramettre) les object quantique a chaque 0.2 secondes
    public void updateObstacles() {

        if (!listObstactles.isEmpty()) {
            
            
            for (Entity obsta : listObstactles) {
                if (obsta instanceof ObstacleQuantique) {
                    ((ObstacleQuantique) obsta).update();

                }
            }
        }
    }

    public void setVitesseY(double vy) {
        this.vy = vy;
    }

    public void setAccY(double ay) {
        this.ay = ay;
    }

    public void addObstactle() {
        Entity obstactle = null;
        Random r = new Random();
        double aparition = 20 + r.nextInt((HEIGHT - 20) - 20 + 1);
        double rayonRand = 10 + r.nextInt((45 - 10) - 10 + 1);
        int valeurRandom = (int) Math.ceil(Math.random() * 3);
        int indexImg = (int) Math.ceil(Math.random() * 27);
  
        switch (valeurRandom) {
            case 1: {
                obstactle = new ObstacleSimple(WIDTH+20, aparition, rayonRand, indexImg);
            }
            break;
            case 2: {
                obstactle = new ObstacleSinus(WIDTH+20, aparition, rayonRand, indexImg);
            }
            break;
            case 3: {
                obstactle = new ObstacleQuantique(WIDTH+20, aparition, rayonRand, indexImg);
            }
        }
        listObstactles.add(obstactle);
    }

    public void supprimerObstactles() {
     
        if (!listObstactles.isEmpty()) {
            for (int i = 0; i < listObstactles.size(); i++) {
                if (listObstactles.get(i).getX() + listObstactles.get(i).getW() / 2 <= 0.0) {
                    listObstactles.remove(i);
                }
                
            }
        }
    }

  

}
