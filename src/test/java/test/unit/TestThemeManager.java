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
        test.addTheme("Alternative");
        test.setTheme("Alternative");
        assertTrue((test.getActiveTheme().getObjectSize() == 15) &&
                (testValidGameObjects(test.getActiveTheme()) == 5) &&
                test.getActiveTheme().getName().equals("Alternative"));
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
        test.addTheme("Alternative");
        test.addTheme("Alternative");
        test.removetheme("Alternative");
        test.removetheme("Default");
        test.addTheme("Alternative");
        test.setTheme("Alternative");
        String activeTheme = test.getActiveTheme().getName();
        assertTrue(test.getNumThemes() == 1 && activeTheme.equals("Alternative"));
    }



    private int testValidGameObjects(Theme input) {
        int i;
        for(i = 0; input.hasNextObject(); i++){
            input.getNextObject();
        }
        return i;
    }




}
