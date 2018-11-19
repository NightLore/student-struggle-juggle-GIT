package logic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SettingsPane extends VBox {

	public SettingsPane(Button backButton) {
		Label label = new Label("Settings");
		
		setAlignment(Pos.CENTER);
		setSpacing(10);
		getChildren().addAll(label, backButton);
	}

}
