package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import logic.Score;

public class TestScore {
	
	@Test
	public void testCompareTo()
	{
		assertTrue(new Score("test", 5).compareTo(new Score("test2", 3)) < 0);
	}
	
	@Test
	public void testEquals()
	{
		assertTrue(new Score("test", 4).equals(new Score("test", 4)));
	}
}
