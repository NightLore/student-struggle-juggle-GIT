package logic;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.themes.ThemeManager;

public class Main extends Application{
    public static ThemeManager themes = new ThemeManager();

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreenManager sceneManager = new ScreenManager(primaryStage, 1366, 768);
		sceneManager.switchTo(ScreenType.MAINMENU);
		primaryStage.setTitle("Struggle Juggle");
		primaryStage.show();
	}
	
}
