package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import logic.Score;

/**
 * @author Nathan Lui
 * Unit test suite for Score class tests
 */
public class TestScore {
	
	@Test
	public void testCompareTo()
	{
		Score score1 = new Score("test", 5);
		Score score2 = new Score("test2", 3);
		assertTrue(score1.compareTo(score2) < 0);
	}
	
	@Test
	public void testEquals()
	{
		Score score1 = new Score("test", 4);
		Score score2 = new Score("test", 4);
		assertTrue(score1.equals(score2));
	}
}
