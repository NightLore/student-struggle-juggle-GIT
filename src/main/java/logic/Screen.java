package logic;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.screens.UpdatablePane;

public class Screen {
	
	private UpdatablePane layout;
	private Scene scene;

	@SuppressWarnings("unchecked")
	public Screen(UpdatablePane layout, double width, double height) {
		this.layout = layout;
		this.scene = new Scene(layout, width, height);
		if (layout instanceof EventHandler)
			this.scene.setOnMouseMoved((EventHandler<MouseEvent>)layout);
	}
	
	public void displayOn(Stage window, ScreenType prevScreen) {
		layout.onShow(prevScreen);
		window.setScene(scene);
	}
}
