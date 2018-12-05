package test.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.JuggleObject;

/**
 * @author Natalie Miller
 */
public class TestJuggleObject {

    /**
     * @author Natalie Miller
     */
    @Test
    public void testUpdateSpeed()
    {
        JuggleObject j = new JuggleObject(5, 10, 2, 3, 0, 0, null);
        j.updateSpeed(1, 1, 1);
        assertEquals(1, j.getSpeedY(), 0.01);
    }

    /**
     * @author Natalie Miller
     */
    @Test
    public void testUpdatePosition()
    {
        JuggleObject j = new JuggleObject(2, 10, 4, 1, 1, 1, null);
        j.updatePosition(1);
        assertEquals(11, j.getY(), 0.01);
    }
}
