package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;


public class GameModel {
	
	private DataOfNumbers playerNumber;
	private DataOfNumbers computerNumber;
	private boolean [] boolArray = new boolean[5040];
	private int [] variants = new int[5040];
	private FileWriter writer;
	private FileReader reader;
	private ArrayList<String> data = new ArrayList<String>();
	private ArrayList<String> replaySteps = new ArrayList<String>();
	int maxValue = 5040;
	String fileName = "last.txt";
	String fileNameBoolean = "lastBoolean.txt";
	String directoryPath = "C:\\Users\\User\\workspace\\BullsAndCows\\filesOfData";
	ObservableList<Number> playerTable;
	ObservableList<Number> computerTable;
	String lastAnswers = "";
	String allAnswers = "";
	File directory;
	GameModel(){
		playerNumber = new DataOfNumbers();
		computerNumber = new DataOfNumbers();
		computerNumber.setNumber(generateComputerNumber());	
		directory = new File(directoryPath);
		if(!directory.isDirectory()) {
			if(!directory.mkdir()) {
				System.out.println("Directory doesn't created");
			}
		}
	}
	public ArrayList<String> getReplaySteps(){
		return replaySteps;
	}
	public void saveFile(String replayFile) {
		replayFile +=".txt";
        try {
			System.out.println(allAnswers);
			FileWriter writerReplay = new FileWriter(directoryPath+"\\"+replayFile);
			writerReplay.write(String.valueOf(computerNumber.getNumber())+"\n"+String.valueOf(playerNumber.getNumber())+allAnswers);
			writerReplay.close();
			allAnswers="";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getAllFiles() {
		String allFiles = "";
		if(directory.isDirectory()) {
			for (File file : directory.listFiles()){
				allFiles+=file.getName();
				allFiles+="\n";
			}
		}
		if(allFiles.equals("")) {
			allFiles = "No saved games.";
		}
		return allFiles;
	}

	public void readReplay(String fileNameReplay) {
		fileNameReplay= directoryPath + "\\" + fileNameReplay;
		if(replaySteps != null) {
			replaySteps.clear();
		}
		try {
			reader = new FileReader(fileNameReplay);
			Scanner scan = new Scanner(reader);
			int i = 1;
			
	        lastAnswers = "";
	        while (scan.hasNextLine()) {
	        	if(i==1) {
	        		computerNumber.setNumber(Integer.valueOf(scan.nextLine()));
	        	}
	        	if(i==2) {
	        		playerNumber.setNumber(Integer.valueOf(scan.nextLine()));
	        	}
	        	if(i>2) {
	        		replaySteps.add(scan.nextLine());
	        	}
	        	i++;
	        }
			reader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readAll(String anyFileName) {	
		if(anyFileName.equals("")) {
			anyFileName = fileName;
		}
		else {
			anyFileName= directoryPath + "\\" + anyFileName;
		}
		playerTable = FXCollections.observableArrayList();
		computerTable = FXCollections.observableArrayList();;
		
		try {
			reader = new FileReader(anyFileName);
			Scanner scan = new Scanner(reader);
			int i = 1;
	        int j = 0;
	        lastAnswers = "";
	        while (scan.hasNextLine()) {
	        	if(i==1) {
	        		computerNumber.setNumber(Integer.valueOf(scan.nextLine()));
	        	}
	        	if(i==2) {
	        		playerNumber.setNumber(Integer.valueOf(scan.nextLine()));
	        	}
	        	if (i>2) {
	        		int number = Integer.valueOf(scan.nextLine());
	        		int[] array = new int[2];
	        		array[0] = Integer.valueOf(scan.nextLine());
	        		array[1] = Integer.valueOf(scan.nextLine());
	        		lastAnswers+="\n";
	        		lastAnswers+= String.valueOf(number);
	        		lastAnswers+="\n";
	        		lastAnswers+=String.valueOf(array[0]);
	        		lastAnswers+="\n";
	        		lastAnswers+=String.valueOf(array[1]);
	        		
	        		if (j == 0) {
	        			computerTable.add(new Number(number, array));
		        		j=1;
	        		}
	        		else {
	        			playerTable.add(new Number(number, array));
		        		j=0;
	        		}
	        		
	        	}
	            i++;
	        }
			reader.close();
			reader = new FileReader(fileNameBoolean);
			scan = new Scanner(reader);
			i=0;
				while (scan.hasNextLine() && i<maxValue) {
					boolArray[i] =Boolean.valueOf(scan.nextLine());
					i++;
				}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
              
	}
	public ObservableList<Number> getPlayerTable() {	
		return playerTable;
              
	}
	public ObservableList<Number> getComputerTable(){
		return computerTable;
	}
	public void writeNumber() {
		try
        {
			writer = new FileWriter(fileName);
            writer.write(String.valueOf(computerNumber.getNumber()));
            writer.close();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
	}
	public void writeTableData(int playerVariantFromTable, int[] playerBullsAndCowsFromTable, int computerVariantFromTable, int[] computerBullsAndCowsFromTable) {
		try {
			writer = new FileWriter(fileName, true);
			writer.write("\n"+String.valueOf(playerVariantFromTable)+"\n"+String.valueOf(playerBullsAndCowsFromTable[0])+"\n"+String.valueOf(playerBullsAndCowsFromTable[1]));
			writer.write("\n"+String.valueOf(computerVariantFromTable)+"\n"+String.valueOf(computerBullsAndCowsFromTable[0])+"\n"+String.valueOf(computerBullsAndCowsFromTable[1]));
			writer.close();
			data.add(String.valueOf(playerVariantFromTable));
			data.add(String.valueOf(playerBullsAndCowsFromTable[0]));
			data.add(String.valueOf(playerBullsAndCowsFromTable[1]));
			data.add(String.valueOf(computerVariantFromTable));
			data.add(String.valueOf(computerBullsAndCowsFromTable[0]));
			data.add(String.valueOf(computerBullsAndCowsFromTable[1]));
			allAnswers+="\n"+String.valueOf(playerVariantFromTable)+"\n"+String.valueOf(playerBullsAndCowsFromTable[0])+"\n"+String.valueOf(playerBullsAndCowsFromTable[1]+"\n"+String.valueOf(computerVariantFromTable)+"\n"+String.valueOf(computerBullsAndCowsFromTable[0])+"\n"+String.valueOf(computerBullsAndCowsFromTable[1]));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void restartModel() {
		computerNumber.setNumber(generateComputerNumber());
		writeNumber();	
		try {
			writer = new FileWriter(fileName, true);
			writer.write("\n"+String.valueOf(playerNumber.getNumber()));
			writer.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
        
	}
	public int[] getBullsAndCowsPlayer() {
		return playerNumber.getBullsAndCows();
	}
	public int[] getBullsAndCowsComputer() {
		return computerNumber.getBullsAndCows();
	}
	public int getComputerNumber() {
		return computerNumber.getNumber();
	}
	public int getPlayerNumber() throws IOException {
		writeNumber();
		writer = new FileWriter(fileName, true);
        writer.write("\n"+String.valueOf(playerNumber.getNumber()));
        if(!lastAnswers.equals("")) {
        	 writer.write(lastAnswers);
        }
       
        writer.close();
		return playerNumber.getNumber();
	}
	
	public void setPlayerNumber(int number) {
		playerNumber.setNumber(number);
		
	}
	public void setComputerVariant(int variant) {
		computerNumber.setVariant(variant);
	}
	public int getComputerVariant() {
		return computerNumber.getVariant();
	}
	public void setPlayerVariant(int variant) {
		playerNumber.setVariant(variant); 
	}
	public int getPlayerVariant() {
		return playerNumber.getVariant();
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
	public void stepOfGame() {
		
		computerNumber.setVariant(computerStep(Integer.valueOf(playerNumber.getNumber())));
		
		computerNumber.setBullsAndCows(Arrays.copyOf(getBullsAndCows(computerNumber.getVariant(), Integer.valueOf(playerNumber.getNumber())), 2)); 
		playerNumber.setBullsAndCows(Arrays.copyOf(getBullsAndCows(playerNumber.getVariant(), computerNumber.getNumber()), 2));
		
		
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
	
	public boolean isValid(int number) {
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
		try {
			writer = new FileWriter(fileNameBoolean);
			writer.close();
			writer = new FileWriter(fileNameBoolean, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0; i< maxValue; i++) {
			if(boolArray[i]) {
				currentBullsAndCows=Arrays.copyOf(getBullsAndCows(number, variants[i]), 2);
				if(bullsAndCows[0]!= currentBullsAndCows[0] || bullsAndCows[1]!= currentBullsAndCows[1]) {
					boolArray[i] = false;
				}
			}
			try {
				
				if(i!=0) {
					writer.write("\n");
				}
				writer.write(String.valueOf(boolArray[i]));
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		try {
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return number;
	}
}