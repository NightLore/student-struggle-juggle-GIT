package test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logic.JuggleObject;
import logic.Paddle;

/**
 * @author Henry Luengas
 */
public class TestPaddle {
    
    /**
     * @author Henry Luengas
     */
    @Test
    public void testCollidesWithTrue()
    {
        Paddle paddle = new Paddle(5, 0, 5);
        paddle.setX(0);
        assertTrue(paddle.collidesWith(new JuggleObject(0, 0, 2, 0, 0, 0, null), 0, 1.0));
    }

    /**
     * @author Henry Luengas
     */
    @Test
    public void testCollidesWithFalse()
    {
        Paddle paddle = new Paddle(5, 0, 5);
        paddle.setX(0);
        assertFalse(paddle.collidesWith(new JuggleObject(12, 10, 2, 0, 0, 0, null), 0, 1.0));
    }

}
