package test.integration;

import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

import logic.themes.Theme;
import logic.themes.ThemeManager;

/**
 * @author Andrew Sulistio
 */
public class TestThemeManagerLoadingTheme {

    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testLoadingAlternativeTheme() {
        ThemeManager test = new ThemeManager();
        test.addTheme("Alternative");
        test.setTheme("Alternative");
        assertTrue((test.getActiveTheme().getObjectSize() == 15) &&
                (testValidGameObjects(test.getActiveTheme()) == 5) &&
                test.getActiveTheme().getName().equals("Alternative"));
    }
    
    private int testValidGameObjects(Theme input) {
        int i;
        for(i = 0; input.hasNextObject(); i++){
            input.getNextObject();
        }
        return i;
    }

}
