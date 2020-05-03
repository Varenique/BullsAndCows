package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
//		Thread timeThread = new Thread(new TimeThread(),"TimeThread");
//      timeThread.start();	
		GameModel model = new GameModel();
		BullsAndCowsController controller = new BullsAndCowsController(model);
		MenuWindow mainWindow = new MenuWindow(controller);	
		
	}
		
}