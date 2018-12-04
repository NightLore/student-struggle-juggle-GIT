package logic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	    ScreenManager.getInstance().setStage(primaryStage).switchTo(ScreenType.MAINMENU);
		primaryStage.setTitle("The Student Struggle Juggle");
		primaryStage.show();
	}
	
}
