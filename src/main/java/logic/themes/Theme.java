package logic.themes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Object class which holds the themes that are loaded into the system
 *
 * @author Andrew Sulistio on 11/14/18.
 * @version student-struggle-juggle-GIT
 */
public class Theme {

    //TODO amount of images necessary for game, i.e. how many sprites, paddle color, etc...

    //Defined Variables
    private int imageSize = 128;

    Map<String, Image> map = new HashMap<String, Image>();

    //TODO define other colors (i.e. different parts of the game screen, what colors are borders etc...
    private Color mainColor = null;

    /**
     * Constructs a Theme object
     *
     * @param name - Takes in string representing path to folder of themes.
     *             Folders should be formatted as such:
     */
    public Theme(String name) throws FileNotFoundException {
        //TODO optimize importing

        //Backgrounds
        map.put("bg",new Image(new FileInputStream("./"+name+"/backgrounds/"+"MenuPlainBackground"+".jpg")));

        String path = "/buttons/";
        map.put("startImage",new Image(new FileInputStream("./"+name+path+"StartButton"+".png")));
        map.put("settingImage",new Image(new FileInputStream("./"+name+path+"SettingsButton"+".png")));
        map.put("scoreImage",new Image(new FileInputStream("./"+name+path+"ScoreButton"+".png")));
        map.put("quitImage",new Image(new FileInputStream("./"+name+path+"QuitButton"+".png")));

        path = "/animations/";
        map.put("leftGif", new Image(new FileInputStream("./"+name+path+"person.gif")));
        map.put("rightGif", new Image(new FileInputStream("./"+name+path+"Books!.gif")));
        map.put("titleGif", new Image(new FileInputStream("./"+name+path+"Logo.gif")));
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
}