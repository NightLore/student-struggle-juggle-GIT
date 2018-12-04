package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JuggleObject
{
    private static final Random RANDOM = new Random();
    public static JuggleObject createRandom(int maxWidth, int maxHeight, Image image)
    {
        double randX = RANDOM.nextDouble() * (maxWidth/2.0) + (maxWidth/4.0);
        double randY = RANDOM.nextDouble() * (maxHeight/2.0) + (maxHeight/4.0);
        double randRadius = RANDOM.nextDouble() * (75.0) + (25.0);
        double randMass = Math.PI * Math.pow(randRadius, 2.0);
        double randSpeedX = RANDOM.nextDouble() * (0.001) - (0.001/2.0);
        double randSpeedY = RANDOM.nextDouble() * (0.001) - (0.001/2.0);
        
        return new JuggleObject(randX, randY, randRadius, randMass, randSpeedX, randSpeedY, image);
    }
	// *******************************************************************************************
	// attributes
	private double radius;
	private double x;
	private double y;
	private double mass;
    private double speedX;
    private double speedY;
	private Image image;
	// *******************************************************************************************
	// constructors
	public JuggleObject(double x, double y, double radius, double mass, double speedX, double speedY,Image image)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.mass = mass;
		this.speedX = speedX;
		this.speedY = speedY;
		this.image = image;
	}
	// *******************************************************************************************
	// methods
	public void update(double frameDiffMilliseconds, double forceOfGravity)
	{
		double accelerationY = (forceOfGravity / mass);
    	double accelerationX = 0.0;
    	
    	updateSpeed(accelerationX, accelerationY, frameDiffMilliseconds);
    	updatePosition(frameDiffMilliseconds);
    	
	}
	public void updateSpeed(double accelerationX, double accelerationY, double frametime)
	{
		// currentSpeed = initialSpeed + (acceleration * time)
		speedX += (accelerationX * frametime);
		speedY += (accelerationY * frametime);
	}
	
	public void updatePosition(double frametime)
	{
		// currentPosition = initialPosition + (speed * time)
		x += (speedX * frametime);
		y += (speedY * frametime);
	}
	
	// reflect off the floor of the scene
	public boolean checkReflectionFloor(int frameHeight, double energyLossRatio)
	{
    	if ( (y + radius >= frameHeight) && (y - radius <= frameHeight) )
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		y = (frameHeight - radius);
    		// reflect speed, reducing it by a constant factor
    		speedY = (0.0 - Math.abs(energyLossRatio * speedY));
    		return true;
    	}
    	return false;
	}
	
	// reflect off the walls of the scene
	public boolean checkReflectionWalls(int frameWidth, double energyLossRatio)
	{
		if (x + radius >= frameWidth)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		x = (frameWidth - radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 - Math.abs(energyLossRatio * speedX));
    		return true;
    	}
    	if (x - radius <= 0)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		x = (0 + radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 + Math.abs(energyLossRatio * speedX));
            return true;
    	}
        return false;
	}
	
	public boolean checkCollision(JuggleObject j, double frameDiffMilliseconds)
	{
        double radiusSumSquare = Math.pow((radius + j.radius), 2.0);
        double distance = (Math.pow((j.x - x), 2.0) + Math.pow((j.y - y), 2.0));
        
        if (distance <= radiusSumSquare)
        {
            double totalMass = mass + j.mass;
            double newSpeedX1 = (speedX * (mass - j.mass) + (2.0 * j.mass * j.speedX)) / totalMass;
            double newSpeedY1 = (speedY * (mass - j.mass) + (2.0 * j.mass * j.speedY)) / totalMass;
            
            double newSpeedX2 = (j.speedX * (j.mass - mass) + (2.0 * mass * speedX)) / totalMass;
            double newSpeedY2 = (j.speedY * (j.mass - mass) + (2.0 * mass * speedY)) / totalMass;
            
            speedX = newSpeedX1;
            speedY = newSpeedY1;
            
            j.speedX = newSpeedX2;
            j.speedY = newSpeedY2;
            
            updatePosition(frameDiffMilliseconds);
            j.updatePosition(frameDiffMilliseconds);
            return true;
        }
	    return false;
	}
	
	public void draw(GraphicsContext gc)
	{
        // Draw juggle object
        gc.drawImage( image,
            0,
            0,
            image.getWidth(),
            image.getHeight(),
            x - radius,
            y - radius,
            radius * 2.0,
            radius * 2.0 );
	}
	
	// *******************************************************************************************
	// getters & setters
	public double getRadius() {
		return radius;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}

    public Image getImage() {
        return image;
    }
}


