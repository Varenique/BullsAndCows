package application;



import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


 
public class GameWindow {
	
	private Stage playStage;
	private AnchorPane child;
	private TextField numberToSend;
	private Button buttonSend;
	private Button buttonBack;
	private Separator separator;
	private ObservableList<Number> dataForComputerTable;
	private ObservableList<Number> dataForPlayerTable;
	private TableView<Number> tableViewComputerNumber;
	private TableView<Number> tableViewPlayerNumber;
	//private BullsAndCowsController controller;
	
	GameWindow(BullsAndCowsController controller) throws IOException{
		
		//this.controller = controller;
		String titleBack="";
		String tooltipBack="Back to the menu";
		String titleSend="";
		String tooltipSend="Send variant of number";
		
		playStage = new Stage();
		playStage.setTitle("BullsAndCows");
		playStage.getIcons().add(new Image("file:images/iconCow.jpg"));
		
		child = new AnchorPane();
		child.getStyleClass().addAll("child");
		
		String stringValueOfNumber = String.valueOf(controller.transferGetPlayerNumber());
		//Tables
		//First table
		dataForPlayerTable = FXCollections.observableArrayList();
		tableViewPlayerNumber = createTable(String.valueOf(stringValueOfNumber.charAt(0)), String.valueOf(stringValueOfNumber.charAt(1)), String.valueOf(stringValueOfNumber.charAt(2)), String.valueOf(stringValueOfNumber.charAt(3)), "Your number");

	    
	    //Second table
		dataForComputerTable = FXCollections.observableArrayList();  
	    tableViewComputerNumber = createTable("X","X","X","X","Computer number");
 
	    
	    numberToSend = new TextField();
	    AnchorPane.setBottomAnchor(numberToSend, 155.0);
		AnchorPane.setLeftAnchor(numberToSend, 650.0);
		numberToSend.setPrefSize(200.0, 40.0);
		
		TextFormatter<String> formetterOnlyInt = new TextFormatter<>(c -> (
				c.getText().matches("[0-9]*") == false || 
	            c.getControlNewText().length() > 4 ) ? null : c);
		numberToSend.setTextFormatter(formetterOnlyInt);
		
		
		buttonSend= createButton(titleSend, tooltipSend, playStage);
		AnchorPane.setBottomAnchor(buttonSend, 95.0);
		AnchorPane.setLeftAnchor(buttonSend, 601.0);
		buttonSend.setPrefSize(300.0, 50.0);
		buttonSend.setId("buttonSend");
		
		buttonSend.setOnMouseClicked(mouseEvent -> {
			if(controller.makeStep(numberToSend.getText())) {
			
			dataForComputerTable.add(new Number(Integer.valueOf(numberToSend.getText()), controller.getBullsAndCowsPlayer()));
			dataForPlayerTable.add(new Number(controller.getComputerVariant(), controller.getBullsAndCowsComputer()));
			controller.saveTableData(Integer.valueOf(numberToSend.getText()), controller.getBullsAndCowsPlayer(), controller.getComputerVariant(), controller.getBullsAndCowsComputer() );
			numberToSend.clear();
			showTable(controller);
			if(controller.getBullsAndCowsComputer()[0] == 4 || controller.getBullsAndCowsPlayer()[0] == 4) {
				String message="";
				if(controller.getBullsAndCowsComputer()[0] == 4 && controller.getBullsAndCowsPlayer()[0] != 4) {
					message = "You lose! Computer number was "+ String.valueOf(controller.getComputerNumber());
				}
				else if(controller.getBullsAndCowsPlayer()[0] == 4 && controller.getBullsAndCowsComputer()[0] != 4){
					message = "You win!";
				}
				else {
					message = "Draw!";
				}
				buttonSend.setDisable(true);
				try {
					showDialogGameOver(message, controller);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			}
		});
		
		buttonBack= createButton(titleBack, tooltipBack, playStage);
		AnchorPane.setBottomAnchor(buttonBack, 50.0);	
		AnchorPane.setLeftAnchor(buttonBack, 50.0);
		buttonBack.setPrefSize(300.0, 50.0);
		buttonBack.setId("buttonBack");
		buttonBack.setOnMouseClicked(mouseEvent -> {
			playStage.hide();
			MenuWindow menu = new MenuWindow(controller);
		});
		
		separator = new Separator();
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
	private void showDialogGameOver(String message, BullsAndCowsController controller) throws IOException {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("Game Over");
		dialog.setHeaderText(message);
		dialog.showAndWait();
		MenuWindow menu = new MenuWindow(controller, playStage);
		if(menu.showDialogForGame()) {
			controller.restart();
		}
	}
	
	private Button createButton(String title, String tooltip, Stage primaryStage) {
		Button result = new Button(title);
		result.setTooltip(new Tooltip(tooltip));
		result.setCursor(Cursor.HAND);
		return result;
	}
	private TableView<Number> createTable(String nameOfFirst, String nameOfSecond, String nameOfThird, String nameOfFourth, String nameOfFifth) {
		TableView<Number> tableView = new TableView<Number>(); 
	    TableColumn <Number, Integer> firstColumn = new TableColumn<Number, Integer>(nameOfFirst);
	    TableColumn <Number, Integer> secondColumn = new TableColumn<Number, Integer>(nameOfSecond);
	    TableColumn <Number, Integer> thirdColumn = new TableColumn<Number, Integer>(nameOfThird);
	    TableColumn <Number, Integer> fourthColumn = new TableColumn<Number, Integer>(nameOfFourth);
	    TableColumn <Number, String> fifthColumn = new TableColumn<Number, String>(nameOfFifth);
	    tableView.setPrefSize(540, 400);
	    firstColumn.setMinWidth(60.0);  
	    secondColumn.setMinWidth(60.0);  
	    thirdColumn.setMinWidth(60.0);  
	    fourthColumn.setMinWidth(60.0);  
	    fifthColumn.setMinWidth(250.0);   
	    firstColumn.setCellValueFactory(new PropertyValueFactory<Number , Integer>("firstDigit"));
	    secondColumn.setCellValueFactory(new PropertyValueFactory<Number , Integer>("secondDigit"));
	    thirdColumn.setCellValueFactory(new PropertyValueFactory<Number , Integer>("thirdDigit"));
	    fourthColumn.setCellValueFactory(new PropertyValueFactory<Number , Integer>("fourthDigit"));
	    fifthColumn.setCellValueFactory(new PropertyValueFactory<Number , String>("fifthProperty"));
	    tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn);
		return tableView;
	}
	public void showTable(BullsAndCowsController controller) {
		tableViewComputerNumber.setItems(dataForComputerTable);
		tableViewPlayerNumber.setItems(dataForPlayerTable);
	
	}
	public ObservableList<Number> getDataForPlayerTable() {
		return dataForPlayerTable;
	}
	public void setDataForPlayerTable(ObservableList<Number> dataForPlayerTable) {
		this.dataForPlayerTable = dataForPlayerTable;
	}
	public ObservableList<Number> getDataForComputerTable() {
		return dataForComputerTable;
	}
	public void setDataForComputerTable(ObservableList<Number> dataForComputerTable) {
		this.dataForComputerTable = dataForComputerTable;
	}
}