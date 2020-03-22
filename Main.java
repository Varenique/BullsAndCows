package application;

import java.util.Arrays;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
//import javafx.stage.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;


//import javafx.scene.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
			
			primaryStage.setTitle("BullsAndCows");
			String titlePlay="Play";
			String titleRules ="Rules";
			String titleExit="Exit";
			String tooltipPlay="Click to start game";
			String tooltipRules="Do you want to see the rules of the game";
			String tooltipExit ="Already leaving?";
			
			AnchorPane root = new AnchorPane();
			
			
//	                {{"First column", "Second column", "Third column", "Fourth column", "Fifth column"},
//	                {"1", "2", "3", "4", "5",},
//	                {"6", "7", "8", "9", "10",},
//	                {"11", "12", "13", "14", "15"}};
//	        ObservableList<String[]> observableList = FXCollections.observableArrayList();
//	        observableList.addAll(Arrays.asList(dataArray));
//	        observableList.remove(0);
//	        TableView<String[]> tableView = new TableView<>();
//	        for (int i = 0; i < dataArray[0].length; i++) {
//	            TableColumn tableColumn = new TableColumn(dataArray[0][i]);
//	            int columnNumber = i;
//	            tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() { {
//	                @Override
//	                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
//	                    return new SimpleStringProperty((p.getValue()[columnNumber]));
//	                }
//	            });
//	            tableView.getColumns().add(tableColumn);
//	        }
//	        tableView.setItems(observableList);
//	        rootStackPane.getChildren().add(tableView);
//	        primaryStage.setScene(new Scene(rootStackPane, 500, 500));
//	        primaryStage.show();
			
			Button buttonPlay = createButton(titlePlay, tooltipPlay, primaryStage);
			buttonPlay.setPrefSize(400.0, 50.0);
			AnchorPane.setRightAnchor(buttonPlay, 300.0);
			AnchorPane.setTopAnchor(buttonPlay, 255.0);
			buttonPlay.setOnMouseClicked(mouseEvent -> {
				//primaryStage.hide();
				showDialogForGame(primaryStage);
				//showScene();});
			});
			
			Button buttonRules = createButton(titleRules, tooltipRules, primaryStage);
			buttonRules.setPrefSize(400.0, 50.0);
			AnchorPane.setRightAnchor(buttonRules, 300.0);
			AnchorPane.setTopAnchor(buttonRules, 325.0);
			buttonRules.setOnMouseClicked(mouseEvent -> {
				showDialog();});
			
			Button buttonExit = createButton(titleExit, tooltipExit, primaryStage);			
			buttonExit.setPrefSize(400.0, 50.0);			
			AnchorPane.setRightAnchor(buttonExit, 300.0);
			AnchorPane.setTopAnchor(buttonExit, 395.0);
			buttonExit.setOnMouseClicked(mouseEvent -> {
				primaryStage.close();});
			
			root.getChildren().addAll(buttonPlay, buttonRules, buttonExit);
			
			Scene scene = new Scene(root,1000,700);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("file:images/bc79e862161323.5a99c5534d9ea.jpg"));
			
			ObservableList<String> stylesheets = scene.getStylesheets();
			String externalForm = getClass().getResource("application.css").toExternalForm();
			stylesheets.add(externalForm);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	private Button createButton(String title, String tooltip, Stage primaryStage) {
		Button result = new Button(title);
		result.setTooltip(new Tooltip(tooltip));
		result.setCursor(Cursor.HAND);
		result.setOnMouseClicked(mouseEvent -> {
			switch(title) {
	
			case("Back"):
				this.start(primaryStage);
				break;
			}
		
		});
		return result;
	}
	private void showScene(Optional<String> playerOptNumber) {
		String titleBack="Back";
		String tooltipBack="Back to the menu";
		String playerNumber = playerOptNumber.get();
		Stage playStage = new Stage();
		playStage.setTitle("BullsAndCows");
		playStage.getIcons().add(new Image("file:images/bc79e862161323.5a99c5534d9ea.jpg"));
		
		AnchorPane child = new AnchorPane();
		child.getStyleClass().addAll("child");
		TableView tableViewPlayerNumber = new TableView();
       
        TableColumn first = new TableColumn(String.valueOf(playerNumber.charAt(0)));
        TableColumn second = new TableColumn<>(String.valueOf(playerNumber.charAt(1)));
        TableColumn third = new TableColumn<>(String.valueOf(playerNumber.charAt(2)));
        TableColumn fourth = new TableColumn<>(String.valueOf(playerNumber.charAt(3)));
        TableColumn fifth = new TableColumn<>("Your number");
        fifth.setMinWidth(300.0);
        tableViewPlayerNumber.getColumns().addAll(first, second, third, fourth, fifth);
        
        
        ObservableList<Number> data = FXCollections.observableArrayList();
       

    

        
        
        TableView<Number> tableViewComputerNumber = new TableView<>();
        
        TableColumn <Number, Integer> firstX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> secondX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> thirdX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> fourthX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, String> fifthX = new TableColumn<Number, String>("Computer number");
        fifthX.setMinWidth(300.0);
        firstX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("X"));
        secondX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("X"));
        thirdX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("X"));
        fourthX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("X"));
        fifthX.setCellValueFactory(new PropertyValueFactory<Number , String>("X"));
        tableViewComputerNumber.getColumns().addAll(firstX, secondX, thirdX, fourthX, fifthX);
        tableViewComputerNumber.setEditable(true);
        
        
        
        data.add(new Number(1, 2, 5, 6,"Non"));
        data.add(new Number(2, 3, 8, 9,"Non"));
     
        
        tableViewComputerNumber.setItems(data);
        //firstX.setCellValueFactory(new PropertyValueFactory<>("1"));
