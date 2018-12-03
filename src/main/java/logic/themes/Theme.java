package logic.themes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Object class which holds the themes that are loaded into the system
 *
 * @author Andrew Sulistio on 11/14/18.
 * @version student-struggle-juggle-GIT
 */
public class Theme {
    private static final String BUTTON_PATH = "/buttons/";
    private static final String ANIMATIONS_PATH = "/animations/";
    private static final String GAME_PATH = "/gameObjects/";

    private Map<String, Image> map = new HashMap<>();
    private LinkedList<Image> gameObjects = new LinkedList<>();
    private Font settingsFont;
    private Font headerFont;
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

        map.put("startImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"StartButton"+".png")));
        map.put("settingImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"SettingsButton"+".png")));
        map.put("scoreImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"ScoreButton"+".png")));
        map.put("quitImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"QuitButton"+".png")));
        map.put("backImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"BackButton"+".png")));
        map.put("creditsImage",new Image(new FileInputStream("./"+name+BUTTON_PATH+"CreditsButton"+".png")));

        map.put("credits", new Image(new FileInputStream("./"+name+"/images/"+"Credits"+".png")));

        map.put("leftGif", new Image(new FileInputStream("./"+name+ANIMATIONS_PATH+"person.gif")));
        map.put("rightGif", new Image(new FileInputStream("./"+name+ANIMATIONS_PATH+"Books!.gif")));
        map.put("titleGif", new Image(new FileInputStream("./"+name+ANIMATIONS_PATH+"Logo.gif")));

        settingsFont = Font.loadFont(
                new FileInputStream("./"+name+"/Font/"+"EraserDust.ttf"),
                36
        );
        headerFont = Font.loadFont(
        		new FileInputStream("./"+name+"/Font/"+"EraserDust.ttf"),
                50
        );


        gameObjects.add(new Image(new FileInputStream("./"+name+GAME_PATH+"Clements.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+GAME_PATH+"Falessi.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+GAME_PATH+"Kearns.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+GAME_PATH+"Mammen.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+GAME_PATH+"Staley.png")));

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
    public Font getHeaderFont() {
    	return headerFont;
    }

    public boolean hasNextObject(){
        return !gameObjects.isEmpty();
    }
    public Image getNextObject() {
        return gameObjects.removeFirst();
    }

    public void resetGameObject(Image image) {
        gameObjects.addLast(image);
        Collections.shuffle(gameObjects);
    }
}