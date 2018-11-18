package logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneFactory {
	
	private double width;
	private double height;
	private Stage window;
	private Scene mainMenu, settings, scoreboard; // credits, game;
	private Button settingsBackButton, scoreboardBackButton; // creditsBackButton
	
	public SceneFactory(Stage primaryStage, double width, double height)
	{
		this.window = primaryStage;
		this.width = width;
		this.height = height;
		
		mainMenu = createMainMenuScene();
		settings = createSettingsScene();
		scoreboard = createScoreboardScene();
	}
	
	public Scene createMainMenuScene()
	{
		Label label = new Label("Main Menu");
		Button[] buttons = new Button[] {
			new Button("Settings"),
			new Button("Scoreboard"),
			new Button("Quit Game")
		};
		buttons[0].setOnAction(e -> window.setScene(getSettingsScene(ev -> window.setScene(getMainMenuScene()))));
		buttons[1].setOnAction(e -> window.setScene(getScoreboardScene(ev -> window.setScene(getMainMenuScene()))));
		buttons[2].setOnAction(e -> System.exit(0));
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().add(label);
		for (Button b : buttons)
		{
			layout.getChildren().add(b);
		}
		
		return new Scene(layout, width, height);
	}
	
	public Scene createSettingsScene()
	{
		Label label = new Label("Settings");
		settingsBackButton = new Button("Back");
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(label, settingsBackButton);
		
		
		return new Scene(layout, width, height);
	}
	
	public Scene createScoreboardScene()
	{
		Label label = new Label("Scoreboard");
		scoreboardBackButton = new Button("Back");
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(label, scoreboardBackButton);
		
		
		return new Scene(layout, width, height);
	}

	public Scene getMainMenuScene() {
		return mainMenu;
	}
	
	public Scene getSettingsScene(EventHandler<ActionEvent> back) {
		settingsBackButton.setOnAction(back);
		return settings;
	}
	
	public Scene getScoreboardScene(EventHandler<ActionEvent> back) {
		scoreboardBackButton.setOnAction(back);
		return scoreboard;
	}
}
