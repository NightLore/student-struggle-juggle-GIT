package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import logic.Score;

public class TestScore {
	
	@Test
	public void testCompareDifferentScores()
	{
		assertTrue(new Score("test", 5).compareTo(new Score("test2", 3)) < 0);
	}
	
	@Test
	public void testCompareSameScores()
	{
		assertTrue(new Score("test", 4).compareTo(new Score("test", 4)) == 0);
	}
}
