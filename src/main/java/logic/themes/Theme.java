package logic.themes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Object class which holds the themes that are loaded into the system
 *
 * @author Andrew Sulistio on 11/14/18.
 * @version student-struggle-juggle-GIT
 */
public class Theme {

    private Map<String, Image> map = new HashMap<>();
    private Font settingsFont;

    /**
     * Constructs a Theme object
     *
     * @param name - Takes in string representing path to folder of themes.
     *             Folders should be formatted as such:
     */
    Theme(String name) throws FileNotFoundException {
        //TODO optimize importing

        //Backgrounds
        map.put("bg",new Image(new FileInputStream("./"+name+"/backgrounds/"+"MenuPlainBackground"+".jpg")));

        String path = "/buttons/";
        map.put("startImage",new Image(new FileInputStream("./"+name+path+"StartButton"+".png")));
        map.put("settingImage",new Image(new FileInputStream("./"+name+path+"SettingsButton"+".png")));
        map.put("scoreImage",new Image(new FileInputStream("./"+name+path+"ScoreButton"+".png")));
        map.put("quitImage",new Image(new FileInputStream("./"+name+path+"QuitButton"+".png")));
        map.put("backImage",new Image(new FileInputStream("./"+name+path+"BackButton"+".png")));
        
        path = "/animations/";
        map.put("leftGif", new Image(new FileInputStream("./"+name+path+"person.gif")));
        map.put("rightGif", new Image(new FileInputStream("./"+name+path+"Books!.gif")));
        map.put("titleGif", new Image(new FileInputStream("./"+name+path+"Logo.gif")));

        settingsFont = Font.loadFont(
                new FileInputStream("./Font/EraserDust.ttf"),
                10
        );

    }

    /**
     * gets the requested icon, should be processed using alternative methods outside of this class
     * Suggestion: https://stackoverflow.com/questions/42116313/how-to-set-an-image-in-a-circle
     * TODO create method which renders icons
     * @param i ith image in list to get
     * @return image object
     */
    public Image getIcon(int i){
        return null;
    }

    public Color getPaddleColor(){
        return null;
    }

    public String getName(){
        return null;
    }

    public Image getAsset(String str) {
        return map.get(str);
    }
    public Font getSettingsFont(){
        return settingsFont;
    }
}