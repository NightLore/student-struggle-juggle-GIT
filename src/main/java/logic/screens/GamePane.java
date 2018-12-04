package logic.screens;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.Game;
import logic.ScreenManager;
import logic.ScreenType;
import logic.themes.ThemeManager;

public class GamePane extends UpdatablePane implements EventHandler<MouseEvent> {
    
    private Game game;

	public GamePane(ScreenManager screens) {
		super(screens);
		Canvas canvas = new Canvas(Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
		
		BorderPane layout = new BorderPane();
		HBox topBox = new HBox();
		topBox.setPadding(new Insets(10, 10, 10, 10));
		Button pauseButton = new Button("PAUSE");
		pauseButton.setFont(ThemeManager.getInstance().getActiveTheme().getSettingsFont());
		pauseButton.setOnAction(e -> {
            game.pause();
		    screens.switchTo(ScreenType.PAUSE);
		});
		
		topBox.getChildren().add(pauseButton);
		layout.setTop(topBox);
        getChildren().addAll( canvas, layout );
        game = new Game(canvas);
	}

	@Override
	public void onShow(ScreenType prevScreen) {
        game.start();
	}

	@Override
    public void handle(MouseEvent userMouse)
    {
	    game.updatePaddle(userMouse.getX());
    }
}
