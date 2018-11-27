package logic.screens;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;

public abstract class UpdatablePane extends StackPane {

	public UpdatablePane(ScreenManager screens) {

        Theme currentTheme = ScreenManager.getThemeManager().getActiveTheme();
		
		setBackground(new Background(new BackgroundImage(currentTheme.getAsset("bg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true))));
	}

	public abstract void onShow(ScreenType prevScreen);
}
