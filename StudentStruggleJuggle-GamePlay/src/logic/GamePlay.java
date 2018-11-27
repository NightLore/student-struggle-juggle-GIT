package logic;

import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
 
public class GamePlay extends Application 
{
	// ratio of reflection velocity to initial velocity (the circles will bounce a little less high each time unless this is 1.0)
	double energyLossRatio = 0.9; 
	
	// frametime keeps track of the time between draw calls (basically how long has it been since I drew the last frame)
	long frametime_nanoSeconds = 0;
	double frametime_seconds = 0;
	
	// defines the force of gravity in the gameplay scene (tweaking this a little can have a huge affect on the scene)
	double forceOfGravity = (9.8 / 1000000000l);
	
	// variables for tracking the scene dimensions
	int frameWidth = 1280;
	int frameHeight = 800;
	
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Student Struggle Juggle" );
     
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
     
        Canvas canvas = new Canvas( frameWidth, frameHeight);
     
        root.getChildren().add( canvas );
     
        // extendible list to hold juggle objects
        Vector<JuggleObject> juggleObjects = new Vector<JuggleObject>();
        
        
        JuggleObject juggle1 = new JuggleObject(100, 250, 50, 1.0, 0.0005, 0.0);
        JuggleObject juggle2 = new JuggleObject(1280 - 100, 250, 50, 1.0, -0.0005, 0.0);
        JuggleObject juggle3 = new JuggleObject(250, 250, 50, 1.0, 0.0, 0.0);
        
        juggleObjects.add(juggle1);
        juggleObjects.add(juggle2);
        juggleObjects.add(juggle3);
        
        
        theScene.setOnMouseMoved(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent userMouse)
                    {
//                        paddle.setPosX(userMouse.getX());
//                        paddle.setPosY(userMouse.getY());
                    }
                });
        
        
     
        GraphicsContext gc = canvas.getGraphicsContext2D();
     
        Image tempJuggleIamge = new Image( "https://cdn.pixabay.com/photo/2016/04/24/04/53/globe-1348777_640.png?attachment" );
     
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	// Get frametime
            	frametime_nanoSeconds = currentNanoTime - frametime_nanoSeconds;
            	frametime_seconds = (frametime_nanoSeconds / 1000000000);
            	
            	// for each juggle object update it's position and speed
            	for (int i = 0; i < juggleObjects.size(); i++)
            	{
            		//**********************************************************************************
                	// Update positions, speeds, etc... using physics
            		juggleObjects.get(i).update(frametime_seconds, forceOfGravity);
            	}
            	
            	// for each juggle object compute collisions
            	for (int i = 0; i < juggleObjects.size(); i++)
            	{
            		circleCollisions(juggleObjects, i, frametime_seconds);
            		
            		juggleObjects.get(i).checkReflection_floor(frameHeight, energyLossRatio);
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
//                gc.drawImage( tempJuggleIamge, 
//                	0,
//                	0,
//                	tempJuggleIamge.getWidth(),
//                	tempJuggleIamge.getHeight(),
//                	paddle.getPosX() - paddle.getRadius(),
//                	paddle.getPosY() - paddle.getRadius(),
//                	paddle.getRadius() * 2.0,
//                	paddle.getRadius() * 2.0 );
            	
            	//draw background color
                gc.setFill( Color.BLUE );
            }
        }.start();
     
        theStage.show();
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
