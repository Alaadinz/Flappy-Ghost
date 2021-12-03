/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyghost;

/**
 *
 * @author jmc
 */

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

    protected double x, y;
    protected double r;
    private boolean dejaDepacer= false;
    

    public Entity(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;

       
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public  double getW(){
     return 2*r;
    };

    public double getH(){
        return 2*r;
    };

   
 public void setPosition(double x, double y){
   this.x = x;
   this.y = y;
   }
   
public double getRayon(){ return r;}

 

    /**
     * Met à jour la position et la vitesse de la balle
     *
     * @param dt Temps écoulé depuis le dernier update() en secondes
     */
 

   public boolean getDepacer(){
   return dejaDepacer;
   }
   public void setDepacer(boolean depacer){
       dejaDepacer = depacer;
   }
  
    public abstract void update(double dt);
 
 public static boolean intersects(Entity entity1,Entity entity2) {
        double dx = entity1.getX() - entity2.getX();
        double dy = entity1.getY() - entity2.getY();
        double d2 = dx * dx + dy * dy;

        return d2 < (entity1.getRayon() + entity2.getRayon()) *
                (entity1.getRayon() + entity2.getRayon());
    }
}
