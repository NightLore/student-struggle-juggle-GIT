package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.ScreenManager;
import logic.ScreenType;

public class GameOverPane extends UpdatablePane{

    public GameOverPane(ScreenManager screens) {
        super(screens);
        
        Label label = new Label("Game Over");
        Label scoreLabel = new Label("Your score is:");
        Label score = new Label("5");
        
        HBox nameBox = new HBox();
        Label nameLabel = new Label("Enter your name: ");
        TextField name = new TextField();
        nameBox.getChildren().addAll(nameLabel, name);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button saveButton = new Button("Save score");
        // not implemented yet
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        buttonBox.getChildren().addAll(saveButton, backButton);
        
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(label, scoreLabel, score, nameBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
    }

    @Override
    public void onShow(ScreenType prevScreen) {
        // Empty as this screen does not need to update any elements when shown
    }

}
