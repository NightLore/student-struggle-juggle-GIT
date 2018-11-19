package logic;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screen {
	
	private UpdatablePane layout;
	private Scene scene;

	public Screen(UpdatablePane layout, double width, double height) {
		this.layout = layout;
		this.scene = new Scene(layout, width, height);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void displayOn(Stage window, ScreenType prevScreen) {
		layout.update(prevScreen);
		window.setScene(scene);
	}
}
