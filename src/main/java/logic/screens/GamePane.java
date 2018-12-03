package logic.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private static final int frameWidth = 1366;
	private static final int frameHeight = 768;
	
	// frametime keeps track of the time between draw calls (basically how long has it been since I drew the last frame)
	private long frametime_nanoSeconds = 0;
	private double frametime_milliseconds = 0;
	
	// counts time passed since newest juggle item was created
	private double juggleSpawnTimer;

    // extendible list to hold juggle objects
    private List<JuggleObject> juggleObjects;
    
    private Random random = new Random();
 
	
	// paddle position
	private double paddlePosX;
	private double paddleRadius= 250;
	
	// max number of juggle items allowed
	private static final int maxJuggleObjectCount = 6;
	
	private AnimationTimer gameLoop;
    private Image tempPaddleImage = new Image( "http://www.clker.com/cliparts/x/J/K/A/R/K/paddle-light-red.svg.hi.png" );

	public GamePane(ScreenManager screens) {
		super(screens);
		Canvas canvas = new Canvas(frameWidth, frameHeight);
        getChildren().add( canvas );
        
        gameLoop = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	// Get frametime
                long frameDiff = currentNanoTime - frametime_nanoSeconds;
            	frametime_milliseconds = (frameDiff / 1000);
            	frametime_nanoSeconds = currentNanoTime;
            	
            	// update spawn timer
            	juggleSpawnTimer += frametime_milliseconds;
            	
            	updateAll();
            	checkCollisions();
            	
            	drawCanvas(canvas.getGraphicsContext2D());
            }
        };
	}

	@Override
	public void onShow(ScreenType prevScreen) {
        juggleObjects = new ArrayList<JuggleObject>();
        frametime_nanoSeconds = 0;
        frametime_milliseconds = 0;
        juggleSpawnTimer = 0.0;
        gameLoop.start();
	}

	@Override
    public void handle(MouseEvent userMouse)
    {
    	paddlePosX = userMouse.getX();         
    	
    	// check if enough time has passed or if the max item count is reached
    	if ( (juggleSpawnTimer > 1500000.0) && (juggleObjects.size() < maxJuggleObjectCount && ScreenManager.getThemeManager().getActiveTheme().hasNextImage()))
    	{
    		juggleObjects.add(createRandomJuggleObject());
    		juggleSpawnTimer = 0.0;
    	}
    }
	
	public void updateAll()
	{
        // for each juggle object update it's position and speed
        for (int i = 0; i < juggleObjects.size(); i++)
        {
            //**********************************************************************************
            // Update positions, speeds, etc... using physics
            juggleObjects.get(i).update(frametime_milliseconds, forceOfGravity);
            
            if (juggleObjects.get(i).getPosY() > (frameHeight * 2))
            {
				ScreenManager.getThemeManager().getActiveTheme().resetGameObject(juggleObjects.get(i).getImage());
				juggleObjects.remove(i);
            }
        }
	}
	
	public void checkCollisions()
	{
        // for each juggle object compute collisions
        for (int i = 0; i < juggleObjects.size(); i++)
        {
            circleCollisions(juggleObjects, i, frametime_milliseconds);
            paddleCollisions(juggleObjects.get(i), paddlePosX, paddleRadius);
//          juggleObjects.get(i).checkReflection_floor(frameHeight, energyLossRatio);
            juggleObjects.get(i).checkReflection_walls(frameWidth, energyLossRatio);
        }
	}
	
	public void drawCanvas(GraphicsContext gc)
	{
        // Clear the canvas
        gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
        gc.fillRect(0,0, frameWidth, frameHeight);
        
        // for each juggle object draw
        for (JuggleObject j : juggleObjects)
        {
            // Draw juggle object
            gc.drawImage( j.getImage(),
                0,
                0,
					j.getImage().getWidth(),
					j.getImage().getHeight(),
                j.getPosX() - j.getRadius(),
                j.getPosY() - j.getRadius(),
                j.getRadius() * 2.0,
                j.getRadius() * 2.0 );
        }
        
        // Draw paddle object
        gc.drawImage( tempPaddleImage, 
            0,
            0,
            tempPaddleImage.getWidth(),
            tempPaddleImage.getHeight(),
            paddlePosX - paddleRadius,
            frameHeight - 5,
            paddleRadius * 2.0,
            10 );
        
        //draw background color
        gc.setFill( Color.BLUE );
	}
	
	public JuggleObject createRandomJuggleObject()
	{
        double randPosX, randPosY, randRadius, randMass, randSpeedX, randSpeedY;
        
        randPosX = ( random.nextDouble() * (frameWidth/2.0) + (frameWidth/4.0) );
        randPosY = ( random.nextDouble() * (frameHeight/2.0) + (frameHeight/4.0) );
        randRadius = ( random.nextDouble() * (75.0) + (25.0) );
        randMass = ( Math.PI * Math.pow(randRadius, 2.0) );
        randSpeedX = ( random.nextDouble() * (0.001) - (0.001/2.0) );
        randSpeedY = ( random.nextDouble() * (0.001) - (0.001/2.0) );
        
        return new JuggleObject(randPosX, randPosY, randRadius, randMass, randSpeedX, randSpeedY,ScreenManager.getThemeManager().getActiveTheme().getNextObject());
	}
    
    public void paddleCollisions(JuggleObject currentJuggleObject, double paddlePosX, double paddleRadius)
    {
    	if ( (currentJuggleObject.getPosX() >= (paddlePosX - paddleRadius)) && (currentJuggleObject.getPosX() <= (paddlePosX + paddleRadius)) )
    	{
    		currentJuggleObject.checkReflection_floor(frameHeight, energyLossRatio);
    	}
    }
    
    public void circleCollisions(List<JuggleObject> juggleObjects, int currentIndex, double frametime_seconds)
    {
    	double radius1, radius2, posX1, posX2, posY1, posY2;
    	double mass1, mass2, speedX1, speedX2, speedY1, speedY2, newSpeedX1, newSpeedX2, newSpeedY1, newSpeedY2;
    	double radiusSumSquare, distance;
    	
    	JuggleObject current = juggleObjects.get(currentIndex); 
    	radius1 = current.getRadius();
    	posX1 = current.getPosX();
    	posY1 = current.getPosY();
    	mass1 = current.getMass();
    	speedX1 = current.getSpeedX();
    	speedY1 = current.getSpeedY();
    	
    	for (int i = 0; i < juggleObjects.size(); i++)
    	{
    	    JuggleObject object = juggleObjects.get(i);
    		// skip check for collisions with ourself
    		if (i != currentIndex)
    		{
    			radius2 = object.getRadius();
    	    	posX2 = object.getPosX();
    	    	posY2 = object.getPosY();
    	    	mass2 = object.getMass();
    	    	speedX2 = object.getSpeedX();
    	    	speedY2 = object.getSpeedY();
    	    	
    	    	radiusSumSquare = Math.pow((radius1 + radius2), 2.0);
    	    	distance = (Math.pow((posX2 - posX1), 2.0) + Math.pow((posY2 - posY1), 2.0));
    	    	
    	    	if (distance <= radiusSumSquare)
    	    	{
    	    		newSpeedX1 = (speedX1 * (mass1 - mass2) + (2.0 * mass2 * speedX2)) / (mass1 + mass2);
    	    		newSpeedY1 = (speedY1 * (mass1 - mass2) + (2.0 * mass2 * speedY2)) / (mass1 + mass2);
    				
    	    		newSpeedX2 = (speedX2 * (mass2 - mass1) + (2.0 * mass1 * speedX1)) / (mass1 + mass2);
    	    		newSpeedY2 = (speedY2 * (mass2 - mass1) + (2.0 * mass1 * speedY1)) / (mass1 + mass2);
    				
    	    		current.setSpeedX(newSpeedX1);
    	    		current.setSpeedY(newSpeedY1);
    	    		
    	    		object.setSpeedX(newSpeedX2);
    	    		object.setSpeedY(newSpeedY2);
    	    		
    	    		current.setPosX(posX1 + (newSpeedX1 * frametime_seconds));
    	    		current.setPosY(posY1 + (newSpeedY1 * frametime_seconds));
    	    		
    	    		object.setPosX(posX2 + (newSpeedX2 * frametime_seconds));
    	    		object.setPosY(posY2 + (newSpeedY2 * frametime_seconds));
    	    	}
    		}
    	}
    }
}
