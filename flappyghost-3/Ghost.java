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
public class Ghost extends Entity{



   

    public Ghost(double x, double y, double r) {
        super(x, y, r);
      
    }

   
    public boolean intersects(Ghost other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double d2 = dx * dx + dy * dy;

        return d2 < (this.r + other.r) * (this.r + other.r);
    }

    @Override
    public void update(double dt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    

  
  

  
   


  
   

   
}
