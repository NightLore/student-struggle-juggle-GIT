package logic.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class ScoreboardPane extends UpdatablePane {

	public ScoreboardPane(ScreenManager scenes) {
		VBox labelPane = new VBox();
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setPadding(new Insets(10));
		labelPane.getChildren().add(new Label("Scoreboard"));
		
		GridPane scores = new GridPane();
		
		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(10));

		Button back = new Button("Back to Main Menu");
		back.setOnAction(e -> scenes.switchTo(ScreenType.MAINMENU));
		buttonPane.getChildren().add(back);
		
		setTop(labelPane);
		setCenter(scores);
		setBottom(buttonPane);
	}

	@Override
	public void update(ScreenType prevScreen) {
		// Empty as this screen does not need to update any elements when shown
	}

}
