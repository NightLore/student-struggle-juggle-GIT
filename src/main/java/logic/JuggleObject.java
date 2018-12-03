package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JuggleObject
{
	// *******************************************************************************************
	// attributes
	private double radius;
	private double posX;
	private double posY;
	private double mass;
    private double speedX;
    private double speedY;
	private Image image;
	// *******************************************************************************************
	// constructors
	public JuggleObject(double posX, double posY, double radius, double mass, double speedX, double speedY,Image image)
	{
		this.posX = posX;
		this.posY = posY;
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
		speedX = speedX + (accelerationX * frametime);
		speedY = speedY + (accelerationY * frametime);
	}
	
	public void updatePosition(double frametime)
	{
		// currentPosition = initialPosition + (speed * time)
		posX = posX + (speedX * frametime);
		posY = posY + (speedY * frametime);
	}
	
	// reflect off the floor of the scene
	public boolean checkReflectionFloor(int frameHeight, double energyLossRatio)
	{
    	if ( (posY + radius >= frameHeight) && (posY - radius <= frameHeight) )
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posY = (frameHeight - radius);
    		// reflect speed, reducing it by a constant factor
    		speedY = (0.0 - Math.abs(energyLossRatio * speedY));
    		return true;
    	}
    	return false;
	}
	
	// reflect off the walls of the scene
	public boolean checkReflectionWalls(int frameWidth, double energyLossRatio)
	{
		if (posX + radius >= frameWidth)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posX = (frameWidth - radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 - Math.abs(energyLossRatio * speedX));
    		return true;
    	}
    	if (posX - radius <= 0)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posX = (0 + radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 + Math.abs(energyLossRatio * speedX));
            return true;
    	}
        return false;
	}
	
	public boolean checkCollision(JuggleObject j, double frameDiffMilliseconds)
	{
        double radius2 = j.getRadius();
        double posX2 = j.getPosX();
        double posY2 = j.getPosY();
        double mass2 = j.getMass();
        double speedX2 = j.getSpeedX();
        double speedY2 = j.getSpeedY();
        
        double radiusSumSquare = Math.pow((radius + radius2), 2.0);
        double distance = (Math.pow((posX2 - posX), 2.0) + Math.pow((posY2 - posY), 2.0));
        
        if (distance <= radiusSumSquare)
        {
            double newSpeedX1 = (speedX * (mass - mass2) + (2.0 * mass2 * speedX2)) / (mass + mass2);
            double newSpeedY1 = (speedY * (mass - mass2) + (2.0 * mass2 * speedY2)) / (mass + mass2);
            
            double newSpeedX2 = (speedX2 * (mass2 - mass) + (2.0 * mass * speedX)) / (mass + mass2);
            double newSpeedY2 = (speedY2 * (mass2 - mass) + (2.0 * mass * speedY)) / (mass + mass2);
            
            setSpeedX(newSpeedX1);
            setSpeedY(newSpeedY1);
            
            j.setSpeedX(newSpeedX2);
            j.setSpeedY(newSpeedY2);
            
            setPosX(posX + (newSpeedX1 * frameDiffMilliseconds));
            setPosY(posY + (newSpeedY1 * frameDiffMilliseconds));
            
            j.setPosX(posX2 + (newSpeedX2 * frameDiffMilliseconds));
            j.setPosY(posY2 + (newSpeedY2 * frameDiffMilliseconds));
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
            getPosX() - getRadius(),
            getPosY() - getRadius(),
            getRadius() * 2.0,
            getRadius() * 2.0 );
	}
	
	// *******************************************************************************************
	// getters & setters
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getSpeedX() {
		return speedX;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

    public Image getImage() {
        return image;
    }
}


