package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;

/**
 * @author Henry Luengas
 */
public class TestScoreIncrease {

    /**
     * @author Henry Luengas
     */
    @Test
    public void testScoreIncrementOnSuccessfulJuggle()
    {
        Game game = new Game(null);
        
        List<JuggleObject> objects = Arrays.asList(new JuggleObject[] {
                new JuggleObject(game.getPaddle().getX(), Game.FRAME_HEIGHT, 25, 1.0, 0, 0, null)
        });
        game.checkCollisions(objects);
        
        int score = game.getInfo().getScore();
        
        assertEquals(1, score);
    }
}
