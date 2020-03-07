package application;

import java.net.URL;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
//import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
//import javafx.scene.*;
import java.util.function.UnaryOperator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("BullsAndCows");
			String 	titlePlay="Play", titleRules ="Rules", titleExit="Exit", 
					tooltipPlay="Click to start game", 
					tooltipRules="Do you want to see the rules of the game",
					tooltipExit ="Already leaving?";
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			AnchorPane root = new AnchorPane();
			//root.setPadding(new Insets(5.0));
			Button buttonPlay = createButton(titlePlay, tooltipPlay, primaryStage);
			Button buttonRules = createButton(titleRules, tooltipRules, primaryStage);
			Button buttonExit = createButton(titleExit, tooltipExit, primaryStage);
			buttonPlay.setPrefSize(400.0, 50.0);
			buttonRules.setPrefSize(400.0, 50.0);
			buttonExit.setPrefSize(400.0, 50.0);
			AnchorPane.setRightAnchor(buttonPlay, 300.0);
			AnchorPane.setRightAnchor(buttonRules, 300.0);
			AnchorPane.setRightAnchor(buttonExit, 300.0);
			
			AnchorPane.setTopAnchor(buttonPlay, 255.0);
			AnchorPane.setTopAnchor(buttonRules, 325.0);
			AnchorPane.setTopAnchor(buttonExit, 395.0);
			
		
			root.getChildren().add(buttonPlay);
			root.getChildren().add(buttonRules);
			root.getChildren().add(buttonExit);
			Scene scene = new Scene(root,1000,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("file:images/bc79e862161323.5a99c5534d9ea.jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private URL getResource(String name) {
		return getClass().getResource(name);
	}
	
	private Button createButton(String title, String tooltip, Stage primaryStage) {
		Button result = new Button(title);
		result.setTooltip(new Tooltip(tooltip));
		result.setCursor(Cursor.HAND);
		result.setOnMouseClicked(mouseEvent -> {
			//new AudioClip(getResource("click.wav").toString()).play();
			switch(title) {
			case("Play"):
				primaryStage.hide();
				showScene();
				break;
			case("Rules"):
				showDialog();
				break;
			
			case("Exit"):
				primaryStage.close();
				break;
			case("Back"):
				this.start(primaryStage);
				break;
			
			}
		
		});
		return result;
	}
	private void showScene() {
		Stage playStage = new Stage();
		AnchorPane child=new AnchorPane();
		TextField myNumber = new TextField();	
		TextField anotherNumber = new TextField();

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.relocate(200, 130);
		scrollPane.setContent(createTextField("1234"));
		
		child.getStyleClass().addAll("child");
		playStage.setTitle("BullsAndCows");
		String titleBack="Back", tooltipBack="Back to the menu";
		Button buttonBack= createButton(titleBack, tooltipBack, playStage);
		AnchorPane.setBottomAnchor(buttonBack, 50.0);
		
		anotherNumber.setEditable(false);
		myNumber.setPrefColumnCount(4);
		anotherNumber.setPrefColumnCount(4);
		myNumber.relocate(200, 100);
		anotherNumber.relocate(725, 100);
		TextFormatter<String> formetterForMyNumber = new TextFormatter<>(c -> (
				c.getText().matches("[0-9]*") == false || 
	            c.getControlNewText().length() > 4 ) ? null : c);
		myNumber.setTextFormatter(formetterForMyNumber);
//		TextFormatter<String> formetterForAnotherNumber = new TextFormatter<>(c -> (
//				c.getText().matches("[0-9]*") == false || 
//	            c.getControlNewText().length() > 4 ) ? null : c);
//		anotherNumber.setTextFormatter(formetterForAnotherNumber);
		anotherNumber.getStyleClass().addAll("another-number");
		child.getChildren().addAll(buttonBack, myNumber, anotherNumber, scrollPane);
//		child.getChildren().add(myNumber);
//		child.getChildren().add(anotherNumber);
//		child.getChildren().add(scrollPane);
		Scene scene = new Scene(child,1000,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		playStage.setScene(scene);
		playStage.getIcons().add(new Image("file:images/bc79e862161323.5a99c5534d9ea.jpg"));
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
}