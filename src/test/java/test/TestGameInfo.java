package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import logic.GameInfo;

/**
 * @author 
 */
public class TestGameInfo {
	
	/**
	 * @author 
	 */
	@Test
	public void testIncrementScore()
	{
	    GameInfo g = new GameInfo();
	    
	    int oldScore = g.getScore();
	    g.incrementScore();
		assertEquals(g.getScore(), oldScore + 1);
	}
	
	/**
	 * @author 
	 */
	@Test
	public void testDecrementLives()
	{
        GameInfo g = new GameInfo();
        g.decrementLives();
		assertEquals(g.getNumLives(), 4);
	}
}
