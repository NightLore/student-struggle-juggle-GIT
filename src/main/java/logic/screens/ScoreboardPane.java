package logic.screens;

import java.util.Iterator;
import java.util.LinkedList;
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

	// default scores -- replace with actual scores
	private static List<Score> scores;
	
	private Label[][] labels;

	public ScoreboardPane(ScreenManager scenes) {
		VBox labelPane = new VBox();
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setPadding(new Insets(10));
		labelPane.getChildren().add(new Label("Scoreboard"));
		
		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(10));
		
		GridPane table = new GridPane();
		table.setAlignment(Pos.CENTER);
		table.setStyle("-fx-grid-lines-visible: true");
		table.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		table.setPadding(new Insets(10, 10, 10, 10));
		
		labels = new Label[2][MAXSCORES];
		for (int i = 0; i < MAXSCORES; i++)
		{
			labels[0][i] = new Label();
			labels[1][i] = new Label();
			table.add(setDefaults(labels[0][i]), 0, i);
			table.add(setDefaults(labels[1][i]), 1, i);
		}

		Button back = new Button("Back to Main Menu");
		back.setOnAction(e -> scenes.switchTo(ScreenType.MAINMENU));
		buttonPane.getChildren().add(back);
		
		setTop(labelPane);
		setCenter(table);
		setBottom(buttonPane);

		// TODO: default values to be removed
		scores = new LinkedList<>();
		scores.add(new Score("CheatingCat", 13));
		scores.add(new Score("KillerKris", 10));
		scores.add(new Score("BoringBob", 5));
		scores.add(new Score("LyingLeon", 3));
	}

	@Override
	public void onShow(ScreenType prevScreen) {
		updateTable(scores); // TODO: intended to update with actual scores
	}
	
	private void updateTable(List<Score> scores) {
		Iterator<Score> iterator = scores.iterator();
		for (int i = 0; i < MAXSCORES && iterator.hasNext(); i++)
		{
			Score score = iterator.next();
			labels[0][i].setText(score.getName());
			labels[1][i].setText("" + score.value());
		}
	}
	
	private Label setDefaults(Label label) {
		label.setPadding(new Insets(10, 10, 10, 10));
		GridPane.setHgrow(label, Priority.ALWAYS);
		GridPane.setVgrow(label, Priority.ALWAYS);
		return label;
	}

}
