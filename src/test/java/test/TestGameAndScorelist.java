package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import logic.Game;
import logic.GameInfo;
import logic.Score;

/**
 * @author
 */
public class TestGameAndScorelist {

    /**
     * @author
     */
    @Test
    public void testGameResetDoesNotClearScores()
    {
        Game game = new Game(null);
        GameInfo info = GameInfo.getInstance();
        info.getScores().add(new Score("test", 0, 5));
        
        game.reset();
        assertEquals(1, info.getScores().size());
    }
}
