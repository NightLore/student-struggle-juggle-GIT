package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.Game;
import logic.JuggleObject;
import logic.Paddle;

/**
 * @author
 */
public class TestObjectAndPaddle {
    
    /**
     * @author
     */
    @Test
    public void testObjectMovingIntoPaddle()
    {
        JuggleObject j = new JuggleObject(0, 5, 1, 0, 0, -1, null);
        Paddle p = new Paddle(2, 0, 0);
        
        j.updatePosition(5);
        assertTrue(p.collidesWith(j, 0, Game.ENERGY_LOSS_RATIO));
    }

}
