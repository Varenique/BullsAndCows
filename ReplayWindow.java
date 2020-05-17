package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

public class ReplayWindow extends GameWindow{
	private Button buttonNext;
	private String fileNameReplay;
	int number;
	int i=0;
	int[] bullsAndCows = new int[2];
	
	ReplayWindow(BullsAndCowsController controller, String fileNameReplay) throws IOException {
		super(controller);
				this.fileNameReplay = fileNameReplay;
				System.out.println(fileNameReplay);
		String titleNext="";
		String tooltipNext="Send variant of number";
		dataForPlayerTable.clear();
		dataForComputerTable.clear();
		
				
			buttonNext= createButton(titleNext, tooltipNext, playStage);
			AnchorPane.setBottomAnchor(buttonNext, 95.0);
			AnchorPane.setLeftAnchor(buttonNext, 601.0);
			buttonNext.setPrefSize(300.0, 50.0);
			buttonNext.setId("buttonNext");
			
			ArrayList<String> steps = controller.getReplaySteps();
			while(i < steps.size()) {
				System.out.println(steps.get(i));
				i++;
			}
			i=0;
			
			
			buttonNext.setOnMouseClicked(mouseEvent -> {
				if(i+5 < steps.size()) {
					number= Integer.valueOf(steps.get(i));
					bullsAndCows[0]= Integer.valueOf(steps.get(i+1));
					bullsAndCows[1]= Integer.valueOf(steps.get(i+2));
					dataForComputerTable.add(new Number(number, bullsAndCows));
					
					
					number= Integer.valueOf(steps.get(i+3));
					bullsAndCows[0]= Integer.valueOf(steps.get(i+4));
					bullsAndCows[1]= Integer.valueOf(steps.get(i+5));
					
					
					dataForPlayerTable.add(new Number(number, bullsAndCows));
					showTable(controller);
					if(Integer.valueOf(steps.get(i+1))==4 || Integer.valueOf(steps.get(i+4))==4) {
						buttonNext.setDisable(true);
					}
					i=i+6;
				}
			});
			child.getChildren().removeAll(buttonSend, numberToSend);
			child.getChildren().addAll(buttonNext);
	}
	
	

}
