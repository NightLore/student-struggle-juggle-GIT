package logic;

import java.util.EnumMap;

import javafx.stage.Stage;

public class SceneManager {

	private Stage window;
	private EnumMap<ScreenType, Screen> screens;
	private ScreenType currentScreen;
	
	public SceneManager(Stage primaryStage, double width, double height) {
		this.window = primaryStage;
		
		screens = new EnumMap<ScreenType, Screen>(ScreenType.class);
		SceneFactory factory = new SceneFactory(this, width, height);
		for (ScreenType type : ScreenType.values()) 
		{
			screens.put(type, factory.createScene(type));
		}
	}
	
	public void switchTo(ScreenType type) {
		screens.get(type).displayOn(window, currentScreen);
		System.out.println(currentScreen);
		currentScreen = type;
	}

}
