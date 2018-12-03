package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
		Score score1 = new Score("test", 5);
		Score score2 = new Score("test2", 3);
		assertTrue(score1.compareTo(score2) < 0);
	}
	
	/**
	 * @author Nathan Lui
	 */
	@Test
	public void testEquals()
	{
		Score score1 = new Score("test", 4);
		Score score2 = new Score("test", 4);
		assertEquals(score1, score2);
	}
}
