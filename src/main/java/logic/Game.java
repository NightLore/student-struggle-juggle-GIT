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
    private int score;

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
        score = 0;
    }
    
    public void start()
    {
        gameLoop.stop();
        reset();
        gameLoop.start();
    }
    
    public void updatePaddle(double mouseX)
    {
        paddle.setX(mouseX);      
        
        // check if enough time has passed or if the max item count is reached
        if ( (juggleSpawnTimer > 1500000.0) && (juggleObjects.size() < MAX_NUM_JUGGLE_OBJECTS) && ScreenManager.getThemeManager().getActiveTheme().hasNextObject())
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
            paddleCollisions(juggleObjects.get(i), paddle.getX(), paddle.getRadius());
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
            score++;
        }
    }
    
    public void circleCollisions(List<JuggleObject> juggleObjects, int currentIndex, double frameDiffMilliseconds)
    {
        JuggleObject current = juggleObjects.get(currentIndex);
        
        for (int i = 0; i < juggleObjects.size(); i++)
        {
            // skip check for collisions with ourself
            if (i != currentIndex)
            {
                current.checkCollision(juggleObjects.get(i), frameDiffMilliseconds);
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
            j.draw(gc);
        }
        
        paddle.draw(gc);
        
        //draw background color
        gc.setFill( Color.BLUE );
    }
    
    public int getScore()
    {
        return score;
    }

}
