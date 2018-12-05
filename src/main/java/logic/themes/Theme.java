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

    public static final String DEFAULT = "Default";

    private Map<String, Image> map = new HashMap<>();
    private LinkedList<Image> gameObjects = new LinkedList<>();
    private LinkedList<Image> gameObjects2 = new LinkedList<>();
    private String name;

    private Font settingsFont;
    private Font headerFont;

    /**
     * Constructs a Theme object
     *
     * @param name - Takes in string representing path to folder of themes.
     *             Folders should be formatted as such:
     */
    public Theme(String name) throws FileNotFoundException {
        //Backgrounds
        map.put("bg",new Image(new FileInputStream("./"+name+"/backgrounds/"+"MenuPlainBackground"+".jpg")));

        map.put("resumeImage",new Image(new FileInputStream("./"+name+"/buttons/ResumeButton.png")));
        map.put("startImage",new Image(new FileInputStream("./"+name+"/buttons/StartButton.png")));
        map.put("settingImage",new Image(new FileInputStream("./"+name+"/buttons/SettingsButton.png")));
        map.put("scoreImage",new Image(new FileInputStream("./"+name+"/buttons/ScoreButton.png")));
        map.put("quitImage",new Image(new FileInputStream("./"+name+"/buttons/QuitButton.png")));
        map.put("backImage",new Image(new FileInputStream("./"+name+"/buttons/BackButton.png")));
        map.put("backToMenuImage",new Image(new FileInputStream("./"+name+"/buttons/BacktoMainMenu.png")));
        map.put("creditsImage",new Image(new FileInputStream("./"+name+"/buttons/CreditsButton.png")));
        map.put("saveScoreImage",new Image(new FileInputStream("./"+name+"/buttons/SaveScoreButton.png")));
        
        map.put("credits", new Image(new FileInputStream("./"+name+"/images/"+"Credits"+".png")));

        map.put("leftGif", new Image(new FileInputStream("./"+name+"/animations/person.gif")));
        map.put("rightGif", new Image(new FileInputStream("./"+name+"/animations/Books!.gif")));
        map.put("titleGif", new Image(new FileInputStream("./"+name+"/animations/Logo.gif")));

        settingsFont = Font.loadFont(
                new FileInputStream("./"+name+"/Font/EraserDust.ttf"),
                36
        );
        headerFont = Font.loadFont(
        		new FileInputStream("./"+name+"/Font/"+"EraserDust.ttf"),
                50
        );

        gameObjects.add(new Image(new FileInputStream("./"+name+"/gameObjects/Clements.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+"/gameObjects/Falessi.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+"/gameObjects/Kearns.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+"/gameObjects/Mammen.png")));
        gameObjects.add(new Image(new FileInputStream("./"+name+"/gameObjects/Staley.png")));

        map.put("Paddle",new Image( new FileInputStream("./Default/paddle.png") ));

        this.name = name;
    }

    /**
     * gets the requested icon, should be processed using alternative methods outside of this class
     * Suggestion: https://stackoverflow.com/questions/42116313/how-to-set-an-image-in-a-circle
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
        return name;
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
        gameObjects2.add(gameObjects.removeFirst());
        return gameObjects2.getLast();
    }

    public void resetGameObjects(){
        gameObjects.addAll(gameObjects2);
    }

    public void resetGameObject(Image image) {
        gameObjects.addLast(image);
        gameObjects2.remove(image);
        Collections.shuffle(gameObjects);
    }

    public int getObjectSize(){
        return map.size();
    }
}
