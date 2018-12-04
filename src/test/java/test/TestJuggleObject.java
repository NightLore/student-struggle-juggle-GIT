package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals(j.getSpeedY(), 1);
    }

    /**
     * @author Natalie Miller
     */
    @Test
    public void testUpdatePosition()
    {
        JuggleObject j = new JuggleObject(2, 10, 4, 1, 1, 1, null);
        j.updatePosition(1);
        assertEquals(j.getY(), 11);
    }
}
