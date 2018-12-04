package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import logic.themes.ThemeManager;

public class PausePane extends UpdatablePane {

    public PausePane(ScreenManager screens) {
        super(screens);
        Theme currentTheme = ThemeManager.getInstance().getActiveTheme();
        Font headerFont = currentTheme.getHeaderFont();
        
        Text header = new Text("Game Paused");
        header.setFill(Color.WHITESMOKE);
		header.setFont(headerFont);
		
		ImageView resumeImage = new ImageView(currentTheme.getAsset("resumeImage"));
        Button resumeButton = new Button("",resumeImage);
        resumeButton.setOnAction(e -> screens.switchTo(ScreenType.GAME));
        
        ImageView settingImage = new ImageView(currentTheme.getAsset("settingImage"));
        Button settingsButton = new Button("", settingImage);
        settingsButton.setOnAction(e -> screens.switchTo(ScreenType.SETTINGS));
        
        ImageView backToMenuImage = new ImageView(currentTheme.getAsset("backToMenuImage"));
        Button backButton = new Button("",backToMenuImage);
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        
        VBox box = new VBox();
        box.getChildren().addAll(header, resumeButton, settingsButton, backButton);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        
        this.getChildren().add(box);
    }

    @Override
    public void onShow(ScreenType prevScreen) {
        // Empty as this screen does not need to update any elements when shown
    }

}
