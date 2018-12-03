package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class PausePane extends UpdatablePane {

    public PausePane(ScreenManager screens) {
        super(screens);
        
        Label label = new Label("PAUSED");
        
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(e -> screens.switchTo(ScreenType.GAME));
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> screens.switchTo(ScreenType.SETTINGS));
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        
        VBox box = new VBox();
        box.getChildren().addAll(label, resumeButton, settingsButton, backButton);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        
        this.getChildren().add(box);
    }

    @Override
    public void onShow(ScreenType prevScreen) {
        // Empty as this screen does not need to update any elements when shown
    }

}
