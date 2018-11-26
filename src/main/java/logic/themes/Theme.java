package logic.themes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

    //Indices 0-4 = game sprite objects
    private Image[] images = new Image[5];
    private String name = null;
    private Color paddleColor = null;

    //TODO define other colors (i.e. different parts of the game screen, what colors are borders etc...
    private Color mainColor = null;

    /**
     * Constructs a Theme object
     *
     * @param name - Takes in string representing path to folder of themes.
     *             Folders should be formatted as such:
     *                  <name>/00.jpg
     *                  <name>/01.jpg
     *                  <name>/02.jpg
     *                  <name>/03.jpg
     *                  <name>/04.jpg
     */
    public Theme(String name) throws FileNotFoundException {
        //TODO redefine images.length as a parameter somewhere
        for (int i = 0;i<images.length;i++ ){
            FileInputStream file = new FileInputStream("./"+name+"/"+i+".jpg");
            images[i] = new Image(file,imageSize,imageSize,true,true);
        }
        this.name = name;
        //TODO add wrapper for reading .properties file
        //this.paddleColor = paddleColor;
    }

    /**
     * gets the requested icon, should be processed using alternative methods outside of this class
     * Suggestion: https://stackoverflow.com/questions/42116313/how-to-set-an-image-in-a-circle
     * TODO create method which renders icons
     * @param i ith image in list to get
     * @return image object
     */
    public Image getIcon(int i){
        return images[i];
    }

    public Color getPaddleColor(){
        return paddleColor;
    }

    public String getName(){
        return name;
    }
}