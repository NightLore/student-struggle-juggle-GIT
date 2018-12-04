package logic;

import java.util.EnumMap;

import javafx.stage.Stage;

public class ScreenManager {

	private Stage window;
	private EnumMap<ScreenType, Screen> screens;
	private ScreenType currentScreen;
	
	private static final ScreenManager INSTANCE = new ScreenManager(1366, 768);
	public static ScreenManager getInstance()
	{
	    return INSTANCE;
	}

    public ScreenManager(double width, double height) {

		screens = new EnumMap<>(ScreenType.class);
		ScreenFactory factory = new ScreenFactory(this, width, height);
		for (ScreenType type : ScreenType.values()) 
		{
			screens.put(type, factory.createScreen(type));
		}
	}

	public void switchTo(ScreenType type) 
	{
		screens.get(type).displayOn(window, currentScreen);
		currentScreen = type;
	}
	
	public ScreenManager setStage(Stage primaryStage)
	{
        this.window = primaryStage;
        return this;
	}

}
