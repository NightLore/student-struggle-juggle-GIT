package test.integration;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;

/**
 * @author Nathan Lui
 */
public class TestGameMovingPaddle {

    /**
     * @author Nathan Lui
     */
    @Test
    public void testUpdatePaddleWithCollisions()
    {
        Game game = new Game(null);
        
        int x = Game.PADDLE_WIDTH + 10;
        List<JuggleObject> objects = Arrays.asList(new JuggleObject[] {
                new JuggleObject(x, Game.FRAME_HEIGHT, 5, 0, 0, 0, null)
        });
        boolean check = game.checkCollisions(objects);
        game.updatePaddle(x);
        assertTrue(!check && game.checkCollisions(objects));
    }
}
