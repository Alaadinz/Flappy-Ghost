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
public class ObstacleQuantique extends Entity {

    private int vitesseHori = 30;
    private boolean dejaDonnee = false;
    private double d;
    private double k;

    public ObstacleQuantique(double x, double y, double r, int indexImg) {
        super(x, y, r);

        this.d = y;
        this.k = x;
    }

    public void update(double dt) {
        x += -dt * vitesseHori;
    }

    ;
      public void update() {

        new Thread() {
            public void run() {

                double d = y, k = x;

                Math.random();
                double t = Math.random();
                t *= Math.floor(Math.random() * 2) == 1 ? 1 : -1;
                y = d + (Math.sin(t) * 60);
                x += -1.5;

                t = Math.random();
                t *= Math.floor(Math.random() * 2) == 1 ? 1 : -1;
                x = k + (Math.cos(t) * 60);

            }
        }.start();

        new Thread() {
            public void run() {

                x += -1.5 * 45;
           
            }
        }.start();

    }

}
