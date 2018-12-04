package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.GameInfo;
import logic.Score;
import logic.ScreenManager;
import logic.ScreenType;

public class GameOverPane extends UpdatablePane {

    private Label score;
    public GameOverPane(ScreenManager screens) {
        super(screens);
        
        Label label = new Label("Game Over");
        Label scoreLabel = new Label("Your score is:");
        score = new Label();
        
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Enter your name: ");
        TextField name = new TextField();
        name.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER))
            {
                setScoreAndSwitch(name.getText());
            }
        });
        nameBox.getChildren().addAll(nameLabel, name);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        Button saveButton = new Button("Save score");
        saveButton.setOnAction(e -> setScoreAndSwitch(name.getText()));
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        buttonBox.getChildren().addAll(saveButton, backButton);
        
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(label, scoreLabel, score, nameBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        
        getChildren().add(layout);
    }

    @Override
    public void onShow(ScreenType prevScreen) {
        score.setText("" + GameInfo.getInstance().getScore());
    }
    
    private void setScoreAndSwitch(String name)
    {
        GameInfo.getInstance().getScores().add(new Score(name, GameInfo.getInstance().getScore()));
        ScreenManager.getInstance().switchTo(ScreenType.SCOREBOARD);
        GameInfo.getInstance().reset();
    }

}
