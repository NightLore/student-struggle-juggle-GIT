package test.unit;


import logic.themes.Theme;
import logic.themes.ThemeManager;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestThemeManager {

    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testLoadingAlternativeTheme() {
        ThemeManager test = new ThemeManager();
        test.addTheme("alternative");
        test.setTheme("alternative");
        assertTrue((test.getActiveTheme().getObjectSize() == 15) &&
                (testValidGameObjects(test.getActiveTheme()) == 5) &&
                test.getActiveTheme().getName().equals("alternative"));
    }

    /**
     * @author Andrew Sulistio
     */
    @Test
    public void testAdditionAndRemovalOfThemes(){
        ThemeManager test = new ThemeManager();
        test.addTheme("Default");
        test.addTheme("Default");
        test.addTheme("Default");
        test.getActiveTheme().resetGameObjects();
        test.addTheme("alternative");
        test.addTheme("alternative");
        test.removetheme("alternative");
        test.removetheme("Default");
        test.addTheme("alternative");
        test.setTheme("alternative");
        String activeTheme = test.getActiveTheme().getName();
        assertTrue(test.getNumThemes() == 1 && activeTheme.equals("alternative"));
    }



    private int testValidGameObjects(Theme input) {
        int i;
        for(i = 0; input.hasNextObject(); i++){
            input.getNextObject();
        }
        return i;
    }




}
