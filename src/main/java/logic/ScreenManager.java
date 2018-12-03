package logic;

import java.util.EnumMap;

import javafx.stage.Stage;

public class ScreenManager {

	private Stage window;
	private EnumMap<ScreenType, Screen> screens;
	private ScreenType currentScreen;

    public ScreenManager(Stage primaryStage, double width, double height) {
		this.window = primaryStage;

		screens = new EnumMap<>(ScreenType.class);
		ScreenFactory factory = new ScreenFactory(this, width, height);
		for (ScreenType type : ScreenType.values()) 
		{
			screens.put(type, factory.createScreen(type));
		}
	}

	public void switchTo(ScreenType type) {
		screens.get(type).displayOn(window, currentScreen);
		currentScreen = type;
	}

}
