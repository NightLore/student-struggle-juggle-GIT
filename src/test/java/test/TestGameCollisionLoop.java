package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;

public class TestGameCollisionLoop {
    
    @Test
    public void testNotExecuted()
    {
        assertFalse(new Game(null).checkCollisions(Arrays.asList(new JuggleObject[] {})));
    }
    
    @Test
    public void testExecutedOnce()
    {
        JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null)
        };
        assertFalse(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }
    
    @Test
    public void testExecutedTwice()
    {
        JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null),
                new JuggleObject(50, 50, 0, 0, 0, 0, null)
        };
        assertTrue(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }
    
    @Test
    public void testExecutedTypical()
    {
        JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null),
                new JuggleObject(100, 50, 0, 0, 0, 0, null),
                new JuggleObject(50, 100, 0, 0, 0, 0, null),
                new JuggleObject(100, 100, 0, 0, 0, 0, null)
        };
        assertFalse(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }
    
    @Test
    public void testExecutedBelowUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS-1];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        assertFalse(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }
    
    @Test
    public void testExecutedUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        assertFalse(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }
    
    @Test
    public void testExecutedAboveUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS+1];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        assertFalse(new Game(null).checkCollisions(Arrays.asList(juggleObject)));
    }

}
