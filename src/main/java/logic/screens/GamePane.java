package logic.screens;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import logic.Game;
import logic.ScreenManager;
import logic.ScreenType;

public class GamePane extends UpdatablePane implements EventHandler<MouseEvent> {
    
    private Game game;

	public GamePane(ScreenManager screens) {
		super(screens);
		Canvas canvas = new Canvas(Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        getChildren().add( canvas );
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
