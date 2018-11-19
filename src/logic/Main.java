package logic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneManager sceneManager = new SceneManager(primaryStage, 500, 500);
		sceneManager.switchToMainMenu();
		primaryStage.setTitle("Struggle Juggle");
		primaryStage.show();
	}
	
}
