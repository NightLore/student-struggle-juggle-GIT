package logic.screens;

import java.util.Random;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.JuggleObject;
import logic.ScreenManager;
import logic.ScreenType;

public class GamePane extends UpdatablePane implements EventHandler<MouseEvent> {
	// ratio of reflection velocity to initial velocity (the circles will bounce a little less high each time unless this is 1.0)
	private static final double energyLossRatio = 1.0; 
	
	// defines the force of gravity in the gameplay scene (tweaking this a little can have a huge affect on the scene)
	private static final double forceOfGravity = (9.8 / 1000000l);
	
	// variables for tracking the scene dimensions
	private static final int frameWidth = 1280;
	private static final int frameHeight = 800;
	
	// frametime keeps track of the time between draw calls (basically how long has it been since I drew the last frame)
	private long frametime_nanoSeconds = 0;
	private double frametime_seconds = 0;
	
	// counts time passed since newest juggle item was created
	private double juggleSpawnTimer = 0.0;
    
    private Canvas canvas;

    // extendible list to hold juggle objects
    private Vector<JuggleObject> juggleObjects = new Vector<JuggleObject>();
    
    private Random randomNumberGenerator = new Random();
    private GraphicsContext gc;
    
    private Image tempJuggleIamge = new Image( "https://cdn.pixabay.com/photo/2016/04/24/04/53/globe-1348777_640.png?attachment" );
    private Image tempPaddleIamge = new Image( "http://www.clker.com/cliparts/x/J/K/A/R/K/paddle-light-red.svg.hi.png" );
 
	
	// paddle position
	double paddlePosX;
	double paddleRadius= 250;
	
	// max number of juggle items allowed
	private static final int maxJuggleObjectCount = 6;

