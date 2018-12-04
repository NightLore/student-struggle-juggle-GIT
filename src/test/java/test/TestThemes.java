package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import logic.themes.Theme;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * Created by Andrew Sulistio on 12/3/18.
 */
public class TestThemes {
    /*
    Default is a valid theme as long as the format is valid, test if it does it successfully.
     */
    @Test
    public void testLoading() throws FileNotFoundException {
        Theme test = new Theme("default");

    }
}
