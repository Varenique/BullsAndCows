package application;

public class DataOfNumbers {
	private int number;
	private int variant;
	private int[] bullsAndCows;
	
	DataOfNumbers(){
		
	}
	public int getVariant() {
		return variant;
	}
	public void setVariant(int variant) {
		this.variant = variant;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int[] getBullsAndCows() {
		return bullsAndCows;
	}
	public void setBullsAndCows(int[] bullsAndCows) {
		this.bullsAndCows = bullsAndCows;
	}

}
