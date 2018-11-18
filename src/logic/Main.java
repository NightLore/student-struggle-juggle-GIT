package logic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	private SceneFactory sceneFactory;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		sceneFactory = new SceneFactory(primaryStage, 500, 500);
		primaryStage.setScene(sceneFactory.getMainMenuScene());
		primaryStage.setTitle("Struggle Juggle");
		primaryStage.show();
	}
	
}
