package application;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuWindow {
	private Stage primaryStage;
	private AnchorPane root;
	private Button buttonPlay;
	private BullsAndCowsController controller;
	private Button buttonRules;
	private Button buttonExit;
	private Button buttonContinue;
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
		AnchorPane.setTopAnchor(buttonPlay, 375.0);	
		buttonPlay.setOnMouseClicked(mouseEvent -> {
			
			try {
				showDialogForGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		buttonContinue = createButton(titleContinue, tooltipContinue, primaryStage);
		buttonContinue.setId("buttonContinue");
		buttonContinue.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonContinue, 350.0);
		AnchorPane.setTopAnchor(buttonContinue, 445.0);
		buttonContinue.setOnMouseClicked(mouseEvent -> {
			primaryStage.hide();
			controller.startLastGame();
			try {
				GameWindow game = new GameWindow(controller);
				game.setDataForPlayerTable(controller.getPlayerTable());
				game.setDataForComputerTable(controller.getComputerTable());
				game.showTable(controller);
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		buttonRules = createButton(titleRules, tooltipRules, primaryStage);
		buttonRules.setId("buttonRules");
		buttonRules.setPrefSize(300.0, 50.0);
		AnchorPane.setRightAnchor(buttonRules, 350.0);
		AnchorPane.setTopAnchor(buttonRules, 515.0);
		buttonRules.setOnMouseClicked(mouseEvent -> {
			showRules();});
		
		
		
		buttonExit = createButton(titleExit, tooltipExit, primaryStage);
		buttonExit.setId("buttonExit");
		buttonExit.setPrefSize(300.0, 50.0);			
		AnchorPane.setRightAnchor(buttonExit, 350.0);
		AnchorPane.setTopAnchor(buttonExit, 585.0);
		buttonExit.setOnMouseClicked(mouseEvent -> {
			primaryStage.close();});
		
		root.getChildren().addAll(buttonPlay, buttonContinue, buttonRules, buttonExit);
		
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
			String playerNumberString = playerNumber.get();
			controller.transferSetPlayerNumber(playerNumberString);
			GameWindow game = new GameWindow(controller);
			isNumber=true;
			//showScene(playerNumber, primaryStage);
		}
		return isNumber;
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
