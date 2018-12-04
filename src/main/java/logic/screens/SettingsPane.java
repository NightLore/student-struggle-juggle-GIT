package logic.screens;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameInfo;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import logic.themes.ThemeManager;

public class SettingsPane extends UpdatablePane {
    private static final String EASY = "Easy";
    private static final String NORMAL = "Normal";
    private static final String HARD = "Hard";
    private static final String alternative = "alternative";

	private ScreenType prevScreen;
	private ToggleButton[] difficultyButtons;
	
	public SettingsPane(ScreenManager screens) {
		super(screens);
		
		Theme currentTheme = ThemeManager.getInstance().getActiveTheme();

		Font settingsFont = currentTheme.getSettingsFont();
		Font headerFont = currentTheme.getHeaderFont();
		
		Text header = new Text("Settings");
		header.setFill(Color.WHITESMOKE);
		header.setFont(headerFont);
		Slider volume = new Slider();
		Text volLabel = new Text("Vol: ");
		volLabel.setFont(settingsFont);
		volLabel.setFill(Color.WHITE);
		
		//Set Volume values
		volume.setMin(0);
		volume.setMax(100);
		volume.setValue(100);
		//Set visuals of slider
		volume.setShowTickLabels(true);
		volume.setShowTickMarks(true);
		volume.setBlockIncrement(10);
		volume.setPrefWidth(200);
		volume.setMaxWidth(500);
		volume.setMinWidth(250);
		
		Text diffLabel = new Text("Difficulty: ");
		diffLabel.setFont(settingsFont);
		diffLabel.setFill(Color.WHITE);
		
		ToggleButton easyButton = new ToggleButton(EASY);
		ToggleButton normalButton = new ToggleButton(NORMAL);
		ToggleButton hardButton = new ToggleButton(HARD);
		difficultyButtons = new ToggleButton[] { easyButton, normalButton, hardButton };

        easyButton.setUserData(EASY);
        normalButton.setUserData(NORMAL);
        hardButton.setUserData(HARD);
		
		ToggleGroup group = new ToggleGroup();
		easyButton.setToggleGroup(group);
		normalButton.setToggleGroup(group);
		hardButton.setToggleGroup(group);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		    public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
		        if (newToggle == null)
		        {
		            oldToggle.setSelected(true);
		            return;
		        }
		        String choice = newToggle.getUserData().toString();
		        int difficulty = GameInfo.getInstance().getDifficulty();
		        if (choice.equals(EASY))
		        {
		            difficulty = 0;
		        }
		        else if (choice.equals(NORMAL))
		        {
                    difficulty = 1;
		        }
                else if (choice.equals(HARD))
                {
                    difficulty = 2;
                }
                GameInfo.getInstance().setDifficulty(difficulty);
		    }
		});
		
		HBox diffButtons = new HBox(easyButton, normalButton, hardButton);
		
		VBox labelBox = new VBox();
		labelBox.setSpacing(20);
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.getChildren().addAll(volLabel, diffLabel);
		
		VBox controlsBox = new VBox();
		controlsBox.setAlignment(Pos.CENTER_LEFT);
		controlsBox.setSpacing(20);
		controlsBox.getChildren().addAll(volume, diffButtons);
		
		HBox leftToRight = new HBox();
		leftToRight.setAlignment(Pos.CENTER);
		leftToRight.setPadding(new Insets(20));
		leftToRight.setSpacing(10);
		leftToRight.getChildren().addAll(labelBox, controlsBox);
		
		Button themeButton = new Button("Change Theme");
		themeButton.setOnAction(e -> changeTheme());
		
		ImageView backImage = new ImageView(currentTheme.getAsset("backImage"));
		ImageView creditsImage = new ImageView(currentTheme.getAsset("creditsImage"));
		
		Button creditsButton = new Button("", creditsImage);
		creditsButton.setOnAction(e -> screens.switchTo(ScreenType.CREDITS));
		
		Button backButton = new Button("",backImage);
		backButton.setOnAction(e -> screens.switchTo(prevScreen));
		
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(20));
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(creditsButton,backButton);
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));
		layout.setSpacing(10);
		layout.getChildren().addAll(header, leftToRight, themeButton, buttonBox);
		
		getChildren().add(layout);
	}

	private void changeTheme() {
		if (!ThemeManager.getInstance().checkTheme(alternative)) {
			ThemeManager.getInstance().addTheme(alternative);
		}
		ThemeManager.getInstance().setTheme(alternative);

	}

	@Override
	public void onShow(ScreenType prevScreen) {
		if (prevScreen != ScreenType.CREDITS)
			this.prevScreen = prevScreen;
		difficultyButtons[GameInfo.getInstance().getDifficulty()].setSelected(true);
	}

}
