package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class MainMenuPane extends UpdatablePane {

	public MainMenuPane(ScreenManager scenes) {
		Label label = new Label("Main Menu");
		Button[] buttons = new Button[] {
			new Button("Settings"),
			new Button("Scoreboard"),
			new Button("Quit Game")
		};
		buttons[0].setOnAction(e -> scenes.switchTo(ScreenType.SETTINGS));
		buttons[1].setOnAction(e -> scenes.switchTo(ScreenType.SCOREBOARD));
		buttons[2].setOnAction(e -> System.exit(0));
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().add(label);
		for (Button b : buttons)
		{
			layout.getChildren().add(b);
		}
		
		setCenter(layout);
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		// Empty as this screen does not need to update any elements when shown
	}

}
