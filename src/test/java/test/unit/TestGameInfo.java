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
		assertEquals(g.getScore(), oldScore + 1);
	}
	
	/**
	 * @author Ellen Liu
	 */
	@Test
	public void testDecrementLives()
	{
        GameInfo g = GameInfo.getInstance();
        g.decrementLives();
		assertEquals(g.getNumLives(), 5);
	}
}
