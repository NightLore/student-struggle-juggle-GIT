package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import logic.GameInfo;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import logic.themes.ThemeManager;

public class MainMenuPane extends UpdatablePane {

	public MainMenuPane(ScreenManager screens) {
		super(screens);
		HBox leftToRight = new HBox();

        Theme currentTheme = ThemeManager.getInstance().getActiveTheme();

		ImageView leftGif = new ImageView (currentTheme.getAsset("leftGif"));
		ImageView rightGif = new ImageView (currentTheme.getAsset("rightGif"));
		ImageView titleGif = new ImageView (currentTheme.getAsset("titleGif"));

		ImageView startImage = new ImageView(currentTheme.getAsset("startImage"));
		ImageView settingImage = new ImageView(currentTheme.getAsset("settingImage"));
		ImageView scoreImage = new ImageView(currentTheme.getAsset("scoreImage"));
		ImageView quitImage = new ImageView(currentTheme.getAsset("quitImage"));


		Button buttonStart = new Button("", startImage);
		Button buttonSettings = new Button("",settingImage);
		Button buttonScores = new Button("",scoreImage);
		Button buttonQuit = new Button("",quitImage);

		buttonStart.setOnAction(e -> screens.switchTo(ScreenType.GAME));
		buttonSettings.setOnAction(e -> screens.switchTo(ScreenType.SETTINGS));
		buttonScores.setOnAction(e -> screens.switchTo(ScreenType.SCOREBOARD));
		buttonQuit.setOnAction(e -> System.exit(0));

		VBox buttonBox = new VBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(titleGif, buttonStart, buttonSettings,buttonScores,buttonQuit);

		leftToRight.setAlignment(Pos.CENTER);
		leftToRight.getChildren().addAll(leftGif,buttonBox, rightGif);

		getChildren().addAll(leftToRight);
	}

	@Override
	public void onShow(ScreenType prevScreen) {
	    GameInfo.getInstance().reset();
	}

}
