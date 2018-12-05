package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import logic.Game;
import logic.GameInfo;

/**
 * @author
 */
public class TestGameAndDifficulty {

    /**
     * @author
     */
    @Test
    public void testGameResetsNumLivesBasedOnDifficulty()
    {
        Game game = new Game(null);
        GameInfo info = GameInfo.getInstance();
        info.setDifficulty(2);
        
        game.reset();
        assertEquals(2, info.getNumLives());
    }
}
