package test.integration;

import logic.themes.ThemeManager;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Andrew Sulistio
 */
public class TestThemeManagerAndTheme {
    
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

}
