package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;

public class MainMenuPane extends UpdatablePane {

	public MainMenuPane(ScreenManager scenes) {
		super(scenes);
		HBox leftToRight = new HBox();

        Theme currentTheme = ScreenManager.getThemeManager().getActiveTheme();

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

		buttonStart.setOnAction(e -> scenes.switchTo(ScreenType.GAME));
		buttonSettings.setOnAction(e -> scenes.switchTo(ScreenType.SETTINGS));
		buttonScores.setOnAction(e -> scenes.switchTo(ScreenType.SCOREBOARD));
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
		// Empty as this screen does not need to update any elements when shown
	}

}
