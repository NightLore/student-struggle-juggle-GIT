package logic;

import logic.screens.CreditsPane;
import logic.screens.GameOverPane;
import logic.screens.GamePane;
import logic.screens.MainMenuPane;
import logic.screens.PausePane;
import logic.screens.ScoreboardPane;
import logic.screens.SettingsPane;
import logic.screens.UpdatablePane;

public class ScreenFactory {
	
	private ScreenManager screens;
	private double width;
	private double height;
	
	public ScreenFactory(ScreenManager screens, double width, double height)
	{
		this.screens = screens;
		this.width = width;
		this.height = height;
	}
	
	public Screen createScreen(ScreenType type) {
		UpdatablePane layout;
		switch (type)
		{
			case MAINMENU:
				layout = new MainMenuPane(screens);
				break;
			case SETTINGS:
				layout = new SettingsPane(screens);
				break;
			case SCOREBOARD:
				layout = new ScoreboardPane(screens);
				break;
			case CREDITS:
				layout = new CreditsPane(screens);
				break;
			case GAME:
				layout = new GamePane(screens);
				break;
			case PAUSE:
			    layout = new PausePane(screens);
			    break;
			case GAMEOVER:
			    layout = new GameOverPane(screens);
			    break;
			default:
				layout = new MainMenuPane(screens);
				break;
		}
		
		return new Screen(layout, width, height);
	}
}
