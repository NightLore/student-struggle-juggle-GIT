package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;

public class TestGameUpdateLoop {
    
	@Test
    public void testNotExecuted()
    {
		ArrayList<JuggleObject> juggleObject = new ArrayList<JuggleObject>();
		
		int numberOfJuggleObjectsBefore, numberOfJuggleObjectsAfter;
		
		numberOfJuggleObjectsBefore = juggleObject.size();
		
        new Game(null).updateAll(juggleObject);
        
        numberOfJuggleObjectsAfter = juggleObject.size();
        
        assertEquals(numberOfJuggleObjectsBefore, numberOfJuggleObjectsAfter);
    }
    
    @Test
    public void testExecutedOnce()
    {
        JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null)
        };
        
        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
    }
    
    @Test
    public void testExecutedTwice()
    {
    	JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null),
                new JuggleObject(100, 50, 0, 0, 0, 0, null)
        };
        
        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
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

        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
    }
    
    @Test
    public void testExecutedBelowUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS-1];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        
        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
    }
    
    @Test
    public void testExecutedUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        
        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
    }
    
    @Test
    public void testExecutedAboveUpperbound()
    {
        JuggleObject[] juggleObject = new JuggleObject[Game.MAX_NUM_JUGGLE_OBJECTS+1];
                
        for (int i = 0; i < juggleObject.length; i++)
        {
            juggleObject[i] = new JuggleObject((i+1) * 25, 50, 0, 0, 0, 0, null);
        }
        double positionBefore, positionAfter;
        
        positionBefore = juggleObject[0].getY();
        
        new Game(null).updateAll(Arrays.asList(juggleObject));
        
        positionAfter = juggleObject[0].getY();
        
        assertNotEquals(positionBefore, positionAfter);
    }

}
