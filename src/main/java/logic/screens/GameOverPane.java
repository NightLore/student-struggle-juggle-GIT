package logic.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameInfo;
import logic.Score;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import logic.themes.ThemeManager;

public class GameOverPane extends UpdatablePane {

    private Label scoreLabel;
    private Label difficultyLabel;
    public GameOverPane(ScreenManager screens) {
        super(screens);
        Theme currentTheme = ThemeManager.getInstance().getActiveTheme();
        Font headerFont = currentTheme.getHeaderFont();
        Font settingsFont = currentTheme.getSettingsFont();
        
        Label header = new Label("Game Over");
        header.setTextFill(Color.WHITESMOKE);
        header.setFont(headerFont);
        scoreLabel = new Label();
        scoreLabel.setTextFill(Color.WHITESMOKE);
        scoreLabel.setFont(settingsFont);
        
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Enter your name: ");
        nameLabel.setTextFill(Color.WHITESMOKE);
        nameLabel.setFont(settingsFont);
        TextField name = new TextField();
        name.setFont(settingsFont);
        name.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER))
            {
                setScoreAndSwitch(name.getText());
            }
        });
        nameBox.getChildren().addAll(nameLabel, name);
        
        difficultyLabel = new Label("");
        difficultyLabel.setTextFill(Color.WHITESMOKE);
        difficultyLabel.setFont(settingsFont);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        Button saveButton = new Button("Save score");
        saveButton.setOnAction(e -> setScoreAndSwitch(name.getText()));
        
        ImageView backToMenuImage = new ImageView(currentTheme.getAsset("backToMenuImage"));
        Button backButton = new Button("",backToMenuImage);
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        buttonBox.getChildren().addAll(saveButton, backButton);
        
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(header, scoreLabel, difficultyLabel, nameBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        
        getChildren().add(layout);
    }

    @Override
    public void onShow(ScreenType prevScreen) {
        scoreLabel.setText("Your score is: " + GameInfo.getInstance().getScore());
        difficultyLabel.setText(difficulty(GameInfo.getInstance().getDifficulty()));
    }
    
    private String difficulty(int difficulty)
    {
        switch (difficulty)
        {
            case 0:
                return "EASY";
            case 1:
                return "NORMAL";
            case 2:
                return "HARD";
            default:
                return "";
        }
    }
    
    private void setScoreAndSwitch(String name)
    {
        GameInfo info = GameInfo.getInstance();
        info.getScores().add(new Score(name, info.getDifficulty(), info.getScore()));
        ScreenManager.getInstance().switchTo(ScreenType.SCOREBOARD);
    }

}
