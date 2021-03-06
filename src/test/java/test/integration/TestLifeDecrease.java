package test.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import logic.Game;
import logic.GameInfo;
import logic.JuggleObject;

/**
 * @author Henry Luengas
 */
public class TestLifeDecrease {

    /**
     * @author Henry Luengas
     */
    @Test
    public void testLivesDecrementOnFailedJuggle()
    {
        Game game = new Game(null);
        
        ArrayList<JuggleObject> objects = new ArrayList<JuggleObject>();
        
        int livesBefore = GameInfo.getInstance().getNumLives();
        
        objects.add(new JuggleObject(game.getPaddle().getX(), (Game.FRAME_HEIGHT * 3), 25, 1.0, 0, 0, null));
        game.updateAll(objects);
        
        int lives = GameInfo.getInstance().getNumLives();
        
        assertEquals(livesBefore - 1, lives);
    }
}