//        final ObservableList data = FXCollections.observableArrayList(
//                "Jacob", "1");
//        tableViewComputerNumber.setItems(data);
//		TextField myNumber = new TextField();
//		myNumber.setPrefColumnCount(4);
//		myNumber.relocate(200, 100);
//		TextFormatter<String> formetterForMyNumber = new TextFormatter<>(c -> (
//				c.getText().matches("[0-9]*") == false || 
//	            c.getControlNewText().length() > 4 ) ? null : c);
//		myNumber.setTextFormatter(formetterForMyNumber);
//		
//		TextField anotherNumber = new TextField();
//		anotherNumber.setEditable(false);	
//		anotherNumber.setPrefColumnCount(4);
//		anotherNumber.relocate(725, 100);
//		anotherNumber.getStyleClass().addAll("another-number");
		
		Button buttonBack= createButton(titleBack, tooltipBack, playStage);
		AnchorPane.setBottomAnchor(buttonBack, 50.0);		
		AnchorPane.setRightAnchor(tableViewPlayerNumber, 70.0);
		AnchorPane.setTopAnchor(tableViewPlayerNumber, 100.0);
		AnchorPane.setLeftAnchor(tableViewComputerNumber, 70.0);
		AnchorPane.setTopAnchor(tableViewComputerNumber, 100.0);
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefHeight(400);
		separator.relocate(500, 100);
//		TextFormatter<String> formetterForAnotherNumber = new TextFormatter<>(c -> (
//				c.getText().matches("[0-9]*") == false || 
//	            c.getControlNewText().length() > 4 ) ? null : c);
//		anotherNumber.setTextFormatter(formetterForAnotherNumber);
		
		child.getChildren().addAll(buttonBack, tableViewPlayerNumber, tableViewComputerNumber);

		Scene scene = new Scene(child,1500,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		playStage.setScene(scene);
		playStage.show();
		
	}
	
	private TextField createTextField(String value) {
		TextField number = new TextField();
		number.setPromptText(value);
		return number;
		
	}
	private void showDialog() {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Rules of BullsAndCows");
		dialog.setHeaderText("Rules:");
		dialog.showAndWait();
	}
	private void showDialogForGame(Stage primaryStage) {
		TextInputDialog dialog = new TextInputDialog("1234");
		dialog.setTitle("Cows and Bulls");
		dialog.setHeaderText("Some text about cows");
		dialog.setContentText("Please enter your number:");

		// Traditional way to get the response value.
		Optional<String> playerNumber = dialog.showAndWait();
		if (playerNumber.isPresent()){
			primaryStage.hide();
			showScene(playerNumber);
		}

		// The Java 8 way to get the response value (with lambda expression).
		
	}
}
