package test.unit;

import logic.themes.Theme;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Created by Andrew Sulistio on 12/3/18.
 */
public class TestThemes {
    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testValidUIElements() throws FileNotFoundException {
        Theme test = new Theme(Theme.DEFAULT);
        assertEquals(14, test.getObjectSize());
    }

    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testValidGameObjects() throws FileNotFoundException {
        Theme test = new Theme(Theme.DEFAULT);
        int i;
        for(i = 0; test.hasNextObject(); i++){
            test.getNextObject();
        }
        assertEquals(5, i);
    }
}
