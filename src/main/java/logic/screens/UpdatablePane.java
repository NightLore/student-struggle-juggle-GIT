package logic.screens;

import javafx.scene.layout.StackPane;
import logic.ScreenType;

public abstract class UpdatablePane extends StackPane {

	public UpdatablePane() {
	}

	public abstract void onShow(ScreenType prevScreen);
}
