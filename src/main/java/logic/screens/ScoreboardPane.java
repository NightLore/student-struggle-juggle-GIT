package logic.screens;

import java.util.Collection;
import java.util.Iterator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameInfo;
import logic.Score;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.Theme;
import logic.themes.ThemeManager;

public class ScoreboardPane extends UpdatablePane {
	
	public static final int MAXSCORES = 8;
	
	private Label[][] labels;

	public ScoreboardPane(ScreenManager screens) {
		super(screens);
		Theme currentTheme = ThemeManager.getInstance().getActiveTheme();
		
		Font headerFont = currentTheme.getHeaderFont();
		Text header = new Text("Scores");
		header.setFill(Color.WHITESMOKE);
		header.setFont(headerFont);
		
		
		BorderPane center = new BorderPane();
		center.setPadding(new Insets(10, 50, 10, 50));
		GridPane table = new GridPane();
		table.setAlignment(Pos.CENTER);
		table.setStyle("-fx-background-color: #FFFFFF; -fx-grid-lines-visible: true");
		table.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		center.setCenter(table);

		labels = new Label[3][MAXSCORES];
		for (int i = 0; i < MAXSCORES; i++)
		{
			labels[0][i] = new Label();
			labels[1][i] = new Label();
            labels[2][i] = new Label();
			table.add(setDefaults(labels[0][i]), 0, i);
            table.add(setDefaults(labels[1][i]), 1, i);
			table.add(setDefaults(labels[2][i]), 2, i);
		}
		
		ImageView backToMenuImage = new ImageView(currentTheme.getAsset("backToMenuImage"));
        Button backButton = new Button("",backToMenuImage);
        backButton.setOnAction(e -> screens.switchTo(ScreenType.MAINMENU));
        
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(header, center, backButton);

		getChildren().add(layout);
		
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		updateTable(GameInfo.getInstance().getScores()); // intended to update with actual scores
	}
	
	private void updateTable(Collection<Score> scores) {
		Iterator<Score> iterator = scores.iterator();
		for (int i = 0; i < MAXSCORES && iterator.hasNext(); i++)
		{
			Score score = iterator.next();
			labels[0][i].setText(score.getName());
			labels[1][i].setText(score.getDifficulty());
            labels[2][i].setText("" + score.value());
		}
	}
	
	private Label setDefaults(Label label) {
		label.setPadding(new Insets(10, 10, 10, 10));
		GridPane.setHgrow(label, Priority.ALWAYS);
		GridPane.setVgrow(label, Priority.ALWAYS);
        label.setFont(ThemeManager.getInstance().getActiveTheme().getSettingsFont());
		return label;
	}

}
