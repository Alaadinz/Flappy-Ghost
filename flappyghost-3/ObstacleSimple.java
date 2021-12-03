/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyghost;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author jmc
 */
public class ObstacleSimple extends Entity {
    private int vitesseHori = 60;
    private boolean dejaDepacer = false;
    public ObstacleSimple(double x, double y,double r, int indexImg){
    super(x,y, r);
    }
    public void update(double dt) {
        x += -dt * vitesseHori;
    }

   public boolean getDepacer(){
   return dejaDepacer;
   }
   public void setDepacer(boolean depacer){
       dejaDepacer = depacer;
   }
  
     

 


   
    
}
