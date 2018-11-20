package logic;

import logic.screens.MainMenuPane;
import logic.screens.ScoreboardPane;
import logic.screens.SettingsPane;
import logic.screens.UpdatablePane;

public class SceneFactory {
	
	private SceneManager scenes;
	private double width;
	private double height;
	
	public SceneFactory(SceneManager scenes, double width, double height)
	{
		this.scenes = scenes;
		this.width = width;
		this.height = height;
	}
	
	public Screen createScene(ScreenType type) {
		UpdatablePane layout;
		switch (type)
		{
			case MAINMENU:
				layout = new MainMenuPane(scenes);
				break;
			case SETTINGS:
				layout = new SettingsPane(scenes);
				break;
			case SCOREBOARD:
				layout = new ScoreboardPane(scenes);
				break;
//			case GAME:
//				layout = new MainMenuPane(scenes);
//				break;
//			case PAUSE:
//				layout = new MainMenuPane(scenes);
//				break;
//			case CREDITS:
//				layout = new MainMenuPane(scenes);
//				break;
			default:
				layout = new MainMenuPane(scenes);
				break;
		}
		
		return new Screen(layout, width, height);
	}
}
