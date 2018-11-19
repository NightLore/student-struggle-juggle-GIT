package logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneManager {

	private Stage window;
	private Scene mainMenu, settings, scoreboard; // credits, game;
	private Button settingsBackButton;
	
	public SceneManager(Stage primaryStage, double width, double height) {
		this.window = primaryStage;
		settingsBackButton = new Button("Back");
		
		SceneFactory factory = new SceneFactory(width, height);
		mainMenu = factory.createMainMenuScene(this);
		settings = factory.createSettingsScene(settingsBackButton);
		scoreboard = factory.createScoreboardScene(this);
	}
	
	public void switchToMainMenu() {
		window.setScene(mainMenu);
	}
	
	public void switchToSettings(EventHandler<ActionEvent> back) {
		settingsBackButton.setOnAction(back);
		window.setScene(settings);
	}
	
	public void switchToScoreboard() {
		window.setScene(scoreboard);
	}

}
