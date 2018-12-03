package logic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.themes.ThemeManager;

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
    
    // frametime keeps track of the time between draw calls (basically how long has it been since I drew the last frame)
    private long frametimeNanoSeconds = 0;
    private double frameDiffMilliseconds = 0;
    // counts time passed since newest juggle item was created
    private double juggleSpawnTimer;

    private List<JuggleObject> juggleObjects;
    private Paddle paddle;
    private GameInfo info;

    private AnimationTimer gameLoop;

    public Game(Canvas canvas) {
        paddle = new Paddle(250, FRAME_HEIGHT - 5, 10);
        info = GameInfo.getInstance();
        
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
        reset();
    }
    
    public void reset()
    {
        juggleObjects = new ArrayList<>();
        frametimeNanoSeconds = 0;
        frameDiffMilliseconds = 0;
        juggleSpawnTimer = 0.0;
        info.reset();
    }
    
    public void start()
    {
        gameLoop.start();
    }
    
    public void pause()
    {
        gameLoop.stop();
    }
    
    public void updatePaddle(double mouseX)
    {
        paddle.setX(mouseX);      
        
        // check if enough time has passed or if the max item count is reached
        if ( (juggleSpawnTimer > 1500000.0) && (juggleObjects.size() < MAX_NUM_JUGGLE_OBJECTS) && ThemeManager.getInstance().getActiveTheme().hasNextObject())
        {
            juggleObjects.add(JuggleObject.createRandom(FRAME_WIDTH, FRAME_HEIGHT, ThemeManager.getInstance().getActiveTheme().getNextObject()));
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
                ThemeManager.getInstance().getActiveTheme().resetGameObject(juggleObjects.get(i).getImage());
                juggleObjects.remove(i);
                info.decrementLives();
                if (info.getNumLives() <= 0)
                {
                    
                }
            }
        }
    }
    
    public void checkCollisions()
    {
        // for each juggle object compute collisions
        for (int i = 0; i < juggleObjects.size(); i++)
        {
            circleCollisions(juggleObjects, i, frameDiffMilliseconds);
            if (paddle.collidesWith(juggleObjects.get(i), FRAME_HEIGHT, ENERGY_LOSS_RATIO))
            {
                info.incrementScore();
            }
            juggleObjects.get(i).checkReflectionWalls(FRAME_WIDTH, ENERGY_LOSS_RATIO);
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

}
