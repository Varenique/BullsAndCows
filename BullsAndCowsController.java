package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;

public class BullsAndCowsController {
	private GameModel model;
	BullsAndCowsController(GameModel model){
		this.model= model;
	}
	public void transferSetPlayerNumber(String playerNumberString){	
		int playerNumber = Integer.valueOf(playerNumberString);
		model.setPlayerNumber(playerNumber);
	}
	public int transferGetPlayerNumber() throws IOException{
		return model.getPlayerNumber();
		
	}
	public int[] getBullsAndCowsPlayer() {
		return model.getBullsAndCowsPlayer();
	}
	public int[] getBullsAndCowsComputer() {
		return model.getBullsAndCowsComputer();
	}
	public int getComputerVariant() {
		return model.getComputerVariant();
	}
	public int getComputerNumber() {
		return model.getComputerNumber();
	}
	public Boolean makeStep(String numberToSend){
		model.setPlayerVariant(Integer.valueOf(numberToSend));
		if(model.isValid(Integer.valueOf(numberToSend))) {
			model.stepOfGame();
		}
		return model.isValid(Integer.valueOf(numberToSend));
	}
	public void restart() {
		model.restartModel();
		
		
	}
	public void saveTableData(int playerVariant, int[] playerBullsAndCows, int computerVariant, int[] computerBullsAndCows) {
		model.writeTableData(playerVariant, playerBullsAndCows, computerVariant, computerBullsAndCows);
	}
	
	public void startLastGame(String fileName) {
		model.readAll(fileName);		
	}
	public void readReplay(String fileName) {
		model.readReplay(fileName);		
	}
	public void saveFile(String nameOfFile) {
		model.saveFile(nameOfFile);		
	}
	public ArrayList<String> getReplaySteps() {
		return model.getReplaySteps();		
	}
	public void skipStep(int number) {
		model.setPlayerVariant(number);
		model.stepOfGame();
		
	}
	public String getAllFiles() {
		return model.getAllFiles();
	}
	public ObservableList<Number> getComputerTable() {
		return model.getComputerTable();		
	}
	public ObservableList<Number> getPlayerTable() {
		return model.getPlayerTable();
			
	}
}
