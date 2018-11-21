package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class SettingsPane extends UpdatablePane {

	private ScreenType prevScreen;
	public SettingsPane(ScreenManager scenes) {
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
	public void onShow(ScreenType prevScreen) {
		this.prevScreen = prevScreen;
	}

}
