package logic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Screen extends Application{

	Stage window;
	Scene mainMenu, settings;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		Label label1 = new Label("Main Menu");
		Button button1 = new Button("Settings");
		button1.setOnAction(e -> window.setScene(settings));
		
		//Layout 1 - children laid out in vertical column
		StackPane layout1 = new StackPane();
		layout1.getChildren().addAll(label1, button1);
		mainMenu = new Scene(layout1, 500, 500);
		
		//Button 2
		Button button2 = new Button("Main Menu");
		button2.setOnAction(e -> window.setScene(mainMenu));
		
		//Layout 2
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		settings = new Scene(layout2, 500, 500);
		
		window.setScene(mainMenu);
		window.setTitle("Struggle Juggle");
		window.show();
	}
	
}
