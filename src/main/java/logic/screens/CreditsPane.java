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

public class CreditsPane extends UpdatablePane {

	public CreditsPane(ScreenManager screens) {
		super(screens);
		Theme currentTheme = ThemeManager.getInstance().getActiveTheme();
		
		Font headerFont = currentTheme.getHeaderFont();
		
		Text header = new Text("Credits");
		header.setFill(Color.WHITESMOKE);
		header.setFont(headerFont);
		
		ImageView credits = new ImageView(currentTheme.getAsset("credits"));
		ImageView backImage = new ImageView(currentTheme.getAsset("backImage"));
		Button backButton = new Button("",backImage);
		backButton.setOnAction(e -> screens.switchTo(ScreenType.SETTINGS));
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.getChildren().addAll(header, credits, backButton);
		
		getChildren().add(layout);
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		// Not needed as of now.
	}

}
