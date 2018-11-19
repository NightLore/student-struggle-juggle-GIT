package logic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsPane extends UpdatablePane {

	public SettingsPane(Button backButton) {
		Label label = new Label("Settings");
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(label, backButton);
		
		setCenter(layout);
	}

	@Override
	public void update() {
		
	}

}
