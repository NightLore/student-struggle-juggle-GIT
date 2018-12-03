package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Paddle {
    private static final Image DEFAULT_IMAGE = new Image( "http://www.clker.com/cliparts/x/J/K/A/R/K/paddle-light-red.svg.hi.png" );
    
    // paddle position
    private double x;
    private double radius;
    
    private double y;
    private double h;

    public Paddle(double radius, double y, double h) {
        this.radius = radius;
        this.y = y;
        this.h = h;
    }
    
    public void draw(GraphicsContext gc)
    {
        
        // Draw paddle object
        gc.drawImage( DEFAULT_IMAGE, 
            0,
            0,
            DEFAULT_IMAGE.getWidth(),
            DEFAULT_IMAGE.getHeight(),
            x - radius,
            y,
            radius * 2.0,
            h );
    }
    
    public boolean collidesWith(JuggleObject j, int frameHeight, double energyLossRatio)
    {
        if ( (j.getPosX() >= (x - radius)) && (j.getPosX() <= (x + radius)) )
        {
            if (j.checkReflectionFloor(frameHeight, energyLossRatio))
            {
                return true;
            }
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
