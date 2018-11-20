package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.SceneManager;
import logic.ScreenType;

public class SettingsPane extends UpdatablePane {

	private ScreenType prevScreen;
	public SettingsPane(SceneManager scenes) {
		Label label = new Label("Settings");
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> scenes.switchTo(prevScreen));
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(label, backButton);
		
		setCenter(layout);
	}

	@Override
	public void update(ScreenType prevScreen) {
		this.prevScreen = prevScreen;
	}

}
