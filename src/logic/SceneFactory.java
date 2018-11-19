package logic;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SceneFactory {
	
	private double width;
	private double height;
	
	public SceneFactory(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
	
	public Scene createMainMenuScene(SceneManager scenes)
	{
		return new Scene(new MainMenuPane(scenes), width, height);
	}
	
	public Scene createSettingsScene(Button backButton)
	{
		return new Scene(new SettingsPane(backButton), width, height);
	}
	
	public Scene createScoreboardScene(SceneManager scenes)
	{
		return new Scene(new ScoreboardPane(scenes), width, height);
	}
}
