package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class CreditsPane extends UpdatablePane {

	public CreditsPane(ScreenManager screens) {
		super(screens);
		
		Label label = new Label("Credits");
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> screens.switchTo(ScreenType.SETTINGS));
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(label, backButton);
		
		getChildren().add(layout);
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		// Not needed as of now.
	}

}
