package logic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuPane extends VBox {

	public MainMenuPane(SceneManager scenes) {
		Label label = new Label("Main Menu");
		Button[] buttons = new Button[] {
			new Button("Settings"),
			new Button("Scoreboard"),
			new Button("Quit Game")
		};
		buttons[0].setOnAction(e -> scenes.switchToSettings(ev -> scenes.switchToMainMenu()));
		buttons[1].setOnAction(e -> scenes.switchToScoreboard());
		buttons[2].setOnAction(e -> System.exit(0));
		
		setAlignment(Pos.CENTER);
		setSpacing(10);
		getChildren().add(label);
		for (Button b : buttons)
		{
			getChildren().add(b);
		}
	}

}
