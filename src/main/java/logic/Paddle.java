package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.themes.ThemeManager;

public class Paddle {
    
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
        Image image = ThemeManager.DEFAULT_PADDLE_IMAGE;
        // Draw paddle object
        gc.drawImage( image, 
            0,
            0,
            image.getWidth(),
            image.getHeight(),
            x - radius,
            y,
            radius * 2.0,
            h );
    }
    
    public boolean collidesWith(JuggleObject j, int frameHeight, double energyLossRatio)
    {
        return j.getPosX() >= (x - radius) && j.getPosX() <= (x + radius) && j.checkReflectionFloor(frameHeight, energyLossRatio);
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

}
