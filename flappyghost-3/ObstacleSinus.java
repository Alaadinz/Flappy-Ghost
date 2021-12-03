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
public class ObstacleSinus extends Entity {

    private int vitesseHori = 60;
    private double t;
    private double d;

    public ObstacleSinus(double x, double y, double r, int indexImg) {
        super(x, y, r);
        d = y;

    }

    @Override
    public double getW() {
        return 2 * r;
    }

    @Override
    public double getH() {
        return 2 * r;
    }

    public void update(double dt) {
        x += -dt * vitesseHori;
        t += 0.06;
        y = d + (Math.sin(t) * 50);
        

    }

  

}
