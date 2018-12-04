package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import logic.themes.Theme;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * Created by Andrew Sulistio on 12/3/18.
 */
public class TestThemes {
    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testValidUIElements() throws FileNotFoundException {
        Theme test = new Theme("default");
        assertTrue(test.getObjectSize() == 13);
    }

    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testValidGameObjects() throws FileNotFoundException {
        Theme test = new Theme("default");
        int i;
        for(i = 0; test.hasNextObject(); i++){
            test.getNextObject();
        }
        assertTrue(i == 5);
    }
}
