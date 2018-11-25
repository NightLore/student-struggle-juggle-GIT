package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;
public class MainMenuPane extends UpdatablePane {

	public MainMenuPane(ScreenManager scenes) {
		StackPane layout = new StackPane();
		layout.setStyle("-fx-background-image: url('images/MenuPlainBackground.jpg')");
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
		buttonBox.getChildren().addAll(buttonStart, buttonSettings,buttonScores,buttonQuit);
		
		layout.getChildren().addAll(buttonBox);
		setCenter(layout);
		
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		// Empty as this screen does not need to update any elements when shown
	}

}
