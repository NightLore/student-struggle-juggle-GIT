package logic;

import logic.screens.CreditsPane;
import logic.screens.GamePane;
import logic.screens.MainMenuPane;
import logic.screens.ScoreboardPane;
import logic.screens.SettingsPane;
import logic.screens.UpdatablePane;

public class ScreenFactory {
	
	private ScreenManager scenes;
	private double width;
	private double height;
	
	public ScreenFactory(ScreenManager scenes, double width, double height)
	{
		this.scenes = scenes;
		this.width = width;
		this.height = height;
	}
	
	public Screen createScreen(ScreenType type) {
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
			case CREDITS:
				layout = new CreditsPane(scenes);
				break;
			case GAME:
				layout = new GamePane(scenes);
				break;
			default:
				layout = new MainMenuPane(scenes);
				break;
		}
		
		return new Screen(layout, width, height);
	}
}
