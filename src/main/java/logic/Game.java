package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
    
    // variables for tracking the scene dimensions
    public static final int FRAME_WIDTH = 1366;
    public static final int FRAME_HEIGHT = 768;
    
    // ratio of reflection velocity to initial velocity (the circles will bounce a little less high each time unless this is 1.0)
    private static final double ENERGY_LOSS_RATIO = 1.0; 
    
    // defines the force of gravity in the gameplay scene (tweaking this a little can have a huge affect on the scene)
    private static final double FORCE_OF_GRAVITY = (9.8 / 1000000l);
    
    // max number of juggle items allowed
    private static final int MAX_NUM_JUGGLE_OBJECTS = 6;
    
    private static final Random RANDOM = new Random();
    
    // frametime keeps track of the time between draw calls (basically how long has it been since I drew the last frame)
    private long frametimeNanoSeconds = 0;
    private double frameDiffMilliseconds = 0;
    // counts time passed since newest juggle item was created
    private double juggleSpawnTimer;

    private List<JuggleObject> juggleObjects;
    private Paddle paddle;

    private AnimationTimer gameLoop;

    public Game(Canvas canvas) {
        paddle = new Paddle(250, FRAME_HEIGHT - 5, 10);
        
        gameLoop = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Get frametime
                long frameDiff = currentNanoTime - frametimeNanoSeconds;
                frameDiffMilliseconds = (frameDiff / 1000.0);
                frametimeNanoSeconds = currentNanoTime;
                
                // update spawn timer
                juggleSpawnTimer += frameDiffMilliseconds;
                
                updateAll();
                checkCollisions();
                
                drawCanvas(canvas.getGraphicsContext2D());
            }
        };
    }
    
    public void reset()
    {
        juggleObjects = new ArrayList<>();
        frametimeNanoSeconds = 0;
        frameDiffMilliseconds = 0;
        juggleSpawnTimer = 0.0;
    }
    
    public void start()
    {
        gameLoop.stop();
        reset();
        gameLoop.start();
    }
    
    public void updatePaddle(double mouseX)
    {
        paddle.setPaddlePosX(mouseX);      
        
        // check if enough time has passed or if the max item count is reached
        if ( (juggleSpawnTimer > 1500000.0) && (juggleObjects.size() < MAX_NUM_JUGGLE_OBJECTS))
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
            juggleObjects.get(i).update(frameDiffMilliseconds, FORCE_OF_GRAVITY);
            
            if (juggleObjects.get(i).getPosY() > (FRAME_HEIGHT * 2))
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
            circleCollisions(juggleObjects, i, frameDiffMilliseconds);
            paddleCollisions(juggleObjects.get(i), paddle.getPaddlePosX(), paddle.getPaddleRadius());
            juggleObjects.get(i).checkReflectionWalls(FRAME_WIDTH, ENERGY_LOSS_RATIO);
        }
    }
    
    public JuggleObject createRandomJuggleObject()
    {
        double randPosX = ( RANDOM.nextDouble() * (FRAME_WIDTH/2.0) + (FRAME_WIDTH/4.0) );
        double randPosY = ( RANDOM.nextDouble() * (FRAME_HEIGHT/2.0) + (FRAME_HEIGHT/4.0) );
        double randRadius = ( RANDOM.nextDouble() * (75.0) + (25.0) );
        double randMass = ( Math.PI * Math.pow(randRadius, 2.0) );
        double randSpeedX = ( RANDOM.nextDouble() * (0.001) - (0.001/2.0) );
        double randSpeedY = ( RANDOM.nextDouble() * (0.001) - (0.001/2.0) );
        
        return new JuggleObject(randPosX, randPosY, randRadius, randMass, randSpeedX, randSpeedY,ScreenManager.getThemeManager().getActiveTheme().getNextObject());
    }
    
    public void paddleCollisions(JuggleObject currentJuggleObject, double paddlePosX, double paddleRadius)
    {
        if ( (currentJuggleObject.getPosX() >= (paddlePosX - paddleRadius)) && (currentJuggleObject.getPosX() <= (paddlePosX + paddleRadius)) )
        {
            currentJuggleObject.checkReflectionFloor(FRAME_HEIGHT, ENERGY_LOSS_RATIO);
        }
    }
    
    public void circleCollisions(List<JuggleObject> juggleObjects, int currentIndex, double frameDiffMilliseconds)
    {
        JuggleObject current = juggleObjects.get(currentIndex); 
        double radius1 = current.getRadius();
        double posX1 = current.getPosX();
        double posY1 = current.getPosY();
        double mass1 = current.getMass();
        double speedX1 = current.getSpeedX();
        double speedY1 = current.getSpeedY();
        
        for (int i = 0; i < juggleObjects.size(); i++)
        {
            JuggleObject object = juggleObjects.get(i);
            // skip check for collisions with ourself
            if (i != currentIndex)
            {
                double radius2 = object.getRadius();
                double posX2 = object.getPosX();
                double posY2 = object.getPosY();
                double mass2 = object.getMass();
                double speedX2 = object.getSpeedX();
                double speedY2 = object.getSpeedY();
                
                double radiusSumSquare = Math.pow((radius1 + radius2), 2.0);
                double distance = (Math.pow((posX2 - posX1), 2.0) + Math.pow((posY2 - posY1), 2.0));
                
                if (distance <= radiusSumSquare)
                {
                    double newSpeedX1 = (speedX1 * (mass1 - mass2) + (2.0 * mass2 * speedX2)) / (mass1 + mass2);
                    double newSpeedY1 = (speedY1 * (mass1 - mass2) + (2.0 * mass2 * speedY2)) / (mass1 + mass2);
                    
                    double newSpeedX2 = (speedX2 * (mass2 - mass1) + (2.0 * mass1 * speedX1)) / (mass1 + mass2);
                    double newSpeedY2 = (speedY2 * (mass2 - mass1) + (2.0 * mass1 * speedY1)) / (mass1 + mass2);
                    
                    current.setSpeedX(newSpeedX1);
                    current.setSpeedY(newSpeedY1);
                    
                    object.setSpeedX(newSpeedX2);
                    object.setSpeedY(newSpeedY2);
                    
                    current.setPosX(posX1 + (newSpeedX1 * frameDiffMilliseconds));
                    current.setPosY(posY1 + (newSpeedY1 * frameDiffMilliseconds));
                    
                    object.setPosX(posX2 + (newSpeedX2 * frameDiffMilliseconds));
                    object.setPosY(posY2 + (newSpeedY2 * frameDiffMilliseconds));
                }
            }
        }
    }
    
    public void drawCanvas(GraphicsContext gc)
    {
        // Clear the canvas
        gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );
        gc.fillRect(0,0, Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        
        // for each juggle object draw
        for (JuggleObject j : this.juggleObjects)
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
        
        paddle.draw(gc);
        
        //draw background color
        gc.setFill( Color.BLUE );
    }

}
