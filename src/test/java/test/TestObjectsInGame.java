package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;

/**
 * @author Ellen Liu
 */
public class TestObjectsInGame {
    
    /**
     * @author Ellen Liu
     */
    @Test
    public void testObjectsCollidingInGame()
    {
        Game game = new Game(null);
        JuggleObject[] juggleObject = {
                new JuggleObject(50, 50, 0, 0, 0, 0, null),
                new JuggleObject(50, 50, 0, 0, 0, 0, null)
        };
        assertTrue(game.circleCollisions(Arrays.asList(juggleObject), 0, 1.0));
    }
}