	public GamePane(ScreenManager screens) {
		super(screens);
		canvas = new Canvas(frameWidth, frameHeight);
		
        getChildren().add( canvas );
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		gc = canvas.getGraphicsContext2D();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	// Get frametime
            	frametime_nanoSeconds = currentNanoTime - frametime_nanoSeconds;
            	frametime_seconds = (frametime_nanoSeconds / 1000000000);
            	
            	// update spawn timer
            	juggleSpawnTimer += frametime_seconds;
            	
            	// for each juggle object update it's position and speed
            	for (int i = 0; i < juggleObjects.size(); i++)
            	{
            		//**********************************************************************************
                	// Update positions, speeds, etc... using physics
            		juggleObjects.get(i).update(frametime_seconds, forceOfGravity);
            		
            		if (juggleObjects.get(i).getPosY() > (frameHeight * 2))
            		{
            			juggleObjects.remove(i);
            		}
            	}
            	
            	// for each juggle object compute collisions
            	for (int i = 0; i < juggleObjects.size(); i++)
            	{
            		circleCollisions(juggleObjects, i, frametime_seconds);
            		paddleCollisions(juggleObjects.get(i), paddlePosX, paddleRadius);
//            		juggleObjects.get(i).checkReflection_floor(frameHeight, energyLossRatio);
            		juggleObjects.get(i).checkReflection_walls(frameWidth, energyLossRatio);
            	}
            	
            	//**********************************************************************************
                // Clear the canvas
                gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
                gc.fillRect(0,0, frameWidth, frameHeight);
                
            	// for each juggle object draw
            	for (int i = 0; i < juggleObjects.size(); i++)
            	{
                    // Draw juggle object
                    gc.drawImage( tempJuggleIamge, 
                    	0,
                    	0,
                    	tempJuggleIamge.getWidth(),
                    	tempJuggleIamge.getHeight(),
                    	juggleObjects.get(i).getPosX() - juggleObjects.get(i).getRadius(),
                    	juggleObjects.get(i).getPosY() - juggleObjects.get(i).getRadius(),
                    	juggleObjects.get(i).getRadius() * 2.0,
                    	juggleObjects.get(i).getRadius() * 2.0 );
            	}
            	
            	// Draw paddle object
                gc.drawImage( tempPaddleIamge, 
                	0,
                	0,
                	tempPaddleIamge.getWidth(),
                	tempPaddleIamge.getHeight(),
                	paddlePosX - paddleRadius,
                	frameHeight - 5,
                	paddleRadius * 2.0,
                	10 );
            	
            	//draw background color
                gc.setFill( Color.BLUE );
            }
        }.start();
	}

	@Override
    public void handle(MouseEvent userMouse)
    {
    	paddlePosX = userMouse.getX();         
    	
    	// check if enough time has passed or if the max item count is reached
    	if ( (juggleSpawnTimer > 1500000.0) && (juggleObjects.size() < maxJuggleObjectCount) )
    	{
    		double randPosX, randPosY, randRadius, randMass, randSpeedX, randSpeedY;
    		
    		randPosX = ( randomNumberGenerator.nextDouble() * (frameWidth/2.0) + (frameWidth/4.0) );
    		randPosY = ( randomNumberGenerator.nextDouble() * (frameHeight/2.0) + (frameHeight/4.0) );
    		randRadius = ( randomNumberGenerator.nextDouble() * (75.0) + (25.0) );
    		randMass = ( Math.PI * Math.pow(randRadius, 2.0) );
    		randSpeedX = ( randomNumberGenerator.nextDouble() * (0.001) - (0.001/2.0) );
    		randSpeedY = ( randomNumberGenerator.nextDouble() * (0.001) - (0.001/2.0) );
    		
    		juggleObjects.add(new JuggleObject(randPosX, randPosY, randRadius, randMass, randSpeedX, randSpeedY));
    		juggleSpawnTimer = 0.0;
    	}
    }
    
    public void paddleCollisions(JuggleObject currentJuggleObject, double paddlePosX, double paddleRadius)
    {
    	if ( (currentJuggleObject.getPosX() >= (paddlePosX - paddleRadius)) && (currentJuggleObject.getPosX() <= (paddlePosX + paddleRadius)) )
    	{
    		currentJuggleObject.checkReflection_floor(frameHeight, energyLossRatio);
    	}
    }
    
    public void circleCollisions(Vector<JuggleObject> juggleObjects, int currentIndex, double frametime_seconds)
    {
    	double radius1, radius2, posX1, posX2, posY1, posY2;
    	double mass1, mass2, speedX1, speedX2, speedY1, speedY2, newSpeedX1, newSpeedX2, newSpeedY1, newSpeedY2;
    	double radiusSumSquare, distance;
    	
    	radius1 = juggleObjects.get(currentIndex).getRadius();
    	posX1 = juggleObjects.get(currentIndex).getPosX();
    	posY1 = juggleObjects.get(currentIndex).getPosY();
    	mass1 = juggleObjects.get(currentIndex).getMass();
    	speedX1 = juggleObjects.get(currentIndex).getSpeedX();
    	speedY1 = juggleObjects.get(currentIndex).getSpeedY();
    	
    	for (int i = 0; i < juggleObjects.size(); i++)
    	{
    		// skip check for collisions with ourself
    		if (i != currentIndex)
    		{
    			radius2 = juggleObjects.get(i).getRadius();
    	    	posX2 = juggleObjects.get(i).getPosX();
    	    	posY2 = juggleObjects.get(i).getPosY();
    	    	mass2 = juggleObjects.get(i).getMass();
    	    	speedX2 = juggleObjects.get(i).getSpeedX();
    	    	speedY2 = juggleObjects.get(i).getSpeedY();
    	    	
    	    	radiusSumSquare = Math.pow((radius1 + radius2), 2.0);
    	    	distance = (Math.pow((posX2 - posX1), 2.0) + Math.pow((posY2 - posY1), 2.0));
    	    	
    	    	if (distance <= radiusSumSquare)
    	    	{
    	    		newSpeedX1 = (speedX1 * (mass1 - mass2) + (2.0 * mass2 * speedX2)) / (mass1 + mass2);
    	    		newSpeedY1 = (speedY1 * (mass1 - mass2) + (2.0 * mass2 * speedY2)) / (mass1 + mass2);
    				
    	    		newSpeedX2 = (speedX2 * (mass2 - mass1) + (2.0 * mass1 * speedX1)) / (mass1 + mass2);
    	    		newSpeedY2 = (speedY2 * (mass2 - mass1) + (2.0 * mass1 * speedY1)) / (mass1 + mass2);
    				
    	    		juggleObjects.get(currentIndex).setSpeedX(newSpeedX1);
    	    		juggleObjects.get(currentIndex).setSpeedY(newSpeedY1);
    	    		
    	    		juggleObjects.get(i).setSpeedX(newSpeedX2);
    	    		juggleObjects.get(i).setSpeedY(newSpeedY2);
    	    		
    	    		juggleObjects.get(currentIndex).setPosX(posX1 + (newSpeedX1 * frametime_seconds));
    	    		juggleObjects.get(currentIndex).setPosY(posY1 + (newSpeedY1 * frametime_seconds));
    	    		
    	    		juggleObjects.get(i).setPosX(posX2 + (newSpeedX2 * frametime_seconds));
    	    		juggleObjects.get(i).setPosY(posY2 + (newSpeedY2 * frametime_seconds));
    	    	}
    		}
    	}
    }
}
