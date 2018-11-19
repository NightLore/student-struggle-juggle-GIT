package logic;

import javafx.scene.control.Button;

public class SceneFactory {
	
	private double width;
	private double height;
	
	public SceneFactory(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
	
	public Screen createMainMenuScene(SceneManager scenes)
	{
		return new Screen(new MainMenuPane(scenes), width, height);
	}
	
	public Screen createSettingsScene(Button backButton)
	{
		return new Screen(new SettingsPane(backButton), width, height);
	}
	
	public Screen createScoreboardScene(SceneManager scenes)
	{
		return new Screen(new ScoreboardPane(scenes), width, height);
	}
}
