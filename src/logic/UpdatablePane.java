package logic;

import javafx.scene.layout.BorderPane;

public abstract class UpdatablePane extends BorderPane {

	public UpdatablePane() {
	}

	public abstract void update(ScreenType prevScreen);
}
