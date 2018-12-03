package logic.themes;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * ThemeManager implements the package for Theme Manager, this enables other parts of the program to quickly and easily
 * access different themes
 *
 * @author Andrew Sulistio on 11/14/18.
 * @version student-struggle-juggle-GIT
 */
public class ThemeManager {

    private String defaultTheme = "Default";

    private Theme currentTheme = null;
    private HashMap<Integer, Theme> themes = new HashMap<>();

    private static final ThemeManager INSTANCE = new ThemeManager();

    public static ThemeManager getInstance(){
        return INSTANCE;
    }

    public ThemeManager(){
        try {
            currentTheme = new Theme(defaultTheme);
            themes.put(defaultTheme.hashCode(),currentTheme);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTheme(String name){
        try {
            themes.put(name.hashCode(),new Theme(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO alert user that theme was not found
        }
    }

    public Theme setTheme(String name){
        Theme temp = themes.get(name.hashCode());
        if (temp == null) {
            throw new NoSuchElementException("Theme: " + name + " not found! Perhaps it wasn't added yet?");
        }
        else{
            currentTheme = temp;
            return temp;
        }
    }

    public Theme removetheme(String name){
        return themes.remove(name.hashCode());
    }

    /**
     * obtains currently active theme/last loaded theme
     * @return null if no theme loaded
     */
    public Theme getActiveTheme(){
        return currentTheme;
    }
}