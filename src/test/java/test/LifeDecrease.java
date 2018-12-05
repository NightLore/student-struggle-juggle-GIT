package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;
import logic.themes.ThemeManager;

/**
 * @author Henry Luengas
 */
public class LifeDecrease {

    /**
     * @author Henry Luengas
     * @throws FileNotFoundException 
     */
    @Test
    public void testLivesDecrementOnFailedJuggle()
    {
        Game game = new Game(null);
        ThemeManager themeMan = new ThemeManager();
        
        ArrayList<JuggleObject> objects = new ArrayList<JuggleObject>();
        
        objects.add(new JuggleObject(game.getPaddle().getX(), (Game.FRAME_HEIGHT * 3), 25, 1.0, 0, 0, null));
        game.updateAll(objects);
        
        int lives = game.getInfo().getNumLives();
        
        assertEquals(5, lives);
    }
}
