package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuWindow {
	private Stage primaryStage;
	private AnchorPane root;
	private Button buttonPlay;
	private BullsAndCowsController controller;
	private Button buttonRules;
	private Button buttonExit;
	private Button buttonContinue;
	private Button buttonReplay;
	MenuWindow(BullsAndCowsController controller, Stage stage){
		this.controller = controller;
		primaryStage = stage;
	}
	MenuWindow(BullsAndCowsController controller){
		this.controller = controller;
		String titlePlay="";
		String titleRules ="";
		String titleExit="";
		String titleContinue="";
		String tooltipPlay="Click to start game";
		String tooltipRules="Do you want to see the rules of the game";
		String tooltipExit ="Already leaving?"; 
		String tooltipContinue="Do you want to continue last game?";
		primaryStage = new Stage();
		primaryStage.setTitle("BullsAndCows");
		
		root = new AnchorPane();
		root.setId("mainWindow");
		
		buttonPlay = createButton(titlePlay, tooltipPlay, primaryStage);
		buttonPlay.setId("buttonPlay");
		buttonPlay.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonPlay, 350.0);
		AnchorPane.setTopAnchor(buttonPlay, 355.0);	
		buttonPlay.setOnMouseClicked(mouseEvent -> {
			
			try {
				showDialogForGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		buttonContinue = createButton(titleContinue, tooltipContinue, primaryStage);
		buttonContinue.setId("buttonContinue");
		buttonContinue.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonContinue, 350.0);
		AnchorPane.setTopAnchor(buttonContinue, 425.0);
		buttonContinue.setOnMouseClicked(mouseEvent -> {
			primaryStage.hide();
			controller.startLastGame("");
			try {
				GameWindow game = new GameWindow(controller);
				game.setDataForPlayerTable(controller.getPlayerTable());
				game.setDataForComputerTable(controller.getComputerTable());
				game.showTable(controller);
				} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buttonReplay = createButton(titleRules, tooltipRules, primaryStage);
		buttonReplay.setId("buttonReplay");
		buttonReplay.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonReplay, 350.0);
		AnchorPane.setTopAnchor(buttonReplay, 495.0);
		buttonReplay.setOnMouseClicked(mouseEvent -> {
			showReplayDialog();});
		
		buttonRules = createButton(titleRules, tooltipRules, primaryStage);
		buttonRules.setId("buttonRules");
		buttonRules.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonRules, 350.0);
		AnchorPane.setTopAnchor(buttonRules, 565.0);
		buttonRules.setOnMouseClicked(mouseEvent -> {
			showRules();});
		
			
		buttonExit = createButton(titleExit, tooltipExit, primaryStage);
		buttonExit.setId("buttonExit");
		buttonExit.setPrefSize(300.0, 50.0);			
		AnchorPane.setRightAnchor(buttonExit, 350.0);
		AnchorPane.setTopAnchor(buttonExit, 635.0);
		buttonExit.setOnMouseClicked(mouseEvent -> {
			primaryStage.close();});      
        
		
        root.getChildren().addAll(buttonPlay, buttonContinue, buttonRules, buttonExit, buttonReplay);
		Scene scene = new Scene(root,1000,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.getIcons().add(new Image("file:images/iconCow.png"));
		
		ObservableList<String> stylesheets = scene.getStylesheets();
		String externalForm = getClass().getResource("application.css").toExternalForm();
		stylesheets.add(externalForm);
	}
	public Boolean showDialogForGame() throws IOException {
		Boolean isNumber = false;
		TextInputDialog dialog = new TextInputDialog("1234");
		dialog.setTitle("Cows and Bulls");
		dialog.setHeaderText("Some text about cows");
		dialog.setContentText("Please enter your number:");
		Optional<String> playerNumber = dialog.showAndWait();
		if (playerNumber.isPresent()){
			primaryStage.hide();
			controller.restart();
			String playerNumberString = playerNumber.get();
			controller.transferSetPlayerNumber(playerNumberString);
			GameWindow game = new GameWindow(controller);
			//thread with time for game window
			TimeThread timeObject = new TimeThread(game);
			Thread timeThread = new Thread(timeObject);
			timeThread.start();
			isNumber=true;
		}
		return isNumber;
	}
	public void showReplayDialog() {
		
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Bulls and Cows");
		dialog.setHeaderText("GAMES TO REPLAY:\n"+controller.getAllFiles());
		dialog.setContentText("Please enter name of file to replay:");
		Optional<String> fileNameToReplay = dialog.showAndWait();
		if (fileNameToReplay.isPresent()){
			primaryStage.hide();
			String fileNameToReplayString = fileNameToReplay.get();
			controller.readReplay(fileNameToReplayString);
			try {
				ReplayWindow window = new ReplayWindow(controller, fileNameToReplayString);
			} catch (IOException e) {
				e.printStackTrace();
			}

		
		}
		
	}
	private void showRules() {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("Rules of BullsAndCows");
		dialog.setHeaderText("Rules:");
		dialog.showAndWait();
		
	}
	private Button createButton(String title, String tooltip, Stage primaryStage) {
		Button result = new Button(title);
		result.setTooltip(new Tooltip(tooltip));
		result.setCursor(Cursor.HAND);
		return result;
	}
}
