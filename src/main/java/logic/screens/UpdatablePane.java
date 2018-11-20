package logic.screens;

import javafx.scene.layout.BorderPane;
import logic.ScreenType;

public abstract class UpdatablePane extends BorderPane {

	public UpdatablePane() {
	}

	public abstract void update(ScreenType prevScreen);
}