package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.Screen;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import sun.applet.Main;

public class MainMenuPane extends UpdatablePane {

	public MainMenuPane(ScreenManager scenes) {
		StackPane layout = new StackPane();

        Theme currentTheme = ScreenManager.getThemeManager().getActiveTheme();

        //Refactor to use the theme manager
		//Image bg = new Image(this.getClass().getResource("./MenuPlainBackground.jpg").toExternalForm());
        Image bg = currentTheme.getAsset("bg");

		layout.setBackground(new Background(new BackgroundImage(bg,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true))));

		ImageView titleImage = new ImageView (currentTheme.getAsset("titleImage"));
		ImageView startImage = new ImageView(new Image("file:images/StartButton.png"));
		ImageView settingImage = new ImageView(new Image("file:images/SettingsButton.png"));
		ImageView scoreImage = new ImageView(new Image("file:images/ScoreButton.png"));
		ImageView quitImage = new ImageView(new Image("file:images/QuitButton.png"));
		
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
		buttonBox.getChildren().addAll(titleImage, buttonStart, buttonSettings,buttonScores,buttonQuit);
		
		layout.getChildren().addAll(buttonBox);
		setCenter(layout);
		
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		// Empty as this screen does not need to update any elements when shown
	}

}
