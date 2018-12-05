package test.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.GameInfo;

/**
 * @author Ellen Liu
 */
public class TestGameInfo {
	
	/**
	 * @author Ellen Liu
	 */
	@Test
	public void testIncrementScore()
	{
	    GameInfo g = GameInfo.getInstance();
	    
	    int oldScore = g.getScore();
	    g.incrementScore();
		assertEquals(oldScore + 1, g.getScore());
	}
	
	/**
	 * @author Ellen Liu
	 */
	@Test
	public void testDecrementLives()
	{
        GameInfo g = GameInfo.getInstance();
        int before = g.getNumLives();
        g.decrementLives();
		assertEquals(before - 1, g.getNumLives());
	}
}
