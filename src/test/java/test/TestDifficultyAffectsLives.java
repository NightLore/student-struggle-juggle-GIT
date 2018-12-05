package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.GameInfo;

/**
 * @author Nathan Lui
 */
public class TestDifficultyAffectsLives {

    /**
     * @author Nathan Lui
     */
    @Test
    public void testNumLivesForEasy()
    {
        GameInfo info = GameInfo.getInstance();
        
        info.setDifficulty(0);
        info.reset();
        assertEquals(11, info.getNumLives());
    }

    /**
     * @author Nathan Lui
     */
    @Test
    public void testNumLivesForNormal()
    {
        GameInfo info = GameInfo.getInstance();
        
        info.setDifficulty(1);
        info.reset();
        assertEquals(6, info.getNumLives());
    }

    /**
     * @author Nathan Lui
     */
    @Test
    public void testNumLivesForHard()
    {
        GameInfo info = GameInfo.getInstance();
        
        info.setDifficulty(2);
        info.reset();
        assertEquals(2, info.getNumLives());
    }
}
