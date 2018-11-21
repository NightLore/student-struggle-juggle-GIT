package logic.screens;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import logic.Score;
import logic.ScreenManager;
import logic.ScreenType;

public class ScoreboardPane extends UpdatablePane {
	
	public static final int MAXSCORES = 10;
	
	private List<Score> scores;
	private GridPane table;

	public ScoreboardPane(ScreenManager scenes) {
		VBox labelPane = new VBox();
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setPadding(new Insets(10));
		labelPane.getChildren().add(new Label("Scoreboard"));
		
		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(10));

		Button back = new Button("Back to Main Menu");
		back.setOnAction(e -> scenes.switchTo(ScreenType.MAINMENU));
		buttonPane.getChildren().add(back);
		
		setTop(labelPane);
		setBottom(buttonPane);
		
		// default scores -- replace with actual scores
		scores = new ArrayList<Score>();
		scores.add(new Score("CheatingCat", 13));
		scores.add(new Score("KillerKris", 10));
		scores.add(new Score("BoringBob", 5));
		scores.add(new Score("LyingLeon", 3));
	}

	@Override
	public void update(ScreenType prevScreen) {
		updateTable(this.scores); // intended to update with actual scores
	}
	
	private void updateTable(List<Score> scores) {
		table = new GridPane();
		table.setAlignment(Pos.CENTER);
		table.setStyle("-fx-grid-lines-visible: true");
		table.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		table.setPadding(new Insets(10, 10, 10, 10));
		
		int i;
		for (i = 0; i < MAXSCORES && i < scores.size(); i++)
		{
			table.add(setDefaults(new Label(scores.get(i).getName())), 0, i);
			table.add(setDefaults(new Label("" + scores.get(i).value())), 1, i);
		}
		while (i < MAXSCORES)
		{
			table.add(setDefaults(new Label("")), 0, i);
			i++;
		}
		
		setCenter(table);
	}
	
	private Label setDefaults(Label label) {
		label.setPadding(new Insets(10, 10, 10, 10));
		GridPane.setHgrow(label, Priority.ALWAYS);
		GridPane.setVgrow(label, Priority.ALWAYS);
		return label;
	}

}
