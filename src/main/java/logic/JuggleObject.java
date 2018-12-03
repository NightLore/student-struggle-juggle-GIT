package logic;

import javafx.scene.image.Image;

public class JuggleObject
{
	// *******************************************************************************************
	// attributes
	private double radius, posX, posY;
	private double mass, speedX, speedY;
	private Image img;
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
		this.img = image;
	}
	// *******************************************************************************************
	// methods
	public void update(double frametime_seconds, double forceOfGravity)
	{
		double accelerationY = (forceOfGravity / mass);
    	double accelerationX = 0.0;
    	
    	updateSpeed(accelerationX, accelerationY, frametime_seconds);
    	updatePosition(frametime_seconds);
    	
	}
	public void updateSpeed(double accelerationX, double accelerationY, double frametime)
	{
		// currentSpeed = initialSpeed + (acceleration * time)
		speedX = speedX + (accelerationX * frametime);
		speedY = speedY + (accelerationY * frametime);
	}
	
	public void updatePosition(double frametime)
	{
        System.out.println(speedX + "," + speedY + "," + frametime);
		// currentPosition = initialPosition + (speed * time)
		posX = posX + (speedX * frametime);
		posY = posY + (speedY * frametime);
	}
	
	// reflect off the floor of the scene
	public void checkReflection_floor(int frameHeight, double energyLossRatio)
	{
    	if ( (posY + radius >= frameHeight) && (posY - radius <= frameHeight) )
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posY = (frameHeight - radius);
    		// reflect speed, reducing it by a constant factor
    		speedY = (0.0 - Math.abs(energyLossRatio * speedY));
    	}
	}
	
	// reflect off the walls of the scene
	public void checkReflection_walls(int frameWidth, double energyLossRatio)
	{
		if (posX + radius >= frameWidth)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posX = (frameWidth - radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 - Math.abs(energyLossRatio * speedX));
    	}
    	if (posX - radius <= 0)
    	{
    		// ensure the object is not trapped in or past a reflection surface
    		posX = (0 + radius);
    		// reflect speed, reducing it by a constant factor
    		speedX = (0.0 + Math.abs(energyLossRatio * speedX));
    	}
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
		return img;
	}
	// *******************************************************************************************
}


