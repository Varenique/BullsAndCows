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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
//
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	private boolean [] boolArray = new boolean[5040];
	private int [] variants = new int[5040];
	int maxValue = 5040;
	@Override
	public void start(Stage primaryStage) {
			
			primaryStage.setTitle("BullsAndCows");
			String titlePlay="";
			String titleRules ="";
			String titleExit="";
			String tooltipPlay="Click to start game";
			String tooltipRules="Do you want to see the rules of the game";
			String tooltipExit ="Already leaving?"; 
			
			AnchorPane root = new AnchorPane();
			root.setId("mainWindow");
			 
			Button buttonPlay = createButton(titlePlay, tooltipPlay, primaryStage);
			buttonPlay.setPrefSize(300.0, 50.0);
			AnchorPane.setRightAnchor(buttonPlay, 350.0);
			AnchorPane.setTopAnchor(buttonPlay, 405.0);
			//buttonPlay.setPickOnBounds(true);
			buttonPlay.setOnMouseClicked(mouseEvent -> {
				//primaryStage.hide();
				showDialogForGame(primaryStage);
				//showScene();});
			});
			buttonPlay.setId("buttonPlay");
			
			Button buttonRules = createButton(titleRules, tooltipRules, primaryStage);
			buttonRules.setPrefSize(300.0, 50.0);
			AnchorPane.setRightAnchor(buttonRules, 350.0);
			AnchorPane.setTopAnchor(buttonRules, 475.0);
			buttonRules.setOnMouseClicked(mouseEvent -> {
				showDialog();});
			buttonRules.setId("buttonRules");
			
			
			Button buttonExit = createButton(titleExit, tooltipExit, primaryStage);			
			buttonExit.setPrefSize(300.0, 50.0);			
			AnchorPane.setRightAnchor(buttonExit, 350.0);
			AnchorPane.setTopAnchor(buttonExit, 545.0);
			buttonExit.setOnMouseClicked(mouseEvent -> {
				primaryStage.close();});
			buttonExit.setId("buttonExit");
			
			root.getChildren().addAll(buttonPlay, buttonRules, buttonExit);
			
			Scene scene = new Scene(root,1000,700);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("file:images/iconCow.png"));
			
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
		return result;
	}
	private void showScene(Optional<String> playerOptNumber, Stage primaryStage) {
		String titleBack="Back";
		String tooltipBack="Back to the menu";
		String titleSend="Send answer";
		String tooltipSend="Send variant of number";
		String playerNumber = playerOptNumber.get();
		
		
		int computerNumber = generateComputerNumber();
		Stage playStage = new Stage();
		playStage.setTitle("BullsAndCows");
		playStage.getIcons().add(new Image("file:images/iconCow.jpg"));
		
		AnchorPane child = new AnchorPane();
		child.getStyleClass().addAll("child");
		
		//Tables
		//First table
		ObservableList<Number> dataForPlayerTable = FXCollections.observableArrayList();
		TableView<Number> tableViewPlayerNumber = new TableView<Number>();
        TableColumn<Number, Integer> first = new TableColumn<Number, Integer>(String.valueOf(playerNumber.charAt(0)));
        TableColumn<Number, Integer> second = new TableColumn<Number, Integer>(String.valueOf(playerNumber.charAt(1)));
        TableColumn<Number, Integer> third = new TableColumn<Number, Integer>(String.valueOf(playerNumber.charAt(2)));
        TableColumn<Number, Integer> fourth = new TableColumn<Number, Integer>(String.valueOf(playerNumber.charAt(3)));
        TableColumn<Number, String> fifth = new TableColumn<Number, String>("Your number");
        tableViewPlayerNumber.setPrefSize(540, 400);
        first.setMinWidth(60.0);  
        second.setMinWidth(60.0);  
        third.setMinWidth(60.0);  
        fourth.setMinWidth(60.0);
        fifth.setMinWidth(300.0);
        first.setCellValueFactory(new PropertyValueFactory<Number , Integer>("firstDigit"));
        second.setCellValueFactory(new PropertyValueFactory<Number , Integer>("secondDigit"));
        third.setCellValueFactory(new PropertyValueFactory<Number , Integer>("thirdDigit"));
        fourth.setCellValueFactory(new PropertyValueFactory<Number , Integer>("fourthDigit"));
        fifth.setCellValueFactory(new PropertyValueFactory<Number , String>("fifthProperty"));
        tableViewPlayerNumber.getColumns().addAll(first, second, third, fourth, fifth);

	    
        //Second table
        ObservableList<Number> dataForComputerTable = FXCollections.observableArrayList();      
        TableView<Number> tableViewComputerNumber = new TableView<Number>(); 
        TableColumn <Number, Integer> firstX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> secondX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> thirdX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, Integer> fourthX = new TableColumn<Number, Integer>("X");
        TableColumn <Number, String> fifthX = new TableColumn<Number, String>("Computer number");
        tableViewComputerNumber.setPrefSize(540, 400);
        firstX.setMinWidth(60.0);  
        secondX.setMinWidth(60.0);  
        thirdX.setMinWidth(60.0);  
        fourthX.setMinWidth(60.0);  
        fifthX.setMinWidth(250.0);   
        firstX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("firstDigit"));
        secondX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("secondDigit"));
        thirdX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("thirdDigit"));
        fourthX.setCellValueFactory(new PropertyValueFactory<Number , Integer>("fourthDigit"));
        fifthX.setCellValueFactory(new PropertyValueFactory<Number , String>("fifthProperty"));
        tableViewComputerNumber.getColumns().addAll(firstX, secondX, thirdX, fourthX, fifthX);

        
        TextField numberToSend = new TextField();
        AnchorPane.setBottomAnchor(numberToSend, 155.0);
		AnchorPane.setLeftAnchor(numberToSend, 650.0);
		numberToSend.setPrefSize(200.0, 40.0);
		TextFormatter<String> formetterOnlyInt = new TextFormatter<>(c -> (
				c.getText().matches("[0-9]*") == false || 
	            c.getControlNewText().length() > 4 ) ? null : c);
		numberToSend.setTextFormatter(formetterOnlyInt);
		
        Button buttonSend= createButton(titleSend, tooltipSend, playStage);
		AnchorPane.setBottomAnchor(buttonSend, 110.0);
		AnchorPane.setLeftAnchor(buttonSend, 650.0);
		buttonSend.setPrefSize(200.0, 40.0);
		buttonSend.setOnMouseClicked(mouseEvent -> {
			if(isValid(Integer.valueOf(numberToSend.getText()))) {
				int computerVariant = computerStep(Integer.valueOf(playerNumber));
				int[]bullsAndCowsComputer = Arrays.copyOf(getBullsAndCows(computerVariant, Integer.valueOf(playerNumber)), 2);
				int[]bullsAndCowsPlayer = Arrays.copyOf(getBullsAndCows(Integer.valueOf(numberToSend.getText()), computerNumber), 2);
				dataForComputerTable.add(new Number(Integer.valueOf(numberToSend.getText()), bullsAndCowsPlayer));
				dataForPlayerTable.add(new Number(computerVariant, bullsAndCowsComputer));
				
				numberToSend.clear();
				tableViewComputerNumber.setItems(dataForComputerTable);
				tableViewPlayerNumber.setItems(dataForPlayerTable);
				if(bullsAndCowsComputer[0] == 4 || bullsAndCowsPlayer[0] == 4) {
					String message="";
					if(bullsAndCowsComputer[0] == 4 && bullsAndCowsPlayer[0] != 4) {
						message = "You lose! Computer number was "+ String.valueOf(computerNumber);
					}
					else if(bullsAndCowsPlayer[0] == 4 && bullsAndCowsComputer[0] != 4){
						message = "You win!";
					}
					else {
						message = "Draw!";
					}
					buttonSend.setDisable(true);
					showDialogGameOver(message, playStage);

				}
				
				
			}
			
			});
        
		
        
		
		Button buttonBack= createButton(titleBack, tooltipBack, playStage);
		AnchorPane.setBottomAnchor(buttonBack, 50.0);	
		AnchorPane.setLeftAnchor(buttonBack, 50.0);
		buttonBack.setOnMouseClicked(mouseEvent -> {
			playStage.hide();
			this.start(primaryStage);
			//showScene();});
		});
		
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefHeight(400);
		separator.relocate(750, 100);
		
		AnchorPane.setRightAnchor(tableViewPlayerNumber, 70.0);
		AnchorPane.setTopAnchor(tableViewPlayerNumber, 100.0);
		AnchorPane.setLeftAnchor(tableViewComputerNumber, 70.0);
		AnchorPane.setTopAnchor(tableViewComputerNumber, 100.0);
		
		
	    
	    CheckBox one = new CheckBox("1");
	    CheckBox two = new CheckBox("2");
	    CheckBox three = new CheckBox("3");
	    CheckBox four = new CheckBox("4");
	    CheckBox five  = new CheckBox("5");
	    CheckBox six = new CheckBox("6");
	    CheckBox seven = new CheckBox("7");
	    CheckBox eight = new CheckBox("8");
	    CheckBox nine = new CheckBox("9");
	    CheckBox zero = new CheckBox("0");
         
         
        FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 0, 10);
        flowPane .getChildren().addAll(one, two, three, four, five, six, seven, eight, nine, zero);
        flowPane.setPadding(new Insets(10));
        AnchorPane.setBottomAnchor(flowPane, 50.0);
        AnchorPane.setLeftAnchor(flowPane, 580.0);
	    
        child.getChildren().addAll(buttonBack, tableViewPlayerNumber, tableViewComputerNumber, buttonSend, flowPane, separator, numberToSend);
		Scene scene = new Scene(child,1500,700);
		child.setId("gameWindow");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		playStage.setScene(scene);
		playStage.show();
		playStage.getIcons().add(new Image("file:images/iconCow.png"));
       
		
	}

	private void showDialog() {
		Alert dialog = new Alert(AlertType.INFORMATION);
		//dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Rules of BullsAndCows");
		dialog.setHeaderText("Rules:");
		dialog.showAndWait();
		
	}
	private void showDialogGameOver(String message, Stage stage) {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Game Over");
		dialog.setHeaderText(message);
		dialog.showAndWait();
		showDialogForGame(stage);
	}
	private void showDialogForGame(Stage primaryStage) {
		TextInputDialog dialog = new TextInputDialog("1234");
		dialog.setTitle("Cows and Bulls");
		dialog.setHeaderText("Some text about cows");
		dialog.setContentText("Please enter your number:");
		Optional<String> playerNumber = dialog.showAndWait();
		if (playerNumber.isPresent()){
			primaryStage.hide();
			showScene(playerNumber, primaryStage);
		}	
	}
	private boolean isValid(int number) {
		int fourthDigit = number % 10;
		number /= 10;
		int thirdDigit = number % 10;
		number /= 10;
		int secondDigit = number % 10;
		number /= 10;
		int firstDigit = number % 10;
		return (firstDigit != secondDigit)&&(firstDigit!= thirdDigit)&&(firstDigit != fourthDigit)&&
				(secondDigit!= thirdDigit)&&(secondDigit != fourthDigit)&&(thirdDigit!=fourthDigit);
	}
	private int generateComputerNumber() {
		int thousands;
		int hundreds;
		int tens;
		int ones;
		fullGuessingBase();
		thousands = (int) ( Math.random() * 9 );
		hundreds = (int) ( Math.random() * 9 );
		while(hundreds == thousands) {
			hundreds = (int) ( Math.random() * 9 );
		}
		tens = (int) ( Math.random() * 9 );
		while(tens == thousands || tens == hundreds) {
			tens = (int) ( Math.random() * 9 );
		}
		ones = (int) ( Math.random() * 9 );
		while(ones == tens || ones == thousands || ones == hundreds) {
			ones = (int) ( Math.random() * 9 );
		}
		return thousands*1000+ hundreds*100+ tens*10+ ones;
	}
	private void fullGuessingBase() {
		int i=0;
		for(int thousands=0; thousands<10 && i<maxValue; thousands++) {
			for(int hundreds=0; hundreds<10 && i<maxValue; hundreds++) {
				for(int tens=0; tens<10 && i<maxValue; tens++) {
					for(int ones=0; ones<10 && i<maxValue; ones++) {
						if(isValid(thousands*1000+ hundreds*100+ tens*10+ ones)) {
							boolArray[i]=true;
							variants[i]=(thousands*1000+ hundreds*100+ tens*10+ ones);
							i++;
						}
					}
				}
			}
		}
	}
	private int[] getBullsAndCows(int number, int unitComputerNumber) {
		int [] numberToCompare = Arrays.copyOf(getDigits(number), 4);
		int [] computerNumber = Arrays.copyOf(getDigits(unitComputerNumber), 4);
		int [] bullsAndCows = new int[2];
		bullsAndCows[0]=0;
		bullsAndCows[1]=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(computerNumber[i]==numberToCompare[j]) {
					if(i==j) {
						bullsAndCows[0]++;
					}
					else {
						bullsAndCows[1]++;
					}
				}
			}
		}
		return bullsAndCows;
	}
	private int[] getDigits(int number) {
		int[] sliceNumber = new int [4];
		sliceNumber[3] = number % 10;
		number /= 10;
		sliceNumber[2] = number % 10;
		number /= 10;
		sliceNumber[1] = number % 10;
		number /= 10;
		sliceNumber[0] = number % 10;
		return sliceNumber;
	}
	private int computerStep(int unitPlayerNumber) {
		int number=0;
		for(int i=0; i< maxValue; i++) {
			if(boolArray[i]) {
				number = variants[i];
				break;
			}
		}
		int [] bullsAndCows = Arrays.copyOf(getBullsAndCows(number, unitPlayerNumber), 2);
		int [] currentBullsAndCows;
		for(int i=0; i< maxValue; i++) {
			if(boolArray[i]) {
				currentBullsAndCows=Arrays.copyOf(getBullsAndCows(number, variants[i]), 2);
				if(bullsAndCows[0]!= currentBullsAndCows[0] || bullsAndCows[1]!= currentBullsAndCows[1]) {
					boolArray[i] = false;
				}
			}
		}
		return number;
	}
	
}
