package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.Score;

/**
 * @author Nathan Lui
 * Unit test suite for Score class tests
 */
public class TestScore {
	
	/**
	 * @author Nathan Lui
	 */
    @Test
	public void testCompareTo()
	{
		Score score1 = new Score("test", 0, 5);
		Score score2 = new Score("test2", 0, 3);
		assertTrue(score1.compareTo(score2) < 0);
	}
	
	/**
	 * @author Nathan Lui
	 */
	@Test
	public void testEquals()
	{
		Score score1 = new Score("test", 0, 4);
		Score score2 = new Score("test", 0, 4);
		assertEquals(score1, score2);
	}
}
